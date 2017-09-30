package br.com.caelum.ingresso.model.descontos;

import java.math.BigDecimal;




public class SemDesconto implements Desconto{

	@Override
	public BigDecimal aplicarDescontoSobre(BigDecimal precoOriginal) {
		return precoOriginal;
	}
	
	public String getDescricao(){
		return "Normal";
		
	}
	
	
	public enum TipoDeIngresso {
		
		INTEIRO(new SemDesconto()),
		ESTUDANTE(new DescontoEstudante()),
		BANCO(new DescontodeTrintaPorCentoParaBancos());
			
		private final Desconto desconto;
		
		TipoDeIngresso(Desconto desconto){
			this.desconto=desconto;
			
		}
		
		public BigDecimal aplicarDesconto(BigDecimal valor){
			return desconto.aplicarDescontoSobre(valor);
		}

		public String getDescricao(){
			return desconto.getDescricao();
		}

	};

}
