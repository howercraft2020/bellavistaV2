package cl.clsoft.bave.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cl.clsoft.bave.R;
import cl.clsoft.bave.model.MtlCycleCountEntries;

public class AdapterItemSerie extends RecyclerView.Adapter<AdapterItemSerie.SerieViewHolder> {

    private static final String TAG = "AdapterItemSerie";
    private List<String> series;
    private List<String> seriesSeleccionados = new ArrayList<>();


    public AdapterItemSerie() {
        this.series = new ArrayList<>();
    }

    public AdapterItemSerie(List<String> series) {
        this.series = series;
    }

    public List<String> getSeries() {
        return this.series;
    }

    public List<String> getSeriesSeleccionados() {
        return this.seriesSeleccionados;
    }

    public void addSerie(String serie) {
        this.series.add(serie);
        this.notifyItemRangeInserted(0, series.size() - 1);
        this.notifyDataSetChanged();
    }

    public void deleteSeleccionados() {
        if (this.seriesSeleccionados.size() > 0) {
            for (String serieSel : this.seriesSeleccionados) {
                Iterator<String> iterator = this.series.iterator();
                while(iterator.hasNext()) {
                    String serie = iterator.next();
                    if (serie.equalsIgnoreCase(serieSel)) {
                        iterator.remove();
                        Log.d(TAG, "ActivoViewHolder::onCheckedChanged::remove id " + serie);
                    }

                }
            }
            seriesSeleccionados = new ArrayList<>();
            this.notifyItemRangeInserted(0, series.size() - 1);
            this.notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public AdapterItemSerie.SerieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_serie, parent, false);
        AdapterItemSerie.SerieViewHolder vh = new AdapterItemSerie.SerieViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterItemSerie.SerieViewHolder holder, int position) {
        holder.onBind(this.series.get(position));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Log.d(TAG, "AdapterItemEntry::onAttachedToRecyclerView");
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        if (series != null) {
            return series.size();
        }
        return 0;
    }

    public class SerieViewHolder extends RecyclerView.ViewHolder {

        private static final String TAG = "SerieViewHolder";
        private CheckBox activoCheckBox;
        private TextView textSerie;

        public SerieViewHolder(@NonNull View itemView) {
            super(itemView);
            this.activoCheckBox = itemView.findViewById(R.id.activoCheckBox);
            this.textSerie = itemView.findViewById(R.id.textSerie);

            this.activoCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        int adapterPosition = getAdapterPosition();
                        Log.d(TAG, "ActivoViewHolder::onCheckedChanged::position " + adapterPosition);
                        Log.d(TAG, "ActivoViewHolder::onCheckedChanged::id " + series.get(adapterPosition));
                        seriesSeleccionados.add(series.get(adapterPosition));
                    } else {
                        Iterator<String> iterator = seriesSeleccionados.iterator();
                        int adapterPosition = getAdapterPosition();
                        while(iterator.hasNext()) {
                            String serie = iterator.next();
                            if (serie.equalsIgnoreCase(series.get(adapterPosition))) {
                                iterator.remove();
                                Log.d(TAG, "ActivoViewHolder::onCheckedChanged::remove id " + serie);
                            }
                        }
                    }
                }
            });

        }

        public void onBind(String serie) {
            Log.d(TAG, "onBind");
            Log.d(TAG, "onBind::serie: " + serie);
            this.activoCheckBox.setChecked(false);
            this.textSerie.setText(serie);
        }
    }
}
