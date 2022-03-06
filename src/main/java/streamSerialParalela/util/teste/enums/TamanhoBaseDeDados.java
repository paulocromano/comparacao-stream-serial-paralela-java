package streamSerialParalela.util.teste.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum TamanhoBaseDeDados {

	CEM(100L),
	MIL(1000L),
	DEZ_MIL(10_000L),
	CINQUENTA_MIL(50_000L),
	CEM_MIL(100_000L),
	QUINHENTOS_MIL(500_000L),
	UM_MILHAO(1_000_000L),
	DOIS_MILHOES(2_000_000L),
	CINCO_MILHOES(5_000_000L),
	DEZ_MILHOES(10_000_000);
	
	
	private long quantidade;
}
