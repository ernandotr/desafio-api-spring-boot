package br.com.rezende.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rezende.exception.ResourceNotfoundExceprion;
import br.com.rezende.math.CalculoDigitoUnico;
import br.com.rezende.model.DigitoUnicoCalculado;
import br.com.rezende.model.EntradaCalculoDTO;
import br.com.rezende.model.Usuario;
import br.com.rezende.request.converter.NumberConverter;
import br.com.rezende.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Dígitto único", description = "Endpoint que possibilita realizar o cálculo do dígito único, dados os valores n e k, sendo: n o número do qual será gerado o dígito único e k o número de vezes que n deverá ser concatenado antes da realização do calculo do dígito único.")
@RestController
@RequestMapping(value = "/api/digito-unico")
public class DigitoUnicoController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@ApiOperation(value = "Retorna o dígito único  calculado com base base nos números N e K enviados. Caso seja envia um ID de usúario neste requisição será considerado que, o dígito calculado deve ser gardado para o usuário em questão.")
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Integer digitoUnico(@RequestBody EntradaCalculoDTO entrada) throws Exception {
		
		if(!NumberConverter.isNumeric(entrada.getN())) {
			throw new ResourceNotfoundExceprion("Este não é número válido");
		}
		if(Integer.valueOf(entrada.getN()) < 1 || Integer.valueOf(entrada.getN()) > Math.pow(10, 1000000)) {
			throw new ResourceNotfoundExceprion("Valor informado para n inválido . Imforme um valor para n onde: 1<=n<=10^1000000");
		}
		if(entrada.getK() < 1 || entrada.getK() > Math.pow(10, 5)) {
			throw new ResourceNotfoundExceprion("Valor informado para k inválido. Imforme um valor para k onde: 1<=k<=10^5");
		}
		DigitoUnicoCalculado digitoEntrada = new DigitoUnicoCalculado(entrada.getN(), entrada.getK());
		
		for(DigitoUnicoCalculado digitoCalculado : CalculoDigitoUnico.getInstance().getDigitosCalculados()) {
			if(digitoCalculado .equals(digitoEntrada)) {
				 return digitoCalculado.getDigito();
			}
		}
		
		Integer digito = CalculoDigitoUnico.digitoUNico(entrada.getN(), entrada.getK());
		digitoEntrada.setDigito(digito);
		CalculoDigitoUnico.getInstance().updateCache(digitoEntrada);
		if(entrada.getIdUsuario() != null) {
			gravarCalculoUsuario(entrada, digito);
		}
		return digito;
	}

	private void gravarCalculoUsuario(EntradaCalculoDTO entrada, Integer digito) {
		Usuario usuario = usuarioService.findUsuarioById(entrada.getIdUsuario());
		DigitoUnicoCalculado digitoCalculadoUsuario = new DigitoUnicoCalculado();
		digitoCalculadoUsuario.setN(entrada.getN());
		digitoCalculadoUsuario.setK(entrada.getK());
		digitoCalculadoUsuario.setDigito(digito);
		usuario.getDigitosUnicos().add(digitoCalculadoUsuario);
		usuarioService.updateUsuario(usuario);
	}
	
	
}
