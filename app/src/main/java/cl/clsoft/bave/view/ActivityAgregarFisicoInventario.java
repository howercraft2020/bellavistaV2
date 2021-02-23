package cl.clsoft.bave.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.presenter.AgregarFisicoInventarioPresenter;
import cl.clsoft.bave.service.impl.InventarioFisicoService;

public class ActivityAgregarFisicoInventario extends BaseActivity<AgregarFisicoInventarioPresenter> {
    private String TAG = "ActivityAgregarFisicoInventario";
    private Long inventarioId;
    private String subinventarioId;


    @NonNull
    @Override
    protected AgregarFisicoInventarioPresenter createPresenter(@NonNull Context context) {
        return new AgregarFisicoInventarioPresenter(this, new InventarioFisicoService());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_activity_agregar_fisico_inventario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_fisico_inventario);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //set controls
        inventarioId = this.getIntent().getLongExtra("InventarioId", 0);
        subinventarioId = this.getIntent().getStringExtra("SubinventarioId");

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.guardarInventario:
                this.finish();
                return true;
            case android.R.id.home:
                if(inventarioId != null && subinventarioId != null){
                    Log.d(TAG, "Agregar inventario tag");
                    Intent i = new Intent(this, ActivityFisicoDetalle.class);
                    i.putExtra("InventarioId", inventarioId);
                    i.putExtra("SubinventarioId", subinventarioId);
                    startActivity(i);
                    this.finish();
                    return true;
                }else if(inventarioId != null && subinventarioId == null){
                    Log.d(TAG, "Agregar inventario subinventario");
                    Intent i = new Intent(this, ActivityFisicoSub.class);
                    i.putExtra("InventarioId", inventarioId);
                    startActivity(i);
                    this.finish();
                    return true;
                }

            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
