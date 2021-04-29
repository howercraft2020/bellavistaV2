package cl.clsoft.bave.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.model.PoLinesAll;
import cl.clsoft.bave.model.RcvTransactions;
import cl.clsoft.bave.model.Subinventario;
import cl.clsoft.bave.presenter.AgregarRecepcionPresenter;
import cl.clsoft.bave.service.impl.RecepcionOcService;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class ActivityAgregarRecepcion extends BaseActivity<AgregarRecepcionPresenter> implements ConfirmationDialog.ConfirmationDialogListener, DialogSeleccionLinea.DialogSeleccionLineaListener{

    //Variables
    private String codigoSigle;
    private String udm;
    private Double cantidad;
    String numeroOc;
    String poHeaderId;
    Long numeroRecep;
    String comentario;
    private String segment;
    private Long inventoryItemId;
    private int LAUNCH_SEARCHSINGLE_ACTIVITY = 2;
    CharSequence[] items;


    //Controls
    private TextInputLayout layoutCantidad;
    private TextInputEditText textCantidad;
    private TextInputLayout layoutSigle;
    private AutoCompleteTextView textSigle;
    private TextInputLayout layoutUdm;
    private AutoCompleteTextView textUdm;
    private TextInputLayout layoutNumerolinea;
    private AutoCompleteTextView textNumeroLinea;
    private ImageView iconSearch;



    @NonNull
    @Override
    protected AgregarRecepcionPresenter createPresenter(@NonNull Context context) {
        return new AgregarRecepcionPresenter(this, new RecepcionOcService());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_agregar_recepcion, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_recepcion);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_highlight_off_white_36dp);

        this.llProgressBar = findViewById(R.id.llProgressBar);
        this.layoutSigle = findViewById(R.id.layoutSigle);
        this.textSigle = findViewById(R.id.textSigle);
        this.layoutCantidad = findViewById(R.id.layoutCantidad);
        this.textCantidad = findViewById(R.id.textCantidad);
        this.layoutUdm = findViewById(R.id.layoutUdm);
        this.textUdm = findViewById(R.id.textUdm);
        this.layoutNumerolinea = findViewById(R.id.layoutNumerolinea);
        this.textNumeroLinea = findViewById(R.id.textNumeroLinea);
        this.iconSearch = findViewById(R.id.iconSearch);


        numeroOc = getIntent().getStringExtra("numeroOc");
        numeroRecep = getIntent().getLongExtra("NumeroRecep",0);
        poHeaderId = getIntent().getStringExtra("poHeaderId");
        comentario = getIntent().getStringExtra("comentario");

        this.textSigle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                segment = parent.getAdapter().getItem(position).toString();
                MtlSystemItems item = mPresenter.getMtlSystemItemsBySegment(segment);
                if (item != null){

                    layoutCantidad.setHintEnabled(true);
                    textCantidad.setEnabled(true);
                    layoutCantidad.setHint("Cantidad (" + item.getPrimaryUomCode() + ")");
                    textUdm.setText(item.getPrimaryUomCode());
                    inventoryItemId = item.getInventoryItemId();
                    //mPresenter.getLines(item.getInventoryItemId(),Long.parseLong(poHeaderId));
                    validaLinea();
                }
                else {
                    showWarning("Item " + segment + " no se ha encontrado en la maestra.");
                }
            }
        });


        this.textSigle.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
                Log.d(TAG, "onEditorAction: " + textView.getText());
                Log.d(TAG, "actionId: " + actionId);
                boolean action = false;
                action = true;

                if(actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER))
                {
                    if(textView.getText() != null && !textView.getText().toString().isEmpty())
                    {
                        segment = textView.getText().toString();
                        MtlSystemItems item = mPresenter.getMtlSystemItemsBySegment(segment);
                        if (item != null){

                            layoutCantidad.setHintEnabled(true);
                            textCantidad.setEnabled(true);
                            layoutCantidad.setHint("Cantidad (" + item.getPrimaryUomCode() + ")");
                            textUdm.setText(item.getPrimaryUomCode());
                            inventoryItemId = item.getInventoryItemId();
                            //mPresenter.getLines(item.getInventoryItemId(),Long.parseLong(poHeaderId));
                            validaLinea();
                        }
                        else {
                            showWarning("Item " + segment + " no se ha encontrado en la maestra.");
                        }
                    }
                    action = true;
                }
                return action;
            }
        });

        this.iconSearch.setOnClickListener(v -> {
            Intent iSearch = new Intent(this, ActivitySigleSearch.class);
            iSearch.putExtra("Tipo", "R");
            iSearch.putExtra("PoHeaderId", this.poHeaderId);
            startActivityForResult(iSearch, LAUNCH_SEARCHSINGLE_ACTIVITY);
        });

        this.mPresenter.getArticulosByOcReceipt(Long.valueOf(poHeaderId),numeroRecep);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == LAUNCH_SEARCHSINGLE_ACTIVITY){
            if(resultCode == Activity.RESULT_OK) {
                segment = data.getStringExtra("Segment1");
                this.textSigle.setText(segment);
                Log.d(TAG, "sigle: " + segment);
                MtlSystemItems item = mPresenter.getMtlSystemItemsBySegment(segment);
                if(item !=null) {

                    layoutCantidad.setHintEnabled(true);
                    textCantidad.setEnabled(true);
                    layoutCantidad.setHint("Cantidad (" + item.getPrimaryUomCode() + ")");
                    textUdm.setText(item.getPrimaryUomCode());
                    inventoryItemId = item.getInventoryItemId();
                    //mPresenter.getLines(item.getInventoryItemId(),Long.parseLong(poHeaderId));
                    validaLinea();
                } else {
                    showWarning("Item " + segment + " no encontrado en tabla maestra");
                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                this.textSigle.setText("");
            }
        }
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String codigoSigle = "";
        Double cantidad = 0.0;
        Long poLineNum = 0L;
        String udm;


        if(this.textSigle.getText().toString().trim().length() != 0) {
            codigoSigle = this.textSigle.getText().toString();
        }

        if(this.textCantidad.getText().toString().trim().length() != 0) {
            cantidad = Double.parseDouble(this.textCantidad.getText().toString());

        }

        if(this.textUdm.getText().toString().trim().length() != 0) {
            udm = this.textUdm.getText().toString();
        }

        if(this.textNumeroLinea.getText().toString().trim().length() != 0) {
            poLineNum = Long.parseLong(this.textNumeroLinea.getText().toString());

        }

        switch (item.getItemId()){
            case R.id.clean:
                this.cleanScreen();
                return true;
            case R.id.action_save:
                mPresenter.cargaRecepcion(codigoSigle,Long.parseLong(poHeaderId),numeroOc,numeroRecep, cantidad, poLineNum);
                return true;
            case android.R.id.home:
                Log.d(TAG, "home");
                this.confirmacionSalir();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void fillSigle(List<MtlSystemItems> articulos) {
        if (articulos != null) {
            if (articulos.size() > 0){
                String[] articulosArray = new String[articulos.size()];
                for (int i = 0; i <articulos.size();i++){
                    articulosArray[i] = articulos.get(i).getSegment1();
                }
                ArrayAdapter<String> adapterArticulos = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, articulosArray);
                this.textSigle.setAdapter(adapterArticulos);
            }
        }
    }

    public void fillLines(List<PoLinesAll> lineas) {
        if (lineas != null) {
            if (lineas.size() > 0){
                String[] lineasArray = new String[lineas.size()];
                for (int i = 0; i <lineas.size();i++){
                    lineasArray[i] = lineas.get(i).getLineNum();
                }
                ArrayAdapter<String> adapterArticulos = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, lineasArray);
                this.textNumeroLinea.setAdapter(adapterArticulos);
            }
        }
    }

    @Override
    public void onDialogAceptarClick(DialogFragment dialog) {
        Intent i = new Intent(this, ActivityArticulosRecepcion.class);
        i.putExtra("numeroOc", numeroOc);
        i.putExtra("NumeroRecep", numeroRecep);
        i.putExtra("poHeaderId", poHeaderId);
        i.putExtra("comentario", comentario);
        startActivity(i);
        this.finish();
    }

    @Override
    public void onDialogCancelarClick(DialogFragment dialog) {

    }

    private void confirmacionSalir() {
        ConfirmationDialog dialogExit = ConfirmationDialog.newInstance("Perdera los datos ingresados. Quiere salir?", "Confirmación", "exit");
        dialogExit.show(getSupportFragmentManager(), "exitAgregarConfirm");
    }

    public void resultadoOkAddTransaction() {
        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Éxito")
                .setContentText("Creación exitosa")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        Log.d("CMFA", "CLICK");
                        Intent i = new Intent(getApplicationContext(), ActivityArticulosRecepcion.class);
                        i.putExtra("numeroOc", numeroOc);
                        i.putExtra("NumeroRecep", numeroRecep);
                        i.putExtra("poHeaderId", poHeaderId);
                        i.putExtra("comentario", comentario);
                        startActivity(i);
                        finish();
                    }
                })
                .show();
    }

    private void validaLinea() {
        if (this.inventoryItemId != null) {
            List<PoLinesAll> lines = mPresenter.getLines(inventoryItemId,Long.parseLong(poHeaderId));
            if (lines.size() == 0) {
                this.textSigle.setText("");
                showWarning("Sigle " + segment + " no encontrado en OC");
                this.inventoryItemId = null;
            } else if (lines.size() > 1) {
                items = new CharSequence[lines.size()];
                int index = 0;
                for (PoLinesAll line : lines) {
                    Log.d(TAG, "items add " + line.getLineNum().toString());
                    items[index] = line.getLineNum().toString();
                    index++;
                }
                DialogSeleccionLinea dialogLinea =new DialogSeleccionLinea();
                Bundle args = new Bundle();
                args.putCharSequenceArray("items", items);
                dialogLinea.setArguments(args);
                dialogLinea.show(getSupportFragmentManager(), "DialogLinea");
            } else {
                this.textNumeroLinea.setText(lines.get(0).getLineNum());
            }
        }
    }

    @Override
    public void onDialogLineaPositiveClick(DialogFragment dialog) {
        Log.d(TAG, "salida " + ((DialogSeleccionLinea) dialog).selectedItem);
        String linea = ((DialogSeleccionLinea) dialog).selectedItem;
        this.textNumeroLinea.setText(linea);
    }


    public void cleanScreen() {

        this.textSigle.setText("");
        this.textNumeroLinea.setText("");
        this.textCantidad.setText("");
        this.textUdm.setText("");
        this.textSigle.requestFocus();
    }


}