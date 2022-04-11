package cl.clsoft.bave.apis;

import cl.clsoft.bave.model.RcvHeadersInterfaceEntrega;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IRestRcvHeadersInterfaceEntrega {

    @POST("RcvHeadersInterfaceEntrega/insert")
    Call<Void> insert(@Body RcvHeadersInterfaceEntrega rcvHeadersInterfaceEntrega);


    @POST("RcvHeadersInterfaceEntrega/insert")
    Observable<Void> insertRx(@Body RcvHeadersInterfaceEntrega rcvHeadersInterfaceEntrega);

}
