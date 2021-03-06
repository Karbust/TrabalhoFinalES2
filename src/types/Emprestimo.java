package types;

import Exceptions.*;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.util.GregorianCalendar;
import java.util.HashMap;

public class Emprestimo {
    private static final Integer limite_prolongacoes = 2;
    private Servidor servidor;
    private Utilizador utilizador;
    private Ebook ebook;
    private GregorianCalendar data_emprestimo;
    private GregorianCalendar data_limite;
    private Integer prolongacoes;
    private HashMap<Integer, GregorianCalendar> prolongacoesArray;

    public Emprestimo() { }

    public Emprestimo(Servidor servidor, Utilizador utilizador, Ebook ebook, GregorianCalendar data_emprestimo, GregorianCalendar data_limite, boolean concordar_termos_servico) throws Exception {
        if (servidor == null || utilizador == null || ebook == null || data_emprestimo == null || data_limite == null) {
            throw new NullFieldsException();
        }
        if (data_emprestimo.after(data_limite) || data_limite.before(data_emprestimo)) {
            throw new InvalidDatasException();
        }
        if (!utilizador.getStatus()) {
            throw new UserAccountStatusDisabled();
        }
        if (!concordar_termos_servico) {
            throw new NoTermsServiceAgreement();
        }
        this.servidor = servidor;
        this.utilizador = utilizador;
        this.ebook = ebook;
        this.data_emprestimo = data_emprestimo;
        this.data_limite = data_limite;
        this.prolongacoes = 0;
        this.prolongacoesArray = new HashMap<>();
    }

    public Servidor getServidor() {
        return servidor;
    }

    public void setServidor(Servidor servidor) throws Exception {
        if (servidor == null) {
            throw new NullFieldsException();
        }
        this.servidor = servidor;
    }

    public Utilizador getUtilizador() {
        return utilizador;
    }

    public void setUtilizador(Utilizador utilizador) throws Exception {
        if (utilizador == null) {
            throw new NullFieldsException();
        }
        this.utilizador = utilizador;
    }

    public Ebook getEbook() {
        return ebook;
    }

    public void setEbook(Ebook ebook) throws Exception {
        if (ebook == null) {
            throw new NullFieldsException();
        }
        this.ebook = ebook;
    }

    public GregorianCalendar getData_emprestimo() {
        return data_emprestimo;
    }

    public void setData_emprestimo(GregorianCalendar data_emprestimo) throws Exception {
        if (data_emprestimo == null) {
            throw new NullFieldsException();
        }
        if (data_emprestimo.after(this.data_limite)) {
            throw new InvalidDatasException();
        }
        this.data_emprestimo = data_emprestimo;
    }

    public GregorianCalendar getData_limite() {
        return data_limite;
    }

    public void setData_limite(GregorianCalendar data_limite) throws Exception {
        if (data_limite == null) {
            throw new NullFieldsException();
        }
        if (data_limite.before(this.data_emprestimo)) {
            throw new InvalidDatasException();
        }
        this.data_limite = data_limite;
    }

    public Integer getProlongacoes() {
        return prolongacoes;
    }

    public void incrementProlongacoes(GregorianCalendar data_limite_nova) throws Exception {
        if (data_limite_nova == null) {
            throw new NullFieldsException();
        }
        if (this.prolongacoes + 1 > limite_prolongacoes) {
            throw new InvalidProlongacoesException();
        }
        if (
                this.data_limite.before(this.data_emprestimo) ||
                data_limite_nova.before(this.data_limite) ||
                data_limite_nova.equals(this.data_limite)
        ) {
            throw new InvalidDatasException();
        }
        this.prolongacoes++;
        this.data_limite = data_limite_nova;
        prolongacoesArray.put(prolongacoes, data_limite_nova);
    }

    public HashMap<Integer, GregorianCalendar> getProlongacoesArray() {
        return prolongacoesArray;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if(!(o instanceof Emprestimo that))
            return false;

        return new EqualsBuilder()
                .append(this.servidor, that.servidor)
                .append(this.utilizador, that.utilizador)
                .append(this.data_emprestimo, that.data_emprestimo)
                .append(this.data_limite, that.data_limite)
                .append(this.prolongacoes, that.prolongacoes)
                .isEquals();
    }
}
