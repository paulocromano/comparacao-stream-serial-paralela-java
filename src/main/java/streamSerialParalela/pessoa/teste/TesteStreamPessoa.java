package streamSerialParalela.pessoa.teste;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Getter;
import lombok.Setter;
import streamSerialParalela.pessoa.enums.EstadoCivil;
import streamSerialParalela.pessoa.model.Pessoa;
import streamSerialParalela.pessoa.repository.PessoaRepository;
import streamSerialParalela.util.teste.enums.TamanhoBaseDeDados;
import streamSerialParalela.util.teste.enums.TipoStream;

@Getter
@Setter
@SuppressWarnings("unused")
public class TesteStreamPessoa {
	
	private TipoStream tipoStream;
	private TamanhoBaseDeDados tamanhoBaseDeDados;
	
	private Stream<Pessoa> baseStreamPessoa;
	
	
	public TesteStreamPessoa(TipoStream tipoStream, TamanhoBaseDeDados tamanhoBaseDeDados) {
		this.tipoStream = tipoStream;
		this.tamanhoBaseDeDados = tamanhoBaseDeDados;
	}

	public void configurar() {
		if (tipoStream.equals(TipoStream.SERIAL))  {
			baseStreamPessoa = PessoaRepository.getPessoas()
					.stream()
					.limit(tamanhoBaseDeDados.getQuantidade());
		}
		else {
			baseStreamPessoa = PessoaRepository.getPessoas()
					.parallelStream()
					.limit(tamanhoBaseDeDados.getQuantidade());
		}
		
		PessoaRepository.limparLista();
	}

	
	public void buscarMaiorSalarioDePessoasJuridicasCasadasPorPais() {
		Map<String, Optional<Pessoa>> collect = baseStreamPessoa
			.filter(pessoa -> pessoa.getEstadoCivil().equals(EstadoCivil.CASADO))
			.collect(Collectors.groupingBy(Pessoa::getPaisDeOrigem, 
					Collectors.filtering(pessoa -> Objects.nonNull(pessoa.getCnpj()), 
							Collectors.maxBy(Comparator.comparing(Pessoa::getSalario)))));		
	}
	
	public void filtrarPessoasPeloSalario() {
		List<Pessoa> pessoas = baseStreamPessoa
				.filter(pessoa -> pessoa.getSalario() > 2000)
				.collect(Collectors.toList());
	}
	
	public void buscarPessoaQuePossuiMenorSalario() {
		Optional<Pessoa> pessoaComMenorSalario = baseStreamPessoa
				.min(Comparator.comparing(Pessoa::getSalario));
	}
	
	public void agruparMediaSalarialDePessoasPorPais() {
		Map<String, Double> mediaSalarialDePessoasPorPais = baseStreamPessoa
			.collect(Collectors.groupingBy(Pessoa::getPaisDeOrigem, 
					Collectors.averagingInt(Pessoa::getSalario)));
	}
	
	
	
	
	
	
	
	public void filterExample2() {
		baseStreamPessoa.filter(pessoa -> pessoa.getNome().contains("Maria")).collect(Collectors.toList());
	}
	
	public void filterExample3() {
		LocalDate data = LocalDate.of(1980, 02, 20);
		
		baseStreamPessoa.filter(pessoa -> pessoa.getDataNascimento().isAfter(data))
			.collect(Collectors.toList());
	}
	
	public void filterExample4() {
		LocalDate data = LocalDate.of(1980, 02, 20);
		
		baseStreamPessoa.filter(pessoa -> pessoa.getSalario() > 2000 && pessoa.getDataNascimento().isAfter(data))
			.collect(Collectors.toList());
	}
	
	public void mapExemplo1() {
		baseStreamPessoa.map(Pessoa::getNome).collect(Collectors.toList());
	}
	
	public void mapExemplo2() {
		baseStreamPessoa.map(Pessoa::getDataNascimento).collect(Collectors.toList());
	}
	
	public void filterMapExemplo1() {
		LocalDate data = LocalDate.of(1980, 02, 20);
		
		baseStreamPessoa
			.filter(pessoa -> pessoa.getDataNascimento().isAfter(data))
			.map(Pessoa::getNome)
			.collect(Collectors.toList());
	}
	
	public void filterMapExemplo2() {	
		baseStreamPessoa
			.filter(pessoa -> pessoa.getPaisDeOrigem().equals("Japão"))
			.map(Pessoa::getNome)
			.collect(Collectors.toList());
	}

	
	public void maiorSalarioPorPais() {
		baseStreamPessoa
			.collect(Collectors.groupingBy(Pessoa::getPaisDeOrigem, 
					Collectors.maxBy(Comparator.comparing(Pessoa::getSalario))));
	}
	
	public void mediaSalarialDePessoasCasadas() {
		baseStreamPessoa
			.filter(pessoa -> pessoa.getEstadoCivil().equals(EstadoCivil.CASADO))
			.collect(Collectors.averagingInt(Pessoa::getSalario));
	}
}
