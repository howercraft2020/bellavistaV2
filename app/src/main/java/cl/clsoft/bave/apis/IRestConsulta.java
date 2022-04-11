package cl.clsoft.bave.apis;

import java.util.List;

import cl.clsoft.bave.model.ConsultaItem;
import cl.clsoft.bave.model.ConsultaResumenItem;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IRestConsulta {


    @GET("ConsultaItem/getAllByItem/{inventoryItemId}")
    Call<List<ConsultaItem>> getAllByItem(@Path("inventoryItemId") Long inventoryItemId);


    @GET("ConsultaItem/getAllBySubinventory/{subinventoryCode}")
    Call<List<ConsultaItem>> getAllBySubinventory(@Path("subinventoryCode") String subinventoryCode);


    @GET("ConsultaItem/getAllBySubinventoryItem/{subinventoryCode}/{inventoryItemId}")
    Call<List<ConsultaItem>> getAllBySubinventoryItem(@Path("subinventoryCode") String subinventoryCode, @Path("inventoryItemId") Long inventoryItemId);


    @GET("ConsultaResumenItem/getAllBySubinventory/{subinventoryCode}")
    Call<List<ConsultaResumenItem>> getAllBySubinventoryResumen(@Path("subinventoryCode") String subinventoryCode);

}
