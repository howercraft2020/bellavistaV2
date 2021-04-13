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

import java.util.ArrayList;
import java.util.List;

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.model.Localizador;
import cl.clsoft.bave.model.MtlOnhandQuantities;
import cl.clsoft.bave.model.MtlSerialNumbersInterface;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.model.Subinventario;
import cl.clsoft.bave.presenter.AgregarTransSubinvPresenter;
import cl.clsoft.bave.service.impl.TransSubinvService;
import cl.clsoft.bave.task.AppTaskExecutor;

public class ActivityAgregarTransSubinv extends BaseActivity<AgregarTransSubinvPresenter> implements ConfirmationDialog.ConfirmationDialogListener{

    //Variables
    private String numeroTraspaso;
    private String glosa;
    private String codigoSigle;
    private String subinvDesde;
    private String localizador;
    private Long locatorId;
    private String locatorCodigo;
    private String nroLote;
    private String subinvHasta;
    private String localHasta;
    private String id;
    private String codSubinventario;
    private String segment;
    private List<String> series = new ArrayList<>();
    private int LAUNCH_SEARCHSINGLE_ACTIVITY = 2;
    private boolean isLote = false;
    private boolean isSerie = false;
    private Double cantidad;

    //Controls
    private EditText nroTraspasoEt;
    private EditText glosaEt;
    private TextInputLayout layoutSubinventario;
    private AutoCompleteTextView textSubinventario;
    private TextInputLayout layoutLocator;
    private AutoCompleteTextView textLocator;
    private TextInputLayout layoutSigle;
    private AutoCompleteTextView textSigle;
    private TextInputLayout layoutLote;
    private AutoCompleteTextView textLote;
    private TextInputLayout layoutCantidad;
    private TextInputEditText textCantidad;
    private ImageView iconSearch;


