package br.com.alura.academico.dominio.aluno;

import br.com.alura.escola.academico.dominio.aluno.Aluno;
import br.com.alura.escola.shared.dominio.CPF;
import br.com.alura.escola.academico.dominio.aluno.Email;
import br.com.alura.escola.academico.dominio.aluno.exceptions.ExcessoTelefonesCadastradosException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlunoTest {
    private Aluno aluno;

    @BeforeEach
    void setUp() {
        aluno = new Aluno(new CPF("660.111.070-10"), "alunoTeste", new Email("teste@teste.com"));
    }

    @Test
    void shouldAddOnePhoneNumber() {
        aluno.adicionarTelefone("61", "123456789");
        assertEquals(1, aluno.getTelefones().size());
    }

    @Test
    void shouldAddTwoPhoneNumber() {
        aluno.adicionarTelefone("61", "123456789");
        aluno.adicionarTelefone("62", "123456780");
        assertEquals(2, aluno.getTelefones().size());
    }



    @Test
    void shouldThrowExceptionWhenAddingThirdPhoneNumber() {
        aluno.adicionarTelefone("61", "123456789");
        aluno.adicionarTelefone("62", "123456780");
        assertThrows(ExcessoTelefonesCadastradosException.class, () -> {
            aluno.adicionarTelefone("61", "123456789");
            aluno.adicionarTelefone("62", "123456780");
            aluno.adicionarTelefone("63", "123456781");
        });
    }
}