package application;

import Exceptions.InvalidCountryException;
import Exceptions.InvalidNomeException;
import Exceptions.NullFieldsException;
import org.junit.jupiter.api.*;
import stubs.EditoraStubs;
import types.Editora;
import types.Empty;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestEditora {
    static Editora editora;

    @BeforeEach
    void setUpBeforeEach() throws Exception {
        editora = new Editora("O'Reilly", "CA", "Termos Teste");
    }

    @Test
    public void testEditoraObjectCreationEmpty() {
        new Editora();
    }

    @Test
    public void testEditoraObjectCreation() throws Exception {
        new Editora("O'Reilly", "CA", "Termos Teste");
    }

    @Test
    public void testEditoraObjectCreationExceptions() throws Exception {
        assertThrows(
                NullFieldsException.class,
                () -> new Editora(
                        null,
                        null,
                        null
                )
        );
        assertThrows(
                NullFieldsException.class,
                () -> new Editora(
                        "",
                        "",
                        ""
                )
        );
        assertThrows(
                InvalidCountryException.class,
                () -> new Editora(
                        "Teste",
                        "Portugal",
                        "Teste"
                )
        );

        assertThrows(
                InvalidNomeException.class,
                () -> new Editora(
                        "12",
                        "Portugal",
                        "Teste"
                )
        );

        assertThrows(
                InvalidNomeException.class,
                () -> new Editora(
                        "12345678901234567890123456",
                        "Portugal",
                        "Teste"
                )
        );
    }

    @Test
    public void testEditoraIgualdade() throws Exception {
        Editora editTest = new Editora("O'Reilly", "CA", "Termos Teste");
        assertEquals(editora, editTest);
        assertEquals(editora, editora);
        assertNotEquals(editora, new Empty());
    }

    @Test
    public void testEditoraNome() throws Exception {
        assertEquals("O'Reilly", editora.getNome());

        editora.setNome("O'Reilly - Novo");
        assertEquals("O'Reilly - Novo", editora.getNome());
        assertNotEquals("Alguma Coisa", editora.getNome());

        assertThrows(
                NullFieldsException.class,
                () -> editora.setNome("")
        );
        assertThrows(
                NullFieldsException.class,
                () -> editora.setNome(null)
        );

        //Voltar ao estado anterior
        editora.setNome("O'Reilly");
    }

    @Test
    public void testEditoraPais() throws Exception {
        assertEquals("CA", editora.getPais());

        editora.setPais("PT");
        assertEquals("PT", editora.getPais());
        assertNotEquals("CA", editora.getPais());

        assertThrows(
                InvalidCountryException.class,
                () -> editora.setPais("Canada")
        );

        //Voltar ao estado anterior
        editora.setPais("CA");
    }

    @Test
    public void testEditoraTermosResponsabilidade() throws Exception {
        assertEquals("Termos Teste", editora.getTermos_responsabilidade());

        editora.setTermos_responsabilidade("Novos Termos");
        assertEquals("Novos Termos", editora.getTermos_responsabilidade());
        assertNotEquals("Alguma Coisa", editora.getTermos_responsabilidade());

        assertThrows(
                NullFieldsException.class,
                () -> editora.setTermos_responsabilidade("")
        );
        assertThrows(
                NullFieldsException.class,
                () -> editora.setTermos_responsabilidade(null)
        );
        //Voltar ao estado anterior
        editora.setTermos_responsabilidade("Termos Teste");
    }

    @Test
    @Order(1)
    public void testLerEditoras() throws Exception {
        assertTrue(EditoraStubs.getInstance().lerEditoras().contains(editora));
    }

    @Test
    @Order(2)
    public void testLerEditora() throws Exception {
        assertEquals(editora, EditoraStubs.getInstance().lerEditora(1));
        assertNull(EditoraStubs.getInstance().lerEditora(null));
        assertNull(EditoraStubs.getInstance().lerEditora(-1));
    }

    @Test
    @Order(3)
    public void testCriarEditora() throws Exception {
        Editora editoraNew = new Editora("O'Reilly 1", "CA", "Termos Teste");

        assertTrue(EditoraStubs.getInstance().registarEditora(editoraNew));
        assertFalse(EditoraStubs.getInstance().registarEditora(editora));
        assertFalse(EditoraStubs.getInstance().registarEditora(null));
    }

    @Test
    @Order(4)
    public void testAtualizarEditora() throws Exception {
        assertTrue(EditoraStubs.getInstance().atualizarEditora(1, editora));
        assertFalse(EditoraStubs.getInstance().atualizarEditora(null, editora));
        assertFalse(EditoraStubs.getInstance().atualizarEditora(-1, editora));
        assertFalse(EditoraStubs.getInstance().atualizarEditora(1, null));
    }

    @Test
    @Order(5)
    public void testApagarEditora() throws Exception {
        assertTrue(EditoraStubs.getInstance().apagarEditora(3));
        assertFalse(EditoraStubs.getInstance().apagarEditora(null));
        assertFalse(EditoraStubs.getInstance().apagarEditora(-1));
    }
}
