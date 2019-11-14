package contrato.com.resource;

import java.util.List;

import contrato.com.model.TipoPrestador;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface TipoPrestadorResource {
    @GET("tipoprestadores")
    Call<List<TipoPrestador>> get();

    @POST("tipoprestadores")
    Call<TipoPrestador> post(@Body TipoPrestador tipoPrestador);

    @PUT("tipoprestadores/{id}")
    Call<TipoPrestador> put(@Body TipoPrestador tipoPrestador);

    @DELETE("tipoprestadores/{id}")
    Call<Void> delete(Integer id);

    @PATCH("tipoprestadores/{id}")
    Call<TipoPrestador> patch(Integer id, @Body TipoPrestador tipoPrestador);

}
