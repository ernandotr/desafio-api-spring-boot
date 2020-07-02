package br.com.rezende.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "digito_calculado_usuario")
public class DigitoUnicoCalculado implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String n;
	
	@Column
	private Integer k;
	
	@Column
	private Integer digito;
	
	
	public DigitoUnicoCalculado(String n, Integer k) {
		this.n = n;
		this.k = k;
	}

	public DigitoUnicoCalculado() {
	}

	public String getN() {
		return n;
	}
	
	public void setN(String n) {
		this.n = n;
	}
	
	public Integer getK() {
		return k;
	}
	
	public void setK(Integer k) {
		this.k = k;
	}
	
	public Integer getDigito() {
		return digito;
	}
	
	public void setDigito(Integer digito) {
		this.digito = digito;
	}
	
}
