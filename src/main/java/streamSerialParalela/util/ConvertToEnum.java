package streamSerialParalela.util;

public class ConvertToEnum {

	public static <E extends Enum<?> & ValueEnum> E converter(Class<E> targetEnum, String value) {		
		for (E constanteEnum : targetEnum.getEnumConstants()) 
			if (constanteEnum.getValue().equals(value))
				return constanteEnum;
		
		throw new IllegalArgumentException("O valor informado é inválido!");
	}
}
