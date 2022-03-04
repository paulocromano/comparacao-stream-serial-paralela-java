package streamSerialParalela.pessoa.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import streamSerialParalela.pessoa.enums.EstadoCivil;
import streamSerialParalela.pessoa.enums.Etnia;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class Pessoa {
	
	private String nome;
	private LocalDate dataNascimento;
	private Etnia etnia;
	private EstadoCivil estadoCivil;
	private String profissao;
	private String cpf;
	private String cnpj;
	private Integer salario;
	private String telefone;
	private String paisDeOrigem;
}
