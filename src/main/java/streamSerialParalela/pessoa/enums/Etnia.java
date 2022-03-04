package streamSerialParalela.pessoa.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import streamSerialParalela.util.ValueEnum;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Etnia implements ValueEnum {

	BRANCO("0"),
	PARDO("1"),
	NEGRO("2"),
	INDIGENA("3"),
	AMARELO("4");
	
	
	private String value;
}
