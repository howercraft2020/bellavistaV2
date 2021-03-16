package cl.clsoft.bave.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cl.clsoft.bave.R;
import cl.clsoft.bave.model.DatosTransOrg;
import cl.clsoft.bave.model.DatosTransSubinv;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

public class AdapterTransOrg extends RecyclerView.Adapter<AdapterTransOrg.TransOrgViewHolder> {

    private static final String TAG = "AdapterTransOrg";
    private List<DatosTransOrg> transferencias;

    public AdapterTransOrg(List<DatosTransOrg> transferencias) {
        this.transferencias = transferencias;
    }

    @NonNull
    @Override
    public TransOrgViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trans_org,parent, false);
        TransOrgViewHolder tovh = new TransOrgViewHolder(v);
        return tovh;
    }

    @Override
    public void onBindViewHolder(@NonNull TransOrgViewHolder holder, int position) {
        Log.d(TAG, "AdapterTransOrg::onBindViewHolder");
        holder.onBind(this.transferencias.get(position));
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        Log.d(TAG, "AdapterTransOrg::onAttachedToRecyclerView");
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "AdapterTransOrg::getItemCount");
        if (transferencias != null && transferencias.size() > 0) {
            Log.d(TAG, "AdapterTransOrg::return " + transferencias.size());
            return transferencias.size();
        } else {
            Log.d(TAG, "AdapterTransOrg::return 0 (no transferencias)");
            return 0;
        }
    }


    public class TransOrgViewHolder extends ViewHolder {

        private static final String TAG = "TransOrgViewHolder";
        private TextView shipmentNumber;
        private TextView transactionSourceName;

        public TransOrgViewHolder(@NonNull View itemView){
            super(itemView);
            this.shipmentNumber = itemView.findViewById(R.id.shipmentNumber);
            this.transactionSourceName = itemView.findViewById(R.id.transactionSourceName);

        }

        public void onBind(DatosTransOrg datosTransOrg){
            this.shipmentNumber.setText(datosTransOrg.getShipmentNumber().toString());
            this.transactionSourceName.setText(datosTransOrg.getTransactionSourceName());
        }
    }


}
