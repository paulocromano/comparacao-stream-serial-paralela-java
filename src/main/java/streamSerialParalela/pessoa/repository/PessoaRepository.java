package streamSerialParalela.pessoa.repository;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import streamSerialParalela.pessoa.enums.EstadoCivil;
import streamSerialParalela.pessoa.enums.Etnia;
import streamSerialParalela.pessoa.model.Pessoa;
import streamSerialParalela.pessoa.model.Pessoa.PessoaBuilder;
import streamSerialParalela.util.ConvertToEnum;

public class PessoaRepository {

	private static List<Pessoa> pessoas = null;
	
	
	public static List<Pessoa> getPessoas() {
		if (Objects.isNull(pessoas)) 
			gerarListaDePessoas();
		
		return pessoas;
	}


	private static void gerarListaDePessoas() {
		try {
			final String FILE_PATH = "src\\main\\resources\\base-dados.txt";
			
			FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			String dadosPessoa = bufferedReader.readLine();
			pessoas = new ArrayList<>();
		
			while (Objects.nonNull(dadosPessoa)) {
				pessoas.add(extrairDadosDaPessoaDoArquivo(dadosPessoa));
				dadosPessoa = bufferedReader.readLine();
			}
			
			bufferedReader.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static Pessoa extrairDadosDaPessoaDoArquivo(String dadosPessoa) {
		final String SEPARADOR_DADOS = ";";
		String[] dadosPessoais = dadosPessoa.split(SEPARADOR_DADOS);
		int indiceDadosPessoais = 0;

		PessoaBuilder pessoaBuilder = Pessoa.builder()
				.nome(dadosPessoais[indiceDadosPessoais])
				.dataNascimento(converterDataNascimentoParaLocalDate(dadosPessoais[++indiceDadosPessoais]))
				.etnia(ConvertToEnum.converter(Etnia.class, dadosPessoais[++indiceDadosPessoais]))
				.estadoCivil(ConvertToEnum.converter(EstadoCivil.class, dadosPessoais[++indiceDadosPessoais]));
		
		boolean pessoaJuridica = Boolean.valueOf(dadosPessoais[++indiceDadosPessoais].toLowerCase());
		
		pessoaBuilder.profissao(dadosPessoais[++indiceDadosPessoais])
				.cnpj(pessoaJuridica ? dadosPessoais[++indiceDadosPessoais] : null)
				.cpf(!pessoaJuridica ? dadosPessoais[++indiceDadosPessoais] : null)
				.salario(Integer.parseInt(dadosPessoais[++indiceDadosPessoais]))
				.telefone(dadosPessoais[++indiceDadosPessoais])
				.paisDeOrigem(dadosPessoais[++indiceDadosPessoais]);
		
		return pessoaBuilder.build();
	}
	
	private static LocalDate converterDataNascimentoParaLocalDate(String dataNascimento) {
		return LocalDate.parse(dataNascimento, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
}
