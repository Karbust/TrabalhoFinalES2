import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

class TestServidor {
    static Editora editora;
    static Ebook ebook;
    static Servidor servidor;
    static ArrayList<Ebook> books = new ArrayList<>();
    static HashMap<Editora, ArrayList<Ebook>> editoras_livros = new HashMap<>();

    @BeforeAll
    static void setUpBeforeAll() throws Exception {
        editora = new Editora("Editora XPTO", "Portugal", "Termos Teste");
        ebook = new Ebook(
                "6fecd6b78613f44394071bb81286f83fd355330d3dd755263760d265dc4404cd",
                33886081,
                "https://ebooks.karbust.me/Technology/Dive Into Design Patterns (2019) - Alexander Shvets.pdf",
                "Dive Into Design Patterns",
                "Alexander Shvets",
                editora,
                1,
                2019,
                410
        );

        books.add(ebook);

        editoras_livros.put(editora, books);

        servidor = new Servidor(
                "Portugal",
                editoras_livros
        );
    }

    @Test
    public void testServidorObjectCreationEmpty() {
        new Servidor();
    }

    @Test
    public void testServidorObjectCreation() {
        new Servidor("Portugal", editoras_livros);
    }

    @Test
    public void testServidorPais() {
        assertEquals("Portugal", servidor.getPais());

        servidor.setPais("Espanha");
        assertEquals("Espanha", servidor.getPais());
        assertNotEquals("Alguma Coisa", servidor.getPais());
    }

    @Test
    public void testServidorEditorasLivros() throws Exception {
        assertEquals(editoras_livros, servidor.getEditoras_livros());

        Editora editora_new = new Editora("McGraw-Hill Education", "Estados Unidos", "Termos Teste");

        Ebook ebook_new = new Ebook(
                "8a5b035fc4f6c91c36a721014e376c2d39c8b87a15374355d6abade2441db949",
                30499265,
                "https://ebooks.karbust.me/Technology/Pressman%20R.,Maxim%20B.%20Software%20Engineering.%20A%20Practioner's%20Approach%208ed%202014.pdf",
                "Software Engineering. A Practioner's Approach",
                "Alexander Shvets",
                editora_new,
                8,
                2014,
                977
        );

        ArrayList<Ebook> books_new = new ArrayList<>();
        books_new.add(ebook_new);
        editoras_livros.put(editora_new, books_new);

        servidor.setEditoras_livros(editoras_livros);
        assertEquals(editoras_livros, servidor.getEditoras_livros());
        assertNotEquals(new HashMap<Editora, ArrayList<Ebook>>(), servidor.getEditoras_livros());
    }
}
