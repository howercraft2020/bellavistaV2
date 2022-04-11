package cl.clsoft.bave.apis;
import java.util.List;

import cl.clsoft.bave.model.MtlTransactionLotNumbers;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
public interface IRestMtlTransactionLotNumbers {



@GET("MtlTransactionLotNumbers/get/{transactionId}")
Call<MtlTransactionLotNumbers> get(@Path("transactionId") Long transactionId);


@GET("MtlTransactionLotNumbers/get/{transactionId}")
Observable<MtlTransactionLotNumbers> getRx(@Path("transactionId") Long transactionId);

}
