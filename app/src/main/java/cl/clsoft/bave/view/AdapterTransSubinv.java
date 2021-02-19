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
import cl.clsoft.bave.model.MtlTransactionsInterface;

public class AdapterTransSubinv extends RecyclerView.Adapter<AdapterTransSubinv.TransSubinvViewHolder> {

    private static final String TAG = "AdapterTransSubinv";
    private List<MtlTransactionsInterface> transferencias;

    public AdapterTransSubinv(List<MtlTransactionsInterface> transferencias) {
        this.transferencias = transferencias;
    }

    @NonNull
    @Override
    public TransSubinvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trans_subinv, parent, false);
        TransSubinvViewHolder tsvh = new TransSubinvViewHolder(v);
        return  tsvh;
    }

    @Override
    public void onBindViewHolder(@NonNull TransSubinvViewHolder holder, int position) {
        Log.d(TAG, "AdapterTransSubinv::onBindViewHolder");
        holder.onBind(this.transferencias.get(position));
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        Log.d(TAG, "AdapterTransSubinv::onAttachedToRecyclerView");
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "AdapterTransSubinv::getItemCount");
        if (transferencias != null && transferencias.size() > 0) {
            Log.d(TAG, "AdapterTransSubinv::return " + transferencias.size());
            return transferencias.size();
        } else {
            Log.d(TAG, "AdapterTransSubinv::return 0 (no transferencias)");
            return 0;
        }
    }

    public class TransSubinvViewHolder extends ViewHolder {

        private static final String TAG = "TransSubinvViewHolder";
        private TextView transactionInterfaceId;
        private TextView transactionReference;

        public TransSubinvViewHolder(@NonNull View itemView){
            super(itemView);
            this.transactionInterfaceId = itemView.findViewById(R.id.transactionInterfaceId);
            this.transactionReference = itemView.findViewById(R.id.transactionReference);

        }

        public void onBind(MtlTransactionsInterface mtlTransactionsInterface){
            this.transactionInterfaceId.setText(mtlTransactionsInterface.getTransactionInterfaceId().toString());
            this.transactionReference.setText(mtlTransactionsInterface.getTransactionReference());
        }
    }
}
