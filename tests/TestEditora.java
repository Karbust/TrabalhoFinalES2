import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class TestEditora {
    static Editora editora;

    @BeforeEach
    void setUpBeforeEach() {
        editora = new Editora("Editora XPTO", "Portugal", "Termos Teste");
    }

    @Test
    public void testEditoraObjectCreationEmpty() {
        new Editora();
    }

    @Test
    public void testEditoraObjectCreation() {
        new Editora("Editora XPTO", "Portugal", "Termos Teste");
    }

    @Test
    public void testEditoraNome() {
        assertEquals("Editora XPTO", editora.getNome());

        editora.setNome("Editora XPTO Novo");
        assertEquals("Editora XPTO Novo", editora.getNome());
        assertNotEquals("Alguma Coisa", editora.getNome());
    }

    @Test
    public void testEditoraPais() {
        assertEquals("Portugal", editora.getPais());

        editora.setPais("Espanha");
        assertEquals("Espanha", editora.getPais());
        assertNotEquals("Alguma Coisa", editora.getPais());
    }

    @Test
    public void testEditoraTermosResponsabilidade() {
        assertEquals("Termos Teste", editora.getTermos_responsabilidade());

        editora.setTermos_responsabilidade("Novos Termos");
        assertEquals("Novos Termos", editora.getTermos_responsabilidade());
        assertNotEquals("Alguma Coisa", editora.getTermos_responsabilidade());
    }

    @Test
    public void testEditoraIgualdade() {
        Editora editTest = new Editora(
                "Editora XPTO",
                "Portugal",
                "Termos Teste"
        );
        assertEquals(editora, editTest);
        assertNotEquals(editora, new Empty());
    }
}
