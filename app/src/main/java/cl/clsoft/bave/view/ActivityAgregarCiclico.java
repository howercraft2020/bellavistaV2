package cl.clsoft.bave.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import cl.clsoft.bave.R;

public class ActivityAgregarCiclico extends AppCompatActivity {

    private EditText subinvEditText, localizadorEditText, cantidadCiclicoEditText;
    private Spinner loteSpinner, serieSpinner;
    private Button confirmarButton;
    public String subinvTxt, localizadorTxt, cantidadTxt, loteTxt, serieTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_ciclico);

        //Busco los datos en pantalla y los asigno a variables locales
        subinvEditText = findViewById(R.id.subinvEditText);
        localizadorEditText = findViewById(R.id.localizadorEditText);
        loteSpinner = findViewById(R.id.loteSpinner);
        serieSpinner = findViewById(R.id.serieSpinner);
        cantidadCiclicoEditText = findViewById(R.id.cantidadCiclicoEditText);
        confirmarButton = findViewById(R.id.confirmarButton);

        //Para las variables Spinner(Listas desplegables) desconozco las alternativas, por lo que creo variables de ejemplo
        String[] opcionesLote = {"lote 1", "lote 2", "lote 3"};
        String[] opcionesSerie = {"serie 1", "serie 2", "serie 3"};

        //Permite la eleccion de las opciones anteriormente declaradas
        ArrayAdapter<String> adapterLote = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opcionesLote);
        loteSpinner.setAdapter(adapterLote);
        ArrayAdapter<String> adapterSerie = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opcionesSerie);
        serieSpinner.setAdapter(adapterSerie);

        //Se crea un botón para poder guardar la informacion entregada en pantalla
        confirmarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Si algun EditText está vacío (a excepcion de serie) se muestra un error por pantalla
                if(subinvEditText == null || localizadorEditText == null || cantidadCiclicoEditText == null || loteSpinner == null /*||
                        serieSpinner == null*/){
                    Toast.makeText(getApplicationContext(),"Debe rellenar todos los campos", Toast.LENGTH_LONG).show();
                }
                subinvTxt = subinvEditText.getText().toString();
                localizadorTxt = localizadorEditText.getText().toString();
                loteTxt = loteSpinner.toString();
                serieTxt = serieSpinner.toString();
                cantidadTxt = cantidadCiclicoEditText.getText().toString();
                Toast.makeText(getApplicationContext(),"Cantidad Grabada", Toast.LENGTH_LONG).show();
                //finish();
            }
        });

    }
}