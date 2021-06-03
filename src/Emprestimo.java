import java.util.Date;

public class Emprestimo {
    private static final Integer limite_prolongacoes = 2;
    private Servidor servidor;
    private Utilizador utilizador;
    private Date data_emprestimo;
    private Date data_limite;
    private Integer prolongacoes;

    public Emprestimo(Servidor servidor, Utilizador utilizador, Date data_emprestimo, Date data_limite) {
        this.servidor = servidor;
        this.utilizador = utilizador;
        this.data_emprestimo = data_emprestimo;
        this.data_limite = data_limite;
    }

    public Servidor getServidor() {
        return servidor;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    public Utilizador getUtilizador() {
        return utilizador;
    }

    public void setUtilizador(Utilizador utilizador) {
        this.utilizador = utilizador;
    }

    public Date getData_limite() {
        return data_limite;
    }

    public void setData_limite(Date data_limite) {
        this.data_limite = data_limite;
    }

    public Integer getProlongacoes() {
        return prolongacoes;
    }

    public void setProlongacoes(Integer prolongacoes) {
        this.prolongacoes = prolongacoes;
    }

    public Date getData_emprestimo() {
        return data_emprestimo;
    }

    public void setData_emprestimo(Date data_emprestimo) {
        this.data_emprestimo = data_emprestimo;
    }
}
