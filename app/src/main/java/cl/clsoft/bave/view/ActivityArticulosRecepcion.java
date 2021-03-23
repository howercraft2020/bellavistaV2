package cl.clsoft.bave.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.model.RcvTransactionsInterface;
import cl.clsoft.bave.presenter.ArticulosRecepcionPresenter;
import cl.clsoft.bave.service.impl.RecepcionOcService;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class ActivityArticulosRecepcion extends BaseActivity<ArticulosRecepcionPresenter> implements ConfirmationDialog.ConfirmationDialogListener {

    // Variables
    private static final String TAG = "ActivityArticulosRecep";
    private List<RcvTransactionsInterface> articulos;
    private TextView segment1;
    private TextView receiptNum;
    private TextView poHeaderId;
    private TextInputLayout layoutComentario;
    private AutoCompleteTextView textComentario;
    private Long interfaceHeaderId;
    private Long groupId;
    String numeroOc;
    Long numeroRecep;
    String id;

    //Controls
    private RecyclerView recyclerViewArticulosRecepcion;
    private AdapterItemArticulosRecepcion adapter;
    private SweetAlertDialog dialog;

    @NonNull
    @Override
    protected ArticulosRecepcionPresenter createPresenter(@NonNull Context context) {
        return new ArticulosRecepcionPresenter(this, new RecepcionOcService());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_articulos_recepcion, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()){
            case R.id.action_more:
                //Log.d(TAG, "Agregar Recepcion");
                Intent i = new Intent(this, ActivityAgregarRecepcion.class);
                i.putExtra("numeroOc", numeroOc);
                i.putExtra("NumeroRecep", numeroRecep);
                i.putExtra("poHeaderId", id);

                startActivity(i);
                this.finish();
                return true;
            case R.id.action_create_file:
                Log.d(TAG, "cerrar");
                ConfirmationDialog dialogClose = ConfirmationDialog.newInstance("Esta seguro de cerrar la recepción?", "Confirmación", "close");
                dialogClose.show(getSupportFragmentManager(), "closeEntregaConfirm");
                return true;
            case android.R.id.home:
                Intent imain = new Intent(this, ActivityRecepcionOc.class);
                startActivity(imain);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articulos_recepcion);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.segment1 = (TextView) findViewById(R.id.segment1);
        this.receiptNum = (TextView) findViewById(R.id.receiptNum);
        this.poHeaderId = (TextView) findViewById(R.id.poHeaderId);
        this.layoutComentario = findViewById(R.id.layoutComentario);
        this.textComentario = findViewById(R.id.textComentario);

        numeroOc = getIntent().getStringExtra("numeroOc");
        numeroRecep = getIntent().getLongExtra("NumeroRecep",0);
        id = getIntent().getStringExtra("poHeaderId");

        interfaceHeaderId = Long.parseLong(id+numeroRecep);

        segment1.setText(numeroOc);
        receiptNum.setText(numeroRecep.toString());
        poHeaderId.setText(id);

        //Bind Controls
        this.llProgressBar = findViewById(R.id.llProgressBar);
        this.recyclerViewArticulosRecepcion = findViewById(R.id.recyclerViewArticulosRecepcion);

        final GestureDetector mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

        // Set Controls
        this.recyclerViewArticulosRecepcion.setHasFixedSize(true);
        this.recyclerViewArticulosRecepcion.setLayoutManager(new LinearLayoutManager(this));

         this.articulos = mPresenter.getAllArticulos(interfaceHeaderId);
        this.adapter = new AdapterItemArticulosRecepcion(articulos);
        this.recyclerViewArticulosRecepcion.setAdapter(this.adapter);

        this.recyclerViewArticulosRecepcion.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
                try{
                    View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                    if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {
                        int position = recyclerView.getChildAdapterPosition(child);
                        Intent i = new Intent(getApplicationContext(), ActivityRecepcionArticuloDetalle.class);
                        i.putExtra("interfaceTransactionId", articulos.get(position).getInterfaceTransactionId());
                        i.putExtra("cantidad", articulos.get(position).getQuantity());
                        i.putExtra("lineLocationId", articulos.get(position).getPoLineLocation());
                        i.putExtra("numeroOc", numeroOc);
                        i.putExtra("NumeroRecep", numeroRecep);
                        i.putExtra("id", id);
                        startActivity(i);
                        finish();
                        return true;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });



    }

    @Override
    public void onDialogAceptarClick(DialogFragment dialog) {
        String tipo = dialog.getArguments().getString("tipo");
        String comentario = textComentario.getText().toString();
        String groupId = new SimpleDateFormat("ddMMyyyyHHmmss", Locale.getDefault()).format(new Date());

        if (tipo.equalsIgnoreCase("close")) {
            mPresenter.crearArchivo(interfaceHeaderId,numeroOc,numeroRecep,Long.parseLong(id), comentario, Long.parseLong(groupId));
        }
    }

    @Override
    public void onDialogCancelarClick(DialogFragment dialog) {

    }

    public void resultadoOkCerrarRecepcion() {
        dialog = new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE);
        dialog.setTitleText("Éxito")
                .setContentText("Cierre exitoso")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        Intent i = new Intent(getApplicationContext(), ActivityRecepcionOc.class);
                        startActivity(i);
                        finish();
                    }
                })
                .show();
    }

}