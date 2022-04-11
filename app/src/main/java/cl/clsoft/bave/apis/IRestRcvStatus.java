package cl.clsoft.bave.apis;

import cl.clsoft.bave.model.RcvStatus;
import cl.clsoft.bave.model.RcvTransactionsInterface;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IRestRcvStatus {


    @POST("RcvStatus/save")
    Call<Void> save(@Body RcvStatus rcvStatus);


    @POST("RcvStatus/actualiza_estado/{estado}/{poheaderId}/{receiptnum}")
    Call<Void> actualiza_estado(@Path("estado") int estado,@Path("poheaderId") Long poheaderId,@Path("receiptnum") Long receiptnum);


    @POST("RcvStatus/actualiza_estado/{estado}/{poheaderId}/{receiptnum}")
    Observable<Void> actualiza_estadoRx(@Path("estado") int estado, @Path("poheaderId") Long poheaderId, @Path("receiptnum") Long receiptnum);



}
