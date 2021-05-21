public class Localizacao {
    private String morada;
    private String cod_postal;
    private String localidade;
    private String pais;

    public Localizacao(String morada, String cod_postal, String localidade, String pais) {
        this.morada = morada;
        this.cod_postal = cod_postal;
        this.localidade = localidade;
        this.pais = pais;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getCod_postal() {
        return cod_postal;
    }

    public void setCod_postal(String cod_postal) {
        this.cod_postal = cod_postal;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
