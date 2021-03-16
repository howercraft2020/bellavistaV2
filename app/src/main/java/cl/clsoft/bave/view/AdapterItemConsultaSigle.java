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
import cl.clsoft.bave.model.ConsultaItem;
import cl.clsoft.bave.model.MtlCycleCountHeaders;

public class AdapterItemConsultaSigle extends RecyclerView.Adapter<AdapterItemConsultaSigle.ConsultaSigleViewHolder> {

    private static final String TAG = "ConsultaSigle";
    private List<ConsultaItem> items;

    public AdapterItemConsultaSigle(List<ConsultaItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public AdapterItemConsultaSigle.ConsultaSigleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "AdapterItemConsultaSigle::onCreateViewHolder");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_consulta_sigle, parent, false);
        AdapterItemConsultaSigle.ConsultaSigleViewHolder vh = new AdapterItemConsultaSigle.ConsultaSigleViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterItemConsultaSigle.ConsultaSigleViewHolder holder, int position) {
        Log.d(TAG, "AdapterItemConsultaSigle::onBindViewHolder");
        holder.onBind(this.items.get(position));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Log.d(TAG, "AdapterItemConsultaSigle::onAttachedToRecyclerView");
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "AdapterItemConsultaSigle::getItemCount");
        if (items != null && items.size() > 0) {
            Log.d(TAG, "AdapterItemConsultaSigle::return " + items.size());
            return items.size();
        } else {
            Log.d(TAG, "AdapterItemConsultaSigle::return 0 (no conteos)");
            return 0;
        }
    }

    public class ConsultaSigleViewHolder extends RecyclerView.ViewHolder {

        private static final String TAG = "ConsultaSigleViewHolder";
        private TextView textSubinventoryCode;
        private TextView textLocatorCode;
        private TextView textCodigoSigle;
        private TextView textDescription;
        private TextView textNumeroLote;
        private TextView textNumeroSerie;
        private TextView textCantidad;
        private TextView textUdm;

        public ConsultaSigleViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textSubinventoryCode = itemView.findViewById(R.id.textSubinventoryCode);
            this.textLocatorCode = itemView.findViewById(R.id.textLocatorCode);
            this.textCodigoSigle = itemView.findViewById(R.id.textCodigoSigle);
            this.textDescription = itemView.findViewById(R.id.textDescription);
            this.textNumeroLote = itemView.findViewById(R.id.textNumeroLote);
            this.textNumeroSerie = itemView.findViewById(R.id.textNumeroSerie);
            this.textCantidad = itemView.findViewById(R.id.textCantidad);
            this.textUdm = itemView.findViewById(R.id.textUdm);
        }

        public void onBind(ConsultaItem consultaItem) {
            if (consultaItem != null) {
                this.textSubinventoryCode.setText(consultaItem.getSubinventoryCode());
                this.textLocatorCode.setText(consultaItem.getLocatorCode());
                this.textCodigoSigle.setText(consultaItem.getSigle());
                this.textDescription.setText(consultaItem.getDescription());
                this.textNumeroLote.setText(consultaItem.getLotNumber());
                this.textNumeroSerie.setText(consultaItem.getSerialNumber());
                this.textCantidad.setText(consultaItem.getCantidad().toString());
                this.textUdm.setText(consultaItem.getUnidad());
            }
        }

    }
}
