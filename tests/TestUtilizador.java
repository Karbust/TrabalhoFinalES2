import Exceptions.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestUtilizador {
    static Utilizador utilizador;

    @BeforeAll
    static void setUp() throws Exception {
        utilizador = new Utilizador(
                "António",
                "PT",
                "teste@teste.pt",
                "karbust",
                "testerino1",
                true
        );
    }
    @Test
    @Order(1)
    public void testUtilizadorObjectCreationEmpty() {
        new Utilizador();
    }

    @Test
    @Order(2)
    public void testUtilizadorObjectCreation() throws Exception {
        new Utilizador(
                "António",
                "PT",
                "teste@teste.pt",
                "karbust",
                "testerino1",
                true
        );
    }

    @Test
    @Order(3)
    public void testUtilizadorObjectCreationExceptions() throws Exception {
        assertThrows(
                NullFieldsException.class,
                () -> new Utilizador(
                        "António",
                        "PT",
                        null,
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
    @Order(7)
    public void testUtilizadorNome() {
        assertEquals("António", utilizador.getNome());

        utilizador.setNome("Novo Nome");
        assertEquals("Novo Nome", utilizador.getNome());
        assertNotEquals("Alguma coisa", utilizador.getNome());
    }

    @Test
    @Order(8)
    public void testUtilizadorPais() throws InvalidCountryException {
        assertEquals("PT", utilizador.getPais());

        utilizador.setPais("ES");
        assertEquals("ES", utilizador.getPais());
        assertNotEquals("Alguma coisa", utilizador.getPais());

        assertThrows(InvalidCountryException.class, () -> utilizador.setPais("Portugal"));
    }

    @Test
    @Order(9)
    public void testUtilizadorEmail() {
        assertEquals("teste@teste.pt", utilizador.getEmail());

        utilizador.setEmail("Novo Email");
        assertEquals("Novo Email", utilizador.getEmail());
        assertNotEquals("Alguma coisa", utilizador.getEmail());
    }

    @Test
    @Order(10)
    public void testUtilizadorNomeUtilizador() {
        assertEquals("karbust", utilizador.getNome_utilizador());

        utilizador.setNome_utilizador("Novo Nome Utilizador");
        assertEquals("Novo Nome Utilizador", utilizador.getNome_utilizador());
        assertNotEquals("Alguma coisa", utilizador.getNome_utilizador());
    }

    @Test
    @Order(11)
    public void testUtilizadorPassword() {
        assertEquals("testerino1", utilizador.getPassword());

        utilizador.setPassword("Nova Password");
        assertEquals("Nova Password", utilizador.getPassword());
        assertNotEquals("Alguma coisa", utilizador.getPassword());
    }

    @Test
    @Order(12)
    public void testUtilizadorStatus() {
        assertTrue(utilizador.getStatus());

        utilizador.setStatus(false);
        assertFalse(utilizador.getStatus());
        assertNotEquals(true, utilizador.getStatus());
    }

    @Test
    @Order(13)
    public void testUtilizadorAutenticacao() {
        assertTrue(utilizador.autenticacao("Novo Nome Utilizador", "Nova Password"));
        assertFalse(utilizador.autenticacao("karbust", "xpto"));
    }
}
