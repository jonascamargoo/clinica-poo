package Model;

public class Endereco {
    private String logradouro;
    private String cidade;
    private String uf;
    private int numero;

    public String getLogradouro() {
        return this.logradouro;
    }

    public String getCidade() {
        return this.cidade;
    }

    public String getUf() {
        return this.uf;
    }

    public int getNumero() {
        return this.numero;
    }

    public Endereco(String logradouro, String cidade, String uf, int numero) {
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.uf = uf;
        this.numero = numero;
    };

    public Endereco(Endereco e) {
        this.logradouro = e.logradouro;
        this.cidade = e.cidade;
        this.uf = e.uf;
        this.numero = e.numero;
    }
}