package br.com.caelum.ingressos.validacao;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.descontos.DescontoEstudante;
import br.com.caelum.ingresso.model.descontos.DescontodeTrintaPorCentoParaBancos;
import br.com.caelum.ingresso.model.descontos.SemDesconto;
import br.com.caelum.ingresso.modelo.Ingresso;

public class DescontoTest {

	
	@Test
	public void deveConcederDescontoDe20PorcentoParaIngressosDeClientesDeBanco(){
		
		Sala sala =new Sala("Eldorado - IMAX",new BigDecimal("20.5"));
		Filme filme1= new Filme("Rogue One", Duration.ofMinutes(120),"SCI-FI",new BigDecimal("12"));
		Sessao sessao= new Sessao(LocalTime.parse("10:00:00"),filme1,sala);
		Ingresso ingresso = new Ingresso(sessao, new DescontodeTrintaPorCentoParaBancos());
	
		BigDecimal precoEsperado = new BigDecimal("22.75");
		
		Assert.assertEquals(precoEsperado,ingresso.getPreco());
	}
	
	@Test
	public void deveConcederDescontoDe50PorcentoParaIngressosDeEstudantes(){
		
		Sala sala =new Sala("Eldorado - IMAX",new BigDecimal("20.5"));
		Filme filme1= new Filme("Rogue One", Duration.ofMinutes(120),"SCI-FI",new BigDecimal("12"));
		Sessao sessao= new Sessao(LocalTime.parse("10:00:00"),filme1,sala);
		Ingresso ingresso = new Ingresso(sessao, new DescontoEstudante());
	
		BigDecimal precoEsperado = new BigDecimal("16.25");
		
		Assert.assertEquals(precoEsperado,ingresso.getPreco());
	}
	
	@Test
	public void deveConcederDescontoParaIngressoNormal(){
		
		Sala sala =new Sala("Eldorado - IMAX",new BigDecimal("20.5"));
		Filme filme1= new Filme("Rogue One", Duration.ofMinutes(120),"SCI-FI",new BigDecimal("12"));
		Sessao sessao= new Sessao(LocalTime.parse("10:00:00"),filme1,sala);
		Ingresso ingresso = new Ingresso(sessao, new SemDesconto());
	
		BigDecimal precoEsperado = new BigDecimal("32.5");
		
		Assert.assertEquals(precoEsperado,ingresso.getPreco());
	}
	
	
}
