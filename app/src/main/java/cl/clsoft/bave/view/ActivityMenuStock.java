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

public class ActivityMenuStock extends AppCompatActivity {

    Spinner comboAcciones;
    Button ingresar;
    TextView txtCargaDatos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_stock);


        comboAcciones = (Spinner) findViewById(R.id.idSpinnerAcciones2);
        ingresar = (Button) findViewById(R.id.btnIngresar2);
        txtCargaDatos = (TextView) findViewById(R.id.txtCargaDatos2);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.combo_acciones_Stock,
                R.layout.estilo_spinner);

        comboAcciones.setAdapter(adapter);
        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String seleccion = comboAcciones.getSelectedItem().toString();
                if (seleccion.equals("Consulta Stock Item")) {
                    Intent intent = new Intent(ActivityMenuStock.this, ActivityConsultaItem.class);
                    intent.putExtra("paso", "1");
                    startActivity(intent);
                }
                if (seleccion.equals("Consulta Stock Subinventario")) {
                    Intent intent = new Intent(ActivityMenuStock.this, ActivityConsultaSubinventario.class);
                    startActivity(intent);
                }
            }
        });

    }
}

