package cl.clsoft.bave.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.presenter.SigleSearchPresenter;
import cl.clsoft.bave.service.impl.InventarioFisicoService;
import cl.clsoft.bave.task.AppTaskExecutor;

public class ActivitySigleSearch extends BaseActivity<SigleSearchPresenter> {

    // Variables
    private String TAG = "SigleSearch";
    private String tipo = "";
    private Long shipmentHeaderId;
    private Long countHeaderId;
    private Long locatorId;
    List<MtlSystemItems> items;

    // Controls
    private TextInputEditText inputTexto;
    private RecyclerView recyclerViewSigles;

    @NonNull
    @Override
    protected SigleSearchPresenter createPresenter(@NonNull Context context) {
        return new SigleSearchPresenter(this, new AppTaskExecutor(this), new InventarioFisicoService());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigle_search);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // bind controls
        this.llProgressBar = findViewById(R.id.llProgressBar);
        this.inputTexto = findViewById(R.id.inputTexto);
        this.recyclerViewSigles = findViewById(R.id.recyclerViewSigles);

        // set controls
        this.tipo = this.getIntent().getStringExtra("Tipo");
        this.shipmentHeaderId = this.getIntent().getLongExtra("ShipmentHeaderId", 0);
        this.countHeaderId = this.getIntent().getLongExtra("CountHeaderId", 0);
        this.locatorId = this.getIntent().getLongExtra("LocatorId", 0);
        this.inputTexto.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
        this.inputTexto.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent event)
            {
                Log.d(TAG, "onEditorAction: " + textView.getText());
                Log.d(TAG, "actionId: " + actionId);
                boolean action = false;
                action = true;
                if (actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER))
                {
                    if (textView.getText() != null && !textView.getText().toString().isEmpty()) {
                        String pattern = "%" + textView.getText().toString().toUpperCase() + "%";
                        if (tipo.equalsIgnoreCase("E"))
                            mPresenter.getItemsEntrega(pattern, shipmentHeaderId);
                        else if (tipo.equalsIgnoreCase("EO"))
                            mPresenter.getItemsEntregaOrganizacion(pattern, shipmentHeaderId);
                        else if (tipo.equalsIgnoreCase("C"))
                            mPresenter.getItemsCiclico(pattern,countHeaderId, locatorId);
                        else
                            mPresenter.getItems(pattern);
                    }
                    action = true;
                }
                return action;
            }
        });

        final GestureDetector mGestureDetector = new GestureDetector(ActivitySigleSearch.this, new GestureDetector.SimpleOnGestureListener() {
            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
        recyclerViewSigles.setHasFixedSize(true);
        recyclerViewSigles.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerViewSigles.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {
            }

            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                try {
                    View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                    if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {
                        int position = recyclerView.getChildAdapterPosition(child);
                        Intent data = new Intent();
                        data.putExtra("InventoryItemId",items.get(position).getInventoryItemId());
                        data.putExtra("Segment1",items.get(position).getSegment1());
                        data.putExtra("PrimaryUomCode",items.get(position).getPrimaryUomCode());
                        data.putExtra("LotControlCode",items.get(position).getLotControlCode());
                        data.putExtra("ShelfLifeCode",items.get(position).getShelfLifeCode());
                        data.putExtra("SerialNumberControlCode",items.get(position).getSerialNumberControlCode());
                        setResult(RESULT_OK,data);
                        finish();
                        return true;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG,"onOptionsItemSelected");
        Log.d(TAG,"item.getItemId(): " + item.getItemId());
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.d(TAG,"home");
                Intent data = new Intent();
                setResult(RESULT_CANCELED,data);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void fillItem(List<MtlSystemItems> items) {
        this.items = items;
        AdapterItemSigle adapter = new AdapterItemSigle(items);
        this.recyclerViewSigles.setAdapter(adapter);
    }
}
