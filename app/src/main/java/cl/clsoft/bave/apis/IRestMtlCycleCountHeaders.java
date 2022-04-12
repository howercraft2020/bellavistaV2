package cl.clsoft.bave.apis;
import cl.clsoft.bave.model.MtlCycleCountHeaders;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import java.util.List;
public interface IRestMtlCycleCountHeaders {


    @POST("MtlCycleCountHeaders/insert")
    Call<Void> insert(@Body MtlCycleCountHeaders mtlCycleCountHeaders);

    @POST("MtlCycleCountHeaders/delete/{cycle_count_header_id}")
    Call<Void> delete(@Path("cycle_count_header_id") Long cycle_count_header_id);

    @GET("MtlCycleCountHeaders/get/{cycle_count_header_id}")
    Call<MtlCycleCountHeaders> get(@Path("cycle_count_header_id") Long cycle_count_header_id);

    @GET("MtlCycleCountHeaders/getAll")
    Call<List<MtlCycleCountHeaders>> getAll();
}
