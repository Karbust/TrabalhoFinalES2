import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class TestListaServidores {
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
    public void testListaServidoresPrivateConstructor() throws NoSuchMethodException {
        assertTrue(Modifier.isPrivate(ListaServidores.class.getDeclaredConstructor().getModifiers()));
    }

    @Test
    public void testListaServidoresStaticSingletonMethod() throws NoSuchMethodException, SecurityException {
        assertTrue(Modifier.isStatic(ListaServidores.class.getDeclaredMethod("getInstance").getModifiers()));
    }

    @Test
    public void testListaServidoresStaticSingletonField() throws NoSuchFieldException, SecurityException {
        assertTrue(Modifier.isStatic(ListaServidores.class.getDeclaredField("instance").getModifiers()));
    }

    @Test
    public void testListaServidoresServidores() {
        assertNull(ListaServidores.getInstance().getServidores());

        HashMap<String, Servidor> lista_servidores = new HashMap<>();
        lista_servidores.put("Portugal", servidor);

        ListaServidores.getInstance().setServidores(lista_servidores);
        assertEquals(lista_servidores, ListaServidores.getInstance().getServidores());

        HashMap<String, Servidor> lista_servidores1 = new HashMap<>(lista_servidores);
        lista_servidores1.put("Portugal", servidor);

        Servidor servidor_new = new Servidor("Espanha", editoras_livros);
        lista_servidores1.put("Espanha", servidor_new);

        assertNotEquals(lista_servidores1, ListaServidores.getInstance().getServidores());
    }
}
