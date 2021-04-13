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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import cl.clsoft.bave.R;
import cl.clsoft.bave.model.MtlPhysicalInventoryTags;

public class AdapterInventarioFisicoDetalleNoInventariado extends RecyclerView.Adapter<AdapterInventarioFisicoDetalleNoInventariado.InventarioFisicoDetalleViewHolder> {
    private static final String TAG = "AdapterInventarioFisico";
    private List<MtlPhysicalInventoryTags> tags;

    public AdapterInventarioFisicoDetalleNoInventariado(){
        this.tags = new ArrayList<>();
    }

    public AdapterInventarioFisicoDetalleNoInventariado(List<MtlPhysicalInventoryTags> tags){
        this.tags = tags;
    }

    public void setTags(List<MtlPhysicalInventoryTags> tags) {
        this.tags.clear();
        this.tags.addAll(tags);
        Log.d(TAG, "size: " +  tags.size());
        this.notifyItemRangeInserted(0, tags.size() -1);
        this.notifyDataSetChanged();
    }

    public List<MtlPhysicalInventoryTags> getTags() {
        return this.tags;
    }

    @NonNull
    @Override
    public AdapterInventarioFisicoDetalleNoInventariado.InventarioFisicoDetalleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tag_pendiente, parent, false);
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

    public class InventarioFisicoDetalleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView textLocalizador;
        private TextView textNumeroParte;
        private TextView textCodigoSigle;
        private TextView textNumeroSerie;
        private TextView textNumeroLote;
        private TextView textVencimiento;

        public InventarioFisicoDetalleViewHolder(@NonNull View itemView) {

            super(itemView);

            this.textLocalizador = itemView.findViewById(R.id.textLocalizador);
            this.textNumeroParte = itemView.findViewById(R.id.textNumeroParte);
            this.textCodigoSigle = itemView.findViewById(R.id.textCodigoSigle);
            this.textNumeroSerie = itemView.findViewById(R.id.textNumeroSerie);
            this.textNumeroLote = itemView.findViewById(R.id.textNumeroLote);
            this.textVencimiento = itemView.findViewById(R.id.textVencimiento);

            itemView.setOnClickListener(this);

        }

        public void onBind(MtlPhysicalInventoryTags mtlPhysicalInventoryTags) {

            if (mtlPhysicalInventoryTags.getLocatorId() != null) {
                String[] localizadorArr = mtlPhysicalInventoryTags.getLocatorCode().split("\\.");
                this.textLocalizador.setText(localizadorArr[0]);
            } else {
                this.textLocalizador.setText("");
            }
            this.textNumeroParte.setText(mtlPhysicalInventoryTags.getDescription());
            this.textCodigoSigle.setText(mtlPhysicalInventoryTags.getSegment1());
            this.textNumeroSerie.setText(mtlPhysicalInventoryTags.getSerialNum());
            this.textNumeroLote.setText(mtlPhysicalInventoryTags.getLotNumber());
            this.textVencimiento.setText(mtlPhysicalInventoryTags.getLotExpirationDate());

        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, "BandejaViewHolder::onClick");
            int position = getAdapterPosition();
            Log.d(TAG, "BandejaViewHolder::onClick::position " + position);
            Intent i = new Intent(itemView.getContext(), ActivityFisicoAgregarDisponible.class);
            i.putExtra("tagId", tags.get(position).getTagId());
            itemView.getContext().startActivity(i);
            ((Activity) itemView.getContext()).finish();

        }
    }

}
