package streamSerialParalela;

import streamSerialParalela.pessoa.model.Pessoa;
import streamSerialParalela.pessoa.repository.PessoaRepository;

public class Principal {

	public static void main(String[] args) {
		PessoaRepository pessoaRepository = new PessoaRepository();
		PessoaRepository.getPessoas().stream().map(Pessoa::getNome).forEach(System.out::println);
	}
}
