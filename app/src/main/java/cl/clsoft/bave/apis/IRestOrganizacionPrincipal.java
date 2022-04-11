package cl.clsoft.bave.apis;

import cl.clsoft.bave.model.OrganizacionPrincipal;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
public interface IRestOrganizacionPrincipal {

    @GET("OrganizacionPrincipal/getId/{id}")
    Call <OrganizacionPrincipal> getId(@Path("id") Long id);


    @GET("OrganizacionPrincipal/get")
    Call<OrganizacionPrincipal> getAll();


    @POST("OrganizacionPrincipal/insert")
    Call<Void> insert(@Body OrganizacionPrincipal organizacionPrincipal);


    @GET("OrganizacionPrincipal/get")
    Observable<OrganizacionPrincipal> getAllRx();



}
