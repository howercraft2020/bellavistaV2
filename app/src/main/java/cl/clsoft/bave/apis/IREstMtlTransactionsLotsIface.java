package cl.clsoft.bave.apis;

import cl.clsoft.bave.model.MtlTransactionsLotsIface;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IREstMtlTransactionsLotsIface {



@GET("MtlTransactionsLotsIface/getByInterfaceTransactionId/{InterfaceTransactionId}")
    Call<MtlTransactionsLotsIface> getByInterfaceTransactionId(@Path("InterfaceTransactionId") Long InterfaceTransactionId);

@POST("MtlTransactionsLotsIface/insert")
    Call<Void> insert(@Body MtlTransactionsLotsIface mtlTransactionsLotsIface);


}
