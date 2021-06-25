package interfaces;

import types.Utilizador;

import java.util.ArrayList;

public interface UtilizadorInterface {
    boolean autenticarUtilizador(Utilizador utilizador) throws Exception;

    ArrayList<Utilizador> lerUtilizadores();

    Utilizador lerUtilizador(Integer id);

    boolean registarUtilizador(Utilizador utilizador);

    boolean atualizarUtilizador(Integer id, Utilizador utilizador);

    boolean apagarUtilizador(Integer id);
}
