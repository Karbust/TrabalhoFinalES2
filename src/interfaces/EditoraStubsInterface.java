package interfaces;

import types.Editora;

import java.util.ArrayList;

public interface EditoraStubsInterface {
    ArrayList<Editora> lerEditoras();

    Editora lerEditora(Integer id);

    boolean registarEditora(Editora editora);

    boolean atualizarEditora(Integer id, Editora editora);

    boolean apagarEditora(Integer id);
}
