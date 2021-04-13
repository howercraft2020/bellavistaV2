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
import cl.clsoft.bave.model.ConsultaResumenItem;

public class AdapterItemConsultaResumenSigle extends RecyclerView.Adapter<AdapterItemConsultaResumenSigle.ConsultaResumenSigleViewHolder>  {

    private static final String TAG = "ConsultaResumenSigle";
    private List<ConsultaResumenItem> items;

    public AdapterItemConsultaResumenSigle(List<ConsultaResumenItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public AdapterItemConsultaResumenSigle.ConsultaResumenSigleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "AdapterItemConsultaResumenSigle::onCreateViewHolder");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_consulta_resumen_sigle, parent, false);
        AdapterItemConsultaResumenSigle.ConsultaResumenSigleViewHolder vh = new AdapterItemConsultaResumenSigle.ConsultaResumenSigleViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterItemConsultaResumenSigle.ConsultaResumenSigleViewHolder holder, int position) {
        Log.d(TAG, "AdapterItemConsultaResumenSigle::onBindViewHolder");
        holder.onBind(this.items.get(position));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Log.d(TAG, "AdapterItemConsultaResumenSigle::onAttachedToRecyclerView");
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "AdapterItemConsultaResumenSigle::getItemCount");
        if (items != null && items.size() > 0) {
            Log.d(TAG, "AdapterItemConsultaResumenSigle::return " + items.size());
            return items.size();
        } else {
            Log.d(TAG, "AdapterItemConsultaResumenSigle::return 0 (no conteos)");
            return 0;
        }
    }

    public class ConsultaResumenSigleViewHolder extends RecyclerView.ViewHolder {

        private static final String TAG = "ConsultaSigleViewHolder";
        private TextView textCodigoSigle;
        private TextView textDescription;
        private TextView textCantidad;
        private TextView textUdm;

        public ConsultaResumenSigleViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textCodigoSigle = itemView.findViewById(R.id.textCodigoSigle);
            this.textDescription = itemView.findViewById(R.id.textDescription);
            this.textCantidad = itemView.findViewById(R.id.textCantidad);
            this.textUdm = itemView.findViewById(R.id.textUdm);
        }

        public void onBind(ConsultaResumenItem consultaResumenItem) {
            if (consultaResumenItem != null) {
                this.textCodigoSigle.setText(consultaResumenItem.getSigle());
                this.textDescription.setText(consultaResumenItem.getDescription());
                this.textCantidad.setText(consultaResumenItem.getCantidad().toString());
                this.textUdm.setText(consultaResumenItem.getUnidad());
            }
        }

    }

}
