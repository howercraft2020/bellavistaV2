package cl.clsoft.bave.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cl.clsoft.bave.R;
import cl.clsoft.bave.model.MtlCycleCountEntries;

public class AdapterItemCiclicoDetalle extends RecyclerView.Adapter<AdapterItemCiclicoDetalle.CiclicoDetalleViewHolder>{

    private static final String TAG = "AdapterCiclicoDetalle";
    private List<MtlCycleCountEntries> entries;

    public AdapterItemCiclicoDetalle(List<MtlCycleCountEntries> entries) { this.entries = entries;}

    @NonNull
    @Override
    public CiclicoDetalleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sigle, parent, false);
        AdapterItemCiclicoDetalle.CiclicoDetalleViewHolder ccvh = new AdapterItemCiclicoDetalle.CiclicoDetalleViewHolder(v);
        return ccvh;
    }

    @Override
    public void onBindViewHolder(@NonNull CiclicoDetalleViewHolder holder, int position) {
        Log.d(TAG, "AdapterItemCiclicoDetalle::onBindViewHolder");
        holder.onBind(this.entries.get(position));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Log.d(TAG, "AdapterItemCiclicoDetalle::onAttachedToRecyclerView");
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        if (entries != null) {
            return entries.size();
        }
        return 0;
    }


    public class CiclicoDetalleViewHolder extends RecyclerView.ViewHolder {

        private static final String TAG = "CiclicoDetalleViewHolder";
        private TextView codigoSigle;
        private TextView descripcionSigle;

        public CiclicoDetalleViewHolder(@NonNull View itemView) {
            super(itemView);
            this.codigoSigle = itemView.findViewById(R.id.codigoSigle);
            //this.descripcionSigle = itemView.findViewById(R.id.descripcionSigle);

        }

        public void onBind(MtlCycleCountEntries mtlCycleCountEntries) {

            this.codigoSigle.setText(mtlCycleCountEntries.getSegment1());
            //this.descripcionSigle.setText(mtlCycleCountEntries.getEntryStatusCode());
        }


    }
}
