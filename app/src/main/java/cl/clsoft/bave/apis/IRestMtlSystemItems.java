package cl.clsoft.bave.apis;
import java.util.List;

import cl.clsoft.bave.model.MtlSystemItems;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
public interface IRestMtlSystemItems {


    @GET("MtlSystemItems/getBySegment/{segment}")
    Call<MtlSystemItems>  getBySegment(@Path("segment") String segment);

    @GET("MtlSystemItems/getBySegment/{segment}")
    Observable<MtlSystemItems> getBySegmentRx(@Path("segment") String segment);

    @GET("MtlSystemItems/getAllByOcReceipt/{PoHeaderId}/{ReceiptNum}")
    Call<List<MtlSystemItems>> getAllByOcReceipt(@Path("PoHeaderId") Long PoHeaderId,@Path("ReceiptNum") Long ReceiptNum);

    @GET("MtlSystemItems/get/{inventoryItemId}")
    Call<MtlSystemItems> get(@Path("inventoryItemId") Long inventoryItemId);


    @GET("MtlSystemItems/get/{inventoryItemId}")
    Observable<MtlSystemItems> getRx(@Path("inventoryItemId") Long inventoryItemId);

    @GET("MtlSystemItems/getAll")
    Call<List<MtlSystemItems>> getAll();

    @GET("MtlSystemItems/getAllByDescription/{pattern}")
    Call<List<MtlSystemItems>>  getAllByDescription(@Path("pattern") String pattern);

    @GET("MtlSystemItems/getAllByDescriptionPoHeaderId/{pattern}/{poHeaderId}")
    Call<List<MtlSystemItems>> getAllByDescriptionPoHeaderId(@Path("pattern") String pattern,@Path("poHeaderId") Long poHeaderId);

    @GET("MtlSystemItems/getAllByDescriptionShipment/{pattern}/{shipmentHeaderId}")
    Call<List<MtlSystemItems>> getAllByDescriptionShipment(@Path("pattern") String pattern,@Path("shipmentHeaderId") Long shipmentHeaderId);



}