package cl.clsoft.bave.view;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
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
    private static final String TAG = "InventarioFisicoDetalle";
    private List<MtlPhysicalInventoryTags> tags;

    public AdapterInventarioFisicoDetalle(List<MtlPhysicalInventoryTags> tags){
        this.tags = tags;
    }

    @NonNull
    @Override
    public InventarioFisicoDetalleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tag, parent, false);
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

    public class InventarioFisicoDetalleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

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
            itemView.setOnClickListener(this);
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

        @Override
        public void onClick(View view) {
            Log.d(TAG, "BandejaViewHolder::onClick");
            int position = getAdapterPosition();
            Log.d(TAG, "BandejaViewHolder::onClick::position " + position);
            Intent i = new Intent(itemView.getContext(), ActivityFisicoEditar.class);
            i.putExtra("tagId", tags.get(position).getTagId());
            itemView.getContext().startActivity(i);
            ((Activity) itemView.getContext()).finish();

        }
    }
}
