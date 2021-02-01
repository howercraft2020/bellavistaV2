package cl.clsoft.bave;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.bave.ConexionSQLiteHelper;
import com.example.bave.Model.Modelo;
import com.example.bave.entidades.MtlTransactionLotsIfaceDto;
import com.example.bave.utilidades.Utilidades;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Entrega extends AppCompatActivity {

    TextView nombreArchivopath, subinventario, localizador, numeroParte;
    EditText cantidad, numeroOc, numeroRecepcion, codigoSigle,udm;
    Button btnBuscarOc, btnControlLote;
    Spinner comboCategoriaLote;
    String IdcExtract, numeroRecepcionExtract;
    int errorCarga = 0;
    int validaFechaExpiracion = 0;

    //array de Archivos
    ArrayList<String> arrayList;
    ArrayList<String> arrayListParte;
    ArrayList<String> arraylistSubinventario;
    ArrayList<String> arraylistLocalizador;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrega);


        nombreArchivopath = (TextView) findViewById(R.id.txtNombreArchivoE);
        subinventario = (TextView) findViewById(R.id.txtSubinventario);
        localizador = (TextView) findViewById(R.id.txtLocalizador);
        btnBuscarOc = (Button) findViewById(R.id.btnBuscarE);
        btnControlLote = (Button) findViewById(R.id.btnControlLote);
        cantidad = (EditText) findViewById(R.id.txtCantidadE);
        numeroOc = (EditText) findViewById(R.id.txtNumeroOcE);
        numeroRecepcion = (EditText) findViewById(R.id.txtNumeroRecepcionE);
        numeroParte = (TextView) findViewById(R.id.txtNumeroParteE);
        codigoSigle = (EditText) findViewById(R.id.txtCodigoSigleE);
        udm = (EditText) findViewById(R.id.txtUDME);




        btnBuscarOc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConsultarOc();
                if (errorCarga == 0){
                    llenarSpinnerNumeroParte();
                }

            }
        });

        btnControlLote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder mbuilder = new AlertDialog.Builder(Entrega.this);
                View mview = getLayoutInflater().inflate(R.layout.dialog_form_lote, null);
                Button ingresar = (Button) mview.findViewById(R.id.btnIngresarLote);
                Button cancelar = (Button) mview.findViewById(R.id.btnCancelarDialog);
                EditText lote = (EditText) mview.findViewById(R.id.lote);
                EditText fechaExpiracion = (EditText) mview.findViewById(R.id.fechaExpiracion);


                validaFechaExpiracion = validaFechaExpiración();

                if (validaFechaExpiracion == 4 || validaFechaExpiracion == 2){
                    fechaExpiracion.setEnabled(true);
                }
                else{
                    fechaExpiracion.setEnabled(false);
                }

                if(cantidad.getText().toString().equals("")){
                    cantidad.setError("Ingrese Cantidad");

                }else if(codigoSigle.getText().toString().equals("")){
                    codigoSigle.setError("Ingrese Codigo Sigle");
                }
                else {

                    mbuilder.setView(mview);
                    AlertDialog dialog = mbuilder.create();
                    dialog.show();


                    ingresar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(lote.getText().toString().equals("")){
                                lote.setError("Ingrese Numero de Lote");
                            }else {

                                //if (fechaValidDate(fechaExpiracion.getText().toString())) {

                                    String numero = "";
                                    String fecha = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                                    String numeroShipmentHeaderIdEx = "";
                                    String mumeroRecepcionEx = "";
                                    String codigoSigleEx = "";

                                    MtlTransactionLotsIfaceDto mtl = new MtlTransactionLotsIfaceDto();
                                    ConexionSQLiteHelper conn = new ConexionSQLiteHelper(Entrega.this, "bd", null, 1);
                                    SQLiteDatabase db = conn.getReadableDatabase();
                                    int resInsert = 0;

                                    numeroShipmentHeaderIdEx = numeroOc.getText().toString();
                                    mumeroRecepcionEx = numeroRecepcion.getText().toString();
                                    codigoSigleEx = codigoSigle.getText().toString();
                                        if (!validalote(numeroShipmentHeaderIdEx, mumeroRecepcionEx,codigoSigleEx)){
                                        Modelo obj = new Modelo();


                                        Cursor b = db.rawQuery("SELECT rhi.SHIPMENT_HEADER_ID, rhi.RECEIPT_NUM, rhi.CREATED_BY, rt.PO_HEADER_ID, rt.PO_LINE_ID, msi.INVENTORY_ITEM_ID FROM RCV_SHIPMENT_HEADERS rhi, RCV_TRANSACTIONS rt, MTL_SYSTEM_ITEMS msi WHERE rhi.SHIPMENT_HEADER_ID = rt.SHIPMENT_HEADER_ID AND rt.ITEM_ID = msi.INVENTORY_ITEM_ID AND rhi.SHIPMENT_HEADER_ID='" + numeroShipmentHeaderIdEx + "' AND rhi.RECEIPT_NUM='" + mumeroRecepcionEx + "' AND msi.SEGMENT1='" + codigoSigleEx + "'", null);
                                        if (b.moveToFirst()) {
                                            do {

                                                mtl.setMtlTransactionLotsIface("4444");
                                                mtl.setLastUpdateDate(fecha);
                                                mtl.setLastUpdateBy(b.getString(2));
                                                mtl.setCreationDate(fecha);
                                                mtl.setCreatedBy(b.getString(2));
                                                mtl.setPoHeaderId(b.getString(3));
                                                mtl.setPoLineId(b.getString(4));
                                                mtl.setInventoryItemId(b.getString(5));
                                                mtl.setLastUpdateLogin("-1");
                                                mtl.setLotNumber(lote.getText().toString());
                                                mtl.setTransactionQuantity(cantidad.getText().toString());
                                                mtl.setPrimaryQuantity(cantidad.getText().toString());
                                                mtl.setSerialTransactionTempId("");
                                                mtl.setProductCode("RCV");
                                                mtl.setProductTransactionId(numeroShipmentHeaderIdEx + mumeroRecepcionEx);
                                                mtl.setSupplierLotNumber("Prueba");
                                                mtl.setLotExpirationDate(fechaExpiracion.getText().toString());
                                                mtl.setAttributeCategory("");
                                                mtl.setAttribute1("");
                                                mtl.setAttribute2("");
                                                mtl.setAttribute3("");

                                                resInsert = obj.insertLote(Entrega.this, mtl);

                                                if (resInsert == 1) {
                                                    Toast.makeText(Entrega.this, "Lote : " + lote.getText().toString() + " ingresado correctamente", Toast.LENGTH_LONG).show();
                                                } else {
                                                    Toast.makeText(Entrega.this, "Error : No se ha podido ingresar el registro", Toast.LENGTH_LONG).show();
                                                }

                                            } while (b.moveToNext());
                                        }

                                        b.close();
                                        db.close();
                                    }
                                    else{
                                        Toast.makeText(Entrega.this, "Este artículo ya tiene un lote ingresado", Toast.LENGTH_SHORT).show();
                                    }
                                //}
                                //else
                                //{
                                //    fechaExpiracion.setError("Formato fecha incorrecto (DD/MM/YYYY)");
                               // }
                            }
                        }
                    });

                    cancelar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            dialog.dismiss();
                        }
                    });







                    //dialog = new Dialog(Entrega.this);
                    //Set custom dialog
                    //dialog.setContentView(R.layout.dialog_form_lote);
                    //Set custom heigth and width
                    //dialog.getWindow().setLayout(650, 900);
                    //Set Transparent backgrounnd
                    //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    //Show dialog
                    //dialog.show();
                }


            }
        });


        llenarSpinner();
        llenarSpinnerSubinventario();

    }



    public void ConsultarOc(){

        try{

            String nombreArchivo = nombreArchivopath.getText().toString();

            if (existeArchivo(nombreArchivo)) {

                    ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd", null, 1);

                    File tarjetaSD = Environment.getExternalStorageDirectory();
                    File Dir = new File(tarjetaSD.getAbsolutePath(), "outbound");
                    File rutaArchivo = new File(Dir, nombreArchivo);

                    FileInputStream fis = new FileInputStream(rutaArchivo);
                    InputStreamReader abrirArchivo = new InputStreamReader(fis);

                    BufferedReader leerArchivo = new BufferedReader(abrirArchivo);
                    String linea = leerArchivo.readLine();

                    SQLiteDatabase db = conn.getWritableDatabase();
                    ContentValues values = new ContentValues();

                    String[] extraccionCab = linea.split("\\|",-1);

                    if (extraccionCab[0].equals("SHP")) {
                        values.put(Utilidades.CAMPO_SHIPMENT_HEADER_ID_RSH, extraccionCab[1]);
                        IdcExtract = extraccionCab[1];
                        values.put(Utilidades.CAMPO_LAST_UPDATE_DATE_RSH, extraccionCab[2]);
                        values.put(Utilidades.CAMPO_LAST_UPDATED_BY_RSH, extraccionCab[3]);
                        values.put(Utilidades.CAMPO_CREATION_DATE_RSH, extraccionCab[4]);
                        values.put(Utilidades.CAMPO_CREATED_BY_RSH, extraccionCab[5]);
                        values.put(Utilidades.CAMPO_USER_ID_RSH, extraccionCab[6]);
                        values.put(Utilidades.CAMPO_VENDOR_ID_RSH, extraccionCab[7]);
                        values.put(Utilidades.CAMPO_VENDOR_SITE_ID_RSH, extraccionCab[8]);
                        values.put(Utilidades.CAMPO_ORGANIZATION_ID_RSH, extraccionCab[9]);
                        values.put(Utilidades.CAMPO_SHIPMENT_NUM_RSH, extraccionCab[10]);
                        values.put(Utilidades.CAMPO_RECEIPT_NUM_RSH, extraccionCab[11]);
                        numeroRecepcionExtract = extraccionCab[11];
                        values.put(Utilidades.CAMPO_EMPLOYEE_ID_RSH, extraccionCab[12]);
                        values.put(Utilidades.CAMPO_SHIP_TO_ORG_ID_RSH, extraccionCab[13]);

                        Long idRespuesta = db.insert(Utilidades.TABLA_RCV_SHIPMENT_HEADERS, Utilidades.CAMPO_SHIPMENT_HEADER_ID_RSH, values);

                        if (idRespuesta < 0) {
                            errorCarga = 1;
                        }


                    }

                    while (linea != null) {
                        linea = leerArchivo.readLine();
                        if (linea != null) {
                            String[] extraccion = linea.split("\\|",-1);

                            for (int a = 0; a < 1; a++) {

                                if (extraccion[0].equals("TRX")) {

                                    values.clear();

                                    values.put(Utilidades.CAMPO_TRANSACTION_ID_RT, extraccion[1]);
                                    values.put(Utilidades.CAMPO_LAST_UPDATE_DATE_RT, extraccion[2]);
                                    values.put(Utilidades.CAMPO_LAST_UPDATED_BY_RT, extraccion[3]);
                                    values.put(Utilidades.CAMPO_CREATION_DATE_RT, extraccion[4]);
                                    values.put(Utilidades.CAMPO_CREATED_BY_RT, extraccion[5]);
                                    values.put(Utilidades.CAMPO_TRANSACTION_TYPE_RT, extraccion[6]);
                                    values.put(Utilidades.CAMPO_TRANSACTION_DATE_RT, extraccion[7]);
                                    values.put(Utilidades.CAMPO_QUANTITY_RT, extraccion[8]);
                                    values.put(Utilidades.CAMPO_UNIT_OF_MEASURE_RT, extraccion[9]);
                                    values.put(Utilidades.CAMPO_SHIPMENT_HEADER_ID_RT, extraccion[10]);
                                    values.put(Utilidades.CAMPO_SHIPMENT_LINE_ID_RT, extraccion[11]);
                                    values.put(Utilidades.CAMPO_SOURCE_DOCUMENT_CODE_RT, extraccion[12]);
                                    values.put(Utilidades.CAMPO_DESTINATION_TYPE_CODE_RT, extraccion[13]);
                                    values.put(Utilidades.CAMPO_PRIMARY_UNIT_OF_MEASURE_RT, extraccion[14]);
                                    values.put(Utilidades.CAMPO_UOM_CODE_RT, extraccion[15]);
                                    values.put(Utilidades.CAMPO_EMPLOYEE_ID_RT, extraccion[16]);
                                    values.put(Utilidades.CAMPO_PO_HEADER_ID_RT, extraccion[17]);
                                    values.put(Utilidades.CAMPO_PO_LINE_ID_RT, extraccion[18]);
                                    values.put(Utilidades.CAMPO_PO_LINE_LOCATION_ID_RT, extraccion[19]);
                                    values.put(Utilidades.CAMPO_PO_DISTRIBUTION_ID_RT, extraccion[20]);
                                    values.put(Utilidades.CAMPO_PO_UNIT_PRICE_RT, extraccion[21]);
                                    values.put(Utilidades.CAMPO_CURRENCY_CONVERSION_TYPE_RT, extraccion[22]);
                                    values.put(Utilidades.CAMPO_CURRENCY_CONVERSION_RATE_RT, extraccion[23]);
                                    values.put(Utilidades.CAMPO_CURRENCY_CONVERSION_DATE_RT, extraccion[24]);
                                    values.put(Utilidades.CAMPO_DELIVER_TO_LOCATION_ID_RT, extraccion[25]);
                                    values.put(Utilidades.CAMPO_VENDOR_ID_RT, extraccion[26]);
                                    values.put(Utilidades.CAMPO_VENDOR_SITE_ID_RT, extraccion[27]);
                                    values.put(Utilidades.CAMPO_ORGANIZATION_ID_RT, extraccion[28]);
                                    values.put(Utilidades.CAMPO_LOCATION_ID_RT, extraccion[29]);
                                    values.put(Utilidades.CAMPO_INSPECTION_STATUS_CODE_RT, extraccion[30]);
                                    values.put(Utilidades.CAMPO_DESTINATION_CONTEXT_RT, extraccion[31]);
                                    values.put(Utilidades.CAMPO_INTERFACE_TRANSACTION_ID_RT, extraccion[32]);
                                    values.put(Utilidades.CAMPO_ITEM_ID_RT, extraccion[33]);


                                    Long idRespuesta = db.insert(Utilidades.TABLA_RCV_TRANSACTIONS, Utilidades.CAMPO_TRANSACTION_ID_RT, values);

                                    if (idRespuesta < 0) {
                                        errorCarga = 1;
                                    }

                                }



                                if (extraccion[0].equals("ITM")) {

                                    values.clear();

                                    values.put(Utilidades.CAMPO_INVENTORY_ITEM_ID_MSI, extraccion[2]);
                                    values.put(Utilidades.CAMPO_DESCRIPTION_MSI, extraccion[3]);
                                    values.put(Utilidades.CAMPO_LONG_DESCRIPTION_MSI, extraccion[4]);
                                    values.put(Utilidades.CAMPO_SEGMENT1_MSI, extraccion[5]);
                                    values.put(Utilidades.CAMPO_PRIMARY_UOM_CODE_MSI, extraccion[6]);
                                    values.put(Utilidades.CAMPO_LOT_CONTROL_CODE_MSI, extraccion[7]);
                                    values.put(Utilidades.CAMPO_SHELF_LIFE_CODE_MSI, extraccion[8]);
                                    values.put(Utilidades.CAMPO_SERIAL_NUMBER_CONTROL_CODE_MSI, extraccion[9]);

                                    Long idRespuesta = db.insert(Utilidades.TABLA_MTL_SYSTEM_ITEMS, Utilidades.CAMPO_INVENTORY_ITEM_ID_MSI, values);

                                    if (idRespuesta < 0) {
                                        errorCarga = 1;
                                    }

                                }
                            }
                            if (errorCarga == 0) {
                                Toast.makeText(this, "Datos Oc Cargados correctamente", Toast.LENGTH_LONG).show();
                                //comentario.setEnabled(true);
                                //numeroParte.setEnabled(true);
                                //codigoSigle.setEnabled(true);
                                //cantidad.setEnabled(true);
                                //udm.setEnabled(true);
                                //btnConfirmarLinea.setEnabled(true);
                                numeroRecepcion.setText(numeroRecepcionExtract);
                                numeroOc.setText(IdcExtract);

                            }
                        }
                    }
                    db.close();
                    leerArchivo.close();
                    abrirArchivo.close();

            }
            else{
                Toast.makeText(getApplicationContext(),"Archivo No Existe", Toast.LENGTH_LONG).show();
            }
        }catch (IOException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public boolean existeArchivo(String filename){
        File tarjetaSD = Environment.getExternalStorageDirectory();
        File file = new File(tarjetaSD.getAbsolutePath() + "/outbound/" +filename);

        if(file.exists()){
            return true;
        }else{
            return false;
        }
    }

    public void llenarSpinner(){
        //Llenar Array
        arrayList = new ArrayList<>();
        File tarjetaSD = Environment.getExternalStorageDirectory();
        File Dir = new File(tarjetaSD.getAbsolutePath(), "outbound");
        File[] files = Dir.listFiles();

        for (int i = 0; i < files.length; i++) {
            if (files[i].getName().startsWith("OC")) {
                arrayList.add(files[i].getName());
            }
        }

        nombreArchivopath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(Entrega.this);
                //Set custom dialog
                dialog.setContentView(R.layout.dialog_searchable_spinner);
                //Set custom heigth and width
                dialog.getWindow().setLayout(650, 800);
                //Set Transparent backgrounnd
                dialog .getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //Show dialog
                dialog.show();

                //Initialize and assing variable
                EditText editText = dialog.findViewById(R.id.edit_text);
                ListView listView = dialog.findViewById(R.id.list_view);

                //Initialize Array adapter
                ArrayAdapter<String> adapter = new ArrayAdapter<>(Entrega.this
                        ,android.R.layout.simple_list_item_1,arrayList);

                //Set Adapter
                listView.setAdapter(adapter);

                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        //Filter array List
                        adapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        nombreArchivopath.setText(adapter.getItem(position));

                        //dismiss Dialog
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    public void llenarSpinnerSubinventario(){
        //Llenar Array
        arraylistSubinventario = new ArrayList<>();

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd",null,1);
        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor a = db.rawQuery("SELECT COD_SUBINVENTARIO FROM SUBINVENTARIO", null);

        if (a.moveToFirst()) {
            do {
                arraylistSubinventario.add(a.getString(0));
            } while (a.moveToNext());
        }


        subinventario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(Entrega.this);
                //Set custom dialog
                dialog.setContentView(R.layout.dialog_searchable_spinner_np);
                //Set custom heigth and width
                dialog.getWindow().setLayout(650, 800);
                //Set Transparent backgrounnd
                dialog .getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //Show dialog
                dialog.show();

                //Initialize and assing variable
                EditText editText = dialog.findViewById(R.id.edit_text_np);
                ListView listView = dialog.findViewById(R.id.list_view_np);

                //Initialize Array adapter
                ArrayAdapter<String> adapter = new ArrayAdapter<>(Entrega.this
                        ,android.R.layout.simple_list_item_1,arraylistSubinventario);

                //Set Adapter
                listView.setAdapter(adapter);

                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        //Filter array List
                        adapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        subinventario.setText(adapter.getItem(position));
                        localizador.setText("");
                        llenarSpinnerLocalizador();
                        dialog.dismiss();
                    }
                });

            }
        });
        a.close();
    }

    public void llenarSpinnerLocalizador(){
        //Llenar Array
        arraylistLocalizador = new ArrayList<>();
        String subinventarioEx = "";

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd",null,1);
        SQLiteDatabase db = conn.getReadableDatabase();

        subinventarioEx = subinventario.getText().toString();

        Cursor a = db.rawQuery("SELECT COD_LOCALIZADOR FROM LOCALIZADOR WHERE COD_SUBINVENTARIO = '"+subinventarioEx+"'", null);

        if (a.moveToFirst()) {
            do {
                arraylistLocalizador.add(a.getString(0));
            } while (a.moveToNext());
        }


        localizador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(Entrega.this);
                //Set custom dialog
                dialog.setContentView(R.layout.dialog_searchable_spinner_np);
                //Set custom heigth and width
                dialog.getWindow().setLayout(650, 800);
                //Set Transparent backgrounnd
                dialog .getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //Show dialog
                dialog.show();

                //Initialize and assing variable
                EditText editText = dialog.findViewById(R.id.edit_text_np);
                ListView listView = dialog.findViewById(R.id.list_view_np);

                //Initialize Array adapter
                ArrayAdapter<String> adapter = new ArrayAdapter<>(Entrega.this
                        ,android.R.layout.simple_list_item_1,arraylistLocalizador);

                //Set Adapter
                listView.setAdapter(adapter);

                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        adapter.getFilter().filter(s);

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        localizador.setText(adapter.getItem(position));
                        dialog.dismiss();
                    }
                });
            }
        });
        a.close();
    }

    public void llenarSpinnerNumeroParte(){
        //Llenar Array
        arrayListParte = new ArrayList<>();
        String numeroOcEx = "";
        String mumeroRecepcionEx = "";
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd",null,1);
        SQLiteDatabase db = conn.getReadableDatabase();

        numeroOcEx = numeroOc.getText().toString();
        mumeroRecepcionEx = numeroRecepcion.getText().toString();

        Cursor a = db.rawQuery("SELECT msi.DESCRIPTION||' '||msi.LONG_DESCRIPTION FROM MTL_SYSTEM_ITEMS msi, RCV_TRANSACTIONS rcv, rcv_shipment_headers rsh  WHERE msi.inventory_item_id = rcv.item_id AND rcv.shipment_header_id = rsh.shipment_header_id AND rsh.SHIPMENT_HEADER_ID='"+numeroOcEx+"' AND rsh.RECEIPT_NUM='"+mumeroRecepcionEx+"'", null);

        if (a.moveToFirst()) {
            do {
                arrayListParte.add(a.getString(0));
            } while (a.moveToNext());
        }


        numeroParte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(Entrega.this);
                //Set custom dialog
                dialog.setContentView(R.layout.dialog_searchable_spinner_np);
                //Set custom heigth and width
                dialog.getWindow().setLayout(650, 800);
                //Set Transparent backgrounnd
                dialog .getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //Show dialog
                dialog.show();

                //Initialize and assing variable
                EditText editText = dialog.findViewById(R.id.edit_text_np);
                ListView listView = dialog.findViewById(R.id.list_view_np);

                //Initialize Array adapter
                ArrayAdapter<String> adapter = new ArrayAdapter<>(Entrega.this
                        ,android.R.layout.simple_list_item_1,arrayListParte);

                //Set Adapter
                listView.setAdapter(adapter);

                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        //Filter array List
                        adapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String seleccion = "";
                        String numeroOcEx = "";
                        String mumeroRecepcionEx = "";
                        seleccion = adapter.getItem(position);
                        numeroParte.setText(adapter.getItem(position));

                        numeroOcEx = numeroOc.getText().toString();
                        mumeroRecepcionEx = numeroRecepcion.getText().toString();


                        Cursor b = db.rawQuery("SELECT msi.SEGMENT1, msi.PRIMARY_UOM_CODE FROM MTL_SYSTEM_ITEMS msi, RCV_TRANSACTIONS rcv, rcv_shipment_headers rsh WHERE msi.inventory_item_id = rcv.item_id AND rcv.shipment_header_id = rsh.shipment_header_id AND rsh.SHIPMENT_HEADER_ID='"+numeroOcEx+"' AND rsh.RECEIPT_NUM='"+mumeroRecepcionEx+"'", null);
                        if (b.moveToFirst()) {
                            do {

                                codigoSigle.setText(b.getString(0));
                                udm.setText(b.getString(1));
                            } while (b.moveToNext());
                        }

                        //dismiss Dialog
                        b.close();
                        dialog.dismiss();
                    }
                });
            }
        });
        a.close();
    }

    public boolean fechaValidDate(String date)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Date testDate = null;
        try
        {
            testDate = sdf.parse(date);
        }
        catch (ParseException e)
        {
            return false;
        }
        if (!sdf.format(testDate).equals(date))
        {
            return false;
        }

        return true;
    }

    public boolean validalote(String shipmentHId, String receiptNum, String articulo){

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd",null,1);
        SQLiteDatabase db = conn.getReadableDatabase();


        Cursor c = db.rawQuery("select mtl_transaction_lots_iface from mtl_transactions_lots_iface mtl, mtl_system_items msi, rcv_transactions rt, rcv_shipment_headers rsh WHERE rsh.shipment_header_id = rt.shipment_header_id AND rt.item_id = msi.inventory_item_id AND rsh.shipment_header_id = '"+shipmentHId+"' AND rsh.receipt_num ='"+receiptNum+"' AND msi.SEGMENT1='"+articulo+"'", null);

        if(c.getCount() <= 0 ) {
            c.close();
            return false;
        }

        c.close();
        db.close();

        return true;

    }

    public int validaFechaExpiración(){
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd",null,1);
        SQLiteDatabase db = conn.getReadableDatabase();
        String numeroOcEx = "";
        String mumeroRecepcionEx = "";
        String codigoSigleEx = "";
        int seleccion   = 0;


        numeroOcEx = numeroOc.getText().toString();
        mumeroRecepcionEx = numeroRecepcion.getText().toString();
        codigoSigleEx = codigoSigle.getText().toString();

        Cursor a = db.rawQuery("select msi.shelf_life_code from mtl_system_items msi, rcv_transactions rt, rcv_shipment_headers rsh WHERE rsh.shipment_header_id = rt.shipment_header_id AND rt.item_id = msi.inventory_item_id AND rsh.shipment_header_id = '"+numeroOcEx+"' AND rsh.receipt_num ='"+mumeroRecepcionEx+"' AND msi.SEGMENT1='"+codigoSigleEx+"'" , null);

        if (a.moveToFirst()) {
            do {
                seleccion = Integer.parseInt(a.getString(0));
            } while (a.moveToNext());
        }

        a.close();
        db.close();

        return seleccion;

    }




}