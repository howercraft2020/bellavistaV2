package cl.clsoft.bave.view;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.List;


import cl.clsoft.bave.R;
import cl.clsoft.bave.model.MtlSerialNumbersInterface;

public class AdapterSeriesTrans extends RecyclerView.Adapter<AdapterSeriesTrans.SeriesTransViewHolder> {

    private static final String TAG = "AdapterTransSubinv";
    private List<MtlSerialNumbersInterface> series;

    public AdapterSeriesTrans(List<MtlSerialNumbersInterface> series) {
        this.series = series;
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
        Log.d(TAG, "AdapterSeriesTrans::getItemCount");
        if (series != null && series.size() > 0) {
            Log.d(TAG, "AdapterSeriesTrans::return " + series.size());
            return series.size();
        } else {
            Log.d(TAG, "AdapterSeriesTrans::return 0 (no series)");
            return 0;
        }
    }

    public class SeriesTransViewHolder extends ViewHolder{

        private static final String TAG = "SeriesTransViewHolder";
        private TextView serie;

        public SeriesTransViewHolder(@NonNull View itemView) {
            super(itemView);
            this.serie = itemView.findViewById(R.id.serieTextView);
        }

        public void onBind(MtlSerialNumbersInterface mtlSerialNumbersInterface){
            this.serie.setText(mtlSerialNumbersInterface.getFmSerialNumber());
        }
    }


}
