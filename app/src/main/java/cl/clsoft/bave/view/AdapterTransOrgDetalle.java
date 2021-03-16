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
import cl.clsoft.bave.model.DatosTransOrgDetalle;

public class AdapterTransOrgDetalle extends RecyclerView.Adapter<AdapterTransOrgDetalle.TransOrgDetalleViewHolder> {

    private static final String TAG = "AdapterTransOrgDetalle";
    private List<DatosTransOrgDetalle> transferencias;

    public AdapterTransOrgDetalle(List<DatosTransOrgDetalle> transferencias) {
        this.transferencias = transferencias;
    }

    @NonNull
    @Override
    public TransOrgDetalleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trans_org_detalle,parent,false);
        TransOrgDetalleViewHolder todv = new TransOrgDetalleViewHolder(v);
        return todv;
    }

    @Override
    public void onBindViewHolder(@NonNull TransOrgDetalleViewHolder holder, int position) {
        Log.d(TAG, "AdapterTransOrgDetalle::onBindViewHolder");
        holder.onBind(this.transferencias.get(position));
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        Log.d(TAG, "AdapterTransOrgDetalle::onAttachedToRecyclerView");
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "AdapterTransOrgDetalle::getItemCount");
        if (transferencias != null && transferencias.size() > 0) {
            Log.d(TAG, "AdapterTransOrgDetalle::return " + transferencias.size());
            return transferencias.size();
        } else {
            Log.d(TAG, "AdapterTransOrgDetalle::return 0 (no transferencias)");
            return 0;
        }
    }

    public class TransOrgDetalleViewHolder extends RecyclerView.ViewHolder {

        private static final String TAG = "TransOrgDetalleViewHolder";
        private TextView orgDestino, codigoSigle, subinvDesde, locDesde, cantidad;

        public TransOrgDetalleViewHolder(@NonNull View itemView) {
            super(itemView);
            this.orgDestino = itemView.findViewById(R.id.orgDestinoTextView);
            this.codigoSigle = itemView.findViewById(R.id.sigleTextView);
            this.subinvDesde = itemView.findViewById(R.id.subInvDesdeTextView);
            this.locDesde = itemView.findViewById(R.id.locDesdeTextView);
            this.cantidad = itemView.findViewById(R.id.cantidadTextView);
        }

        public void onBind(DatosTransOrgDetalle datosTransOrgDetalle){
            this.orgDestino.setText(datosTransOrgDetalle.getOrganizationCode());
            this.codigoSigle.setText(datosTransOrgDetalle.getSegment1());
            this.subinvDesde.setText(datosTransOrgDetalle.getSubinventoryCode());
            this.locDesde.setText(datosTransOrgDetalle.getLocalizador());
            this.cantidad.setText(datosTransOrgDetalle.getTransactionQuantity().toString());

        }
    }
}
