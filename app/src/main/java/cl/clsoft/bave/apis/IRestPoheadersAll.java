package cl.clsoft.bave.apis;
import java.util.List;

import cl.clsoft.bave.model.PoHeadersAll;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;

import retrofit2.Response;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IRestPoheadersAll {
    @GET("PoHeadersAll/getAll")
    Call<List<PoHeadersAll>> getAll();

    @GET("PoHeadersAll/getbyId/{po_header_id}")
    Call<PoHeadersAll> getbyId(@Path("po_header_id") Long po_header_id);

    @POST("PoHeadersAll/delete/{po_header_id}")
    Call<Void> delete(@Path("po_header_id") Long po_header_id);

    @POST("PoHeadersAll/delete/{po_header_id}")
    Observable<Void> deleteRx(@Path("po_header_id") Long po_header_id);

    @GET("PoHeadersAll/getAll")
    Call<ResponseBody>  getAllResponse();

}