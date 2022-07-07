package streamSerialParalela.util.teste;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import org.reflections.Reflections;

import lombok.NonNull;
import streamSerialParalela.util.teste.enums.TamanhoBaseDeDados;
import streamSerialParalela.util.teste.enums.TipoStream;

public class InitializerTests {
	
	private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

	public static void run(@NonNull TipoStream tipoStream, @NonNull TamanhoBaseDeDados tamanhoBaseDeDados) {
		Reflections reflections = new Reflections("");
		Set<Class<? extends Object>> classesAnotadas = reflections.getTypesAnnotatedWith(Test.class);
		
        for (Class<?> classe : classesAnotadas) {
        	if (classe.getAnnotation(Test.class).enable() == true) {        		
            	try {    		
            		Object classTest = getInstanceClassTest(classe);
            		ConfigurationClassTest configurationClassTest = configureClassTest(classTest, tipoStream, tamanhoBaseDeDados);
					
        			for (Method method : classe.getMethods()) {
        				if (method.isAnnotationPresent(Test.class) && method.getAnnotation(Test.class).enable() == true) {
        					validMethod(method);
        					System.out.println(timeFormatter.format(LocalTime.now()));
        					method.invoke(classTest);
        					configurationClassTest.reconfigureForNextTest();
        				}
        			}
            	} 
            	catch (NoSuchMethodException | SecurityException | InstantiationException 
            			| IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				} 
        	}
        }
	}
	
	private static Object getInstanceClassTest(Class<?> classe) throws NoSuchMethodException, 
		SecurityException, InstantiationException, IllegalAccessException, 
		IllegalArgumentException, InvocationTargetException {
		
		Constructor<?> constructor = classe.getConstructor();
		return constructor.newInstance();
	}
	
	private static ConfigurationClassTest configureClassTest(Object classTest, TipoStream tipoStream,
		TamanhoBaseDeDados tamanhoBaseDeDados) {
		
		ConfigurationClassTest configurationClassTest = null;
		
		try {
			configurationClassTest = (ConfigurationClassTest) classTest;
			
			configurationClassTest.setTipoStream(tipoStream);
			configurationClassTest.setTamanhoBaseDeDados(tamanhoBaseDeDados);
			configurationClassTest.configure();
		}
    	catch (ClassCastException e) {
    		throw new ClassCastException("A classe '" + classTest.getClass().getName()
    			+ "' deve implementar a Interface '" 
				+ ConfigurationClassTest.class.getName());
    	}
	
		return configurationClassTest;
	}
	
	private static void validMethod(Method method) throws IllegalAccessException {
		if (Modifier.isStatic(method.getModifiers()))
			throw new IllegalAccessException("O método '" + method.toString() 
				+ "' não pode ser estático!");
		
		if (!method.getReturnType().equals(void.class)) 
			throw new IllegalArgumentException("O método não deve ter retorno!");
		
		if (method.getParameterCount() != 0) 
			throw new IllegalArgumentException("O método '" + method.toString() 
				+ "' não deve possuir parâmetros!");
	}
}
