package cl.clsoft.bave.view;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.ArrayList;
import java.util.List;


import cl.clsoft.bave.R;
import cl.clsoft.bave.model.MtlSerialNumbersInterface;

public class AdapterSeriesTrans extends RecyclerView.Adapter<AdapterSeriesTrans.SeriesTransViewHolder> {

    private static final String TAG = "AdapterTransSubinv";
    private List<String> series;
    private List<String> seriesSeleccionados = new ArrayList<>();

    public AdapterSeriesTrans(List<String> series) {
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

    @NonNull
    @Override
    public SeriesTransViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_serial_trans, parent, false);
        SeriesTransViewHolder stvh = new SeriesTransViewHolder(v);
        return stvh;
    }

    @Override
    public void onBindViewHolder(@NonNull SeriesTransViewHolder holder, int position) {
        Log.d(TAG, "AdapterSeriesTrans::onBindViewHolder");
        holder.onBind(this.series.get(position));
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        Log.d(TAG, "AdapterSeriesTrans::onAttachedToRecyclerView");
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        if (series != null) {
            return series.size();
        }
        return 0;
    }

    public class SeriesTransViewHolder extends ViewHolder{

        private static final String TAG = "SeriesTransViewHolder";
        private TextView serie;

        public SeriesTransViewHolder(@NonNull View itemView) {
            super(itemView);
            this.serie = itemView.findViewById(R.id.serieTextView);
        }

        public void onBind(String serie){
            this.serie.setText(serie);
        }
    }


}
