package interfaces;

import types.Servidor;

import java.util.ArrayList;

public interface ServidorInterface {
    ArrayList<Servidor> lerServidores();

    Servidor lerServidor(Integer id);

    boolean registarServidor(Servidor servidor);

    boolean atualizarServidor(Integer id, Servidor servidor);

    boolean apagarEbook(Integer id);
}
