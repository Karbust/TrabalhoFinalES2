package stubs;

import types.Ebook;
import types.Editora;

import java.util.ArrayList;

public class EbookStubs {
    private static EbookStubs instance;
    ArrayList<Ebook> ebooks = new ArrayList<>();

    private EbookStubs() throws Exception {
        Ebook ebook1 = new Ebook(
                "6fecd6b78613f44394071bb81286f83fd355330d3dd755263760d265dc4404cd",
                33886081,
                "https://ebooks.karbust.me/Technology/Dive Into Design Patterns (2019) - Alexander Shvets.pdf",
                "Dive Into Design Patterns",
                "Alexander Shvets",
                null,
                1,
                2019,
                410
        );

        Ebook ebook2 = new Ebook(
                "4a97711f4533c45bd25c37a21de3d2f6e7dfcf11f801ebd78ef693f4d2bde484",
                49899830,
                "https://ebooks.karbust.me/Technology/Head First Design Patterns - Building Extensible and Maintainable Object-Oriented Software - Eric Freeman, Elisabeth Robson - O'Reilly Media (2020).pdf",
                "Head First Design Patterns - Building Extensible and Maintainable Object-Oriented Software",
                "Eric Freeman & Elisabeth Robson",
                new Editora(
                        "O'Reilly",
                        "CA",
                        "Termos Teste"
                ),
                2,
                2020,
                672
        );

        ebooks.add(ebook1);
        ebooks.add(ebook2);
    }

    public static EbookStubs getInstance() throws Exception {
        if (instance == null) {
            synchronized (EbookStubs.class) {
                if (instance == null) {
                    instance = new EbookStubs();
                }
            }
        }
        return instance;
    }

    public ArrayList<Ebook> lerEbooks() {
        return ebooks;
    }

    public Ebook lerEbook(Integer id) {
        if (id != null && id > 0) {
            return ebooks.get(id - 1);
        }
        return null;
    }

    public boolean registarEbook(Ebook ebook) {
        if (ebook != null) {
            return ebooks.add(ebook);
        }
        return false;
    }

    public boolean atualizarEbook(Integer id, Ebook ebook) {
        if (ebook != null && id != null && id > 0){
            ebooks.set(id - 1, ebook);
            return true;
        }
        return false;
    }

    public boolean apagarEbook(Integer id) {
        if (id != null && id > 0 && id <= ebooks.size()) {
            ebooks.remove(id - 1);
            return true;
        }
        return false;
    }
}
