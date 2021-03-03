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

public class AdapterItemEntry extends RecyclerView.Adapter<AdapterItemEntry.EntryViewHolder> {

    private static final String TAG = "AdapterItemEntry";
    private List<MtlCycleCountEntries> entries;

    public AdapterItemEntry(List<MtlCycleCountEntries> entries) {
        this.entries = entries;
    }

    @NonNull
    @Override
    public AdapterItemEntry.EntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_entry, parent, false);
        AdapterItemEntry.EntryViewHolder vh = new AdapterItemEntry.EntryViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterItemEntry.EntryViewHolder holder, int position) {
        holder.onBind(this.entries.get(position));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Log.d(TAG, "AdapterItemEntry::onAttachedToRecyclerView");
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        if (entries != null) {
            return entries.size();
        }
        return 0;
    }

    public class EntryViewHolder extends RecyclerView.ViewHolder {

        private TextView textLocalizador;
        private TextView textNumeroParte;
        private TextView textCodigoSigle;
        private TextView textNumeroSerie;
        private TextView textNumeroLote;
        private TextView textCantidad;
        private TextView textUdm;

        public EntryViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textLocalizador = itemView.findViewById(R.id.textLocalizador);
            this.textNumeroParte = itemView.findViewById(R.id.textNumeroParte);
            this.textCodigoSigle = itemView.findViewById(R.id.textCodigoSigle);
            this.textNumeroSerie = itemView.findViewById(R.id.textNumeroSerie);
            this.textNumeroLote = itemView.findViewById(R.id.textNumeroLote);
            this.textCantidad = itemView.findViewById(R.id.textCantidad);
            this.textUdm = itemView.findViewById(R.id.textUdm);
        }

        public void onBind(MtlCycleCountEntries mtlCycleCountEntries) {

            if (mtlCycleCountEntries.getLocatorId() != null) {
                this.textLocalizador.setText(mtlCycleCountEntries.getLocatorCode());
            } else {
                this.textLocalizador.setText("");
            }
            this.textNumeroParte.setText(mtlCycleCountEntries.getDescription());
            this.textCodigoSigle.setText(mtlCycleCountEntries.getSegment1());
            this.textNumeroSerie.setText(mtlCycleCountEntries.getSerialNumber());
            this.textNumeroLote.setText(mtlCycleCountEntries.getLotNumber());
            this.textCantidad.setText(mtlCycleCountEntries.getCount().toString());
            this.textUdm.setText(mtlCycleCountEntries.getPrimaryUomCode());
        }

    }
}
