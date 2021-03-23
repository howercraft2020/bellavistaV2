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
import cl.clsoft.bave.model.EntregaOrgsHeader;
import cl.clsoft.bave.model.RcvShipmentHeaders;

public class AdapterItemEntregaOrgs extends RecyclerView.Adapter<AdapterItemEntregaOrgs.EntregaOrgsViewHolder> {

    private static final String TAG = "EntregaOrgs";
    private List<EntregaOrgsHeader> headers;

    public AdapterItemEntregaOrgs(List<EntregaOrgsHeader> headers) {
        this.headers = headers;
    }

    @NonNull
    @Override
    public AdapterItemEntregaOrgs.EntregaOrgsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_entrega_orgs, parent, false);
        AdapterItemEntregaOrgs.EntregaOrgsViewHolder vh = new AdapterItemEntregaOrgs.EntregaOrgsViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterItemEntregaOrgs.EntregaOrgsViewHolder holder, int position) {
        holder.onBind(this.headers.get(position));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Log.d(TAG, "AdapterItemEntrega::onAttachedToRecyclerView");
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        if (headers != null) {
            return headers.size();
        }
        return 0;
    }

    public class EntregaOrgsViewHolder extends RecyclerView.ViewHolder {

        private TextView textShipmentNumber;
        private TextView textReceiptNum;
        private TextView textCreationDate;


        public EntregaOrgsViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textShipmentNumber = itemView.findViewById(R.id.textShipmentNumber);
            this.textReceiptNum = itemView.findViewById(R.id.textReceiptNum);
            this.textCreationDate = itemView.findViewById(R.id.textCreationDate);
        }

        public void onBind(EntregaOrgsHeader entregaOrgsHeader) {
            if (entregaOrgsHeader != null) {
                this.textShipmentNumber.setText(entregaOrgsHeader.getShipmentNumber());
                this.textReceiptNum.setText(entregaOrgsHeader.getReceiptNumber());
                this.textCreationDate.setText(entregaOrgsHeader.getCreationDate());
            }
        }

    }

}
