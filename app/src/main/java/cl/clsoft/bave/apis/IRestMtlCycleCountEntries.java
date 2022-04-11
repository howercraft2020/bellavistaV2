package cl.clsoft.bave.apis;
import cl.clsoft.bave.model.MtlCycleCountEntries;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import java.util.List;
public interface IRestMtlCycleCountEntries {




    @POST("MtlCycleCountEntries/insert")
    Call<Void> insert(@Body MtlCycleCountEntries mtlCycleCountEntries);

    @POST("MtlCycleCountEntries/delete/{cycleCountEntryId}")
    Call<Void> delete(@Path("cycleCountEntryId") Long cycleCountEntryId);

    @POST("MtlCycleCountEntries/deleteByHeader/{headerId}")
    Call<Void> deleteByHeader(@Path("headerId") Long headerId);

    @GET("MtlCycleCountEntries/get/{cycleCountEntryId}")
    Call<MtlCycleCountEntries> get(@Path("cycleCountEntryId") Long cycleCountEntryId);

    @GET("MtlCycleCountEntries/getAll")
    Call<List<MtlCycleCountEntries>> getAll();

    @GET("MtlCycleCountEntries/getAllBySubinventarioLocatorSegmentLoteSerie/{countHeaderId}/{subinventory}/{locatorId}/{segment}/{serie}/{lote}")
    Call<List<MtlCycleCountEntries>> getAllBySubinventarioLocatorSegmentLoteSerie(@Path("countHeaderId") Long countHeaderId,@Path("subinventory") String subinventory,@Path("locatorId") Long locatorId,@Path("segment") String segment,@Path("serie") String serie,@Path("lote") String lote);

    @GET("MtlCycleCountEntries/getAllBySubinventarioSegmentLoteSerie/{countHeaderId}/{subinventory}/{segment}/{serie}/{lote}")
    Call<List<MtlCycleCountEntries>> getAllBySubinventarioSegmentLoteSerie(@Path("countHeaderId") Long countHeaderId,@Path("subinventory") String subinventory,@Path("segment") String segment,@Path("serie") String serie,@Path("lote") String lote);

    @GET("MtlCycleCountEntries/getAllInventariadosByHeader/{countHeaderId}")
    Call<List<MtlCycleCountEntries>> getAllInventariadosByHeader(@Path("countHeaderId") Long countHeaderId);

    @GET("MtlCycleCountEntries/getAllInventariadosBySubinventario/{countHeaderId}/{subinventory}")
    Call<List<MtlCycleCountEntries>> getAllInventariadosBySubinventario(@Path("countHeaderId") Long countHeaderId,@Path("subinventory") String subinventory);

    @GET("MtlCycleCountEntries/getSegmentsByCountHeaderLocator/{countHeaderId}/{locatorId}")
    Call<List<String>> getSegmentsByCountHeaderLocator(@Path("countHeaderId") Long countHeaderId,@Path("locatorId") Long locatorId);


    @GET("MtlCycleCountEntries/getSegmentsByCountHeaderSubinventory/{countHeaderId}/{subinventory}")
    Call<List<String>> getSegmentsByCountHeaderSubinventory(@Path("countHeaderId") Long countHeaderId,@Path("subinventory") String subinventory);

    @GET("MtlCycleCountEntries/getSegmentsByCountHeaderSubinventoryLocator/{countHeaderId}/{subinventory}/{locatorId}")
    Call<List<String>> getSegmentsByCountHeaderSubinventoryLocator(@Path("countHeaderId") Long countHeaderId,@Path("subinventory") String subinventory,@Path("locatorId") Long locatorId);


    @GET("MtlCycleCountEntries/getLoteByCountHeaderLocatorSegment/{cycleCountHeaderId}/{locatorId}/{segment}")
    Call<List<String>> getLoteByCountHeaderLocatorSegment(@Path("cycleCountHeaderId") Long cycleCountHeaderId,@Path("locatorId") Long locatorId,@Path("segment") String segment);


    @GET("MtlCycleCountEntries/getLoteByCountHeaderSubinventoryLocatorSegment/{cycleCountHeaderId}/{subinventory}/{locatorId}/{segment}")
    Call<List<String>> getLoteByCountHeaderSubinventoryLocatorSegment(@Path("cycleCountHeaderId") Long cycleCountHeaderId,@Path("subinventory") String subinventory,@Path("locatorId") Long locatorId,@Path("segment") String segment);


    @GET("MtlCycleCountEntries/getLoteByCountHeaderSubinventorySegment/{cycleCountHeaderId}/{subinventory}/{segment}")
    Call<List<String>> getLoteByCountHeaderSubinventorySegment(@Path("cycleCountHeaderId") Long cycleCountHeaderId,@Path("subinventory") String subinventory,@Path("segment") String segment);

    @GET("MtlCycleCountEntries/getSerialByCountHeaderSubinventoryLocatorSegment/{cycleCountHeaderId}/{subinventory}/{locatorId}/{segment}")
    Call<List<String>> getSerialByCountHeaderSubinventoryLocatorSegment(@Path("cycleCountHeaderId") Long cycleCountHeaderId,@Path("subinventory") String subinventory,@Path("locatorId") Long locatorId,@Path("segment") String segment);

    @GET("MtlCycleCountEntries/getSerialByCountHeaderSubinventorySegment/{cycleCountHeaderId}/{subinventory}/{segment}")
    Call<List<String>> getSerialByCountHeaderSubinventorySegment(@Path("cycleCountHeaderId") Long cycleCountHeaderId,@Path("subinventory") String subinventory,@Path("segment") String segment);



}
