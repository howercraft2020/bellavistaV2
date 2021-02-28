package cl.clsoft.bave.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cl.clsoft.bave.R;
import cl.clsoft.bave.model.MtlPhysicalInventoryTags;

public class AdapterInventarioFisicoDetalleNoInventariado extends RecyclerView.Adapter<AdapterInventarioFisicoDetalleNoInventariado.InventarioFisicoDetalleViewHolder> {
    private static final String TAG = "AdapterInventarioFisicoDetalle";
    private List<MtlPhysicalInventoryTags> tags;

    public AdapterInventarioFisicoDetalleNoInventariado(List<MtlPhysicalInventoryTags> tags){
        this.tags = tags;
    }

    @NonNull
    @Override
    public AdapterInventarioFisicoDetalleNoInventariado.InventarioFisicoDetalleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tag, parent, false);
        AdapterInventarioFisicoDetalleNoInventariado.InventarioFisicoDetalleViewHolder Ifdvh = new AdapterInventarioFisicoDetalleNoInventariado.InventarioFisicoDetalleViewHolder(v);

        return Ifdvh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterInventarioFisicoDetalleNoInventariado.InventarioFisicoDetalleViewHolder holder, int position) {
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

        private TextView textLocalizador;
        private TextView textNumeroParte;
        private TextView textCodigoSigle;
        private TextView textNumeroSerie;
        private TextView textNumeroLote;
        private TextView textVencimiento;
        private TextView textCantidad;
        private TextView textUdm;

        public InventarioFisicoDetalleViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textLocalizador = itemView.findViewById(R.id.textLocalizador);
            this.textNumeroParte = itemView.findViewById(R.id.textNumeroParte);
            this.textCodigoSigle = itemView.findViewById(R.id.textCodigoSigle);
            this.textNumeroSerie = itemView.findViewById(R.id.textNumeroSerie);
            this.textNumeroLote = itemView.findViewById(R.id.textNumeroLote);
            this.textVencimiento = itemView.findViewById(R.id.textVencimiento);
            this.textCantidad = itemView.findViewById(R.id.textCantidad);
            this.textUdm = itemView.findViewById(R.id.textUdm);
        }

        public void onBind(MtlPhysicalInventoryTags mtlPhysicalInventoryTags) {

            if (mtlPhysicalInventoryTags.getLocatorId() != null) {
                this.textLocalizador.setText(mtlPhysicalInventoryTags.getLocatorCode());
            } else {
                this.textLocalizador.setText("");
            }
            this.textNumeroParte.setText(mtlPhysicalInventoryTags.getDescription());
            this.textCodigoSigle.setText(mtlPhysicalInventoryTags.getSegment1());
            this.textNumeroSerie.setText(mtlPhysicalInventoryTags.getSerialNum());
            this.textNumeroLote.setText(mtlPhysicalInventoryTags.getLotNumber());
            this.textVencimiento.setText(mtlPhysicalInventoryTags.getLotExpirationDate());
            this.textCantidad.setText(mtlPhysicalInventoryTags.getCount().toString());
            this.textUdm.setText(mtlPhysicalInventoryTags.getPrimaryUomCode());
        }
    }

}
