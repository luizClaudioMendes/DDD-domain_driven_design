package br.com.alura.escola.gamificacao.infra.selo;

import br.com.alura.escola.shared.dominio.CPF;
import br.com.alura.escola.gamificacao.dominio.selo.RepositorioDeSelo;
import br.com.alura.escola.gamificacao.dominio.selo.Selo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioDeSelosEmMemoria implements RepositorioDeSelo {

	private List<Selo> selos = new ArrayList<>();

	@Override
	public void adicionar(Selo selo) {
		this.selos.add(selo);
	}

	@Override
	public List<Selo> selosDoAlunoDeCPF(CPF cpf) {
		return selos.stream().filter(
				s -> s.getCpfDoAluno().getNumero() == cpf.getNumero()).collect(Collectors.toList());
	}

}
