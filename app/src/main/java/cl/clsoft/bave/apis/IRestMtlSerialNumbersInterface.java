package cl.clsoft.bave.apis;

import java.util.List;

import cl.clsoft.bave.model.MtlSerialNumbersInterface;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IRestMtlSerialNumbersInterface {



    @GET("MtlSerialNumbersInterface/getAllByInterfaceTransactionId/{InterfaceTransactionId}")
    Call<List<MtlSerialNumbersInterface>> getAllByInterfaceTransactionId(@Path("InterfaceTransactionId") Long InterfaceTransactionId);


    @POST("MtlSerialNumbersInterface/insert")
    Call<Void> insert(@Body MtlSerialNumbersInterface mtlSerialNumbersInterface);


}
