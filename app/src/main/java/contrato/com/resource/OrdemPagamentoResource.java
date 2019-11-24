package contrato.com.resource;

import java.util.List;

import contrato.com.model.OrdemPagamento;
import contrato.com.model.OrdemServico;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface OrdemPagamentoResource {
    @GET("ordempagamento")
    Call<List<OrdemPagamento>> get();

    @GET("ordempagamento/{id}")
    Call<OrdemPagamento> getPorId(@Path("id") Long id);

    @POST("ordempagamento")
    Call<OrdemPagamento> post(@Body OrdemPagamento ordemPagamento);

    @PUT("ordempagamento/{id}")
    Call<OrdemPagamento> put(@Path("id") Long id, @Body OrdemPagamento ordemPagamento);

}
