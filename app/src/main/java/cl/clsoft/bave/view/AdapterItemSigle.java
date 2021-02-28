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
import cl.clsoft.bave.model.MtlSystemItems;

public class AdapterItemSigle extends RecyclerView.Adapter<AdapterItemSigle.SigleViewHolder> {

    private static final String TAG = "AdapterItemSigle";
    private List<MtlSystemItems> items;

    public AdapterItemSigle(List<MtlSystemItems> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public AdapterItemSigle.SigleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sigle, parent, false);
        AdapterItemSigle.SigleViewHolder vh = new AdapterItemSigle.SigleViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterItemSigle.SigleViewHolder holder, int position) {
        holder.onBind(this.items.get(position));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Log.d(TAG, "AdapterItemSigle::onAttachedToRecyclerView");
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        if (items != null) {
            return items.size();
        }
        return 0;
    }

    public class SigleViewHolder extends RecyclerView.ViewHolder {

        private static final String TAG = "SigleViewHolder";
        private TextView textSigle;
        private TextView textDescription;

        public SigleViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textSigle = itemView.findViewById(R.id.textSigle);
            this.textDescription = itemView.findViewById(R.id.textDescription);
        }

        public void onBind(MtlSystemItems mtlSystemItems) {
            if (mtlSystemItems != null) {
                this.textSigle.setText(mtlSystemItems.getSegment1());
                this.textDescription.setText(mtlSystemItems.getDescription());
            }
        }

    }
}
