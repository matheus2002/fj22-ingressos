package br.com.caelum.ingresso.model.descontos;

import java.math.BigDecimal;

public class DescontodeTrintaPorCentoParaBancos implements Desconto{

	private BigDecimal percentualDesconto = new BigDecimal("0.3");
	
	@Override
	public BigDecimal aplicarDescontoSobre(BigDecimal precoOriginal) {
		return precoOriginal.subtract(trintaPorCentoSobre(precoOriginal));		
		
	}
	
	private BigDecimal trintaPorCentoSobre(BigDecimal  precoOriginal){
		return precoOriginal.multiply(percentualDesconto);
		
		
	}
	
	public String getDescricao(){
		return "Desconto Banco";
		
	}
	
}
