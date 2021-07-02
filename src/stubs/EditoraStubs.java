package stubs;

import interfaces.EditoraStubsInterface;
import types.Editora;

import java.util.ArrayList;

public class EditoraStubs implements EditoraStubsInterface {
    private static EditoraStubs instance;
    ArrayList<Editora> editoras = new ArrayList<>();

    private EditoraStubs() throws Exception {
        Editora editora1 = new Editora("O'Reilly", "CA", "Termos Teste");
        Editora editora2 = new Editora("McGraw-Hill Education", "US", "Termos Teste");

        editoras.add(editora1);
        editoras.add(editora2);
    }

    public static EditoraStubs getInstance() throws Exception {
        if (instance == null) {
            synchronized (EditoraStubs.class) {
                if (instance == null) {
                    instance = new EditoraStubs();
                }
            }
        }
        return instance;
    }

    public ArrayList<Editora> lerEditoras() {
        return editoras;
    }

    public Editora lerEditora(Integer id) {
        if (id != null && id > 0) {
            return editoras.get(id - 1);
        }
        return null;
    }

    public boolean registarEditora(Editora editora) {
        if (editora != null) {
            if (editoras.contains(editora)) {
                return false;
            }
            return editoras.add(editora);
        }
        return false;
    }

    public boolean atualizarEditora(Integer id, Editora editora) {
        if (editora != null && id != null && id > 0){
            editoras.set(id - 1, editora);
            return true;
        }
        return false;
    }

    public boolean apagarEditora(Integer id) {
        if (id != null && id > 0 && id <= editoras.size()) {
            editoras.remove(id - 1);
            return true;
        }
        return false;
    }
}
