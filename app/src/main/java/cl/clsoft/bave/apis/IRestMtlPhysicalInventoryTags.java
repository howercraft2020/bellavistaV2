package cl.clsoft.bave.apis;

import cl.clsoft.bave.model.MtlCycleCountEntries;
import cl.clsoft.bave.model.MtlPhysicalInventoryTags;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

public interface IRestMtlPhysicalInventoryTags {


    @POST("MtlCycleCountEntries/insert")
    Call<Void> insert(@Body MtlCycleCountEntries mtlCycleCountEntries);


    @POST("/MtlPhysicalInventoryTags/insert")
    Call<Void> insert(@Body MtlPhysicalInventoryTags mtlPhysicalInventoryTags);


    @POST("/MtlPhysicalInventoryTags/delete/{tagId}")
    Call<Void> delete(@Path("tagId") Long tagId);

    @GET("/MtlPhysicalInventoryTags/get/{tagId}")
    MtlPhysicalInventoryTags get(@Path("tagId") Long tagId);

    @POST("/MtlPhysicalInventoryTags/deleteByPhysicalInventory/{physicalInventoryId}")
    Call<Void> deleteByPhysicalInventory(@Path("physicalInventoryId") Long physicalInventoryId);

    @GET("/MtlPhysicalInventoryTags/getAllByInventory/{physicalInventoryId}")
    Call<List<MtlPhysicalInventoryTags>> getAllByInventory(@Path("physicalInventoryId") Long physicalInventoryId);


    @GET("/MtlPhysicalInventoryTags/getAllByInventorySubinventory/{physicalInventoryId}/{subinventory}")
    Call<List<MtlPhysicalInventoryTags>> getAllByInventorySubinventory(@Path("physicalInventoryId") Long physicalInventoryId,@Path("subinventory") String subinventory);


    @GET("/MtlPhysicalInventoryTags/getAllInventariadosByInventory/{physicalInventoryId}")
    Call<List<MtlPhysicalInventoryTags>> getAllInventariadosByInventory(@Path("physicalInventoryId") Long physicalInventoryId);

    @GET("/MtlPhysicalInventoryTags/getAllInventariadosByInventorySubinventory/{physicalInventoryId}/{subinventory}")
    Call<List<MtlPhysicalInventoryTags>> getAllInventariadosByInventorySubinventory(@Path("physicalInventoryId") Long physicalInventoryId,@Path("subinventory") String subinventory);

    @GET("/MtlPhysicalInventoryTags/getAllNoInventariadosByInventorySubinventory/{physicalInventoryId}/{subinventory}")
    Call<List<MtlPhysicalInventoryTags>> getAllNoInventariadosByInventorySubinventory(@Path("physicalInventoryId") Long physicalInventoryId,@Path("subinventory") String subinventory);

    @GET("/MtlPhysicalInventoryTags/getAllByInventorySubinventorySegmentSerieLote/{physicalInventoryId}/{subinventory}/{locatorId}/{segment}/{serie}/{lote}/{vencimiento}")
    Call<List<MtlPhysicalInventoryTags>> getAllByInventorySubinventorySegmentSerieLote(@Path("physicalInventoryId") Long physicalInventoryId,@Path("subinventory") String subinventory,@Path("locatorId") Long locatorId,@Path("segment") String segment,@Path("serie") String serie,@Path("lote") String lote,@Path("vencimiento") String vencimiento);


    @GET("/MtlPhysicalInventoryTags/getAllByInventorySubinventorySegmentSerieLoteLocatorNull/{physicalInventoryId}/{subinventory}/{segment}/{serie}/{lote}/{vencimiento}")
    Call<List<MtlPhysicalInventoryTags>> getAllByInventorySubinventorySegmentSerieLoteLocatorNull(@Path("physicalInventoryId") Long physicalInventoryId,@Path("subinventory") String subinventory,@Path("segment") String segment,@Path("serie") String serie,@Path("lote") String lote,@Path("vencimiento") String vencimiento);

    @GET("/MtlPhysicalInventoryTags/getLocatorByInventorySubinventory/{physicalInventoryId}/{subinventory}")
    Call<List<String>> getLocatorByInventorySubinventory(@Path("physicalInventoryId") Long physicalInventoryId,@Path("subinventory") String subinventory);

    @GET("/MtlPhysicalInventoryTags/getSegment1ByInventorySubinventory/{physicalInventoryId}/{subinventory}")
    Call<List<String>> getSegment1ByInventorySubinventory(@Path("physicalInventoryId") Long physicalInventoryId,@Path("subinventory") String subinventory);

    @GET("/MtlPhysicalInventoryTags/getSegment1ByInventorySubinventoryLocator/{physicalInventoryId}/{subinventory}/{locatorId}")
    Call<List<String>> getSegment1ByInventorySubinventoryLocator(@Path("physicalInventoryId") Long physicalInventoryId,@Path("subinventory") String subinventory,@Path("locatorId") Long locatorId);

    @GET("/MtlPhysicalInventoryTags/getSeriesByInventorySubinventory/{physicalInventoryId}/{subinventory}")
    Call<List<String>> getSeriesByInventorySubinventory(@Path("physicalInventoryId") Long physicalInventoryId,@Path("subinventory") String subinventory);

    @GET("/MtlPhysicalInventoryTags/getSeriesByInventorySubinventoryLocator/{physicalInventoryId}/{subinventory}/{locatorId}")
    Call<List<String>> getSeriesByInventorySubinventoryLocator(@Path("physicalInventoryId") Long physicalInventoryId,@Path("subinventory") String subinventory,@Path("locatorId") Long locatorId);

    @GET("/MtlPhysicalInventoryTags/getSeriesByInventorySubinventoryLocatorSegment/{physicalInventoryId}/{subinventory}/{locatorId}/{segment}")
    Call<List<String>> getSeriesByInventorySubinventoryLocatorSegment(@Path("physicalInventoryId") Long physicalInventoryId,@Path("subinventory") String subinventory,@Path("locatorId") Long locatorId,@Path("segment") String segment);

    @GET("/MtlPhysicalInventoryTags/getLotesByInventorySubinventory/{physicalInventoryId}/{subinventory}")
    Call<List<String>> getLotesByInventorySubinventory(@Path("physicalInventoryId") Long physicalInventoryId,@Path("subinventory") String subinventory);

    @GET("/MtlPhysicalInventoryTags/getLotesByInventorySubinventoryLocatorSegment/{physicalInventoryId}/{subinventory}/{locatorId}/{segment}")
    Call<List<String>> getLotesByInventorySubinventoryLocatorSegment(@Path("physicalInventoryId") Long physicalInventoryId,@Path("subinventory") String subinventory,@Path("locatorId") Long locatorId,@Path("segment") String segment);

    @GET("/MtlPhysicalInventoryTags/getVencimientosByInventorySubinventoryLocatorSegment/{physicalInventoryId}/{subinventory}/{locatorId}/{segment}")
    Call<List<String>> getVencimientosByInventorySubinventoryLocatorSegment(@Path("physicalInventoryId") Long physicalInventoryId,@Path("subinventory") String subinventory,@Path("locatorId") Long locatorId,@Path("segment") String segment);

}
