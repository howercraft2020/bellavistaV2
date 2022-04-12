package cl.clsoft.bave.view;

import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import cl.clsoft.bave.R;

public class ActivityPaso_1 extends AppCompatActivity {

    Spinner comboAcciones;
    Button ingresar;
    TextView txtCargaDatos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paso1);


        comboAcciones = (Spinner) findViewById(R.id.idSpinnerAcciones1);
        ingresar = (Button) findViewById(R.id.btnIngresar1);
        txtCargaDatos = (TextView) findViewById(R.id.txtCargaDatos1);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                        R.array.combo_acciones_Recepcion,
                R.layout.estilo_spinner);

        comboAcciones.setAdapter(adapter);
        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String seleccion = comboAcciones.getSelectedItem().toString();
                if (seleccion.equals("Paso Uno")) {
                    Intent intent = new Intent(ActivityPaso_1.this, ActivityRecepcionOc.class);
                    intent.putExtra("paso", "1");
                    startActivity(intent);
                }
                if (seleccion.equals("Paso Dos")) {
                    Intent intent = new Intent(ActivityPaso_1.this, ActivityEntregas.class);
                    startActivity(intent);
                }

            }
        });

    }
}



