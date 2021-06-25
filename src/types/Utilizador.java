package types;

import Exceptions.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.Arrays;
import java.util.Locale;

public class Utilizador {
    private String nome;
    private String pais;
    private String email;
    private String nome_utilizador;
    private String password;
    private boolean status;

    public Utilizador() { }

    public Utilizador(String nome, String pais, String email, String nome_utilizador, String password, boolean status/*, HashMap<types.Editora, HashMap<Boolean, Boolean>> termos_responsabilidade*/) throws Exception {
        if (nome == null || pais == null || email == null || nome_utilizador == null || password == null) {
            throw new NullFieldsException();
        }

        if (nome.length() == 0 || pais.length() == 0 || email.length() == 0 || nome_utilizador.length() == 0 || password.length() == 0) {
            throw new NullFieldsException();
        }

        if (nome.length() < 2 || nome.length() > 15) {
            throw new InvalidUsernameException();
        }

        if (isInvalidEmail(email)) {
            throw new InvalidEmailException();
        }

        if (!password.matches(".*\\d.*") || password.length() < 8) {
            throw new InvalidPasswordException();
        }

        if (nome_utilizador.length() < 3 || nome_utilizador.length() > 15) {
            throw new InvalidUsernameException();
        }

        if (!Arrays.toString(Locale.getISOCountries()).contains(pais)) {
            throw new InvalidCountryException();
        }

        this.nome = nome;
        this.pais = pais;
        this.email = email;
        this.nome_utilizador = nome_utilizador;
        this.password = password;
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws NullFieldsException, InvalidNomeException {
        if (nome == null || nome.length() == 0) {
            throw new NullFieldsException();
        }

        if (nome.length() < 2 || nome.length() > 15) {
            throw new InvalidNomeException();
        }

        this.nome = nome;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) throws InvalidCountryException {
        if (!Arrays.toString(Locale.getISOCountries()).contains(pais)) {
            throw new InvalidCountryException();
        }

        this.pais = pais;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws NullFieldsException, InvalidEmailException {
        if (email == null || email.length() == 0) {
            throw new NullFieldsException();
        }

        if (isInvalidEmail(email)) {
            throw new InvalidEmailException();
        }

        this.email = email;
    }

    public String getNome_utilizador() {
        return nome_utilizador;
    }

    public void setNome_utilizador(String nome_utilizador) throws NullFieldsException, InvalidUsernameException {
        if (nome_utilizador == null || nome_utilizador.length() == 0) {
            throw new NullFieldsException();
        }

        if (nome_utilizador.length() < 3 || nome_utilizador.length() > 15) {
            throw new InvalidUsernameException();
        }

        this.nome_utilizador = nome_utilizador;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws NullFieldsException, InvalidPasswordException {
        if (password == null || password.length() == 0) {
            throw new NullFieldsException();
        }

        if (!password.matches(".*\\d.*") || password.length() < 8) {
            throw new InvalidPasswordException();
        }

        this.password = password;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean autenticacao(String nome_utilizador, String password) {
        return nome_utilizador.equals(this.nome_utilizador) && password.equals(this.password);
    }

    public static boolean isInvalidEmail(String email) {
        EmailValidator validator = EmailValidator.getInstance();

        return !validator.isValid(email);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if(!(o instanceof Utilizador that))
            return false;

        return new EqualsBuilder()
                .append(this.nome, that.nome)
                .append(this.pais, that.pais)
                .append(this.email, that.email)
                .append(this.nome_utilizador, that.nome_utilizador)
                .append(this.password, that.password)
                .append(this.status, status)
                .isEquals();
    }
}
