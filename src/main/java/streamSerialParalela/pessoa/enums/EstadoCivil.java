package streamSerialParalela.pessoa.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import streamSerialParalela.util.ValueEnum;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum EstadoCivil implements ValueEnum {

	SOLTEIRO("0"),
	CASADO("1"),
	SEPARADO("2"),
	DIVORCIADO("3"),
	VIUVO("4");
	
	private String value;
}
