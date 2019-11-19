package contrato.com.resource;

import java.util.List;

import contrato.com.model.Cliente;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ClienteResource {
    @GET("clientes")
    Call<List<Cliente>> get();

    @GET("clientes/{id}")
    Call<Cliente> getPorId(@Path("id") Integer id);

    @POST("clientes")
    Call<Cliente> post(@Body Cliente cliente);

    @PUT("clientes/{id}")
    Call<Cliente> put(@Path("id") Integer id, @Body Cliente cliente);

    @DELETE("clientes/{id}")
    Call<Void> delete(@Path("id") Integer id);

    @PATCH("clientes/{id}")
    Call<Cliente> patch(@Body Cliente cliente);

}
