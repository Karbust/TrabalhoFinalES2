package stubs;

import Exceptions.*;
import interfaces.UtilizadorStubsInterface;
import types.Utilizador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class UtilizadorStubs implements UtilizadorStubsInterface {
    private static UtilizadorStubs instance;
    ArrayList<Utilizador> utilizadores = new ArrayList<>();

    private UtilizadorStubs() throws Exception {
        final Utilizador utilizador1 = new Utilizador(
                "Adriano",
                "PT",
                "adriano@teste.pt",
                "notoriousadri",
                "testerino1",
                true
        );
        final Utilizador utilizador2 = new Utilizador(
                "António",
                "PT",
                "antonio@teste.pt",
                "karbust",
                "testerino2",
                true
        );
        final Utilizador utilizador3 = new Utilizador(
                "Marta",
                "PT",
                "marta@teste.pt",
                "simplazygirl",
                "testerino3",
                true
        );
        final Utilizador utilizador4 = new Utilizador(
                "Vitor",
                "PT",
                "vitor@teste.pt",
                "yogurte",
                "testerino4",
                true
        );

        utilizadores.add(utilizador1);
        utilizadores.add(utilizador2);
        utilizadores.add(utilizador3);
        utilizadores.add(utilizador4);
    }

    public static UtilizadorStubs getInstance() throws Exception {
        if (instance == null) {
            synchronized (UtilizadorStubs.class) {
                if (instance == null) {
                    instance = new UtilizadorStubs();
                }
            }
        }
        return instance;
    }

    public boolean autenticarUtilizador(String nomeUtilizador, String password) {
        for (Utilizador utilizadorTemp : utilizadores) {
            if (utilizadorTemp.autenticacao(nomeUtilizador, password)) {
                return true;
            }
        }

        return false;
    }

    public ArrayList<Utilizador> lerUtilizadores() {
        return utilizadores;
    }

    public Utilizador lerUtilizador(Integer id) {
        if (id != null && id > 0) {
            return utilizadores.get(id - 1);
        }
        return null;
    }

    public boolean registarUtilizador(String nome, String pais, String email, String nome_utilizador, String password, boolean status) throws Exception {
        if (nome == null || pais == null || email == null || nome_utilizador == null || password == null) {
            throw new NullFieldsException();
        }

        if (nome.length() == 0 || pais.length() == 0 || email.length() == 0 || nome_utilizador.length() == 0 || password.length() == 0) {
            throw new NullFieldsException();
        }

        if (nome.length() < 2 || nome.length() > 15) {
            throw new InvalidUsernameException();
        }

        if (Utilizador.isInvalidEmail(email)) {
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

        Utilizador newUser = new Utilizador(nome, pais, email, nome_utilizador, password, status);

        if (utilizadores.contains(newUser)) {
            return false;
        }

        return utilizadores.add(newUser);
    }

    public boolean atualizarUtilizador(Integer id, Utilizador utilizador) {
        if (utilizador != null && id != null && id > 0){
            utilizadores.set(id - 1, utilizador);
            return true;
        }
        return false;
    }

    public boolean apagarUtilizador(Integer id) {
        if (id != null && id > 0 && id <= utilizadores.size()) {
            utilizadores.remove(id - 1);
            return true;
        }
        return false;
    }

    public boolean editarStatusUtilizador(Integer id, boolean status) {
        if (id != null && id > 0 && id <= utilizadores.size()) {
            utilizadores.get(id - 1).setStatus(status);
            return true;
        }
        return false;
    }
}
