package cl.clsoft.bave.apis;

import cl.clsoft.bave.model.RcvTransactionsInterfaceEntrega;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IRestRcvTransactionsInterfaceEntrega {


    @POST("RcvTransactionsInterfaceEntrega/insert")
    Call<Void> insert(@Body RcvTransactionsInterfaceEntrega rcvTransactionsInterfaceEntrega );

    @POST("RcvTransactionsInterfaceEntrega/insert")
    Observable<Void> insertRx(@Body RcvTransactionsInterfaceEntrega rcvTransactionsInterfaceEntrega );

}
