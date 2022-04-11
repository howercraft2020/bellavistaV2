package cl.clsoft.bave.apis;

import java.util.List;

import cl.clsoft.bave.model.RcvShipmentHeaders;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IRestRcvShipmentHeaders {


    @POST("RcvShipmentHeaders/insert")
    Call<Void> insert(@Body RcvShipmentHeaders rcvShipmentHeaders);


    @GET("RcvShipmentHeaders/get/{shipmentHeaderId}")
    Call<RcvShipmentHeaders> get(@Path("shipmentHeaderId") Long shipmentHeaderId);

    @GET("RcvShipmentHeaders/getAll")
    Call<List<RcvShipmentHeaders>> getAll();

    @GET("RcvShipmentHeaders/get/{shipmentHeaderId}")
    Observable<RcvShipmentHeaders> getRx(@Path("shipmentHeaderId") Long shipmentHeaderId);





}
