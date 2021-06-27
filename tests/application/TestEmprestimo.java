package application;

import Exceptions.*;
import org.junit.jupiter.api.*;
import stubs.EbookStubs;
import stubs.EmprestimoStubs;
import types.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestEmprestimo {
    static Ebook ebook;
    static Servidor servidor;
    static Emprestimo emprestimo;
    static Utilizador utilizador;
    static HashSet<Ebook> ebooksSemEditora = new HashSet<>();

    @BeforeAll
    static void setUpBeforeAll() throws Exception {
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

        servidor = new Servidor(
                "PT",
                ebooksSemEditora
        );

        utilizador = new Utilizador(
                "António",
                "PT",
                "antonio@teste.pt",
                "karbust",
                "testerino2",
                true
        );

        emprestimo = new Emprestimo(
                servidor,
                utilizador,
                ebook,
                new GregorianCalendar(2021, Calendar.JUNE, 24),
                new GregorianCalendar(2021, Calendar.JUNE, 25),
                true
        );
    }

    @Test
    public void testEmprestimoObjectCreationEmpty() {
        new Emprestimo();
    }

    @Test
    public void testEmprestimoObjectCreation() throws Exception {
        new Emprestimo(
                servidor,
                utilizador,
                ebook,
                new GregorianCalendar(2021, Calendar.JUNE, 24),
                new GregorianCalendar(2021, Calendar.JUNE, 25),
                true
        );
    }

    @Test
    public void testEmprestimoObjectCreationExceptions() throws Exception {
        assertThrows(
                NullFieldsException.class,
                () -> new Emprestimo(
                        null,
                        null,
                        null,
                        null,
                        null,
                        false
                )
        );

        assertThrows(
                InvalidDatasException.class,
                () -> new Emprestimo(
                        servidor,
                        utilizador,
                        ebook,
                        new GregorianCalendar(2021, Calendar.JUNE, 25),
                        new GregorianCalendar(2021, Calendar.JUNE, 24),
                        true
                )
        );

        assertThrows(
                NoTermsServiceAgreement.class,
                () -> new Emprestimo(
                        servidor,
                        utilizador,
                        ebook,
                        new GregorianCalendar(2021, Calendar.JUNE, 24),
                        new GregorianCalendar(2021, Calendar.JUNE, 25),
                        false
                )
        );

        Utilizador utilizador1 = new Utilizador(
                "António",
                "PT",
                "antonio@teste.pt",
                "karbust",
                "testerino2",
                true
        );

        utilizador1.setStatus(false);

        assertThrows(
                UserAccountStatusDisabled.class,
                () -> new Emprestimo(
                        servidor,
                        utilizador1,
                        ebook,
                        new GregorianCalendar(2021, Calendar.JUNE, 24),
                        new GregorianCalendar(2021, Calendar.JUNE, 25),
                        true
                )
        );
    }

    @Test
    public void testEmprestimoIgualdade() throws Exception {
        Servidor servidor_new = new Servidor(
                "ES",
                ebooksSemEditora
        );
        Emprestimo emprestimo_new = new Emprestimo(
                servidor_new,
                utilizador,
                ebook,
                new GregorianCalendar(2021, Calendar.JUNE, 24),
                new GregorianCalendar(2021, Calendar.JUNE, 25),
                true
        );
        assertNotEquals(emprestimo, emprestimo_new);
        assertEquals(emprestimo, emprestimo);
        assertNotEquals(emprestimo, new Empty());
        assertNotEquals(emprestimo, new Emprestimo());
    }

    @Test
    public void testEmprestimoServidor() throws Exception {
        assertEquals(servidor, emprestimo.getServidor());

        assertThrows(
                NullFieldsException.class,
                () -> emprestimo.setServidor(null)
        );

        Servidor servidor_new = new Servidor(
                "ES",
                ebooksSemEditora
        );
        emprestimo.setServidor(servidor_new);
        assertEquals(servidor_new, emprestimo.getServidor());

        assertNotEquals(servidor_new, servidor);

        emprestimo.setServidor(servidor);
    }

    @Test
    public void testEmprestimoUtilizador() throws Exception {
        assertEquals(utilizador, emprestimo.getUtilizador());

        assertThrows(
                NullFieldsException.class,
                () -> emprestimo.setUtilizador(null)
        );

        Utilizador utilizadorTest = new Utilizador(
                "António",
                "ES",
                "antonio@teste.pt",
                "karbust",
                "testerino2",
                true
        );
        emprestimo.setUtilizador(utilizadorTest);
        assertEquals(utilizadorTest, emprestimo.getUtilizador());

        assertNotEquals(utilizador, new Utilizador());

        emprestimo.setUtilizador(utilizador);
    }

    @Test
    public void testEmprestimoEbook() throws Exception {
        assertEquals(ebook, emprestimo.getEbook());

        assertThrows(
                NullFieldsException.class,
                () -> emprestimo.setEbook(null)
        );

        emprestimo.setEbook(EbookStubs.getInstance().lerEbook(2));
        assertEquals(EbookStubs.getInstance().lerEbook(2), emprestimo.getEbook());

        //Voltar ao estado anterior
        emprestimo.setEbook(ebook);
    }

    @Test
    public void testEmprestimoDataEmprestimo() throws Exception {
        assertEquals(new GregorianCalendar(2021, Calendar.JUNE, 24), emprestimo.getData_emprestimo());
        assertThrows(
                NullFieldsException.class,
                () -> emprestimo.setData_emprestimo(null)
        );
        assertThrows(
                InvalidDatasException.class,
                () -> emprestimo.setData_emprestimo(new GregorianCalendar(2021, Calendar.JUNE, 26))
        );
        emprestimo.setData_emprestimo(new GregorianCalendar(2021, Calendar.JUNE, 23));
        assertNotEquals(new GregorianCalendar(2021, Calendar.JUNE, 24), emprestimo.getData_emprestimo());

        emprestimo.setData_emprestimo(new GregorianCalendar(2021, Calendar.JUNE, 24));
    }

    @Test
    public void testEmprestimoDataLimite() throws Exception {
        assertEquals(new GregorianCalendar(2021, Calendar.JUNE, 25), emprestimo.getData_limite());
        assertThrows(
                NullFieldsException.class,
                () -> emprestimo.setData_limite(null)
        );
        assertThrows(
                InvalidDatasException.class,
                () -> emprestimo.setData_limite(new GregorianCalendar(2021, Calendar.JUNE, 23))
        );
        emprestimo.setData_limite(new GregorianCalendar(2021, Calendar.JUNE, 26));
        assertNotEquals(new GregorianCalendar(2021, Calendar.JUNE, 25), emprestimo.getData_limite());

        emprestimo.setData_limite(new GregorianCalendar(2021, Calendar.JUNE, 25));
    }

    @Test
    public void testEmprestimoProlongacoes() throws Exception {
        assertEquals(emprestimo.getProlongacoes(), 0);

        assertThrows(
                NullFieldsException.class,
                () -> emprestimo.incrementProlongacoes(null)
        );

        assertThrows(
                InvalidDatasException.class,
                () -> emprestimo.incrementProlongacoes(new GregorianCalendar(2021, Calendar.JUNE, 25))
        );

        GregorianCalendar prolongacao1 = new GregorianCalendar(2021, Calendar.JUNE, 26);
        GregorianCalendar prolongacao2 = new GregorianCalendar(2021, Calendar.JUNE, 27);

        emprestimo.incrementProlongacoes(prolongacao1);
        emprestimo.incrementProlongacoes(prolongacao2);

        assertThrows(
                InvalidProlongacoesException.class,
                () -> emprestimo.incrementProlongacoes(new GregorianCalendar(2021, Calendar.JUNE, 28))
        );

        HashMap<Integer, GregorianCalendar> prolongacoes = emprestimo.getProlongacoesArray();

        assertNull(prolongacoes.get(0));
        assertEquals(prolongacoes.get(1), prolongacao1);
        assertEquals(prolongacoes.get(2), prolongacao2);
        assertNull(prolongacoes.get(3));
    }

    @Test
    @Order(1)
    public void testLerEmprestimos() throws Exception {
        assertTrue(EmprestimoStubs.getInstance().lerEmprestimos().contains(emprestimo));
    }

    @Test
    @Order(2)
    public void testLerEmprestimo() throws Exception {
        Emprestimo emprestimoStub = EmprestimoStubs.getInstance().lerEmprestimo(2);
        assertEquals(emprestimo.getEbook(), emprestimoStub.getEbook());
        assertEquals(emprestimo.getUtilizador(), emprestimoStub.getUtilizador());
        assertEquals(emprestimo.getData_emprestimo(), emprestimoStub.getData_emprestimo());
        assertEquals(emprestimo.getData_limite(), emprestimoStub.getData_limite());

        assertNull(EmprestimoStubs.getInstance().lerEmprestimo(null));
        assertNull(EmprestimoStubs.getInstance().lerEmprestimo(-1));
    }

    @Test
    @Order(3)
    public void testCriarEmprestimo() throws Exception {
        assertTrue(EmprestimoStubs.getInstance().registarEmprestimo(emprestimo));
        assertFalse(EmprestimoStubs.getInstance().registarEmprestimo(null));
    }

    @Test
    @Order(4)
    public void testAtualizarEmprestimo() throws Exception {
        assertTrue(EmprestimoStubs.getInstance().atualizarEmprestimo(1, emprestimo));
        assertFalse(EmprestimoStubs.getInstance().atualizarEmprestimo(null, emprestimo));
        assertFalse(EmprestimoStubs.getInstance().atualizarEmprestimo(-1, emprestimo));
        assertFalse(EmprestimoStubs.getInstance().atualizarEmprestimo(1, null));
    }

    @Test
    @Order(5)
    public void testApagarEmprestimo() throws Exception {
        assertTrue(EmprestimoStubs.getInstance().apagarEmprestimo(1));
        assertFalse(EmprestimoStubs.getInstance().apagarEmprestimo(null));
        assertFalse(EmprestimoStubs.getInstance().apagarEmprestimo(-1));
    }
}
