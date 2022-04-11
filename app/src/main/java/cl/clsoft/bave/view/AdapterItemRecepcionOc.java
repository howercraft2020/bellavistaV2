package cl.clsoft.bave.view;

import android.content.Context;
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
import cl.clsoft.bave.model.PoHeadersAll;

public class AdapterItemRecepcionOc extends RecyclerView.Adapter<AdapterItemRecepcionOc.RecepcionOcViewHolder>{

    private static final String TAG = "AdapterRecepcionOc";
    private List<PoHeadersAll> recepciones;
    private Context context;

    public AdapterItemRecepcionOc(Context context, List<PoHeadersAll> recepciones) {
        this.context = context;
        this.recepciones = recepciones;
    }


    public void setRecepciones(List<PoHeadersAll> recepciones){

        this.recepciones = recepciones;
        notifyDataSetChanged();
    }

    public List<PoHeadersAll> getRecepciones(){
        notifyDataSetChanged();
        return this.recepciones;
    }

    @NonNull
    @Override
    public RecepcionOcViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recepcion_oc, parent, false);
        RecepcionOcViewHolder rovh = new RecepcionOcViewHolder(v);
        return rovh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecepcionOcViewHolder holder, int position) {
        Log.d(TAG, "AdapterItemRecepcionOc::onBindViewHolder");
        holder.onBind(this.recepciones.get(position));
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        Log.d(TAG, "AdapterItemRecepcionOc::onAttachedToRecyclerView");
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "AdapterItemRecepcionOc::getItemCount");
        if (recepciones != null && recepciones.size() > 0) {
            Log.d(TAG, "AdapterItemRecepcionOc::return " + recepciones.size());
            return recepciones.size();
        } else {
            Log.d(TAG, "AdapterItemRecepcionOc::return 0 (no recepciones)");
            return 0;
        }
    }

    public class RecepcionOcViewHolder extends ViewHolder {

        private static final String TAG = "RecepcionOcViewHolder";
        private TextView segment1;
        private TextView poHeaderId;
        private TextView receiptNum;



        public RecepcionOcViewHolder(@NonNull View itemView) {
            super(itemView);
            this.segment1 = itemView.findViewById(R.id.segment1);
            this.receiptNum = itemView.findViewById(R.id.receiptNum);
            this.poHeaderId = itemView.findViewById(R.id.poHeaderId);
        }

        public void onBind(PoHeadersAll poHeadersAll){
            this.segment1.setText(poHeadersAll.getSegment1().toString());
            this.receiptNum.setText(poHeadersAll.getReceiptNum().toString());
            this.poHeaderId.setText(poHeadersAll.getPoHeaderId().toString());
        }

    }
}
