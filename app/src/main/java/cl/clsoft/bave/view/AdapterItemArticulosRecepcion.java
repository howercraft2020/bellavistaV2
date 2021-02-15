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
import cl.clsoft.bave.model.RcvTransactionsInterface;

public class AdapterItemArticulosRecepcion extends RecyclerView.Adapter<AdapterItemArticulosRecepcion.ArticulosRecepcionViewHolder> {

    private static final String TAG = "AdapterArticulosOc";
    private List<RcvTransactionsInterface> articulos;

    public AdapterItemArticulosRecepcion(List<RcvTransactionsInterface> articulos) {
        this.articulos = articulos;
    }


    @NonNull
    @Override
    public AdapterItemArticulosRecepcion.ArticulosRecepcionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_articulos_recepcion, parent, false);
        ArticulosRecepcionViewHolder arvh = new ArticulosRecepcionViewHolder(v);
        return arvh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterItemArticulosRecepcion.ArticulosRecepcionViewHolder holder, int position) {
        Log.d(TAG, "AdapterArticulosRecepcion::onBindViewHolder");
        holder.onBind(this.articulos.get(position));

    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        Log.d(TAG, "AdapterArticulosRecepcion::onAttachedToRecyclerView");
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "AdapterArticulosRecepcion::getItemCount");
        if (articulos != null && articulos.size() > 0) {
            Log.d(TAG, "AdapterArticulosRecepcion::return " + articulos.size());
            return articulos.size();
        } else {
            Log.d(TAG, "AdapterArticulosRecepcion::return 0 (no articulos)");
            return 0;
        }
    }

    public class ArticulosRecepcionViewHolder extends ViewHolder {

        private static final String TAG = "ArticulosRecepcionViewHolder";
        private TextView codigoSigle;
        private TextView cantidad;
        private TextView udm;


        public ArticulosRecepcionViewHolder(@NonNull View itemView) {
            super(itemView);
            this.codigoSigle = itemView.findViewById(R.id.codigoSigle);
            this.cantidad = itemView.findViewById(R.id.cantidad);
            this.udm = itemView.findViewById(R.id.udm);
        }

        public void onBind(RcvTransactionsInterface rcvTransactionsInterface){
            this.codigoSigle.setText(rcvTransactionsInterface.getSegment1());
            this.cantidad.setText(rcvTransactionsInterface.getQuantity().toString());
            this.udm.setText(rcvTransactionsInterface.getUnitOfMeasure());
        }
    }
}
