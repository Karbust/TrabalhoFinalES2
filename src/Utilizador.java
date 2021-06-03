import Exceptions.*;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.Arrays;
import java.util.Locale;

public class Utilizador {
    private String nome;
    private String pais;
    private String email;
    private String nome_utilizador;
    private String password;
    private Boolean status;
    //private HashMap<Editora, HashMap<Boolean, Boolean>> termos_responsabilidade; //<Editora, {PrimeiraVez, Aceite} >

    public Utilizador() { }

    public Utilizador(String nome, String pais, String email, String nome_utilizador, String password, Boolean status/*, HashMap<Editora, HashMap<Boolean, Boolean>> termos_responsabilidade*/) throws Exception {
        if (nome == null || pais == null || email == null || nome_utilizador == null || password == null) {
            throw new NullFieldsException();
        }

        if (nome.length() == 0 || pais.length() == 0 || email.length() == 0 || nome_utilizador.length() == 0 || password.length() == 0) {
            throw new NullFieldsException();
        }

        if (!isValidEmail(email)) {
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
        //this.termos_responsabilidade = termos_responsabilidade;
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

    public void setPais(String pais) throws InvalidCountryException {
        if (!Arrays.toString(Locale.getISOCountries()).contains(pais)) {
            throw new InvalidCountryException();
        }

        this.pais = pais;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome_utilizador() {
        return nome_utilizador;
    }

    public void setNome_utilizador(String nome_utilizador) {
        this.nome_utilizador = nome_utilizador;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    /*public HashMap<Editora, HashMap<Boolean, Boolean>> getTermos_responsabilidade() {
        return termos_responsabilidade;
    }

    public void setTermos_responsabilidade(HashMap<Editora, HashMap<Boolean, Boolean>> termos_responsabilidade) {
        this.termos_responsabilidade = termos_responsabilidade;
    }*/

    public Boolean autenticacao(String nome_utilizador, String password) {
        return nome_utilizador.equals(this.nome_utilizador) && password.equals(this.password);
    }

    private static boolean isValidEmail(String email) {
        EmailValidator validator = EmailValidator.getInstance();

        return validator.isValid(email);
    }
}
