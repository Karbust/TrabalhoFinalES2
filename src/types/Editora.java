package types;

import Exceptions.InvalidCountryException;
import Exceptions.InvalidNomeException;
import Exceptions.NullFieldsException;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;

public class Editora {
    private String nome;
    private String pais;
    private String termos_responsabilidade;

    public Editora() { }

    public Editora(String nome, String pais, String termos_responsabilidade) throws Exception {
        if (nome == null || pais == null || termos_responsabilidade == null) {
            throw new NullFieldsException();
        }
        if (nome.length() == 0 || pais.length() == 0 || termos_responsabilidade.length() == 0) {
            throw new NullFieldsException();
        }
        if(nome.length() < 3 || nome.length() > 25) {
            throw new InvalidNomeException();
        }
        if (!Arrays.toString(Locale.getISOCountries()).contains(pais)) {
            throw new InvalidCountryException();
        }
        this.nome = nome;
        this.pais = pais;
        this.termos_responsabilidade = termos_responsabilidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws Exception {
        if (nome == null || nome.length() == 0) {
            throw new NullFieldsException();
        }
        this.nome = nome;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) throws Exception {
        if (!Arrays.toString(Locale.getISOCountries()).contains(pais)) {
            throw new InvalidCountryException();
        }
        this.pais = pais;
    }

    public String getTermos_responsabilidade() {
        return termos_responsabilidade;
    }

    public void setTermos_responsabilidade(String termos_responsabilidade) throws Exception {
        if (termos_responsabilidade == null || termos_responsabilidade.length() == 0) {
            throw new NullFieldsException();
        }
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

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
