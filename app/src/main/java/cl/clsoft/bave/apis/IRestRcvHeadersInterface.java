package cl.clsoft.bave.apis;

import cl.clsoft.bave.model.RcvHeadersInterface;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
public interface IRestRcvHeadersInterface {

    @POST("RcvHeadersInterface/insert")
    Call<Void> insert(@Body RcvHeadersInterface rcvHeadersInterface);

    @GET("RcvHeadersInterface/get/{headerInterfaceId}")
    Call<RcvHeadersInterface> get(@Path("headerInterfaceId") Long headerInterfaceId);

    @GET("RcvHeadersInterface/existe/{headerInterfaceId}")
    Call<Long> existe(@Path("headerInterfaceId") Long headerInterfaceId);


    @POST("RcvHeadersInterface/deleteByHeaderInterfaceId/{headerInterfaceId}")
    Call<Void> deleteByHeaderInterfaceId(@Path("headerInterfaceId") Long headerInterfaceId);

    @POST("RcvHeadersInterface/deleteByHeaderInterfaceId/{headerInterfaceId}")
    Observable<Void> deleteByHeaderInterfaceIdRx(@Path("headerInterfaceId") Long headerInterfaceId);



}
