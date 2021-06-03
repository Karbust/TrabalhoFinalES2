import org.apache.commons.lang3.builder.EqualsBuilder;

public class Editora {
    private String nome;
    private String pais;
    private String termos_responsabilidade;

    public Editora() { }

    public Editora(String nome, String pais, String termos_responsabilidade) {
        this.nome = nome;
        this.pais = pais;
        this.termos_responsabilidade = termos_responsabilidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTermos_responsabilidade() {
        return termos_responsabilidade;
    }

    public void setTermos_responsabilidade(String termos_responsabilidade) {
        this.termos_responsabilidade = termos_responsabilidade;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if(!(o instanceof Editora that))
            return false;

        return new EqualsBuilder()
                .append(this.nome, that.nome)
                .append(this.pais, that.pais)
                .append(this.termos_responsabilidade, that.termos_responsabilidade)
                .isEquals();
    }
}
