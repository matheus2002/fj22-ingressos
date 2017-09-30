package br.com.caelum.ingresso.modelo;

import java.math.BigDecimal;

import br.com.caelum.ingresso.model.Lugar;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.descontos.SemDesconto.TipoDeIngresso;

public class Ingresso {

	private Sessao sessao;
	private BigDecimal preco;
	private Lugar lugar;
	private TipoDeIngresso  tipoDeIngresso;
	public Ingresso(){}
	
	public Ingresso(Sessao sessao, TipoDeIngresso tipoDeIngresso,Lugar lugar){
		this.lugar=lugar;
		this.sessao =sessao;
		this.tipoDeIngresso=tipoDeIngresso;
		this.preco = this.tipoDeIngresso.aplicarDesconto(sessao.getPreco());
	}

	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
	
	
}
