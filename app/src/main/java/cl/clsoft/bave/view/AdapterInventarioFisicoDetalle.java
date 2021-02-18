package cl.clsoft.bave.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cl.clsoft.bave.model.MtlPhysicalInventoryTags;
import cl.clsoft.bave.R;

public class AdapterInventarioFisicoDetalle extends RecyclerView.Adapter<AdapterInventarioFisicoDetalle.InventarioFisicoDetalleViewHolder>{
    private static final String TAG = "AdapterInventarioFisicoDetalle";
    private List<MtlPhysicalInventoryTags> tags;

    public AdapterInventarioFisicoDetalle(List<MtlPhysicalInventoryTags> tags){
        this.tags = tags;
    }

    @NonNull
    @Override
    public InventarioFisicoDetalleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity_inventario_detalle, parent, false);
        InventarioFisicoDetalleViewHolder Ifdvh = new InventarioFisicoDetalleViewHolder(v);

        return Ifdvh;
    }

    @Override
    public void onBindViewHolder(@NonNull InventarioFisicoDetalleViewHolder holder, int position) {
        holder.onBind(this.tags.get(position));
    }

    @Override
    public int getItemCount() {
        if(this.tags != null){
            return this.tags.size();
        }
        return 0;
    }

    public class InventarioFisicoDetalleViewHolder extends RecyclerView.ViewHolder{
        private TextView itemNumeroParte;
        private TextView itemSigle;
        private TextView itemSerie;
        private TextView itemLote;
        private TextView itemCantidad;
        private TextView itemStock;
        private TextView localizador;

        public InventarioFisicoDetalleViewHolder(@NonNull View itemView) {
            super(itemView);

            this.localizador = itemView.findViewById(R.id.localizadorEtiquetaItemDetalle);
            this.itemNumeroParte = itemView.findViewById(R.id.numeroParteEtiquetaItemDetalle);
            this.itemSigle = itemView.findViewById(R.id.sigleEtiquetaItemDetalle);
            this.itemSerie = itemView.findViewById(R.id.serieEtiquetaItemDetalle);
            this.itemLote = itemView.findViewById(R.id.loteEtiquetaItemDetalle);
            this.itemCantidad = itemView.findViewById(R.id.cantidadEtiquetaItemDetalle);
            this.itemStock = itemView.findViewById(R.id.stockEtiquetaItemDetalle);
        }

        public void onBind(MtlPhysicalInventoryTags mtlPhysicalInventoryTags) {

            this.localizador.setText(mtlPhysicalInventoryTags.getLocatorId().toString());
            this.itemNumeroParte.setText(mtlPhysicalInventoryTags.getDescription());
            this.itemSigle.setText(mtlPhysicalInventoryTags.getSegment1());
            this.itemSerie.setText(mtlPhysicalInventoryTags.getSerialNum());
            this.itemLote.setText(mtlPhysicalInventoryTags.getLotNumber());
            this.itemCantidad.setText(mtlPhysicalInventoryTags.getCount().toString());
            this.itemStock.setText(mtlPhysicalInventoryTags.getPrimaryUomCode());
        }
    }
}
