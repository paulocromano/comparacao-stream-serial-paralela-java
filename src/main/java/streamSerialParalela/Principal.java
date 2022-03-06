package streamSerialParalela;

import streamSerialParalela.util.teste.InitializerTests;
import streamSerialParalela.util.teste.enums.TamanhoBaseDeDados;
import streamSerialParalela.util.teste.enums.TipoStream;

public class Principal {

	public static void main(String[] args) {
		//PessoaRepository.getPessoas().stream().map(Pessoa::getNome).forEach(System.out::println);
		InitializerTests.run(TipoStream.SERIAL, TamanhoBaseDeDados.CEM_MIL);
	}
}
