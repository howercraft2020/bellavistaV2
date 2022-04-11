package cl.clsoft.bave.apis;

import cl.clsoft.bave.model.XXPDA_RCV_TRANS_INTERFACE;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IRestXXPDA_RCV_TRANS_INTERFACE {



    @POST("XXPDA_RCV_TRANS_INTERFACE/insert")
    Call<Void> insert(@Body XXPDA_RCV_TRANS_INTERFACE xxpda_rcv_trans_interface);



    @POST("XXPDA_RCV_TRANS_INTERFACE/insert")
    Observable<Void> insertRx(@Body XXPDA_RCV_TRANS_INTERFACE xxpda_rcv_trans_interface);



}
