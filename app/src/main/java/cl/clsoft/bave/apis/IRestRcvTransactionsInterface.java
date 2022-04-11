package cl.clsoft.bave.apis;

import java.util.List;

import cl.clsoft.bave.model.RcvTransactionsInterface;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IRestRcvTransactionsInterface {

    @GET("RcvTransactionsInterface/getArticulos/{id}")
    Call<List<RcvTransactionsInterface>> getArticulos(@Path("id") Long id);


    @POST("RcvTransactionsInterface/insert")
    Call<Void> insert(@Body RcvTransactionsInterface rcvTransactionsInterface);


    @POST("RcvTransactionsInterface/delete/{interfaceTransactionId}")
    Call<Void> delete(@Path("interfaceTransactionId") Long interfaceTransactionId);



    @POST("RcvTransactionsInterface/deleteByHeaderInterfaceId/{headerInterfaceId}")
    Call<Void> deleteByHeaderInterfaceId(@Path("headerInterfaceId") Long headerInterfaceId);



    @POST("RcvTransactionsInterface/deleteByHeaderInterfaceId/{headerInterfaceId}")
    Observable<Void> deleteByHeaderInterfaceIdRx(@Path("headerInterfaceId") Long headerInterfaceId);


    @GET("RcvTransactionsInterface/get/{headerInterfaceId}/{codigoSigle}/{poLineId}")
    Call<RcvTransactionsInterface>  get(@Path("headerInterfaceId") Long headerInterfaceId,@Path("codigoSigle") String codigoSigle,@Path("poLineId") Long poLineId);



    @GET("RcvTransactionsInterface/get/{headerInterfaceId}/{codigoSigle}/{poLineId}")
    Observable<RcvTransactionsInterface>  getRx(@Path("headerInterfaceId") Long headerInterfaceId,@Path("codigoSigle") String codigoSigle,@Path("poLineId") Long poLineId);



    @GET("RcvTransactionsInterface/getByTransactionId/{transactionId}")
    Call<RcvTransactionsInterface> getByTransactionId(@Path("transactionId") Long transactionId);


    @GET("RcvTransactionsInterface/getByInterfaceTransactionId/{interfaceTransactionId}")
    Call<RcvTransactionsInterface> getByInterfaceTransactionId(@Path("interfaceTransactionId")  Long interfaceTransactionId);


    @GET("RcvTransactionsInterface/getAllByHeader/{headerInterfaceId}")
    Call<List<RcvTransactionsInterface>> getAllByHeader(@Path("headerInterfaceId") Long headerInterfaceId);

    @GET("RcvTransactionsInterface/getAllByHeaderV2/{headerInterfaceId}")
    Call<List<RcvTransactionsInterface>> getAllByHeaderV2(@Path("headerInterfaceId") Long headerInterfaceId);

    @GET("RcvTransactionsInterface/getByTransactionId/{transactionId}")
    Observable<RcvTransactionsInterface> getByTransactionIdRx(@Path("transactionId") Long transactionId);

    @GET("/RcvTransactionsInterface/getAll")
    Observable<List<RcvTransactionsInterface>> getAll();


    @GET("/RcvTransactionsInterface/getbyShipmentHeaderId/{shipment_header_id}")
    Call<List<RcvTransactionsInterface>> getbyShipmentHeaderId(@Path("") Long shipment_header_id);



}

