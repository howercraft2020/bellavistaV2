package cl.clsoft.bave.apis;


import java.util.List;

import cl.clsoft.bave.model.MtlMaterialTransactions;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IRestMtlMaterialTransactions {



@GET("MtlMaterialTransactions/get/{transactionId}")
Call<MtlMaterialTransactions> get(@Path("transactionId") Long transactionId);

@GET("MtlMaterialTransactions/get/{transactionId}")
Observable<MtlMaterialTransactions> getRx(@Path("transactionId") Long transactionId);






}
