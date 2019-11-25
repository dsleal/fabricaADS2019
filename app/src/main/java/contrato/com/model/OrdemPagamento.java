package contrato.com.model;

import java.sql.Date;

public class OrdemPagamento {
    private Long id;
    private Date data;
    private Date dataPagamento;
    private Float valor;
    private OrdemServico ordemServico;

    public OrdemPagamento() {
    }

    public OrdemPagamento(Long id, Date data, Date dataPagamento, Float valor, OrdemServico ordemServico) {
        this.id = id;
        this.data = data;
        this.dataPagamento = dataPagamento;
        this.valor = valor;
        this.ordemServico = ordemServico;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public OrdemServico getOrdemServico() {
        return ordemServico;
    }

    public void setOrdemServico(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
    }
}
