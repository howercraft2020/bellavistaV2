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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subinventario, parent, false);
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

        private TextView textSubinventario;
        private TextView textDescription;

        public InventarioFisicoSubViewHolder(@NonNull View itemView) {

            super(itemView);

            this.textSubinventario = itemView.findViewById(R.id.textSubinventario);
            this.textDescription = itemView.findViewById(R.id.textDescription);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }

        public void onBind(MtlPhysicalSubinventories mtlPhysicalSubinventories) {
            if(mtlPhysicalSubinventories != null){
                    this.textSubinventario.setText(mtlPhysicalSubinventories.getSubinventory());
                    this.textDescription.setText(mtlPhysicalSubinventories.getSubinventoryDescription());
            }

        }

        
    }

}



