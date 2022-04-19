package cl.clsoft.bave.apis;

import cl.clsoft.bave.model.MtlPhysicalInventories;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

public interface IRestMtlPhysicalInventories {

    @POST("/MtlPhysicalInventories/insert")
    void insert(@Body MtlPhysicalInventories mtlPhysicalInventories);

    @POST("/MtlPhysicalInventories/delete/{id}")
    void delete(@Path("id") Long id);

    @GET("/MtlPhysicalInventories/get/{id}")
    MtlPhysicalInventories get(@Path("id") Long id);

    @GET("/MtlPhysicalInventories/getAll")
    Call<List<MtlPhysicalInventories>> getAll();

}
