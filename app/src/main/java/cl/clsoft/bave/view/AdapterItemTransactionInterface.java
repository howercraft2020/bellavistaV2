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
import cl.clsoft.bave.dto.TransactionsDto;
import cl.clsoft.bave.model.RcvTransactionsInterface;

public class AdapterItemTransactionInterface extends RecyclerView.Adapter<AdapterItemTransactionInterface.TransactionViewHolder> {

    private static final String TAG = "AdapterItem";
    private List<TransactionsDto> transactions;

    public AdapterItemTransactionInterface(List<TransactionsDto> transactions) {
        this.transactions = transactions;
    }

    @NonNull
    @Override
    public AdapterItemTransactionInterface.TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaction_interface, parent, false);
        AdapterItemTransactionInterface.TransactionViewHolder vh = new AdapterItemTransactionInterface.TransactionViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterItemTransactionInterface.TransactionViewHolder holder, int position) {
        holder.onBind(this.transactions.get(position));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Log.d(TAG, "AdapterItemSigle::onAttachedToRecyclerView");
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        if (transactions != null) {
            return transactions.size();
        }
        return 0;
    }

    public class TransactionViewHolder extends RecyclerView.ViewHolder {

        private static final String TAG = "SigleViewHolder";
        private TextView textLinea;
        private TextView textSigle;
        private TextView textFechaCreacion;

        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textLinea = itemView.findViewById(R.id.textLinea);
            this.textSigle = itemView.findViewById(R.id.textSigle);
            this.textFechaCreacion = itemView.findViewById(R.id.textFechaCreacion);
        }

        public void onBind(TransactionsDto dto) {
            if (dto != null) {
                if (dto.getLineNum() != null)
                    this.textLinea.setText(dto.getLineNum().toString());
                else
                    this.textLinea.setText("");
                this.textSigle.setText(dto.getSegment1());
                this.textFechaCreacion.setText(dto.getCreationDate());
            }
        }

    }
}
