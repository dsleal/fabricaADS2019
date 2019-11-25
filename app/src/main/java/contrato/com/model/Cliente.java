package contrato.com.model;


import java.io.Serializable;
import java.sql.Date;

public class Cliente implements Serializable {
    private long id;
    private String nome;
    private String cpf_cnpj;
    private String email;
    private Date dt_nascimento;
    private String identidade;
    private Endereco endereco;

    public Cliente() {
    }

    public Cliente(long id, String nome, String cpf_cnpj, String email, Date dt_nascimento, String identidade, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.cpf_cnpj = cpf_cnpj;
        this.email = email;
        this.dt_nascimento = dt_nascimento;
        this.identidade = identidade;
        this.endereco = endereco;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDt_nascimento() {
        return dt_nascimento;
    }

    public void setDt_nascimento(Date dt_nascimento) {
        this.dt_nascimento = dt_nascimento;
    }

    public String getIdentidade() {
        return identidade;
    }

    public void setIdentidade(String identidade) {
        this.identidade = identidade;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
