package br.com.rezende.math;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import br.com.rezende.exception.ResourceNotfoundExceprion;
import br.com.rezende.model.DigitoUnicoCalculado;


public class CalculoDigitoUnico {
	
	private static Queue<DigitoUnicoCalculado> digitosCalculados = new LinkedBlockingQueue<DigitoUnicoCalculado>(10);
	
	private CalculoDigitoUnico() {}
	
	private static CalculoDigitoUnico instance;
	
	public static CalculoDigitoUnico getInstance() {
		if(instance == null) {
			instance = new CalculoDigitoUnico();
		}
		return instance;
	}
	
	public static Integer digitoUNico(String x) {
		if(x.length() == 1) {
			return Integer.valueOf(x);
		}
		char[] digitos = x.toCharArray();
		Integer digito = 0;
		for (char c : digitos) {
			digito += Integer.parseInt(String.valueOf(c));
		}
		return digitoUNico(digito.toString());
	}
	
	public static Integer digitoUNico(String n, int k) {
		if(Double.valueOf(n) > 0 && Double.valueOf(n) <= Math.pow(10, 1000000) && k > 0 && k < Math.pow(10, 5)) {
			String P = "";
			for(int i = 0; i < k; i++) {
				P += n;
			}
			return digitoUNico(P);
		}
		throw new ResourceNotfoundExceprion("Please, type n value (1<=n<=10^1000000) and type k value (1<=k<=10^5)");
		
	}
	
	public void updateCache(DigitoUnicoCalculado digitoCalculado) {
		if(digitosCalculados.size() == 10) {
			digitosCalculados.poll();
		}
		digitosCalculados.add(digitoCalculado);
	}
	
	public  Queue<DigitoUnicoCalculado> getDigitosCalculados() {
		return digitosCalculados;
	}

}
