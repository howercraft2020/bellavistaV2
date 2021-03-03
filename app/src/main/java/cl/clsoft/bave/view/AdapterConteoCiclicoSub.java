package cl.clsoft.bave.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cl.clsoft.bave.R;
import cl.clsoft.bave.model.Subinventario;

public class AdapterConteoCiclicoSub extends RecyclerView.Adapter<AdapterConteoCiclicoSub.SubinventarioViewHolder> {

    private static final String TAG = "AdapterDetalleInventarioFisico";
    private List<Subinventario> subinventories;

    public AdapterConteoCiclicoSub(List<Subinventario> subinventories){
        this.subinventories = subinventories;
    }

    @NonNull
    @Override
    public AdapterConteoCiclicoSub.SubinventarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subinventario, parent, false);
        AdapterConteoCiclicoSub.SubinventarioViewHolder vh = new AdapterConteoCiclicoSub.SubinventarioViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterConteoCiclicoSub.SubinventarioViewHolder holder, int position) {
        holder.onBind(this.subinventories.get(position));
    }

    @Override
    public int getItemCount() {
        if(this.subinventories != null){
            return this.subinventories.size();
        }
        return 0;
    }

    public class SubinventarioViewHolder extends RecyclerView.ViewHolder {

        private TextView textSubinventario;
        private TextView textDescription;

        public SubinventarioViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textSubinventario = itemView.findViewById(R.id.textSubinventario);
            this.textDescription = itemView.findViewById(R.id.textDescription);
        }

        public void onBind(Subinventario subinventario) {
            if(subinventario != null){
                this.textSubinventario.setText(subinventario.getCodSubinventario());
                this.textDescription.setText(subinventario.getDescription());
            }

        }

    }
}
