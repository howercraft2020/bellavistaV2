package cl.clsoft.bave.apis;

import cl.clsoft.bave.model.DatosCabeceraRecepcion;
import cl.clsoft.bave.model.DatosRecepcion;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
public interface IRestDatosRecepcion {


    @GET("DatosCabeceraRecepcion/get/{poHeaderId}/{receiptNum}")
    Observable<DatosCabeceraRecepcion> get(@Path("poHeaderId") Long poHeaderId, @Path("receiptNum") Long receiptNum);

    @GET("DatosRecepcion/getDatosRecepcion/{segment1}/{oc}/{receiptNum}/{poLineNum}")
    Observable<DatosRecepcion> getDatosRecepcion(@Path("segment1")  String segment1,@Path("oc") String oc,@Path("receiptNum") Long receiptNum,@Path("poLineNum") Long poLineNum);

    @GET("DatosCabeceraRecepcion/get/{poHeaderId}/{receiptNum}")
    Call<DatosCabeceraRecepcion> getV2(@Path("poHeaderId") Long poHeaderId, @Path("receiptNum") Long receiptNum);

    @GET("DatosRecepcion/getDatosRecepcion/{segment1}/{oc}/{receiptNum}/{poLineNum}")
    Call<DatosRecepcion> getDatosRecepcionV2(@Path("segment1")  String segment1,@Path("oc") String oc,@Path("receiptNum") Long receiptNum,@Path("poLineNum") Long poLineNum);


}
