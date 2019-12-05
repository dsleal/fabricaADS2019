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
import retrofit2.http.Path;

public interface TipoPrestadorResource {
    @GET("tipoprestadores")
    Call<List<TipoPrestador>> getAtivos();

    @GET("tipoprestadores/todos")
    Call<List<TipoPrestador>> getTodos();

    @GET("tipoprestadores/{id}")
    Call<TipoPrestador> getPorId(@Path("id") Integer id);

    @POST("tipoprestadores")
    Call<TipoPrestador> post(@Body TipoPrestador tipoPrestador);

    @PUT("tipoprestadores/{id}")
    Call<TipoPrestador> put(@Path("id") Integer id, @Body TipoPrestador tipoPrestador);

    @DELETE("tipoprestadores/{id}")
    Call<Void> delete(@Path("id") Integer id);


}
