package cl.clsoft.bave.apis;
import cl.clsoft.bave.model.Subinventario;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import java.util.List;
public interface IRestSubinventario {

@GET("Subinventario/getAll")
Call<List<Subinventario>> getAll();

@GET("Subinventario/getByCodigo/{codigo}")
Call<Subinventario> getByCodigo(@Path("codigo") String codigo);


@GET("Subinventario/getByCodigo/{codigo}")
Observable<Subinventario> getByCodigoRx(@Path("codigo") String codigo);


@GET("Subinventario/getAllByCiclico/{conteoCiclicoId}")
Call<List<Subinventario>> getAllByCiclico(@Path("conteoCiclicoId") Long conteoCiclicoId);

}
