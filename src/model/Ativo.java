package model;

public class Ativo {

    private Long id;
    private String ticker, alocacao, precoentrada;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getAlocacao() {
        return alocacao;
    }

    public void setAlocacao(String alocacao) {
        this.alocacao = alocacao;
    }

    public String getPrecoEntrada() {
        return precoentrada;
    }

    public void setPrecoEntrada(String precoentrada) {
        this.precoentrada = precoentrada;
    }

    @Override
    public String toString() {
        return "Ativo{" +
                "id=" + id +
                ", ticker='" + ticker + '\'' +
                ", alocacao='" + alocacao + '\'' +
                ", precoentrada='" + precoentrada + '\'' +
                '}';
    }
}
