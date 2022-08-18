package br.com.alura.escola.gamificacao.aplicacao;

import br.com.alura.escola.gamificacao.dominio.selo.RepositorioDeSelo;
import br.com.alura.escola.gamificacao.dominio.selo.Selo;
import br.com.alura.escola.shared.dominio.CPF;
import br.com.alura.escola.shared.dominio.evento.Evento;
import br.com.alura.escola.shared.dominio.evento.Ouvinte;
import br.com.alura.escola.shared.dominio.evento.TipoDeEvento;

public class GeraSeloAlunoNovato extends Ouvinte {

    private final RepositorioDeSelo repositorio;

    public GeraSeloAlunoNovato(RepositorioDeSelo repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    protected boolean deveProcessar(Evento evento) {
        return evento.tipo() == TipoDeEvento.ALUNO_MATRICULADO;
    }

    @Override
    protected void reageAo(Evento evento) {
        CPF cpfDoAluno = (CPF) evento.informacoes().get("cpf");
        Selo novato = new Selo(cpfDoAluno, "Novato");
        repositorio.adicionar(novato);
    }
}
