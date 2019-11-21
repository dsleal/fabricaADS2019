package contrato.com.resource;

import java.util.List;

import contrato.com.model.Solicitacao;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface SolicitacaoResource {
    @GET("solicitacoes")
    Call<List<Solicitacao>> get();

    @GET("solicitacoes/{id}")
    Call<Solicitacao> getPorId(@Path("id") Integer id);

    @POST("solicitacoes")
    Call<Solicitacao> post(@Body Solicitacao solicitacao);

    @PUT("solicitacoes/{id}")
    Call<Solicitacao> put(@Path("id") Integer id, @Body Solicitacao solicitacao);

    @DELETE("solicitacoes/{id}")
    Call<Void> delete(@Path("id") Integer id);

    @PATCH("solicitacoes/{id}")
    Call<Solicitacao> patch(@Body Solicitacao solicitacao);

}
