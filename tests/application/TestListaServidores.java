package application;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import types.Ebook;
import types.Editora;
import types.ListaServidores;
import types.Servidor;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class TestListaServidores {
    static Editora editora;
    static Ebook ebook;
    static Servidor servidor;
    static HashSet<Ebook> ebooksEditora = new HashSet<>();
    static HashSet<Ebook> ebooksSemEditora = new HashSet<>();
    static HashMap<Editora, HashSet<Ebook>> editoras_livros = new HashMap<>();

    @BeforeAll
    static void setUpBeforeAll() throws Exception {
        editora = new Editora(
                "O'Reilly",
                "CA",
                "Termos Teste"
        );
        ebook = new Ebook(
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

        ebooksEditora.add(ebook);

        editoras_livros.put(editora, ebooksEditora);

        servidor = new Servidor(
                "PT",
                editoras_livros
        );

        ebook = new Ebook(
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

        ebooksSemEditora.add(ebook);

        servidor.setEbooksSemEditora(ebooksSemEditora);

        /*for (Ebook ebookTest : servidor.getLivros_editora(editora)) {
            System.out.println(ebookTest.getTitulo());
        }

        for (Ebook ebookTest : servidor.getEbooksSemEditora()) {
            System.out.println(ebookTest.getTitulo());
        }*/
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
    public void testListaServidoresServidores() throws Exception {
        assertNull(ListaServidores.getInstance().getServidores());

        HashMap<String, Servidor> lista_servidores = new HashMap<>();
        lista_servidores.put("PT", servidor);

        ListaServidores.getInstance().setServidores(lista_servidores);
        assertEquals(lista_servidores, ListaServidores.getInstance().getServidores());

        HashMap<String, Servidor> lista_servidores1 = new HashMap<>(lista_servidores);
        lista_servidores1.put("PT", servidor);

        Servidor servidor_new = new Servidor("ES", editoras_livros);
        lista_servidores1.put("ES", servidor_new);

        assertNotEquals(lista_servidores1, ListaServidores.getInstance().getServidores());
    }
}
