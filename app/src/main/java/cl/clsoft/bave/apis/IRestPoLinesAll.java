package cl.clsoft.bave.apis;

import java.util.List;

import cl.clsoft.bave.model.PoLinesAll;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
public interface IRestPoLinesAll {

    @GET("poLinesAll/getLinea/{inventoryItemId}/{poHeaderId}/{poLineNum}")
    Call<PoLinesAll> getLinea(@Path("inventoryItemId") Long inventoryItemId,@Path("poHeaderId") Long poHeaderId,@Path("poLineNum") Long poLineNum);


    @GET("poLinesAll/getLines/{inventoryItemId}/{poHeaderId}")
    Call<List<PoLinesAll>>  getLines(@Path("inventoryItemId") Long inventoryItemId,@Path("poHeaderId") Long poHeaderId);

    @GET("poLinesAll/getById/{poLineId}")
    Call<PoLinesAll> getById(@Path("poLineId") Long poLineId);

    @POST("poLinesAll/deleteByPoHeaderId/{poHeaderId}")
    Call<Void> deleteByPoHeaderId(@Path("poHeaderId") Long poHeaderId);

    @POST("poLinesAll/deleteByPoHeaderId/{poHeaderId}")
    Observable<Void> deleteByPoHeaderIdRx(@Path("poHeaderId") Long poHeaderId);

    @POST("poLinesAll/insert")
    Call<Void> insert(@Body PoLinesAll poLinesAll);



    @GET("poLinesAll/getLinea/{inventoryItemId}/{poHeaderId}/{poLineNum}")
    Observable<PoLinesAll> getLineaRx(@Path("inventoryItemId") Long inventoryItemId, @Path("poHeaderId") Long poHeaderId, @Path("poLineNum") Long poLineNum);

}