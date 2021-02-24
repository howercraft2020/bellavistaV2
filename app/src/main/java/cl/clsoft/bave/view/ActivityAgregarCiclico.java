package cl.clsoft.bave.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import cl.clsoft.bave.R;

public class ActivityAgregarCiclico extends AppCompatActivity {

    private EditText subinvEditText, localizadorEditText, cantidadCiclicoEditText;
    private Spinner loteSpinner, serieSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_ciclico);

        subinvEditText = findViewById(R.id.subinvEditText);
        localizadorEditText = findViewById(R.id.localizadorEditText);
        cantidadCiclicoEditText = findViewById(R.id.cantidadCiclicoEditText);
        loteSpinner = findViewById(R.id.loteSpinner);
        serieSpinner = findViewById(R.id.serieSpinner);

        String[] opcionesLote = {"lote 1", "lote 2", "lote 3"};
        String[] opcionesSerie = {"serie 1", "serie 2", "serie 3"};

        ArrayAdapter<String> adapterLote = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opcionesLote);
        loteSpinner.setAdapter(adapterLote);
        ArrayAdapter<String> adapterSerie = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opcionesSerie);
        serieSpinner.setAdapter(adapterSerie);

    }
}