    @NonNull
    @Override
    protected AgregarTransSubinvPresenter createPresenter(@NonNull Context context) {
        return new AgregarTransSubinvPresenter(this, new AppTaskExecutor(this), new TransSubinvService());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_agregar_trans_subinv, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_trans_subinv);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_highlight_off_white_36dp);


        this.llProgressBar = findViewById(R.id.llProgressBar);
        this.layoutSubinventario = findViewById(R.id.layoutSubinventario);
        this.textSubinventario = findViewById(R.id.textSubinventario);
        this.layoutLocator = findViewById(R.id.layoutLocator);
        this.textLocator = findViewById(R.id.textLocator);
        this.layoutSigle = findViewById(R.id.layoutSigle);
        this.textSigle = findViewById(R.id.textSigle);
        this.layoutLote = findViewById(R.id.layoutLote);
        this.textLote = findViewById(R.id.textLote);
        this.layoutCantidad = findViewById(R.id.layoutCantidad);
        this.textCantidad = findViewById(R.id.textCantidad);
        this.iconSearch = findViewById(R.id.iconSearch);
        this.glosaEt = (EditText) findViewById(R.id.glosaEditText);
        this.nroTraspasoEt = (EditText) findViewById(R.id.traspasoEditText);

        this.numeroTraspaso = getIntent().getStringExtra("numeroTraspaso");
        this.glosa = getIntent().getStringExtra("glosa");
        this.codigoSigle = getIntent().getStringExtra("codigoSigle");
        this.subinvDesde = getIntent().getStringExtra("subinvDesde");
        this.localizador = getIntent().getStringExtra("localizador");
        this.nroLote = getIntent().getStringExtra("nroLote");
        this.cantidad = getIntent().getDoubleExtra("cantidad",0);
        this.subinvHasta = getIntent().getStringExtra("subinvHasta");
        this.localHasta = getIntent().getStringExtra("localHasta");
        this.id = getIntent().getStringExtra("id");
        this.series = this.getIntent().getStringArrayListExtra("series");

        nroTraspasoEt.setText(numeroTraspaso);
        glosaEt.setText(glosa);
        textSigle.setText(codigoSigle);
        textSubinventario.setText(subinvDesde);
        textLocator.setText(localizador);
        textLote.setText(nroLote);

        if (cantidad > 0) {
            textCantidad.setText(String.valueOf(cantidad));
        }
        else{
            textCantidad.setText("");
        }

        if(localizador != null){
            textLocator.setEnabled(false);
            textSubinventario.setEnabled(false);
        }

        if(codigoSigle != null){
            textSigle.setEnabled(false);
        }

        if(nroLote != null){
            textLote.setEnabled(false);
        }


        this.textSubinventario.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                codSubinventario = parent.getAdapter().getItem(position).toString();
                Log.d(TAG, "codSubinventario: " + codSubinventario);
                if (codSubinventario != null && !codSubinventario.equalsIgnoreCase("SIN LOCALIZADOR")) {
                    mPresenter.getLocalizadoresBySubinventario(codSubinventario);
                }

                loadSigle();
                textLocator.requestFocus();
            }
        });

        /*
        this.textLote.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        */

        this.textLocator.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id) {
                locatorCodigo = parent.getAdapter().getItem(pos).toString();
                Log.d(TAG, "locatorCodigo: " + locatorCodigo);
                if (locatorCodigo != null && !locatorCodigo.equalsIgnoreCase("SIN LOCALIZADOR")) {
                    Localizador localizador = mPresenter.getLocalizadorbyCodigo(locatorCodigo);
                    if (localizador != null) {
                        locatorId = localizador.getIdLocalizador();
                    }
                }
                loadSigle();
            }
        });

        this.textSigle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                textSigle.setEnabled(false);
                segment = parent.getAdapter().getItem(pos).toString();
                MtlSystemItems item = mPresenter.getMtlSystemItemsBySegment(segment);
                if (item != null) {
                    if (item.getLotControlCode().equalsIgnoreCase("2")) {
                        fillLote();
                    }
                    layoutCantidad.setHintEnabled(true);
                    textCantidad.setEnabled(true);
                    layoutCantidad.setHint("Cantidad (" + item.getPrimaryUomCode() + ")");
                } else {
                    showWarning("Item " + segment + " no encontrado en tabla maestra");
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

                            //textSigle.setEnabled(false); Preguntar
                            if(item.getLotControlCode().equalsIgnoreCase("2")){
                                textLote.setEnabled(true);
                                layoutLote.setHintEnabled(true);
                                isLote = true;
                                fillLote();
                            }
                            else {
                                textLote.setEnabled(false);
                                layoutLote.setHintEnabled(false);
                                isLote = false;
                            }
                            if (item.getSerialNumberControlCode().equalsIgnoreCase("2") || item.getSerialNumberControlCode().equalsIgnoreCase("5")) {
                                isSerie = true;
                            } else {
                                isSerie = false;
                            }
                            textSigle.setEnabled(false);
                            layoutCantidad.setHintEnabled(true);
                            textCantidad.setEnabled(true);
                            layoutCantidad.setHint("Cantidad (" + item.getPrimaryUomCode() +")");
                            //mPresenter

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
            iSearch.putExtra("Tipo", "TS");
            iSearch.putExtra("Subinventario", this.codSubinventario);
            iSearch.putExtra("LocatorId", this.locatorId);
            startActivityForResult(iSearch, LAUNCH_SEARCHSINGLE_ACTIVITY);
        });

        this.mPresenter.getSubinventarios();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        String nroTraspaso;
        String articulo;
        String lote;
        String localizador;
        String subinventario;
        String glosa;
        Double cantidad = 0.0;

        if(this.textCantidad.getText().toString().trim().length() != 0) {
            cantidad = Double.parseDouble(this.textCantidad.getText().toString());
        }

        nroTraspaso = this.nroTraspasoEt.getText().toString();
        articulo = this.textSigle.getText().toString();
        lote = this.textLote.getText().toString();
        localizador = this.textLocator.getText().toString();
        subinventario = this.textSubinventario.getText().toString();
        glosa = this.glosaEt.getText().toString();

        switch (item.getItemId()) {
            case R.id.clean:
                this.cleanScreen();
                return true;
            case R.id.action_more:
                if( mPresenter.cargaTransferencia(articulo, lote, subinventario, localizador, cantidad)) {
                    Intent i = new Intent(this, ActivityTransSubinvDest.class);
                    i.putExtra("codigoSigle", articulo);
                    i.putExtra("subinvDesde", subinventario);
                    i.putExtra("localizador", localizador);
                    i.putExtra("nroLote", lote);
                    i.putExtra("cantidad", cantidad);
                    i.putExtra("glosa", glosa);
                    i.putExtra("numeroTraspaso",nroTraspaso);
                    i.putExtra("id",id);
                    i.putExtra("subinvHasta",subinvHasta);
                    i.putExtra("localHasta",localHasta);
                    i.putStringArrayListExtra("series", (ArrayList<String>) this.series);
                    startActivity(i);
                    this.finish();
                    return true;
                }
                return true;
            case android.R.id.home:
                Log.d(TAG, "home");
                this.confirmacionSalir();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void fillSubinventario(List<Subinventario> subinventarios) {
        if (subinventarios != null) {
            if (subinventarios.size() > 0){
                String[] subinventariosArray = new String[subinventarios.size()];
                for (int i = 0; i <subinventarios.size();i++){
                    subinventariosArray[i] = subinventarios.get(i).getCodSubinventario();
                }
                ArrayAdapter<String> adapterSubinventarios = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, subinventariosArray);
                this.textSubinventario.setAdapter(adapterSubinventarios);
            }
        }
    }

    public void fillLocator(List<Localizador> localizadores) {
        if (localizadores != null) {
            if (localizadores.size() > 0) {
                String[] locators = new String[localizadores.size()];
                for (int i = 0; i < localizadores.size(); i++) {
                    locators[i] = localizadores.get(i).getCodLocalizador();
                }
                ArrayAdapter<String> adapterLocator = new ArrayAdapter<String> (this, android.R.layout.select_dialog_item, locators);
                this.textLocator.setAdapter(adapterLocator);
                this.textLocator.setText("");
            } else {
                this.loadSigle();
            }
        }
    }

    public void loadSigle() {
        this.mPresenter.getSegment1(codSubinventario, locatorCodigo);
    }


    public void fillSigle(List<String> listSigles) {
        if (listSigles != null) {
            if (listSigles.size() > 0) {
                String[] sigles = new String[listSigles.size()];
                for (int i = 0; i < listSigles.size(); i++) {
                    sigles[i] = listSigles.get(i);
                }
                ArrayAdapter<String> adapterSerie = new ArrayAdapter<String> (this, android.R.layout.select_dialog_item, sigles);

                if (!textLocator.getText().toString().equals("")) {
                    textLocator.setEnabled(false);
                    textSubinventario.setEnabled(false);
                }

                this.textSigle.setAdapter(adapterSerie);
                this.textSigle.setEnabled(true);
                this.layoutSigle.setHintEnabled(true);
                //this.textSigle.requestFocus();
            } else {
                this.showWarning("No se encontraron productos");
                this.textLocator.setText("");
                this.textLocator.requestFocus();
            }
        } else {
            this.showWarning("No se encontraron productos");
            this.textLocator.setText("");
            this.textLocator.requestFocus();
        }
    }

    public void fillLote() {
        List<String> listLotes = this.mPresenter.getLotesBySubinvLoc(codSubinventario, locatorId, segment);
        if (listLotes != null) {
            if (listLotes.size() > 0) {
                String[] lotes = new String[listLotes.size()];
                for (int i = 0; i < listLotes.size(); i++) {
                    lotes[i] = listLotes.get(i);
                }
                ArrayAdapter<String> adapterLote = new ArrayAdapter<String> (this, android.R.layout.select_dialog_item, lotes);
                this.textLote.setAdapter(adapterLote);
                this.textLote.setEnabled(true);
                this.layoutLote.setHintEnabled(true);
            } else {
                this.textLote.setEnabled(false);
                this.layoutLote.setHintEnabled(false);
            }
        }
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
                    //textSigle.setEnabled(false); preguntar

                    if(item.getLotControlCode().equalsIgnoreCase("2")){
                        this.textLote.setEnabled(true);
                        this.layoutLote.setHintEnabled(true);
                        isLote = true;
                    }
                    else {
                        this.textLote.setEnabled(false);
                        this.layoutLote.setHintEnabled(false);
                        isLote = false;
                    }
                    if (item.getSerialNumberControlCode().equalsIgnoreCase("2") || item.getSerialNumberControlCode().equalsIgnoreCase("5")) {
                        isSerie = true;
                    } else {
                        isSerie = false;
                    }
                    layoutCantidad.setHintEnabled(true);
                    textCantidad.setEnabled(true);
                    layoutCantidad.setHint("Cantidad (" + item.getPrimaryUomCode() + ")");
                } else {
                    showWarning("Item " + segment + " no encontrado en tabla maestra");
                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                this.textSigle.setText("");
            }
        }
    }

    private void confirmacionSalir() {
        ConfirmationDialog dialogExit = ConfirmationDialog.newInstance("Perdera los datos ingresados. Quiere salir?", "Confirmación", "exit");
        dialogExit.show(getSupportFragmentManager(), "exitAgregarConfirm");
    }

    @Override
    public void onDialogAceptarClick(DialogFragment dialog) {
        Intent i = new Intent(this, ActivityTransSubinv.class);
        startActivity(i);
        this.finish();
    }

    @Override
    public void onDialogCancelarClick(DialogFragment dialog) {

    }

    public void cleanScreen() {
        String[] vencimientos = new String[0];
        ArrayAdapter<String> adapterClear = new ArrayAdapter<String> (this, android.R.layout.select_dialog_item, vencimientos);

        this.textSubinventario.requestFocus();
        this.textLocator.setEnabled(true);
        this.textSubinventario.setEnabled(true);
        this.textSubinventario.setText("");
        this.codSubinventario = null;
        this.textLocator.setText("");
        this.locatorCodigo = null;
        this.locatorId = null;
        this.textSigle.setText("");
        this.textLote.setText("");
        this.textCantidad.setText("");
    }
}