package contrato.com.resource;

import java.util.List;

import contrato.com.model.Prestador;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PrestadorResource {
    @GET("prestadores")
    Call<List<Prestador>> get();

    @GET("prestadores/{id}")
    Call<Prestador> getPorId(@Path("id") Integer id);

    @POST("prestadores")
    Call<Prestador> post(@Body Prestador prestador);

    @PUT("prestadores/{id}")
    Call<Prestador> put(@Path("id") Integer id, @Body Prestador prestador);

    @DELETE("prestadores/{id}")
    Call<Void> delete(@Path("id") Integer id);


}
