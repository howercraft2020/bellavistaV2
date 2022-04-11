package cl.clsoft.bave.apis;
import java.util.List;

import cl.clsoft.bave.model.MtlSerialNumbers;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface IRestMtlSerialNumbers {


@GET("MtlSerialNumbers/getAllByShipmentHeaderIdInventoryItemId/{shipmentHeaderId}/{inventoryItemId}")
Call<List<MtlSerialNumbers>> getAllByShipmentHeaderIdInventoryItemId(@Path("shipmentHeaderId") Long shipmentHeaderId,@Path("inventoryItemId") Long inventoryItemId);


@GET("MtlSerialNumbers/getAllByShipmentHeaderIdInventoryItemId/{shipmentHeaderId}/{inventoryItemId}")
Observable<List<MtlSerialNumbers>> getAllByShipmentHeaderIdInventoryItemIdRx(@Path("shipmentHeaderId") Long shipmentHeaderId,@Path("inventoryItemId") Long inventoryItemId);


}
