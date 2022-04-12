package cl.clsoft.bave.apis;
import cl.clsoft.bave.model.Localizador;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import java.util.List;

public interface IRestLocalizador {


    @GET("Localizador/getLocalizadoresBySubinventario/{subinventarioCodigo}")
    Call<List<Localizador>> getLocalizadoresBySubinventario(@Path("subinventarioCodigo") String subinventarioCodigo);


    @GET("Localizador/get/{localizador}")
    Call<Long> get(@Path("localizador") String localizador);


    @GET("Localizador/get/{localizador}")
    Observable<Long> getRx(@Path("localizador") String localizador);


    @GET("Localizador/getId/{id}")
    Call<Localizador> getId(@Path("id") Long id);

    @GET("Localizador/getByCodigo/{codigo}")
    Call<Localizador> getByCodigo(@Path("codigo") String codigo);



    @GET("Localizador/getByCodigo/{codigo}")
    Observable<Localizador> getByCodigoRx(@Path("codigo") String codigo);


    @GET("Localizador/getAllBySubinventarioCountheaderId/{subinventarioCodigo}/{cycleCountHeaderId}")
    Call<List<Localizador>> getAllBySubinventarioCountheaderId(@Path("subinventarioCodigo") String subinventarioCodigo,@Path("cycleCountHeaderId") Long cycleCountHeaderId);
}
