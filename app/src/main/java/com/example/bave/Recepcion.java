package com.example.bave;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bave.Model.Modelo;
import com.example.bave.entidades.RcvHeadersInterfaceDTO;
import com.example.bave.entidades.RcvTransactionsInterfaceDTO;
import com.example.bave.utilidades.Utilidades;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Recepcion extends AppCompatActivity {

    EditText comentario, cantidad, codigoSigle, udm, numeroRecepcion, numeroOc;
    Button btnBuscarOc, btnConfirmarLinea, btnConfirmarRecepcion;
    TextView nombreArchivopath, numeroParte;
    String cantidadRecibida, numeroOcExtract, numeroRecepcionExtract;
    int tolerancia = 0;
    int resInsertCabecera = 0;
    int resInsertLinea = 0;
    int resSelect = 0;
    int errorCarga = 0;

    //array de Archivos
    ArrayList<String> arrayList;
    ArrayList<String> arrayListParte;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recepcion);

        btnBuscarOc = (Button) findViewById(R.id.btnBuscar);
        btnConfirmarLinea = (Button) findViewById(R.id.btnConfirmarLinea);
        btnConfirmarRecepcion = (Button) findViewById(R.id.btnConfirmarRecepcion);
        comentario = (EditText) findViewById(R.id.txtComentario);
        cantidad = (EditText) findViewById(R.id.txtCantidad);
        numeroParte = (TextView) findViewById(R.id.txtNumeroParte);
        codigoSigle = (EditText) findViewById(R.id.txtCodigoSigle);
        udm = (EditText) findViewById(R.id.txtUDM);
        nombreArchivopath = (TextView) findViewById(R.id.txtNombreArchivo);
        numeroRecepcion = (EditText) findViewById(R.id.txtNumeroRecepcion);
        numeroOc = (EditText) findViewById(R.id.txtNumeroOc);

        String paso = getIntent().getStringExtra("paso");

        btnBuscarOc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConsultarOc();
                if (errorCarga == 0){
                    llenarSpinnerNumeroParte();
                    if(existenLineas()){
                        btnConfirmarRecepcion.setEnabled(true);

                    }
                }
            }
        });

        btnConfirmarLinea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cantidadRecibida = cantidad.getText().toString();

                if(cantidadRecibida.equals("")){
                    cantidad.setError("Ingrese Cantidad");
                }else {
                    tolerancia = calculaCantidad();
                    if(Integer.parseInt(cantidadRecibida) >= tolerancia){
                        Toast.makeText(Recepcion.this,"Cantidad no puede ser superior a : "+tolerancia, Toast.LENGTH_SHORT).show();
                        cantidad.setError("Cantidad no puede ser superior a : "+tolerancia);
                    }else {
                        resInsertCabecera = InsertCabecera();
                        resInsertLinea = InsertLineas(cantidad.getText().toString());
                    }
                }

            }
        });

        btnConfirmarRecepcion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                escribirArchivo(nombreArchivopath.getText().toString(), paso);
            }
        });

        codigoSigle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!codigoSigle.equals("") && codigoSigle.length() > 5){
                    resSelect = datosArticulos(codigoSigle.getText().toString());

                    if(resSelect == 1){
                        cantidad.requestFocus();
                    }else {
                        limpiaCampos();
                        codigoSigle.requestFocus();
                    }

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

     llenarSpinner();

    }

    public void ConsultarOc(){

        try{

            String nombreArchivo = nombreArchivopath.getText().toString();

            if (existeArchivo(nombreArchivo)) {

                if(!validaDatosCargados(nombreArchivo)) {


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

                    if (extraccionCab[0].equals("CAB")) {
                        values.put(Utilidades.CAMPO_CREATED_BY_PHA, extraccionCab[4]);
                        values.put(Utilidades.CAMPO_CREATION_DATE_PHA, extraccionCab[5]);
                        values.put(Utilidades.CAMPO_VENDOR_NAME_PHA, extraccionCab[6]);
                        values.put(Utilidades.CAMPO_VENDOR_ID_PHA, extraccionCab[7]);
                        values.put(Utilidades.CAMPO_VENDOR_SITE_ID_PHA, extraccionCab[8]);
                        values.put(Utilidades.CAMPO_VENDOR_SITE_CODE_PHA, extraccionCab[9]);
                        values.put(Utilidades.CAMPO_PO_HEADER_ID_PHA, extraccionCab[10]);
                        values.put(Utilidades.CAMPO_SEGMENT1_PHA, extraccionCab[11]);
                        numeroOcExtract = extraccionCab[11];
                        values.put(Utilidades.CAMPO_ORG_ID_PHA, extraccionCab[12]);
                        values.put(Utilidades.CAMPO_APPROVED_DATE_PHA, extraccionCab[13]);
                        values.put(Utilidades.CAMPO_OPERATING_UNIT_PHA, extraccionCab[14]);
                        values.put(Utilidades.CAMPO_TERMS_ID_PHA, extraccionCab[15]);
                        values.put(Utilidades.CAMPO_CURRENCY_CODE_PHA, extraccionCab[16]);
                        values.put(Utilidades.CAMPO_RATE_TYPE_PHA, extraccionCab[17]);
                        values.put(Utilidades.CAMPO_RATE_DATE_PHA, extraccionCab[18]);
                        values.put(Utilidades.CAMPO_RATE_PHA, extraccionCab[19]);
                        values.put(Utilidades.CAMPO_USER_ID_PHA, extraccionCab[20]);
                        values.put(Utilidades.CAMPO_RECEIPT_NUM_PHA, extraccionCab[21]);
                        numeroRecepcionExtract = extraccionCab[21];

                        Long idRespuesta = db.insert(Utilidades.TABLA_PO_HEADERS_ALL, Utilidades.CAMPO_RECEIPT_NUM_PHA, values);

                        if (idRespuesta < 0) {
                            errorCarga = 1;
                        }


                    }

                    while (linea != null) {
                        linea = leerArchivo.readLine();
                        if (linea != null) {
                            String[] extraccion = linea.split("\\|",-1);

                            for (int a = 0; a < 1; a++) {

                                if (extraccion[0].equals("LIN")) {

                                    values.clear();

                                    values.put(Utilidades.CAMPO_PO_LINE_ID_PLA, extraccion[2]);
                                    values.put(Utilidades.CAMPO_PO_HEADER_ID_PLA, extraccion[3]);
                                    values.put(Utilidades.CAMPO_LINE_NUM_PLA, extraccion[4]);
                                    values.put(Utilidades.CAMPO_ITEM_ID_PLA, extraccion[5]);
                                    values.put(Utilidades.CAMPO_ITEM_DESCRIPTION_PLA, extraccion[6]);
                                    values.put(Utilidades.CAMPO_UNIT_PRICE_PLA, extraccion[7]);
                                    values.put(Utilidades.CAMPO_QUANTITY_PLA, extraccion[8]);
                                    values.put(Utilidades.CAMPO_UNIT_MEAS_LOOKUP_CODE_PLA, extraccion[9]);
                                    values.put(Utilidades.CAMPO_BASE_UOM_PLA, extraccion[10]);

                                    Long idRespuesta = db.insert(Utilidades.TABLA_PO_LINES_ALL, Utilidades.CAMPO_PO_LINE_ID_PLA, values);

                                    if (idRespuesta < 0) {
                                        errorCarga = 1;
                                    }

                                }

                                if (extraccion[0].equals("ENV")) {

                                    values.clear();

                                    values.put(Utilidades.CAMPO_LINE_LOCATION_ID_PLL, extraccion[2]);
                                    values.put(Utilidades.CAMPO_PO_LINE_ID_PLL, extraccion[3]);
                                    values.put(Utilidades.CAMPO_PO_HEADER_ID_PLL, extraccion[4]);
                                    values.put(Utilidades.CAMPO_QUANTITY_PLL, extraccion[5]);
                                    values.put(Utilidades.CAMPO_QUANTITY_RECEIVED_PLL, extraccion[6]);
                                    values.put(Utilidades.CAMPO_QUANTITY_CANCELLED_PLL, extraccion[7]);
                                    values.put(Utilidades.CAMPO_QUANTITY_BILLED_PLL, extraccion[8]);
                                    values.put(Utilidades.CAMPO_SHIPMENT_NUM_PLL, extraccion[9]);
                                    values.put(Utilidades.CAMPO_SHIP_TO_LOCATION_ID_PLL, extraccion[10]);
                                    values.put(Utilidades.CAMPO_QTY_RCV_TOLERANCE_PLL, extraccion[11]);
                                    values.put(Utilidades.CAMPO_ORG_ID_PLL, extraccion[12]);

                                    Long idRespuesta = db.insert(Utilidades.TABLA_PO_LINE_LOCATIONS_ALL, Utilidades.CAMPO_LINE_LOCATION_ID_PLL, values);

                                    if (idRespuesta < 0) {
                                        errorCarga = 1;
                                    }

                                }

                                if (extraccion[0].equals("DIS")) {

                                    values.clear();

                                    values.put(Utilidades.CAMPO_PO_DISTRIBUTION_ID_PDA, extraccion[2]);
                                    values.put(Utilidades.CAMPO_LINE_LOCATION_ID_PDA, extraccion[3]);
                                    values.put(Utilidades.CAMPO_PO_LINE_ID_PDA, extraccion[4]);
                                    values.put(Utilidades.CAMPO_PO_HEADER_ID_PDA, extraccion[5]);
                                    values.put(Utilidades.CAMPO_DISTRIBUTION_NUM_PDA, extraccion[6]);
                                    values.put(Utilidades.CAMPO_RATE_DATE_PDA, extraccion[7]);
                                    values.put(Utilidades.CAMPO_RATE_PDA, extraccion[8]);
                                    values.put(Utilidades.CAMPO_DESTINATION_SUBINVENTORY_PDA, extraccion[9]);
                                    values.put(Utilidades.CAMPO_DELIVER_TO_LOCATION_ID_PDA, extraccion[10]);
                                    values.put(Utilidades.CAMPO_QUANTITY_ORDERED_PDA, extraccion[11]);
                                    values.put(Utilidades.CAMPO_QUANTITY_DELIVERED_PDA, extraccion[12]);
                                    values.put(Utilidades.CAMPO_QUANTITY_BILLED_PDA, extraccion[13]);
                                    values.put(Utilidades.CAMPO_QUANTITY_CANCELLED_PDA, extraccion[14]);

                                    Long idRespuesta = db.insert(Utilidades.TABLA_PO_DISTRIBUTIONS_ALL, Utilidades.CAMPO_PO_DISTRIBUTION_ID_PDA, values);

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
                                comentario.setEnabled(true);
                                numeroParte.setEnabled(true);
                                codigoSigle.setEnabled(true);
                                cantidad.setEnabled(true);
                                udm.setEnabled(true);
                                btnConfirmarLinea.setEnabled(true);
                                numeroRecepcion.setText(numeroRecepcionExtract);
                                numeroOc.setText(numeroOcExtract);

                            }
                        }
                    }
                    db.close();
                    leerArchivo.close();
                    abrirArchivo.close();

                }else {
                    buscarDatosIngresados(nombreArchivopath.getText().toString());
                }
            }
            else{
                Toast.makeText(getApplicationContext(),"Archivo No Existe", Toast.LENGTH_LONG).show();
            }
        }catch (IOException e){
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public int InsertCabecera(){

        String numero = "";
        String fecha = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String numeroOcEx = "";
        String mumeroRecepcionEx = "";
        RcvHeadersInterfaceDTO rhi = new RcvHeadersInterfaceDTO();
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd",null,1);
        SQLiteDatabase db = conn.getReadableDatabase();
        int resInsert = 0;

        numeroOcEx = numeroOc.getText().toString();
        mumeroRecepcionEx = numeroRecepcion.getText().toString();

        Cursor b = db.rawQuery("SELECT PO_HEADER_ID||RECEIPT_NUM FROM PO_HEADERS_ALL WHERE SEGMENT1='"+numeroOcEx+"' AND RECEIPT_NUM='"+mumeroRecepcionEx+"'", null);

        if (b.moveToFirst()) {
            do {
                numero = b.getString(0);
            } while (b.moveToNext());
        }


    if(!existeOc(numero)) {
            Modelo obj = new Modelo();


            Cursor c = db.rawQuery("SELECT PO_HEADER_ID, RECEIPT_NUM, VENDOR_ID, VENDOR_SITE_CODE, VENDOR_SITE_ID, USER_ID, CURRENCY_CODE, RATE_TYPE, RATE, RATE_DATE,TERMS_ID, ORG_ID FROM PO_HEADERS_ALL WHERE SEGMENT1='"+numeroOc.getText().toString()+"' AND RECEIPT_NUM='"+numeroRecepcion.getText().toString()+"'", null);
            if (c.moveToFirst()){
                do{

                    rhi.setHeaderInterfaceId(c.getString(0)+c.getString(1));
                    rhi.setReceiptSourceCode("PDA");
                    rhi.setTransactionType("VENDOR");
                    rhi.setLastUpdateDate(fecha);
                    rhi.setLastUpdateBy(c.getString(5));
                    rhi.setCreatedBy(c.getString(5));
                    rhi.setReceiptNum(c.getString(1));
                    rhi.setVendorId(c.getString(2));
                    rhi.setVendorSiteCode(c.getString(3));
                    rhi.setVendorSiteId(c.getString(4));
                    rhi.setShipToOrganizationCode("Q01");
                    rhi.setLocationId("248");
                    rhi.setReceiverId(c.getString(5));
                    rhi.setCurrencyCode(c.getString(6));
                    rhi.setConversionRateType(c.getString(7));
                    rhi.setConversionRate(c.getString(8));
                    rhi.setConversionRateDate(c.getString(9));
                    rhi.setPaymentTermsId(c.getString(10));
                    rhi.setTransactionDate(fecha);
                    rhi.setComments(comentario.getText().toString());
                    rhi.setOrgId("288");
                    rhi.setProcessingStatusCode("PENDING");
                    rhi.setValidationFlag("Y");
                    rhi.setGroupId("33");

                    resInsert = obj.insertUsuario(Recepcion.this, rhi);

                    if (resInsert < 0){
                        Toast.makeText(Recepcion.this, "Error : No se ha podido ingresar el registro", Toast.LENGTH_LONG).show();
                    }

                }while(c.moveToNext());
            }
            b.close();
            c.close();
            db.close();
        }
            return resInsert;
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

    public int InsertLineas(String cantidad) {

        String codigoArticulo = "";
        String numeroOcEx = "";
        String mumeroRecepcionEx = "";
        String interfaceHeaderId = "";
        String fecha = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd", null, 1);
        SQLiteDatabase db = conn.getReadableDatabase();
        int resInsert = 0;

        numeroOcEx = numeroOc.getText().toString();
        mumeroRecepcionEx = numeroRecepcion.getText().toString();
        codigoArticulo = codigoSigle.getText().toString();

        Cursor b = db.rawQuery("SELECT PO_HEADER_ID||RECEIPT_NUM FROM PO_HEADERS_ALL WHERE SEGMENT1='"+numeroOcEx+"' AND RECEIPT_NUM='"+mumeroRecepcionEx+"'", null);

        if (b.moveToFirst()) {
            do {
                interfaceHeaderId = b.getString(0);
            } while (b.moveToNext());
        }

        if(existeArticulo(codigoArticulo)) {

            if(!existeArticuloInterface(interfaceHeaderId, codigoArticulo)){

                Modelo obj = new Modelo();
                RcvTransactionsInterfaceDTO rti = new RcvTransactionsInterfaceDTO();

                Cursor c = db.rawQuery("SELECT pla.ITEM_ID, pla.UNIT_MEAS_LOOKUP_CODE, pla.ITEM_DESCRIPTION, pha.VENDOR_ID, pha.VENDOR_SITE_ID, pha.PO_HEADER_ID, pla.PO_LINE_ID, pll.LINE_LOCATION_ID, pla.UNIT_PRICE, pha.CURRENCY_CODE, pha.RATE_TYPE, pha.RATE, pha.RATE_DATE, pha.VENDOR_SITE_CODE, pha.USER_ID, pla.BASE_UOM, pda.PO_DISTRIBUTION_ID FROM PO_HEADERS_ALL pha, PO_LINES_ALL pla, PO_LINE_LOCATIONS_ALL pll, PO_DISTRIBUTIONS_ALL pda, MTL_SYSTEM_ITEMS msi WHERE pha.PO_HEADER_ID = pla.PO_HEADER_ID AND pla.PO_LINE_ID = pll.PO_LINE_ID AND pla.PO_LINE_ID = pda.PO_LINE_ID AND pll.LINE_LOCATION_ID = pda.LINE_LOCATION_ID AND pla.ITEM_ID = msi.INVENTORY_ITEM_ID AND msi.SEGMENT1='" + codigoArticulo + "'", null);

                if (c.moveToFirst()){
                    do{

                        rti.setInterfaceTransactionId(c.getString(6)+mumeroRecepcionEx);
                        rti.setLastUpdateDate(fecha);
                        rti.setLastUpdatedBy(c.getString(14));
                        rti.setCreationDate(fecha);
                        rti.setCreatedBy(c.getString(14));
                        rti.setTransactionType("RECEIVE");
                        rti.setTransactionDate(fecha);
                        rti.setQuantity(cantidad);
                        rti.setUnitOfMeasure(c.getString(1));
                        rti.setItemId(c.getString(0));
                        rti.setItemDescription(c.getString(2));
                        rti.setUomCode(c.getString(15));
                        rti.setShipToLocationId("248");
                        rti.setPrimaryQuantity(cantidad);
                        rti.setReceiptSourceCode("RCV");
                        rti.setVendorId(c.getString(3));
                        rti.setVendorSiteId(c.getString(4));
                        rti.setToOrganizationId("288");
                        rti.setPoHeaderId(c.getString(5));
                        rti.setPoLineId(c.getString(6));
                        rti.setPoLineLocationId(c.getString(7));
                        rti.setPoUnitPrice(c.getString(8));
                        rti.setCurrencyCode(c.getString(9));
                        rti.setSourceDocumentCode("PO");
                        rti.setCurrencyConversionType(c.getString(10));
                        rti.setCurrencyConversionRate(c.getString(11));
                        rti.setCurrencyConversionDate(c.getString(12));
                        rti.setPoDistributionId(c.getString(16));
                        rti.setDestinationTypeCode("RECEIVING");
                        rti.setLocationId("248");
                        rti.setDeliverToLocationId("248");
                        rti.setInspectionStatusCode("NOT INSPECTED");
                        rti.setHeaderInterfaceId(interfaceHeaderId);
                        rti.setVendorSiteCode(c.getString(13));
                        rti.setProcessingStatusCode("PENDING");
                        rti.setComments("P/N");
                        rti.setProcessingModeCode("BATCH");
                        rti.setUseMtlLot("0");
                        rti.setUseMtlSerial("0");
                        rti.setTransactionStatusCode("PENDING");
                        rti.setValidationFlag("Y");
                        rti.setOrgId("288");
                        rti.setGroupId("33");
                        rti.setAutoTransactCode("RECEIVE");

                        resInsert = obj.insertLineas(Recepcion.this, rti);

                        if (resInsert == 1) {
                            Toast.makeText(Recepcion.this, "Datos Ingresados Correctamente", Toast.LENGTH_LONG).show();
                            limpiaCampos();
                            codigoSigle.requestFocus();
                            btnConfirmarRecepcion.setEnabled(true);

                        } else {
                            Toast.makeText(Recepcion.this, "Error : No se ha podido ingresar el registro", Toast.LENGTH_LONG).show();
                        }

                    }while(c.moveToNext());
                }
                b.close();
                c.close();
                db.close();
            }else{
                Toast.makeText(Recepcion.this,"El Articulo ya ha sido ingresado anteriormente",Toast.LENGTH_LONG).show();
            }

        }else{
            Toast.makeText(Recepcion.this,"El Articulo no existe",Toast.LENGTH_LONG).show();
        }

        return resInsert;
    }

    public boolean existeOc(String serial){
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd",null,1);
        SQLiteDatabase db = conn.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT HEADER_INTERFACE_ID FROM RCV_HEADERS_INTERFACE WHERE HEADER_INTERFACE_ID='"+serial+"'" , null);

        if(c.getCount() <= 0 ) {
            c.close();
            return false;
        }
        c.close();
        db.close();
        return true;

    }

    public boolean existeArticulo(String articulo){
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd",null,1);
        SQLiteDatabase db = conn.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT PO_LINE_ID FROM PO_LINES_ALL pla, MTL_SYSTEM_ITEMS msi WHERE pla.ITEM_ID = msi.INVENTORY_ITEM_ID AND msi.SEGMENT1 ='"+articulo+"'" , null);

        if(c.getCount() <= 0 ) {
            c.close();
            return false;
        }
        c.close();
        db.close();
        return true;

    }

    public boolean existeArticuloInterface(String id, String articulo){
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd",null,1);
        SQLiteDatabase db = conn.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT INTERFACE_TRANSACTION_ID FROM RCV_TRANSACTIONS_INTERFACE rti, MTL_SYSTEM_ITEMS msi WHERE rti.ITEM_ID AND msi.INVENTORY_ITEM_ID AND rti.HEADER_INTERFACE_ID='"+id+"' AND msi.SEGMENT1='"+articulo+"'" , null);
        if(c.getCount() <= 0 ) {
            c.close();
            return false;
        }
        c.close();
        db.close();
        return true;
    }

    public boolean existenLineas(){
        String numeroOcEx = "";
        String mumeroRecepcionEx = "";
        String interfaceHeaderId = "";
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd",null,1);
        SQLiteDatabase db = conn.getReadableDatabase();

        numeroOcEx = numeroOc.getText().toString();
        mumeroRecepcionEx = numeroRecepcion.getText().toString();

        Cursor b = db.rawQuery("SELECT PO_HEADER_ID||RECEIPT_NUM FROM PO_HEADERS_ALL WHERE SEGMENT1='"+numeroOcEx+"' AND RECEIPT_NUM='"+mumeroRecepcionEx+"'", null);

        if (b.moveToFirst()) {
            do {
                interfaceHeaderId = b.getString(0);
            } while (b.moveToNext());
        }

        Cursor c = db.rawQuery("SELECT INTERFACE_TRANSACTION_ID FROM RCV_TRANSACTIONS_INTERFACE WHERE HEADER_INTERFACE_ID='"+interfaceHeaderId+"'" , null);
        if(c.getCount() <= 0 ) {
            c.close();
            return false;
        }
        c.close();
        db.close();
        return true;
    }

    public void escribirArchivo(String nombreArchivo, String paso){
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd",null,1);
        SQLiteDatabase db = conn.getReadableDatabase();
        String numeroOcEx = "";
        String mumeroRecepcionEx = "";
        String interfaceHeaderId = "";
        String nomenclatura = "";

        numeroOcEx = numeroOc.getText().toString();
        mumeroRecepcionEx = numeroRecepcion.getText().toString();

        nomenclatura = "I_"+paso+"_"+numeroOcEx+"_"+mumeroRecepcionEx+".csv";

        Cursor a = db.rawQuery("SELECT PO_HEADER_ID||RECEIPT_NUM FROM PO_HEADERS_ALL WHERE SEGMENT1='"+numeroOcEx+"' AND RECEIPT_NUM='"+mumeroRecepcionEx+"'", null);

        if (a.moveToFirst()) {
            do {
                interfaceHeaderId = a.getString(0);
            } while (a.moveToNext());
        }

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("¿Desea Confirmar Recepción?");

        File tarjetaSD = Environment.getExternalStorageDirectory();
        File Dir = new File(tarjetaSD.getAbsolutePath(), "inbound");
        File rutaArchivo = new File(Dir, nomenclatura);
        StringBuilder sb = new StringBuilder();

        Cursor b = db.rawQuery("SELECT msi.SEGMENT1, rti.QUANTITY FROM RCV_TRANSACTIONS_INTERFACE rti, MTL_SYSTEM_ITEMS msi WHERE rti.ITEM_ID = msi.INVENTORY_ITEM_ID AND rti.HEADER_INTERFACE_ID='"+interfaceHeaderId+"'", null);


        Cursor c = db.rawQuery("SELECT HEADER_INTERFACE_ID, RECEIPT_SOURCE_CODE," +
                " TRANSACTION_TYPE, LAST_UPDATE_DATE, LAST_UPDATED_BY," +
                " CREATED_BY, RECEIPT_NUM, VENDOR_ID, VENDOR_SITE_CODE, VENDOR_SITE_ID," +
                " SHIP_TO_ORGANIZATION_CODE, LOCATION_ID, RECEIVER_ID, CURRENCY_CODE," +
                " CONVERSION_RATE_TYPE, CONVERSION_RATE, CONVERSION_RATE_DATE, PAYMENT_TERMS_ID," +
                " TRANSACTION_DATE, COMMENTS, ORG_ID, PROCESSING_STATUS_CODE, VALIDATION_FLAG, GROUP_ID FROM RCV_HEADERS_INTERFACE WHERE HEADER_INTERFACE_ID='"+interfaceHeaderId+"'" , null);

        Cursor d = db.rawQuery("SELECT INTERFACE_TRANSACTION_ID, LAST_UPDATE_DATE, LAST_UPDATED_BY," +
                " CREATION_DATE, CREATED_BY, TRANSACTION_TYPE, TRANSACTION_DATE, QUANTITY, UNIT_OF_MEASURE," +
                " ITEM_ID, ITEM_DESCRIPTION, UOM_CODE, SHIP_TO_LOCATION_ID, PRIMARY_QUANTITY, RECEIPT_SOURCE_CODE," +
                " VENDOR_ID, VENDOR_SITE_ID, TO_ORGANIZATION_ID, PO_HEADER_ID, PO_LINE_ID, PO_LINE_LOCATION_ID," +
                " PO_UNIT_PRICE, CURRENCY_CODE, SOURCE_DOCUMENT_CODE, CURRENCY_CONVERSION_TYPE," +
                " CURRENCY_CONVERSION_RATE, CURRENCY_CONVERSION_DATE, PO_DISTRIBUTION_ID," +
                " DESTINATION_TYPE_CODE, LOCATION_ID, DELIVER_TO_LOCATION_ID, INSPECTION_STATUS_CODE," +
                " HEADER_INTERFACE_ID, VENDOR_SITE_CODE, PROCESSING_STATUS_CODE, USE_MTL_LOT, USE_MTL_SERIAL, TRANSACTION_STATUS_CODE, VALIDATION_FLAG, ORG_ID, GROUP_ID, AUTO_TRANSACT_CODE FROM RCV_TRANSACTIONS_INTERFACE WHERE HEADER_INTERFACE_ID='"+interfaceHeaderId+"'", null);

        if (b.moveToFirst()) {
            do {
                sb.append("Articulo: "+ b.getString(0) + " Cant: " + b.getString(1)+"\n");
            } while (b.moveToNext());
        }

        alert.setMessage(sb);

        alert.setPositiveButton("Si, confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try{
                    FileWriter writer = new FileWriter(rutaArchivo);

                    writer.write("RECIBO;FIN" +"\r\n");

                    if (c.moveToFirst()) {
                        do {

                            writer.write("1;"+c.getString(0)+";"+c.getString(1)+";"+
                                    c.getString(2)+";"+c.getString(3)+";"+
                                    c.getString(4)+";"+c.getString(5)+";"+
                                    c.getString(6)+";"+c.getString(7)+";"+
                                    c.getString(8)+";"+c.getString(9)+";"+
                                    c.getString(10)+";"+c.getString(11)+";"+
                                    c.getString(12)+";"+c.getString(13)+";"+
                                    c.getString(14)+";"+c.getString(15)+";"+
                                    c.getString(16)+";"+c.getString(17)+";"+
                                    c.getString(18)+";"+c.getString(19)+";"+
                                    c.getString(20)+";"+c.getString(21)+";"+
                                    c.getString(22)+";"+c.getString(23)+";FIN"+"\r\n");

                            if (d.moveToFirst()) {
                                do {
                                    writer.write("2;"+d.getString(0)+";"+d.getString(1)+";"+
                                            d.getString(2)+";"+d.getString(3)+";"+
                                            d.getString(4)+";"+d.getString(5)+";"+
                                            d.getString(6)+";"+d.getString(7)+";"+
                                            d.getString(8)+";"+d.getString(9)+";"+
                                            d.getString(10)+";"+d.getString(11)+";"+
                                            d.getString(12)+";"+d.getString(13)+";"+
                                            d.getString(14)+";"+d.getString(15)+";"+
                                            d.getString(16)+";"+d.getString(17)+";"+
                                            d.getString(18)+";"+d.getString(19)+";"+
                                            d.getString(20)+";"+d.getString(21)+";"+
                                            d.getString(22)+";"+d.getString(23)+";"+
                                            d.getString(24)+";"+d.getString(25)+";"+
                                            d.getString(26)+";"+d.getString(27)+";"+
                                            d.getString(28)+";"+d.getString(29)+";"+
                                            d.getString(30)+";"+d.getString(31)+";"+
                                            d.getString(32)+";"+d.getString(33)+";"+
                                            d.getString(34)+";"+d.getString(35)+";"+
                                            d.getString(36)+";"+d.getString(37)+";"+
                                            d.getString(38)+";"+d.getString(39)+";"+
                                            d.getString(40)+";"+d.getString(41)+";FIN"+"\r\n");

                                } while (d.moveToNext());
                            }

                            writer.flush();
                            writer.close();

                        } while (c.moveToNext());

                    }


                }catch (IOException e){
                    e.printStackTrace();
                }

                a.close();
                b.close();
                c.close();
                d.close();
                db.close();
                Toast.makeText(Recepcion.this,"Archivo creado correctamene", Toast.LENGTH_LONG).show();
                eliminarArchivo(nombreArchivo);
                limpiaCamposComplete();
                llenarSpinner();
            }

        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alert.create().show();



    }

    public void validacionNulos(){
            if (cantidad.equals("")){
                Toast.makeText(this, "Debe Ingresar Cantidad", Toast.LENGTH_LONG).show();
            }
    }

    public void limpiaCampos(){
        numeroParte.setText("");
        cantidad.setText("");
        codigoSigle.setText("");
        udm.setText("");
    }

    public void limpiaCamposComplete(){
        nombreArchivopath.setText("");
        numeroRecepcion.setText("");
        comentario.setText("");
        numeroParte.setText("");
        cantidad.setText("");
        codigoSigle.setText("");
        udm.setText("");
        nombreArchivopath.requestFocus();
        btnConfirmarRecepcion.setEnabled(false);
    }

    public void llenarArray(){

        File tarjetaSD = Environment.getExternalStorageDirectory();
        File Dir = new File(tarjetaSD.getAbsolutePath(), "outbound");
        File[] files = Dir.listFiles();

        for (int i = 0; i < files.length; i++)
        {
            arrayList.add(files[i].getName());
            Log.d("FilesJp", "FileName:" + files[i].getName());
        }

}

    public int datosArticulos(String codigoSigle){
        int resInsert = 0;
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd",null,1);
        SQLiteDatabase db = conn.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT PRIMARY_UOM_CODE FROM MTL_SYSTEM_ITEMS WHERE SEGMENT1='"+codigoSigle+"'" , null);
        if(c.getCount() <= 0 ) {
            c.close();
            Toast.makeText(this, "No se ha encontrado articulo "+ codigoSigle, Toast.LENGTH_LONG).show();
            resInsert = 0;
        }else{
            if (c.moveToFirst()) {
                do {
                    udm.setText(c.getString(0));

                } while (c.moveToNext());
            }
         resInsert = 1;
        }

        c.close();
        db.close();
        return resInsert;
    }

    public void eliminarArchivo(String nombreArchivo){
        File tarjetaSD = Environment.getExternalStorageDirectory();
        File Dir = new File(tarjetaSD.getAbsolutePath(), "outbound");
        File file = new File(Dir,nombreArchivo);
        file.delete();
    }

    public void llenarSpinner(){
        //Llenar Array
        arrayList = new ArrayList<>();
        File tarjetaSD = Environment.getExternalStorageDirectory();
        File Dir = new File(tarjetaSD.getAbsolutePath(), "outbound");
        File[] files = Dir.listFiles();

        for (int i = 0; i < files.length; i++) {
            if (files[i].getName().startsWith("O_1")) {
                arrayList.add(files[i].getName().toString());
            }
        }

        nombreArchivopath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(Recepcion.this);
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
                ArrayAdapter<String> adapter = new ArrayAdapter<>(Recepcion.this
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

    public void llenarSpinnerNumeroParte(){
        //Llenar Array
        arrayListParte = new ArrayList<>();
        String numeroOcEx = "";
        String mumeroRecepcionEx = "";
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd",null,1);
        SQLiteDatabase db = conn.getReadableDatabase();

        numeroOcEx = numeroOc.getText().toString();
        mumeroRecepcionEx = numeroRecepcion.getText().toString();

        Cursor a = db.rawQuery("SELECT msi.DESCRIPTION||' '||msi.LONG_DESCRIPTION FROM MTL_SYSTEM_ITEMS msi, PO_LINES_ALL pla, PO_HEADERS_ALL pha WHERE pha.po_header_id = pla.po_header_id AND pla.item_id = msi.inventory_item_id AND pha.SEGMENT1='"+numeroOcEx+"' AND pha.RECEIPT_NUM='"+mumeroRecepcionEx+"'", null);

        if (a.moveToFirst()) {
            do {
                arrayListParte.add(a.getString(0));
            } while (a.moveToNext());
        }


        numeroParte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(Recepcion.this);
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
                ArrayAdapter<String> adapter = new ArrayAdapter<>(Recepcion.this
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


                        Cursor b = db.rawQuery("SELECT msi.SEGMENT1, msi.PRIMARY_UOM_CODE FROM MTL_SYSTEM_ITEMS msi, PO_LINES_ALL pla, PO_HEADERS_ALL pha WHERE pha.po_header_id = pla.po_header_id AND pla.item_id = msi.inventory_item_id AND pha.SEGMENT1='"+numeroOcEx+"' AND RECEIPT_NUM='"+mumeroRecepcionEx+"' AND msi.DESCRIPTION||' '||msi.LONG_DESCRIPTION = '"+seleccion+"'", null);
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

    public boolean validaDatosCargados(String nombreArchivo){
        String numeroOcAr = "";
        String recepcionAr = "";
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd",null,1);
        SQLiteDatabase db = conn.getReadableDatabase();
        String [] extraccion = nombreArchivo.split("_", -1);

        for (int a=0; a <1; a++) {
            numeroOcAr = extraccion[2];
            recepcionAr = extraccion[3];
        }

        Cursor c = db.rawQuery("SELECT SEGMENT1 FROM PO_HEADERS_ALL WHERE SEGMENT1 = '"+numeroOcAr+"' AND RECEIPT_NUM ='"+recepcionAr+"'" , null);

        if(c.getCount() <= 0 ) {
            c.close();
            return false;
        }

        c.close();
        db.close();

        return true;

    }

    public void buscarDatosIngresados(String nombreArchivo) {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd", null, 1);
        SQLiteDatabase db = conn.getReadableDatabase();
        String numeroOcAr = "";
        String recepcionAr = "";
        String comentarioAr = "";


        String[] extraccion = nombreArchivo.split("_");
        for (int a = 0; a < 1; a++) {
            numeroOcAr = extraccion[2];
            recepcionAr = extraccion[3];
        }

        numeroOc.setText(numeroOcAr);
        numeroRecepcion.setText(recepcionAr);

        Cursor c = db.rawQuery("SELECT COMMENTS FROM RCV_HEADERS_INTERFACE WHERE HEADER_INTERFACE_ID='" + numeroOcAr + recepcionAr + "'", null);

        if (c.moveToFirst()) {
            do {
                comentarioAr = c.getString(0);
            } while (c.moveToNext());
        }

        comentario.setText(comentarioAr);

    }

    public int calculaCantidad(){
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd",null,1);
        SQLiteDatabase db = conn.getReadableDatabase();
        String numeroOcEx = "";
        String mumeroRecepcionEx = "";
        String codigoSigleEx = "";
        int cantidadQ   = 0;
        int cantidadRecibidaQ = 0;
        int cantidadCanceladaQ = 0;
        int cantidadPendiente = 0;
        int tolerancia = 0;

        numeroOcEx = numeroOc.getText().toString();
        mumeroRecepcionEx = numeroRecepcion.getText().toString();
        codigoSigleEx = codigoSigle.getText().toString();

        Cursor a = db.rawQuery("SELECT pll.QUANTITY, pll.QUANTITY_RECEIVED, pll.QUANTITY_CANCELLED, pll.QTY_RCV_TOLERANCE FROM PO_HEADERS_ALL pha, PO_LINES_ALL pla, MTL_SYSTEM_ITEMS msi, PO_LINE_LOCATIONS_ALL pll WHERE pha.po_header_id = pla.po_header_id AND pha.po_header_id = pll.po_header_id AND pla.po_header_id = pha.po_header_id AND pla.po_line_id = pll.po_line_id AND pla.item_id = msi.inventory_item_id AND pha.SEGMENT1='"+numeroOcEx+"' AND pha.RECEIPT_NUM='"+mumeroRecepcionEx+"' AND msi.SEGMENT1='"+codigoSigleEx+"'", null);

        if (a.moveToFirst()) {
            do {
                cantidadQ = Integer.parseInt(a.getString(0));
                cantidadRecibidaQ = Integer.parseInt(a.getString(1));
                cantidadCanceladaQ = Integer.parseInt(a.getString(2));
                tolerancia =  Integer.parseInt(a.getString(3));
            } while (a.moveToNext());
        }

        cantidadPendiente = (cantidadQ-cantidadRecibidaQ-cantidadCanceladaQ) * (1 + tolerancia/100);



        a.close();
        db.close();

        return cantidadPendiente;


    }
}