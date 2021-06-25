package stubs;

import types.Ebook;
import types.Editora;
import types.Servidor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ServidorStubs {
    private static ServidorStubs instance;
    ArrayList<Servidor> servidores = new ArrayList<>();

    private ServidorStubs() throws Exception {
        HashSet<Ebook> ebooksEditora1 = new HashSet<>();
        HashSet<Ebook> ebooksEditora2 = new HashSet<>();
        HashMap<Editora, HashSet<Ebook>> editoras_livros = new HashMap<>();

        Editora editora1 = new Editora("O'Reilly", "CA", "Termos Teste");
        Editora editora2 = new Editora("McGraw-Hill Education", "US", "Termos Teste");

        Ebook ebook1 = new Ebook(
                "4a97711f4533c45bd25c37a21de3d2f6e7dfcf11f801ebd78ef693f4d2bde484",
                49899830,
                "https://ebooks.karbust.me/Technology/Head First Design Patterns - Building Extensible and Maintainable Object-Oriented Software - Eric Freeman, Elisabeth Robson - O'Reilly Media (2020).pdf",
                "Head First Design Patterns - Building Extensible and Maintainable Object-Oriented Software",
                "Eric Freeman & Elisabeth Robson",
                editora1,
                2,
                2020,
                672
        );
        Ebook ebook2 = new Ebook(
                "8a5b035fc4f6c91c36a721014e376c2d39c8b87a15374355d6abade2441db949",
                30499265,
                "https://ebooks.karbust.me/Technology/Pressman R.,Maxim B. Software Engineering. A Practioner's Approach 8ed 2014.pdf",
                "Software Engineering. A Practioner's Approach",
                "Alexander Shvets",
                editora2,
                8,
                2014,
                977
        );

        ebooksEditora1.add(ebook1);
        ebooksEditora2.add(ebook2);

        editoras_livros.put(editora1, ebooksEditora1);
        editoras_livros.put(editora2, ebooksEditora2);

        final Servidor servidor1 = new Servidor("PT", editoras_livros);
        final Servidor servidor2 = new Servidor("ES", editoras_livros);

        servidores.add(servidor1);
        servidores.add(servidor2);
    }

    public static ServidorStubs getInstance() throws Exception {
        if (instance == null) {
            synchronized (ServidorStubs.class) {
                if (instance == null) {
                    instance = new ServidorStubs();
                }
            }
        }
        return instance;
    }

    public ArrayList<Servidor> lerServidores() {
        return servidores;
    }

    public Servidor lerServidor(Integer id) {
        if (id != null && id > 0) {
            return servidores.get(id - 1);
        }
        return null;
    }

    public boolean registarServidor(Servidor servidor) {
        if (servidor != null) {
            return servidores.add(servidor);
        }
        return false;
    }

    public boolean atualizarServidor(Integer id, Servidor servidor) {
        if (servidor != null && id != null && id > 0){
            servidores.set(id - 1, servidor);
            return true;
        }
        return false;
    }

    public boolean apagarServidor(Integer id) {
        if (id != null && id > 0 && id <= servidores.size()) {
            servidores.remove(id - 1);
            return true;
        }
        return false;
    }
}
