package cl.clsoft.bave.apis;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IRestHomologacion {



    @GET("XXPDA_HOMOLOGACION_V/getHomologacion/{codBarra}")
    Call<String> getInventoryItemId(@Path("codBarra") String codBarra);
}

