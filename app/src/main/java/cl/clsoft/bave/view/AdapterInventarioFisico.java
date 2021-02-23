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
import cl.clsoft.bave.model.MtlPhysicalInventories;

public class AdapterInventarioFisico extends RecyclerView.Adapter<AdapterInventarioFisico.InventarioFisicoViewHolder>{

    private static final String TAG = "AdapterInventarioFisico";
    private List<MtlPhysicalInventories> conteos;
    private RecyclerViewClickListener listener;


    public AdapterInventarioFisico(List<MtlPhysicalInventories> conteosFisicos, RecyclerViewClickListener listener) {
        this.conteos = conteosFisicos;
        this.listener = listener;
    }


    @NonNull
    @Override
    public InventarioFisicoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "AdapterInventarioFisico::onCreateViewHolder");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inventario, parent, false);
        InventarioFisicoViewHolder cfvh = new InventarioFisicoViewHolder(v);
        return cfvh;
    }


    @Override
    public void onBindViewHolder(@NonNull InventarioFisicoViewHolder holder, int position) {
        Log.d(TAG, "AdapterInventarioFisico::onBindViewHolder");
        holder.onBind(this.conteos.get(position));
    }


    @Override
    public int getItemCount() {
        if (this.conteos != null) {
            return this.conteos.size();
        }
        return 0;
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }



    public class InventarioFisicoViewHolder extends ViewHolder implements View.OnClickListener{

        private static final String TAG = "ConteoFisicoViewHolder";
        private TextView contadorFisicoId;
        private TextView contadorFisicoNombre;
        private TextView contadorFisicoFechaCreacion;

        public InventarioFisicoViewHolder(@NonNull View itemView) {
            super(itemView);
            this.contadorFisicoId = itemView.findViewById(R.id.idTextView);
            this.contadorFisicoNombre = itemView.findViewById(R.id.nombreTextView);
            this.contadorFisicoFechaCreacion = itemView.findViewById(R.id.fechaCreacionTextView);

            itemView.setOnClickListener(this);
        }
        public void onBind(MtlPhysicalInventories mtlPhysicalInventories){

            this.contadorFisicoId.setText(mtlPhysicalInventories.getPhysicalInventoryId().toString());
            this.contadorFisicoNombre.setText(mtlPhysicalInventories.getPhysicalInventoryName());
            this.contadorFisicoFechaCreacion.setText(mtlPhysicalInventories.getCreationDate());
        }

        @Override
        public void onClick(View view) {
            listener.onClick(itemView, getAdapterPosition());
        }
    }
}
