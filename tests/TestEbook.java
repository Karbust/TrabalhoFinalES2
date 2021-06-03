import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

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
    public void testEbookEditora() {
        assertNull(ebook.getEditora());

        Editora editora = new Editora(
                "Editora XPTO",
                "Portugal",
                "Termos Teste"
        );

        ebook.setEditora(editora);
        assertEquals(ebook.getEditora(), editora);

        Editora editTest = new Editora(
                "Editora XPTO1",
                "Portugal",
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
}
