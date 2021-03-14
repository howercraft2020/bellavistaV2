package cl.clsoft.bave.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.model.ConsultaItem;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.presenter.ConsultaItemPresenter;
import cl.clsoft.bave.service.impl.ConsultaServiceImpl;
import cl.clsoft.bave.task.AppTaskExecutor;

public class ActivityConsultaItem extends BaseActivity<ConsultaItemPresenter> {

    // Variables
    private String TAG = "ConsultaItem";
    private int LAUNCH_SEARCHSINGLE_ACTIVITY = 2;
    private String currentSegment = "";
    private String segment;
    private Long inventoryItemId;

    // Controls
    private TextInputLayout layoutSigle;
    private TextInputEditText textSigle;
    private ImageView iconSearch;
    private RecyclerView recyclerViewItems;

    @NonNull
    @Override
    protected ConsultaItemPresenter createPresenter(@NonNull Context context) {
        return new ConsultaItemPresenter(this, new AppTaskExecutor(this), new ConsultaServiceImpl());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Instance Layout.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_item);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Bind Controls
        this.llProgressBar = findViewById(R.id.llProgressBar);
        this.layoutSigle = findViewById(R.id.layoutSerie);
        this.textSigle = findViewById(R.id.textSigle);
        this.iconSearch = findViewById(R.id.iconSearch);
        this.recyclerViewItems = findViewById(R.id.recyclerViewItems);

        // Set Controls

        this.textSigle.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
                Log.d(TAG, "onEditorAction: " + textView.getText());
                Log.d(TAG, "actionId: " + actionId);
                boolean action = false;
                action = true;
                if (actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    if (textView.getText() != null && !textView.getText().toString().isEmpty()) {
                        segment = textView.getText().toString();
                        if (!segment.equalsIgnoreCase(currentSegment)) {
                            currentSegment = segment;
                            MtlSystemItems item = mPresenter.getMtlSystemItemsBySegment(segment);
                            if (item != null) {
                                inventoryItemId = item.getInventoryItemId();
                                fillStock(inventoryItemId);
                            } else {
                                textSigle.setText("");
                                currentSegment = "";
                            }
                        }
                    }
                    action = true;
                }
                return action;
            }
        });

        this.iconSearch.setOnClickListener(v -> {
            Intent i = new Intent(this, ActivitySigleSearch.class);
            startActivityForResult(i, LAUNCH_SEARCHSINGLE_ACTIVITY);
        });
        this.recyclerViewItems.setHasFixedSize(true);
        this.recyclerViewItems.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.d(TAG, "home");
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LAUNCH_SEARCHSINGLE_ACTIVITY) {
            if (resultCode == Activity.RESULT_OK) {
                segment = data.getStringExtra("Segment1");
                inventoryItemId = data.getLongExtra("InventoryItemId", 0);
                this.textSigle.setText(segment);
                this.fillStock(inventoryItemId);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
            }
        }
    }

    private void fillStock(Long inventoryItemId) {
        List<ConsultaItem> items = mPresenter.getAllByItem(inventoryItemId);
        AdapterItemConsultaSigle adapterItemConsultaSigle = new AdapterItemConsultaSigle(items);
        this.recyclerViewItems.setAdapter(adapterItemConsultaSigle);
        if (items.size() == 0) {
            showWarning("No se encontró Stock para " + segment);
        }
        this.textSigle.setText("");
    }

}
