package application;

import Exceptions.InvalidCountryException;
import Exceptions.NullFieldsException;
import org.junit.jupiter.api.*;
import stubs.ServidorStubs;
import types.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestServidor {
    static Ebook ebook;
    static Editora editora;
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
    public void testServidorObjectCreationOnlyCountry() throws Exception {
        new Servidor("PT");

        assertThrows(
                InvalidCountryException.class,
                () -> new Servidor("Portugal")
        );
    }

    @Test
    public void testServidorObjectCreationEbooksEditoras() throws Exception {
        new Servidor("PT", editoras_livros);

        assertThrows(
                InvalidCountryException.class,
                () -> new Servidor(
                        "Portugal",
                        editoras_livros
                )
        );

        assertThrows(
                NullFieldsException.class,
                () -> new Servidor(
                        "PT",
                        (HashMap<Editora, HashSet<Ebook>>) null
                )
        );
    }

    @Test
    public void testServidorObjectCreationEbooksSemEditoras() throws Exception {
        new Servidor("PT", ebooksSemEditora);

        assertThrows(
                InvalidCountryException.class,
                () -> new Servidor(
                        "Portugal",
                        ebooksSemEditora
                )
        );

        assertThrows(
                NullFieldsException.class,
                () -> new Servidor(
                        "PT",
                        (HashSet<Ebook>) null
                )
        );
    }

    @Test
    public void testServidorIgualdade() throws Exception {
        Servidor servidor_new = new Servidor(
                "ES",
                ebooksSemEditora
        );
        assertNotEquals(servidor, servidor_new);
        assertEquals(servidor, servidor);
        assertNotEquals(servidor, new Empty());
    }

    @Test
    public void testServidorPais() throws Exception {
        assertEquals("PT", servidor.getPais());

        servidor.setPais("ES");
        assertEquals("ES", servidor.getPais());
        assertNotEquals("PT", servidor.getPais());

        assertThrows(
                InvalidCountryException.class,
                () -> servidor.setPais("Portugal")
        );

        //Voltar ao estado anterior
        servidor.setPais("PT");
    }

    @Test
    public void testServidorEbooksEditora() throws Exception {
        assertEquals(editoras_livros, servidor.getEditoras_livros());

        Editora editora_new = new Editora("McGraw-Hill Education", "US", "Termos Teste");

        Ebook ebook_new = new Ebook(
                "8a5b035fc4f6c91c36a721014e376c2d39c8b87a15374355d6abade2441db949",
                30499265,
                "https://ebooks.karbust.me/Technology/Pressman R.,Maxim B. Software Engineering. A Practioner's Approach 8ed 2014.pdf",
                "Software Engineering. A Practioner's Approach",
                "Alexander Shvets",
                editora_new,
                8,
                2014,
                977
        );

        HashSet<Ebook> books_new = new HashSet<>();
        books_new.add(ebook_new);

        HashMap<Editora, HashSet<Ebook>> editoras_livros_new = new HashMap<>();
        editoras_livros_new.put(editora_new, books_new);

        servidor.setEditoras_livros(editoras_livros_new);

        //Tentar adicionar uma editora que já existe - Coverage
        servidor.setEditoras_livros(editoras_livros);

        assertThrows(
                NullFieldsException.class,
                () -> servidor.setEditoras_livros(null)
        );
        assertEquals(editoras_livros, servidor.getEditoras_livros());
        assertNotEquals(new HashMap<Editora, ArrayList<Ebook>>(), servidor.getEditoras_livros());

        assertEquals(new HashSet<>(), servidor.getLivros_editora(null));
        assertEquals(ebooksEditora, servidor.getLivros_editora(editora));
    }

    @Test
    public void testServidorEbooksSemEditora() throws Exception {
        assertEquals(ebooksSemEditora, servidor.getEbooksSemEditora());

        Ebook ebook_new = new Ebook(
                "bc2730e6ff45d413ddabbcecffc6a8c9e2ccf1c45249066f42bf63c02dab30eb",
                15536421,
                "https://ebooks.karbust.me/Technology/Docker Deep Dive - Nigel Poulton.pdf",
                "Docker Deep Dive",
                "Nigel Poulton",
                null,
                1,
                2018,
                419
        );

        ebooksSemEditora.add(ebook_new);

        servidor.setEbooksSemEditora(ebooksSemEditora);
        assertEquals(ebooksSemEditora, servidor.getEbooksSemEditora());
        assertNotEquals(new ArrayList<>(), servidor.getEbooksSemEditora());
        assertThrows(
                NullFieldsException.class,
                () -> servidor.setEbooksSemEditora(null)
        );
    }

    @Test
    @Order(1)
    public void testLerServidores() throws Exception {
        assertTrue(ServidorStubs.getInstance().lerServidores().contains(servidor));
    }

    @Test
    @Order(2)
    public void testLerServidor() throws Exception {
        assertEquals(servidor, ServidorStubs.getInstance().lerServidor(1));
        assertNull(ServidorStubs.getInstance().lerServidor(null));
        assertNull(ServidorStubs.getInstance().lerServidor(-1));
    }

    @Test
    @Order(3)
    public void testCriarServidorValid() throws Exception {
        Servidor servidorNew = new Servidor(
                "FR",
                editoras_livros
        );
        assertTrue(ServidorStubs.getInstance().registarServidor(servidorNew));
        assertFalse(ServidorStubs.getInstance().registarServidor(servidor));
        assertFalse(ServidorStubs.getInstance().registarServidor(null));
    }

    @Test
    @Order(4)
    public void testAtualizarServidor() throws Exception {
        assertTrue(ServidorStubs.getInstance().atualizarServidor(1, new Servidor("ES")));
        assertFalse(ServidorStubs.getInstance().atualizarServidor(null, new Servidor("ES")));
        assertFalse(ServidorStubs.getInstance().atualizarServidor(-1 , new Servidor("ES")));
        assertFalse(ServidorStubs.getInstance().atualizarServidor(1, null));
    }

    @Test
    @Order(5)
    public void testApagarServidor() throws Exception {
        assertTrue(ServidorStubs.getInstance().apagarServidor(1));
        assertFalse(ServidorStubs.getInstance().apagarServidor(null));
        assertFalse(ServidorStubs.getInstance().apagarServidor(-1));
    }
}
