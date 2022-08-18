package br.com.alura.escola.academico.dominio.aluno.exceptions;

public class ExcessoTelefonesCadastradosException extends RuntimeException {
    public ExcessoTelefonesCadastradosException(String errorMessage) {
        super(errorMessage);
    }
}
