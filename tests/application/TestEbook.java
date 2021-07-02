package application;

import Exceptions.*;
import org.junit.jupiter.api.*;
import stubs.EbookStubs;
import types.Ebook;
import types.Editora;
import types.Empty;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestEbook {
    static Ebook ebook;

    @BeforeAll
    static void setUp() throws Exception {
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
    }

    @Test
    public void testEbookObjectCreationEmpty() {
        new Ebook();
    }

    @Test
    public void testEbookObjectCreation() throws Exception {
        new Ebook(
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

        new Ebook(
                "6fecd6b78613f44394071bb81286f83fd355330d3dd755263760d265dc4404cd",
                33886081,
                "https://ebooks.karbust.me/Technology/Dive Into Design Patterns (2019) - Alexander Shvets.epub",
                "Dive Into Design Patterns",
                "Alexander Shvets",
                null,
                1,
                2019,
                410
        );
    }

    @Test
    public void testEbookObjectCreationExceptions() throws Exception {
        assertThrows(
                NullFieldsException.class,
                () -> new Ebook(
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                )
        );

        assertThrows(
                NullFieldsException.class,
                () -> new Ebook(
                        "",
                        0,
                        "",
                        "",
                        "",
                        null,
                        0,
                        0,
                        0
                )
        );

        assertThrows(
                InvalidFieldNumberException.class,
                () -> new Ebook(
                        "6fecd6b78613f44394071bb81286f83fd355330d3dd755263760d265dc4404cd",
                        33886081,
                        "https://ebooks.karbust.me/Technology/Dive Into Design Patterns (2019) - Alexander Shvets.pdf",
                        "Dive Into Design Patterns",
                        "Alexander Shvets",
                        null,
                        -1,
                        -1,
                        -1
                )
        );

        assertThrows(
                InvalidLinkException.class,
                () -> new Ebook(
                        "6fecd6b78613f44394071bb81286f83fd355330d3dd755263760d265dc4404cd",
                        33886081,
                        "teste",
                        "Dive Into Design Patterns",
                        "Alexander Shvets",
                        null,
                        1,
                        2019,
                        410
                )
        );

        assertThrows(
                InvalidLinkException.class,
                () -> new Ebook(
                        "6fecd6b78613f44394071bb81286f83fd355330d3dd755263760d265dc4404cd",
                        33886081,
                        "teste",
                        "Dive Into Design Patterns",
                        "Alexander Shvets",
                        null,
                        1,
                        2019,
                        410
                )
        );

        assertThrows(
                InvalidEbookTitleException.class,
                () -> new Ebook(
                        "6fecd6b78613f44394071bb81286f83fd355330d3dd755263760d265dc4404cd",
                        33886081,
                        "https://ebooks.karbust.me/Technology/Dive Into Design Patterns (2019) - Alexander Shvets.pdf",
                        "t",
                        "Alexander Shvets",
                        null,
                        1,
                        2019,
                        410
                )
        );

        assertThrows(
                InvalidEbookTitleException.class,
                () -> new Ebook(
                        "6fecd6b78613f44394071bb81286f83fd355330d3dd755263760d265dc4404cd",
                        33886081,
                        "https://ebooks.karbust.me/Technology/Dive Into Design Patterns (2019) - Alexander Shvets.pdf",
                        "SEnMZha7qygjdbHJzQw6tU5VDTvrRXKWx9pG3Fm4fPLNYkcA8BpdvtJBaZgfEcTYXNAMuxwkV3D9nyRrjF5hU2GC87mzW4PSQseqa",
                        "Alexander Shvets",
                        null,
                        1,
                        2019,
                        410
                )
        );

        assertThrows(
                InvalidAutorException.class,
                () -> new Ebook(
                        "6fecd6b78613f44394071bb81286f83fd355330d3dd755263760d265dc4404cd",
                        33886081,
                        "https://ebooks.karbust.me/Technology/Dive Into Design Patterns (2019) - Alexander Shvets.pdf",
                        "Dive Into Design Patterns",
                        "t",
                        null,
                        1,
                        2019,
                        410
                )
        );

        assertThrows(
                InvalidAutorException.class,
                () -> new Ebook(
                        "6fecd6b78613f44394071bb81286f83fd355330d3dd755263760d265dc4404cd",
                        33886081,
                        "https://ebooks.karbust.me/Technology/Dive Into Design Patterns (2019) - Alexander Shvets.pdf",
                        "Dive Into Design Patterns",
                        "RCaw9SsFp7cG3U8HKr4NxdXfmB2kuZnjPYQMAe5vtygDWzTLE6hNQYFu4SJLADCgHydsrm6pEaGkP27xVRew5BbUvz8Wn3MqfKjTa",
                        null,
                        1,
                        2019,
                        410
                )
        );

        assertThrows(
                InvalidFileFormatException.class,
                () -> new Ebook(
                        "6fecd6b78613f44394071bb81286f83fd355330d3dd755263760d265dc4404cd",
                        33886081,
                        "https://ebooks.karbust.me/Technology/Dive Into Design Patterns (2019) - Alexander Shvets.pd",
                        "Dive Into Design Patterns",
                        "Alexander Shvets",
                        null,
                        1,
                        2019,
                        410
                )
        );
    }

    @Test
    public void testEbookIgualdade() throws Exception {
        Ebook ebookTest = new Ebook(
            "6fecd6b78613f44394071bb81286f83fd355330d3dd755263760d265dc4404cd",
            123,
            "https://ebooks.karbust.me/Technology/Dive Into Design Patterns (2019) - Alexander Shvets.pdf",
            "Dive Into Design Patterns",
            "Novo Autor",
            new Editora(
                "Editora XPTO",
                "PT",
                "Termos Teste"
            ),
            2,
            2019,
            410
        );
        assertEquals(ebook, ebookTest);
        assertEquals(ebook, ebook);
        assertNotEquals(ebook, new Empty());
    }

    @Test
    public void testEbookHash() {
        assertEquals(
                "6fecd6b78613f44394071bb81286f83fd355330d3dd755263760d265dc4404cd",
                ebook.getHash()
        );

        ebook.setHash("Novo Hash");
        assertEquals("Novo Hash", ebook.getHash());
        assertNotEquals("Alguma coisa", ebook.getHash());
    }

    @Test
    public void testEbookTamanho() {
        assertEquals(33886081, ebook.getTamanho());

        ebook.setTamanho(123);
        assertEquals(123, ebook.getTamanho());
        assertNotEquals(1234, ebook.getTamanho());
    }

    @Test
    public void testEbookLink() {
        assertEquals(
                "https://ebooks.karbust.me/Technology/Dive Into Design Patterns (2019) - Alexander Shvets.pdf",
                ebook.getLink()
        );

        ebook.setLink("Novo Link");
        assertEquals("Novo Link", ebook.getLink());
        assertNotEquals("Alguma coisa", ebook.getLink());
    }

    @Test
    public void testEbookTitulo() {
        assertEquals("Dive Into Design Patterns", ebook.getTitulo());

        ebook.setTitulo("Novo Titulo");
        assertEquals("Novo Titulo", ebook.getTitulo());
        assertNotEquals("Alguma coisa", ebook.getTitulo());
    }

    @Test
    public void testEbookAutor() {
        assertEquals("Alexander Shvets", ebook.getAutor());

        ebook.setAutor("Novo Autor");
        assertEquals("Novo Autor", ebook.getAutor());
        assertNotEquals("Alguma coisa", ebook.getAutor());
    }

    @Test
    public void testEbookEditora() throws Exception {
        assertNull(ebook.getEditora());

        Editora editora = new Editora(
                "Editora XPTO",
                "PT",
                "Termos Teste"
        );

        ebook.setEditora(editora);
        assertEquals(ebook.getEditora(), editora);

        Editora editTest = new Editora(
                "Editora XPTO1",
                "PT",
                "Termos Teste"
        );
        assertNotEquals(ebook.getEditora(), editTest);
        assertNotEquals(ebook.getEditora(), new Empty());
    }

    @Test
    public void testEbookEdicao() {
        assertEquals(1, ebook.getEdicao());

        ebook.setEdicao(2);
        assertEquals(2, ebook.getEdicao());
        assertNotEquals(3, ebook.getEdicao());
    }

    @Test
    public void testEbookAno() {
        assertEquals(2019, ebook.getAno());

        ebook.setAno(2000);
        assertEquals(2000, ebook.getAno());
        assertNotEquals(2021, ebook.getAno());
    }

    @Test
    public void testEbookPaginas() {
        assertEquals(410, ebook.getPaginas());

        ebook.setPaginas(411);
        assertEquals(411, ebook.getPaginas());
        assertNotEquals(412, ebook.getPaginas());
    }

    @Test
    @Order(1)
    public void testLerEbooks() throws Exception {
        assertTrue(EbookStubs.getInstance().lerEbooks().contains(ebook));
    }

    @Test
    @Order(2)
    public void testLerEbook() throws Exception {
        assertEquals(ebook, EbookStubs.getInstance().lerEbook(1));
        assertNull(EbookStubs.getInstance().lerEbook(null));
        assertNull(EbookStubs.getInstance().lerEbook(-1));
    }

    @Test
    @Order(3)
    public void testCriarEbook() throws Exception {
        assertTrue(EbookStubs.getInstance().registarEbook(new Ebook(
                "4a97711f4533c45bd25c37a21de3d2f6e7dfcf11f801ebd78ef693f4d2bde4841",
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

        )));

        assertFalse(EbookStubs.getInstance().registarEbook(new Ebook(
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

        )));

        assertFalse(EbookStubs.getInstance().registarEbook(null));
    }

    @Test
    @Order(4)
    public void testAtualizarEbook() throws Exception {
        assertTrue(EbookStubs.getInstance().atualizarEbook(1, new Ebook(
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

        )));

        assertFalse(EbookStubs.getInstance().atualizarEbook(-1, new Ebook(
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

        )));

        assertFalse(EbookStubs.getInstance().atualizarEbook(null, new Ebook(
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

        )));
    }

    @Test
    @Order(5)
    public void testApagarEbook() throws Exception {
        assertTrue(EbookStubs.getInstance().apagarEbook(1));
        assertFalse(EbookStubs.getInstance().apagarEbook(null));
        assertFalse(EbookStubs.getInstance().apagarEbook(-1));
    }
}
