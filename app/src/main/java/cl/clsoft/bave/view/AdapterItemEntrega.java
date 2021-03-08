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
import cl.clsoft.bave.model.RcvShipmentHeaders;

public class AdapterItemEntrega extends RecyclerView.Adapter<AdapterItemEntrega.EntregaViewHolder> {

    private static final String TAG = "AdapterItemEntrega";
    private List<RcvShipmentHeaders> headers;

    public AdapterItemEntrega(List<RcvShipmentHeaders> headers) {
        this.headers = headers;
    }

    @NonNull
    @Override
    public AdapterItemEntrega.EntregaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_entrega, parent, false);
        AdapterItemEntrega.EntregaViewHolder vh = new AdapterItemEntrega.EntregaViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterItemEntrega.EntregaViewHolder holder, int position) {
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

    public class EntregaViewHolder extends RecyclerView.ViewHolder {

        private TextView textShipmentHeaderId;
        private TextView textReceiptNum;
        private TextView textCreationDate;


        public EntregaViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textShipmentHeaderId = itemView.findViewById(R.id.textShipmentHeaderId);
            this.textReceiptNum = itemView.findViewById(R.id.textReceiptNum);
            this.textCreationDate = itemView.findViewById(R.id.textCreationDate);
        }

        public void onBind(RcvShipmentHeaders rcvShipmentHeaders) {
            if (rcvShipmentHeaders != null) {
                this.textShipmentHeaderId.setText(rcvShipmentHeaders.getPoNumber().toString());
                this.textReceiptNum.setText(rcvShipmentHeaders.getReceiptNum());
                this.textCreationDate.setText(rcvShipmentHeaders.getCreationDate());
            }
        }

    }
}
