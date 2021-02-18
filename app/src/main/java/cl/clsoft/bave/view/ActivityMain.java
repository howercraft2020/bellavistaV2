package cl.clsoft.bave.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bave.ConexionSQLiteHelper;
import com.example.bave.utilidades.Utilidades;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import cl.clsoft.bave.Entrega;
import cl.clsoft.bave.R;
import cl.clsoft.bave.Recepcion;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.presenter.MainPresenter;
import cl.clsoft.bave.service.impl.BaveServiceImpl;

public class ActivityMain extends BaseActivity<MainPresenter> {

    Spinner comboAcciones;
    Button ingresar;
    TextView txtCargaDatos;

    int PERMISSION_ALL = 100;
    String[] PERMISSIONS = {
            android.Manifest.permission.READ_PHONE_STATE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
    };


    @NonNull
    @Override
    protected MainPresenter createPresenter(@NonNull Context context) {
        return new MainPresenter(this, new BaveServiceImpl());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind Controls
        this.llProgressBar = findViewById(R.id.llProgressBar);
        comboAcciones = (Spinner) findViewById(R.id.idSpinnerAcciones);
        ingresar = (Button) findViewById(R.id.btnIngresar);
        txtCargaDatos = (TextView) findViewById(R.id.txtCargaDatos);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.combo_acciones,
                R.layout.estilo_spinner);

        comboAcciones.setAdapter(adapter);
        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String seleccion = comboAcciones.getSelectedItem().toString();
                if (seleccion.equals("Recepcion")){
                    Intent intent = new Intent(ActivityMain.this, ActivityRecepcionOc.class);
                    intent.putExtra("paso", "1");
                    startActivity(intent);
                }
                if (seleccion.equals("Entrega")){
                    Intent intent = new Intent(ActivityMain.this, Entrega.class);
                    intent.putExtra("paso", "2");
                    startActivity(intent);
                }
                if (seleccion.equals("Conteo Cíclico")){
                    Intent intent = new Intent(ActivityMain.this, ActivityCiclicos.class);
                    startActivity(intent);
                }
                if (seleccion.equals("Inventario Físico")){
                    Intent intent = new Intent(ActivityMain.this, ActivityFisicos.class);
                    startActivity(intent);
                }
            }
        });

        //Check permisos
        if (!checkPermission()) {
            requestPermission();
        }
        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
        mPresenter.cargaArchivos();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==100){
            if(grantResults.length==2 && grantResults[0]==PackageManager.PERMISSION_GRANTED && grantResults[1]==PackageManager.PERMISSION_GRANTED){
                EliminarTablas();
                //Consultar();
            }
        }
    }

    //Metodo Eliminar Tablas
    public void EliminarTablas() {

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd",null,1);
        SQLiteDatabase db = conn.getWritableDatabase();
        db.delete(Utilidades.TABLA_ORGANIZACION, null, null);
        db.delete(Utilidades.TABLA_SUBINVENTARIO, null, null);
        db.delete(Utilidades.TABLA_LOCALIZADOR, null, null);
        db.close();

    }


    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkPermission(){
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION);
        if (result == PackageManager.PERMISSION_GRANTED){
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(this,new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CALL_PHONE},PERMISSION_ALL
        );
    }

}