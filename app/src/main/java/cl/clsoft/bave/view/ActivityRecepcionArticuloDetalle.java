package cl.clsoft.bave.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.presenter.RecepcionArticuloDetallePresenter;
import cl.clsoft.bave.service.impl.RecepcionOcService;

public class ActivityRecepcionArticuloDetalle extends BaseActivity<RecepcionArticuloDetallePresenter> implements ConfirmationDialog.ConfirmationDialogListener {

    //Variables
    String numeroOc;
    Long numeroRecep;
    Long interfaceTransactionId;
    Long lineLocationId;
    Double cantidad;
    String id;

    //Controls
    private TextView segment1;
    private TextView receiptNum;
    private TextView poHeaderId;
    private TextInputLayout layoutCantidad;
    private TextInputEditText textCantidad;


    @NonNull
    @Override
    protected RecepcionArticuloDetallePresenter createPresenter(@NonNull Context context) {
        return new RecepcionArticuloDetallePresenter(this, new RecepcionOcService());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_recepcion_articulo_detalle, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recepcion_articulo_detalle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.segment1 = (TextView) findViewById(R.id.segment1);
        this.receiptNum = (TextView) findViewById(R.id.receiptNum);
        this.poHeaderId = (TextView) findViewById(R.id.poHeaderId);
        this.layoutCantidad = findViewById(R.id.layoutCantidad);
        this.textCantidad = findViewById(R.id.textCantidad);

        numeroOc = getIntent().getStringExtra("numeroOc");
        numeroRecep = getIntent().getLongExtra("NumeroRecep",0);
        id = getIntent().getStringExtra("id");
        interfaceTransactionId = getIntent().getLongExtra("interfaceTransactionId",0);
        cantidad = getIntent().getDoubleExtra("cantidad",0.0);
        lineLocationId = getIntent().getLongExtra("lineLocationId",0);

        segment1.setText(numeroOc);
        receiptNum.setText(numeroRecep.toString());
        poHeaderId.setText(id);
        textCantidad.setText(String.valueOf(cantidad));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.update:
                String strCantidad = this.textCantidad.getText().toString();
                if (strCantidad.isEmpty()) {
                    this.textCantidad.setError("Ingrese la cantidad");
                    return true;
                }
                cantidad = Double.valueOf(strCantidad);
                if (cantidad < 0) {
                    this.textCantidad.setError("Ingrese una cantidad válida");
                    return true;
                }

                ConfirmationDialog dialogUpdate = ConfirmationDialog.newInstance("Esta seguro de actualizar la cantidad?", "Confirmación", "update");
                dialogUpdate.show(getSupportFragmentManager(), "updateRecepcionConfirm");
                return true;
            case android.R.id.home:
                Intent i = new Intent(getApplicationContext(), ActivityArticulosRecepcion.class);
                i.putExtra("numeroOc", numeroOc);
                i.putExtra("NumeroRecep", numeroRecep);
                i.putExtra("poHeaderId", id);
                startActivity(i);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDialogAceptarClick(DialogFragment dialog) {
        String tipo = dialog.getArguments().getString("tipo");
        if (tipo.equalsIgnoreCase("update")) {
            this.mPresenter.updateEntry(this.interfaceTransactionId, cantidad, lineLocationId);
        }
    }

    @Override
    public void onDialogCancelarClick(DialogFragment dialog) {

    }
}