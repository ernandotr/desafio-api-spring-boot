package br.com.rezende.security;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.rezende.exception.ResourceNotfoundExceprion;
import br.com.rezende.math.CalculoDigitoUnico;

public class CalculoDigitoUnicoTest {
	
	@Test(expected = ResourceNotfoundExceprion.class)
	public void testDigitoUnicoUnsuportedMathOperationExceprion(){
		String n = "0";
		Integer k = 100001;
		CalculoDigitoUnico.digitoUNico(n, k);
	}
	
	@Test
	public void testDigitoUnicoWhereSizeNequalOneAndKequalOne(){
		String n = "9";
		Integer k = 1;
		Integer digito = CalculoDigitoUnico.digitoUNico(n, k);
		
		assertEquals(Integer.valueOf(Integer.valueOf(n)), digito);
	}
	
	@Test
	public void testDigitoUnicoWhereSizeNequalFourandKequalOne(){
		String n = "9875";
		Integer k = 1;
		Integer digito = CalculoDigitoUnico.digitoUNico(n, k);
		
		assertEquals(Integer.valueOf(2), digito);
	}
	
	@Test
	public void testDigitoUnicoWhereSizeNequalFourAndKequalFour(){
		String n = "9875";
		Integer k = 4;
		Integer digito = CalculoDigitoUnico.digitoUNico(n, k);
		
		assertEquals(Integer.valueOf(8), digito);
	}

}
