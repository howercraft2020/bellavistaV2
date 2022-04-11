package cl.clsoft.bave.apis;

import java.util.List;

import cl.clsoft.bave.model.RcvTransactions;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IRestRcvTransactions {



@POST("RcvTransactions/insert")
Call<Void> insert(@Body RcvTransactions rcvTransactions);

@POST("RcvTransactions/delete/{transactionId}")
Call<Void> delete(@Path("transactionId") Long transactionId);

@GET("RcvTransactions/get/{transactionId}")
Call<RcvTransactions> get(@Path("transactionId") Long transactionId);


@GET("RcvTransactions/getAllByShipment/{shipmentHeaderId}")
Call<List<RcvTransactions>> getAllByShipment(@Path("shipmentHeaderId") Long shipmentHeaderId);

@GET("RcvTransactions/getAllByShipmentInventory/{shipmentHeaderId}/{inventoryItemId}")
Call<List<RcvTransactions>> getAllByShipmentInventory(@Path("shipmentHeaderId") Long shipmentHeaderId,@Path("inventoryItemId") Long inventoryItemId);

@GET("RcvTransactions/getSegmentsByShipment/{shipmentHeaderId}")
Call<List<String>> getSegmentsByShipment(@Path("shipmentHeaderId") Long shipmentHeaderId);



@GET("RcvTransactions/getAllByShipment/{shipmentHeaderId}")
Observable<List<RcvTransactions>> getAllByShipmentRx(@Path("shipmentHeaderId") Long shipmentHeaderId);

@GET("RcvTransactions/get/{transactionId}")
Observable<RcvTransactions> getRx(@Path("transactionId") Long transactionId);
}
