package interfaces;

import types.Utilizador;

import java.util.ArrayList;

public interface UtilizadorStubsInterface {
    boolean autenticarUtilizador(String nomeUtilizador, String password) throws Exception;

    ArrayList<Utilizador> lerUtilizadores();

    Utilizador lerUtilizador(Integer id);

    boolean registarUtilizador(String nome, String pais, String email, String nome_utilizador, String password, boolean status) throws Exception;

    boolean atualizarUtilizador(Integer id, Utilizador utilizador);

    boolean apagarUtilizador(Integer id);

    boolean editarStatusUtilizador(Integer id, boolean status);
}
