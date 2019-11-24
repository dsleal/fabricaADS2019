package contrato.com.resource;

import java.util.List;

import contrato.com.model.OrdemServico;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface OrdemServicoResource {
    @GET("ordensservicos")
    Call<List<OrdemServico>> get();

    @GET("ordensservicos/{id}")
    Call<OrdemServico> getPorId(@Path("id") Long id);

    @POST("ordensservicos")
    Call<OrdemServico> post(@Body OrdemServico ordemServico);

    @PUT("ordensservicos/{id}")
    Call<OrdemServico> put(@Path("id") Long id, @Body OrdemServico ordemServico);


}
