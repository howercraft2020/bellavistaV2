package cl.clsoft.bave.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

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
    private String nroLote;
    private String id;
    private String codSubinventario;
    private String segment;

    //Controls
    private EditText nroTraspasoEt, glosaEt;
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
    private Long cantidad;

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




        numeroTraspaso = getIntent().getStringExtra("nroTraspaso");
        glosa = getIntent().getStringExtra("glosa");
        codigoSigle = getIntent().getStringExtra("codigoSigle");
        subinvDesde = getIntent().getStringExtra("subinvDesde");
        localizador = getIntent().getStringExtra("localizador");
        nroLote = getIntent().getStringExtra("nroLote");
        cantidad = getIntent().getLongExtra("cantidad",0L);
        id = getIntent().getStringExtra("id");

        nroTraspasoEt.setText(numeroTraspaso);
        glosaEt.setText(glosa);
        textSigle.setText(codigoSigle);
        textSubinventario.setText(subinvDesde);
        textLocator.setText(localizador);
        textLote.setText(nroLote);
        textCantidad.setText(String.valueOf(cantidad));

        this.textSubinventario.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                codSubinventario = parent.getAdapter().getItem(position).toString();
                Log.d(TAG, "codSubinventario: " + codSubinventario);
                if (codSubinventario != null && !codSubinventario.equalsIgnoreCase("SIN LOCALIZADOR")) {
                    mPresenter.getLocalizadoresBySubinventario(codSubinventario);
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

                            textSigle.setEnabled(false);
                            if(item.getLotControlCode().equalsIgnoreCase("2")){
                                textLote.setEnabled(true);
                                layoutLote.setHintEnabled(true);
                            }
                            else {
                                textLote.setEnabled(false);
                                layoutLote.setHintEnabled(false);
                            }
                            layoutCantidad.setHintEnabled(true);
                            textCantidad.setEnabled(true);
                            layoutCantidad.setHint("Cantidad (" + item.getPrimaryUomCode());

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
        Long cantidad = 0L;

        if(this.textCantidad.getText().toString().trim().length() != 0) {
            cantidad = Long.parseLong(this.textCantidad.getText().toString());
        }

        nroTraspaso = this.nroTraspasoEt.getText().toString();
        articulo = this.textSigle.getText().toString();
        lote = this.textLote.getText().toString();
        localizador = this.textLocator.getText().toString();
        subinventario = this.textSubinventario.getText().toString();
        glosa = this.glosaEt.getText().toString();

        switch (item.getItemId()) {
            case R.id.action_more:
                if( mPresenter.cargaTransferencia(articulo, lote, subinventario, localizador, cantidad)) {
                    Intent i = new Intent(this, ActivityTransSubinvDest.class);
                    i.putExtra("codSigle", articulo);
                    i.putExtra("subinvdesde", subinventario);
                    i.putExtra("localizador", localizador);
                    i.putExtra("nroLote", lote);
                    i.putExtra("cantidad", cantidad);
                    i.putExtra("glosa", glosa);
                    i.putExtra("nroTraspaso",nroTraspaso);
                    i.putExtra("id",id);
                    startActivity(i);
                    this.finish();
                    return true;
                }
                return true;
            case android.R.id.home:
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
                this.textSubinventario.setText("");
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
                this.textLocator.setVisibility(View.GONE);
                this.layoutLocator.setVisibility(View.GONE);
                //this.textSigle.setEnabled(false); JP
                //this.hayLocalizador = false; JP
                //this.loadSigle();
            }
        }
    }


    @Override
    public void onDialogAceptarClick(DialogFragment dialog) {

    }

    @Override
    public void onDialogCancelarClick(DialogFragment dialog) {

    }
}