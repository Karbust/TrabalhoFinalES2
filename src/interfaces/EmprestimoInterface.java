package interfaces;

import types.Emprestimo;

import java.util.ArrayList;

public interface EmprestimoInterface {
    ArrayList<Emprestimo> lerEmprestimos();

    Emprestimo lerEmprestimo(Integer id);

    boolean registarEmprestimo(Emprestimo emprestimo);

    boolean atualizarEmprestimo(Integer id, Emprestimo emprestimo);

    boolean apagarEmprestimo(Integer id);
}
