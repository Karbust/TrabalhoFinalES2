import java.util.ArrayList;
import java.util.HashMap;

public class Servidor {
    private String pais;
    private HashMap<Editora, ArrayList<Ebook>> editoras_livros;

    public Servidor() { }

    public Servidor(String pais, HashMap<Editora, ArrayList<Ebook>> editoras_livros) {
        this.pais = pais;
        this.editoras_livros = editoras_livros;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public HashMap<Editora, ArrayList<Ebook>> getEditoras_livros() {
        return editoras_livros;
    }

    public void setEditoras_livros(HashMap<Editora, ArrayList<Ebook>> editoras_livros) {
        this.editoras_livros = editoras_livros;
    }
}
