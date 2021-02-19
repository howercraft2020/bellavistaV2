package cl.clsoft.bave.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cl.clsoft.bave.R;
import cl.clsoft.bave.model.MtlCycleCountEntries;

public class AdapterItemSigleDetalle extends RecyclerView.Adapter<AdapterItemSigleDetalle.SigleDetalleViewHolder> {

    private static final String TAG = "AdapterSigleDetalle";
    private List<MtlCycleCountEntries> entries;

    public AdapterItemSigleDetalle(List<MtlCycleCountEntries> entries){this.entries = entries;}

    @NonNull
    @Override
    public SigleDetalleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_ciclico_sigle_detalle, parent, false);
        AdapterItemSigleDetalle.SigleDetalleViewHolder ccvh = new AdapterItemSigleDetalle.SigleDetalleViewHolder(v);
        return ccvh;
    }

    @Override
    public void onBindViewHolder(@NonNull SigleDetalleViewHolder holder, int position) {
        holder.onBind(this.entries.get(position));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Log.d(TAG, "AdapterItemSigleDetalle::onAttachedToRecyclerView");
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        if (entries != null) {
            return entries.size();
        }
        return 0;
    }

    public class SigleDetalleViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "SigleDetalleViewHolder";
        private TextView subinventarioSigle;
        private TextView localizadorSigle;
        private TextView loteSigle;
        private TextView numSerieSigle;
        private TextView cantidadSigle;
        private TextView UDMSigle;

        public SigleDetalleViewHolder(@NonNull View itemView) {
            super(itemView);
            this.subinventarioSigle = itemView.findViewById(R.id.subinventarioSigle);
            this.localizadorSigle = itemView.findViewById(R.id.localizadorSigle);
            this.loteSigle = itemView.findViewById(R.id.loteSigle);
            this.numSerieSigle = itemView.findViewById(R.id.numSerieSigle);
            this.cantidadSigle = itemView.findViewById(R.id.cantidadSigle);
            this.UDMSigle = itemView.findViewById(R.id.UDMSigle);
        }

        public void onBind(MtlCycleCountEntries mtlCycleCountEntries) {
            this.subinventarioSigle.setText(mtlCycleCountEntries.getSubinventory());
            this.localizadorSigle.setText(mtlCycleCountEntries.getLocatorId().toString());
            this.loteSigle.setText(mtlCycleCountEntries.getLotNumber());
            this.numSerieSigle.setText(mtlCycleCountEntries.getSerialNumber());
            //this.cantidadSigle.setText(mtlCycleCountEntries);
            this.UDMSigle.setText(mtlCycleCountEntries.getPrimaryUomCode());
        }

    }
}
