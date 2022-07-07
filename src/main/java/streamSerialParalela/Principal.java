package streamSerialParalela;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import streamSerialParalela.pessoa.teste.TesteStreamPessoa;
import streamSerialParalela.util.teste.enums.TamanhoBaseDeDados;
import streamSerialParalela.util.teste.enums.TipoStream;

public class Principal {

	public static void main(String[] args) {		
		TipoStream tipoStream = TipoStream.PARALELA;
		TamanhoBaseDeDados tamanhoBaseDeDados = TamanhoBaseDeDados.CEM_MIL;
		
		TesteStreamPessoa testeStreamPessoa = new TesteStreamPessoa(tipoStream, tamanhoBaseDeDados);
		testeStreamPessoa.configure();
		
		System.out.println(DateTimeFormatter.ofPattern("HH:mm:ss.SSS").format(LocalTime.now()));
		testeStreamPessoa.agruparMediaSalarialDePessoasPorPais();
	}
}
