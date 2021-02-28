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
import cl.clsoft.bave.model.DatosTransSubinvDetalle;
import cl.clsoft.bave.model.MtlTransactionsInterface;

public class AdapterTransSubinvDetalle extends RecyclerView.Adapter<AdapterTransSubinvDetalle.TransSubinvDetalleViewHolder> {

    private static final String TAG = "AdapterTransSubinvDet";
    private List<DatosTransSubinvDetalle> transferencias;

    public AdapterTransSubinvDetalle(List<DatosTransSubinvDetalle> transferencias) {
        this.transferencias = transferencias;
    }

    @NonNull
    @Override
    public TransSubinvDetalleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trans_subinv_detalle,parent,false);
        TransSubinvDetalleViewHolder tsdv = new TransSubinvDetalleViewHolder(v);
        return tsdv;
    }

    @Override
    public void onBindViewHolder(@NonNull TransSubinvDetalleViewHolder holder, int position) {
        Log.d(TAG, "AdapterTransSubinvDetalle::onBindViewHolder");
        holder.onBind(this.transferencias.get(position));
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        Log.d(TAG, "AdapterTransSubinvDetalle::onAttachedToRecyclerView");
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "AdapterTransSubinvDetalle::getItemCount");
        if (transferencias != null && transferencias.size() > 0) {
            Log.d(TAG, "AdapterTransSubinvDetalle::return " + transferencias.size());
            return transferencias.size();
        } else {
            Log.d(TAG, "AdapterTransSubinvDetalle::return 0 (no transferencias)");
            return 0;
        }
    }

    public class TransSubinvDetalleViewHolder extends ViewHolder {

        private static final String TAG = "TransSubinvDetalleViewHolder";
        private TextView codigoSigle, subinvDesde, locDesde, subinvHasta, locHasta, cantidad;

        public TransSubinvDetalleViewHolder(@NonNull View itemView) {
            super(itemView);
            this.codigoSigle = itemView.findViewById(R.id.sigleTextView);
            this.subinvDesde = itemView.findViewById(R.id.subInvDesdeTextView);
            this.locDesde = itemView.findViewById(R.id.locDesdeTextView);
            this.subinvHasta = itemView.findViewById(R.id.subinvHastaTextView);
            this.locHasta = itemView.findViewById(R.id.locHastaTextView);
            this.cantidad = itemView.findViewById(R.id.cantidadTextView);
        }

        public void onBind(DatosTransSubinvDetalle datosTransSubinvDetalle){
            this.codigoSigle.setText(datosTransSubinvDetalle.getSegment1());
            this.subinvDesde.setText(datosTransSubinvDetalle.getSubinventoryCode());
            this.locDesde.setText(datosTransSubinvDetalle.getLocalizador());
            this.subinvHasta.setText(datosTransSubinvDetalle.getSubinventarioHasta());
            this.locHasta.setText(datosTransSubinvDetalle.getLocalizadorHasta());
            this.cantidad.setText(datosTransSubinvDetalle.getTransactionQuantity().toString());

        }
    }
}
