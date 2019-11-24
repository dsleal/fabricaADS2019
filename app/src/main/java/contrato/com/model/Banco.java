package contrato.com.model;

public class Banco {
    private long cod;
    private String banco;

    public Banco() {
    }

    public Banco(long cod, String banco) {
        this.cod = cod;
        this.banco = banco;
    }

    public long getCod() {
        return cod;
    }

    public void setCod(long cod) {
        this.cod = cod;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }
}
