package application;

import Exceptions.*;
import org.junit.jupiter.api.*;
import types.Empty;
import types.Utilizador;
import stubs.UtilizadorStubs;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestUtilizador {
    static Utilizador utilizador;

    @BeforeAll
    static void setUp() throws Exception {
        utilizador = new Utilizador(
                "António",
                "PT",
                "antonio@teste.pt",
                "karbust",
                "testerino2",
                true
        );
    }

    @Test
    public void testUtilizadorObjectCreationEmpty() {
        new Utilizador();
    }

    @Test
    public void testUtilizadorObjectCreation() throws Exception {
        new Utilizador(
                "António",
                "PT",
                "antonio@teste.pt",
                "karbust",
                "testerino2",
                true
        );
    }

    @Test
    public void testUtilizadorObjectCreationExceptions() {
        assertThrows(
                NullFieldsException.class,
                () -> new Utilizador(
                        "",
                        "",
                        "",
                        "",
                        "",
                        true
                )
        );

        assertThrows(
                NullFieldsException.class,
                () -> new Utilizador(
                        null,
                        null,
                        null,
                        null,
                        null,
                        true
                )
        );

        assertThrows(
                InvalidEmailException.class,
                () -> new Utilizador(
                        "António",
                        "PT",
                        "teste@teste.p",
                        "karbust",
                        "xpto",
                        true
                )
        );

        assertThrows(
                InvalidPasswordException.class,
                () -> new Utilizador(
                        "António",
                        "PT",
                        "teste@teste.pt",
                        "karbust",
                        "testerino",
                        true
                )
        );

        assertThrows(
                InvalidUsernameException.class,
                () -> new Utilizador(
                        "aXKBD438JNHpGrYx",
                        "PT",
                        "teste@teste.pt",
                        "12",
                        "testerino1",
                        true
                )
        );

        assertThrows(
                InvalidUsernameException.class,
                () -> new Utilizador(
                        "António",
                        "PT",
                        "teste@teste.pt",
                        "12",
                        "testerino1",
                        true
                )
        );

        assertThrows(
                InvalidUsernameException.class,
                () -> new Utilizador(
                        "António",
                        "PT",
                        "teste@teste.pt",
                        "1234567890123456",
                        "testerino1",
                        true
                )
        );

        assertThrows(
                InvalidCountryException.class,
                () -> new Utilizador(
                        "António",
                        "Portugal",
                        "teste@teste.pt",
                        "karbust",
                        "testerino1",
                        true
                )
        );
    }

    @Test
    public void testUtilizadorIgualdade() throws Exception {
        Utilizador utilizadorTest = new Utilizador(
                "Novo Nome",
                "ES",
                "antonio@teste.pt",
                "karbust",
                "testerino2",
                false
        );
        assertEquals(utilizador, utilizadorTest);
        assertEquals(utilizador, utilizador);
        assertNotEquals(utilizador, new Empty());
    }

    @Test
    public void testUtilizadorNome() throws Exception {
        assertEquals("António", utilizador.getNome());

        utilizador.setNome("Novo Nome");
        assertEquals("Novo Nome", utilizador.getNome());
        assertNotEquals("Alguma coisa", utilizador.getNome());

        assertThrows(NullFieldsException.class, () -> utilizador.setNome(null));
        assertThrows(NullFieldsException.class, () -> utilizador.setNome(""));
        assertThrows(InvalidNomeException.class, () -> utilizador.setNome("1"));
        assertThrows(InvalidNomeException.class, () -> utilizador.setNome("1234567890123456"));
    }

    @Test
    public void testUtilizadorPais() throws Exception {
        assertEquals("PT", utilizador.getPais());

        utilizador.setPais("ES");
        assertEquals("ES", utilizador.getPais());
        assertNotEquals("Alguma coisa", utilizador.getPais());

        assertThrows(InvalidCountryException.class, () -> utilizador.setPais("Portugal"));
    }

    @Test
    public void testUtilizadorEmail() throws Exception {
        assertEquals("antonio@teste.pt", utilizador.getEmail());

        utilizador.setEmail("teste@teste.pt");
        assertEquals("teste@teste.pt", utilizador.getEmail());
        assertNotEquals("Alguma coisa", utilizador.getEmail());

        assertThrows(NullFieldsException.class, () -> utilizador.setEmail(null));
        assertThrows(NullFieldsException.class, () -> utilizador.setEmail(""));
        assertThrows(InvalidEmailException.class, () -> utilizador.setEmail("teste@teste.p"));
    }

    @Test
    public void testUtilizadorNomeUtilizador() throws Exception {
        assertEquals("karbust", utilizador.getNome_utilizador());

        utilizador.setNome_utilizador("karbust1");
        assertEquals("karbust1", utilizador.getNome_utilizador());
        assertNotEquals("Alguma coisa", utilizador.getNome_utilizador());

        assertThrows(NullFieldsException.class, () -> utilizador.setNome_utilizador(null));
        assertThrows(NullFieldsException.class, () -> utilizador.setNome_utilizador(""));
        assertThrows(InvalidUsernameException.class, () -> utilizador.setNome_utilizador("1"));
        assertThrows(InvalidUsernameException.class, () -> utilizador.setNome_utilizador("1234567890123456"));
    }

    @Test
    public void testUtilizadorPassword() throws Exception {
        assertEquals("testerino2", utilizador.getPassword());

        utilizador.setPassword("testerino3");
        assertEquals("testerino3", utilizador.getPassword());
        assertNotEquals("Alguma coisa", utilizador.getPassword());
        utilizador.setPassword("testerino2");

        assertThrows(NullFieldsException.class, () -> utilizador.setPassword(null));
        assertThrows(NullFieldsException.class, () -> utilizador.setPassword(""));
        assertThrows(InvalidPasswordException.class, () -> utilizador.setPassword("teste"));
    }

    @Test
    public void testUtilizadorStatus() {
        assertTrue(utilizador.getStatus());

        utilizador.setStatus(false);
        assertFalse(utilizador.getStatus());
        assertNotEquals(true, utilizador.getStatus());
    }

    @Test
    @Order(1)
    public void testAutenticarUtilizador() throws Exception {
        assertTrue(UtilizadorStubs.getInstance().autenticarUtilizador("karbust", "testerino2"));
        assertFalse(UtilizadorStubs.getInstance().autenticarUtilizador("karbust1", "testerino2"));
    }

    @Test
    @Order(2)
    public void testLerUtilizadores() throws Exception {
        assertTrue(UtilizadorStubs.getInstance().lerUtilizadores().contains(utilizador));
    }

    @Test
    @Order(3)
    public void testLerUtilizador() throws Exception {
        assertEquals(new Utilizador(
                "Adriano",
                "PT",
                "adriano@teste.pt",
                "notoriousadri",
                "testerino1",
                true
        ), UtilizadorStubs.getInstance().lerUtilizador(1));
        assertNull(UtilizadorStubs.getInstance().lerUtilizador(null));
        assertNull(UtilizadorStubs.getInstance().lerUtilizador(-1));
    }

    @Test
    @Order(4)
    public void testCriarUtilizador() throws Exception {
        assertTrue(UtilizadorStubs.getInstance().registarUtilizador(
                "Adriano",
                "PT",
                "adriano@teste.pt",
                "notoriousadri",
                "testerino1",
                true
        ));
        assertThrows(
                NullFieldsException.class,
                () -> UtilizadorStubs.getInstance().registarUtilizador(
                        null,
                        null,
                        null,
                        null,
                        null,
                        false
                )
        );
        assertThrows(
                NullFieldsException.class,
                () -> UtilizadorStubs.getInstance().registarUtilizador(
                        "",
                        "",
                        "",
                        "",
                        "",
                        true
                )
        );

        assertThrows(
                InvalidEmailException.class,
                () -> new Utilizador(
                        "António",
                        "PT",
                        "teste@teste.p",
                        "karbust",
                        "xpto",
                        true
                )
        );

        assertThrows(
                InvalidPasswordException.class,
                () -> new Utilizador(
                        "António",
                        "PT",
                        "teste@teste.pt",
                        "karbust",
                        "testerino",
                        true
                )
        );

        assertThrows(
                InvalidUsernameException.class,
                () -> new Utilizador(
                        "aXKBD438JNHpGrYx",
                        "PT",
                        "teste@teste.pt",
                        "12",
                        "testerino1",
                        true
                )
        );

        assertThrows(
                InvalidUsernameException.class,
                () -> new Utilizador(
                        "António",
                        "PT",
                        "teste@teste.pt",
                        "12",
                        "testerino1",
                        true
                )
        );

        assertThrows(
                InvalidUsernameException.class,
                () -> new Utilizador(
                        "António",
                        "PT",
                        "teste@teste.pt",
                        "1234567890123456",
                        "testerino1",
                        true
                )
        );

        assertThrows(
                InvalidCountryException.class,
                () -> new Utilizador(
                        "António",
                        "Portugal",
                        "teste@teste.pt",
                        "karbust",
                        "testerino1",
                        true
                )
        );
    }

    @Test
    @Order(5)
    public void testAtualizarUtilizador() throws Exception {
        assertTrue(UtilizadorStubs.getInstance().atualizarUtilizador(1, new Utilizador(
                "Adriano",
                "PT",
                "adriano@teste.pt",
                "notoriousadri1",
                "testerino1",
                true
        )));
        assertFalse(UtilizadorStubs.getInstance().atualizarUtilizador(-1, new Utilizador(
                "Adriano",
                "PT",
                "adriano@teste.pt",
                "notoriousadri",
                "testerino1",
                true
        )));
        assertFalse(UtilizadorStubs.getInstance().atualizarUtilizador(null, new Utilizador(
                "Adriano",
                "PT",
                "adriano@teste.pt",
                "notoriousadri",
                "testerino1",
                true
        )));
        assertFalse(UtilizadorStubs.getInstance().atualizarUtilizador(1, null));
    }

    @Test
    @Order(6)
    public void testApagarUtilizador() throws Exception {
        assertTrue(UtilizadorStubs.getInstance().apagarUtilizador(1));
        assertFalse(UtilizadorStubs.getInstance().apagarUtilizador(null));
        assertFalse(UtilizadorStubs.getInstance().apagarUtilizador(-1));
    }
}
