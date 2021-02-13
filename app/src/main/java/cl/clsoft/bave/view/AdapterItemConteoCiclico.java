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
import cl.clsoft.bave.model.MtlCycleCountHeaders;

public class AdapterItemConteoCiclico extends RecyclerView.Adapter<AdapterItemConteoCiclico.ConteoCiclicoViewHolder> {

    private static final String TAG = "AdapterConteoCiclico";
    private List<MtlCycleCountHeaders> conteos;
    private RecyclerViewClickListener listener;

    public AdapterItemConteoCiclico(List<MtlCycleCountHeaders> conteos, RecyclerViewClickListener listener) {
        this.conteos = conteos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ConteoCiclicoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_conteo_ciclico, parent, false);
        ConteoCiclicoViewHolder ccvh = new ConteoCiclicoViewHolder(v);
        return ccvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ConteoCiclicoViewHolder holder, int position) {
        Log.d(TAG, "AdapterItemConteoCiclico::onBindViewHolder");
        holder.onBind(this.conteos.get(position));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Log.d(TAG, "AdapterItemConteoCiclico::onAttachedToRecyclerView");
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "AdapterItemConteoCiclico::getItemCount");
        if (conteos != null && conteos.size() > 0) {
            Log.d(TAG, "AdapterItemConteoCiclico::return " + conteos.size());
            return conteos.size();
        } else {
            Log.d(TAG, "AdapterItemConteoCiclico::return 0 (no conteos)");
            return 0;
        }
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }

    public class ConteoCiclicoViewHolder extends ViewHolder{

        private static final String TAG = "ConteoCiclicoViewHolder";
        private TextView cycleCountHeaderId;
        private TextView cycleCountHeaderName;
        private TextView creationDate;

        public ConteoCiclicoViewHolder(@NonNull View itemView) {
            super(itemView);
            this.cycleCountHeaderId = itemView.findViewById(R.id.cycleCountHeaderId);
            this.cycleCountHeaderName = itemView.findViewById(R.id.cycleCountHeaderName);
            this.creationDate = itemView.findViewById(R.id.creationDate);

            //itemView.setOnClickListener(this);
        }

        public void onBind(MtlCycleCountHeaders mtlCycleCountHeaders) {
            this.cycleCountHeaderId.setText(mtlCycleCountHeaders.getCycleCountHeaderId().toString());
            this.cycleCountHeaderName.setText(mtlCycleCountHeaders.getCycleCountHeaderName());
            this.creationDate.setText(mtlCycleCountHeaders.getCreationDate());
        }


    }


}
