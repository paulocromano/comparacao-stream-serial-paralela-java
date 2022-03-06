package streamSerialParalela.util.teste;

import streamSerialParalela.util.teste.enums.TamanhoBaseDeDados;
import streamSerialParalela.util.teste.enums.TipoStream;

public interface ConfigurationClassTest {

	public void setTipoStream(TipoStream tipoStream);
	public void setTamanhoBaseDeDados(TamanhoBaseDeDados tamanhoBaseDeDados);
	public void configure();
	public void reconfigureForNextTest();
}
