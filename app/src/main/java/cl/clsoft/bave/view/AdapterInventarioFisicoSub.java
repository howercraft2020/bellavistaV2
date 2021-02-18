package cl.clsoft.bave.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.List;

import cl.clsoft.bave.R;
import cl.clsoft.bave.model.MtlPhysicalInventoryTags;
import cl.clsoft.bave.model.MtlPhysicalSubinventories;

public class AdapterInventarioFisicoSub extends RecyclerView.Adapter<AdapterInventarioFisicoSub.InventarioFisicoSubViewHolder>{

    private static final String TAG = "AdapterDetalleInventarioFisico";
    private List<MtlPhysicalSubinventories> subinventories;
    private AdapterInventarioFisicoSub.RecyclerViewClickListener listener;

    public AdapterInventarioFisicoSub(List<MtlPhysicalSubinventories> subinventories){
        this.subinventories = subinventories;
        //this.listener = listener;
    }

    @NonNull
    @Override
    public InventarioFisicoSubViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity_fisico_sub, parent, false);
        InventarioFisicoSubViewHolder Ifsvh = new InventarioFisicoSubViewHolder(v);

        return Ifsvh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterInventarioFisicoSub.InventarioFisicoSubViewHolder holder, int position) {
        holder.onBind(this.subinventories.get(position));
    }


    @Override
    public int getItemCount() {
        if(this.subinventories != null){
            return this.subinventories.size();
        }
        return 0;
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }

    public class InventarioFisicoSubViewHolder extends ViewHolder implements View.OnClickListener {

        private TextView subinventarioId;


        public InventarioFisicoSubViewHolder(@NonNull View itemView) {

            super(itemView);

            this.subinventarioId = itemView.findViewById(R.id.subinventarioText);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }

        public void onBind(MtlPhysicalSubinventories mtlPhysicalSubinventories) {
            if(mtlPhysicalSubinventories != null){

                    this.subinventarioId.setText("Subinventario: " + mtlPhysicalSubinventories.getSubinventory());
            }

        }

        
    }

}



