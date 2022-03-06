package streamSerialParalela.pessoa.teste;

import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Setter;
import streamSerialParalela.pessoa.model.Pessoa;
import streamSerialParalela.pessoa.repository.PessoaRepository;
import streamSerialParalela.util.teste.ConfigurationClassTest;
import streamSerialParalela.util.teste.Test;
import streamSerialParalela.util.teste.enums.TamanhoBaseDeDados;
import streamSerialParalela.util.teste.enums.TipoStream;

@Test
@Setter
public class TesteStreamPessoa implements ConfigurationClassTest {
	
	private TipoStream tipoStream;
	private TamanhoBaseDeDados tamanhoBaseDeDados;
	
	private Stream<Pessoa> baseStreamPessoa;
	

	@Override
	public void configure() {
		if (tipoStream.equals(TipoStream.SERIAL))  {
			baseStreamPessoa = PessoaRepository.getPessoas()
					.parallelStream()
					.limit(tamanhoBaseDeDados.getQuantidade());
		}
		else {
			baseStreamPessoa = PessoaRepository.getPessoas()
					.stream()
					.limit(tamanhoBaseDeDados.getQuantidade());
		}
	}
	
	@Override
	public void reconfigureForNextTest() {
		configure();
	}
	
	@Test
	public void filterExample1() {
		baseStreamPessoa.filter(pessoa -> pessoa.getSalario() > 2000).collect(Collectors.toList());
	}
	
	@Test
	public void filterExample2() {
		baseStreamPessoa.filter(pessoa -> pessoa.getNome().contains("Maria")).collect(Collectors.toList());
	}
	
	@Test
	public void filterExample3() {
		LocalDate data = LocalDate.of(1980, 02, 20);
		
		baseStreamPessoa.filter(pessoa -> pessoa.getDataNascimento().isAfter(data))
			.collect(Collectors.toList());
	}
}
