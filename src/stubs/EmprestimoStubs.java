package stubs;

import types.*;

import java.util.*;

public class EmprestimoStubs {
    private static EmprestimoStubs instance;
    ArrayList<Emprestimo> emprestimos = new ArrayList<>();

    private EmprestimoStubs() throws Exception {
        HashSet<Ebook> ebooksEditora = new HashSet<>();
        HashSet<Ebook> ebooksSemEditora = new HashSet<>();
        HashMap<Editora, HashSet<Ebook>> editoras_livros = new HashMap<>();

        Editora editora = new Editora("O'Reilly", "CA", "Termos Teste");

        Ebook ebook = new Ebook(
                "4a97711f4533c45bd25c37a21de3d2f6e7dfcf11f801ebd78ef693f4d2bde484",
                49899830,
                "https://ebooks.karbust.me/Technology/Head First Design Patterns - Building Extensible and Maintainable Object-Oriented Software - Eric Freeman, Elisabeth Robson - O'Reilly Media (2020).pdf",
                "Head First Design Patterns - Building Extensible and Maintainable Object-Oriented Software",
                "Eric Freeman & Elisabeth Robson",
                editora,
                2,
                2020,
                672
        );

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

        ebooksEditora.add(ebook);
        ebooksSemEditora.add(ebook1);

        editoras_livros.put(editora, ebooksEditora);

        final Servidor servidor = new Servidor("PT", editoras_livros);
        servidor.setEbooksSemEditora(ebooksSemEditora);
        servidor.setEditoras_livros(editoras_livros);

        Utilizador utilizador = new Utilizador(
                "António",
                "PT",
                "antonio@teste.pt",
                "karbust",
                "testerino2",
                true
        );

        Emprestimo emprestimo = new Emprestimo(
                servidor,
                utilizador,
                ebook,
                new GregorianCalendar(2021, Calendar.JUNE, 24),
                new GregorianCalendar(2021, Calendar.JUNE, 25),
                true
        );
        Emprestimo emprestimo1 = new Emprestimo(
                servidor,
                utilizador,
                ebook1,
                new GregorianCalendar(2021, Calendar.JUNE, 24),
                new GregorianCalendar(2021, Calendar.JUNE, 25),
                true
        );

        emprestimos.add(emprestimo);
        emprestimos.add(emprestimo1);
    }

    public static EmprestimoStubs getInstance() throws Exception {
        if (instance == null) {
            synchronized (EmprestimoStubs.class) {
                if (instance == null) {
                    instance = new EmprestimoStubs();
                }
            }
        }
        return instance;
    }

    public ArrayList<Emprestimo> lerEmprestimos() {
        return emprestimos;
    }

    public Emprestimo lerEmprestimo(Integer id) {
        if (id != null && id > 0) {
            return emprestimos.get(id - 1);
        }
        return null;
    }

    public boolean registarEmprestimo(Emprestimo emprestimo) {
        if (emprestimo != null) {
            return emprestimos.add(emprestimo);
        }
        return false;
    }

    public boolean atualizarEmprestimo(Integer id, Emprestimo emprestimo) {
        if (emprestimo != null && id != null && id > 0){
            emprestimos.set(id - 1, emprestimo);
            return true;
        }
        return false;
    }

    public boolean apagarEmprestimo(Integer id) {
        if (id != null && id > 0 && id <= emprestimos.size()) {
            emprestimos.remove(id - 1);
            return true;
        }
        return false;
    }
}
