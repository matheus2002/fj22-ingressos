package br.com.caelum.ingressos.validacao;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorDeSessaoTest {

	@Test
	public void garanteQueNaoDevePermitirSessaoNoMesmoHorario(){
		
		Filme filme= new Filme("Rogue One", Duration.ofMinutes(120),"SCI-FI",BigDecimal.ONE);
		LocalTime horario = LocalTime.parse("10:00:00");
	
		Sala sala =new Sala("Eldorado - IMAX",BigDecimal.ONE);
		List<Sessao> sessoes= Arrays.asList(new Sessao(horario,filme,sala));
		
		Sessao sessao= new Sessao(horario, filme, sala);
		GerenciadorDeSessao gerenciador= new GerenciadorDeSessao(sessoes);
		Assert.assertFalse(gerenciador.cabe(sessao));
	}
	
	@Test
	public void garanteQueNaoDevePermitirSessoesTerminandoDentroDoHorarioDeUmaSessaoJaExistente(){
		
		Filme filme= new Filme("Rogue One", Duration.ofMinutes(120),"SCI-FI",BigDecimal.ONE);
		LocalTime horario = LocalTime.parse("10:00:00");
	
		Sala sala =new Sala("Eldorado - IMAX",BigDecimal.ONE);
		List<Sessao> sessoes= Arrays.asList(new Sessao(horario,filme,sala));
		
		Sessao sessao= new Sessao(horario.minusHours(1),filme,sala);
		GerenciadorDeSessao gerenciador= new GerenciadorDeSessao(sessoes);
		Assert.assertFalse(gerenciador.cabe(sessao));
	}
	
	@Test
	public void garanteQueNaoDevePermitirSessoesIniciandoDentroDoHorarioDeUmaSessaoJaExistente(){
		
		Filme filme= new Filme("Rogue One", Duration.ofMinutes(120),"SCI-FI",BigDecimal.ONE);
		LocalTime horario = LocalTime.parse("10:00:00");
	
		Sala sala =new Sala("Eldorado - IMAX",BigDecimal.ONE);
		List<Sessao> sessoesDaSala= Arrays.asList(new Sessao(horario,filme,sala));
		
		GerenciadorDeSessao gerenciador= new GerenciadorDeSessao(sessoesDaSala);
		Assert.assertFalse(gerenciador.cabe(new Sessao(horario.plusHours(1),filme,sala)));
	}
	
	@Test
	public void garanteQueNaoDevePermirirUmaInsercaoEntreDoisFilmes(){
		Sala sala =new Sala("Eldorado - IMAX",BigDecimal.ONE);
		
		Filme filme1= new Filme("Rogue One", Duration.ofMinutes(120),"SCI-FI",BigDecimal.ONE);
		LocalTime dezhoras = LocalTime.parse("10:00:00");
		Sessao sessaodadez= new Sessao(dezhoras,filme1,sala);
		
		Filme filme2= new Filme("Rogue One", Duration.ofMinutes(120),"SCI-FI",BigDecimal.ONE);
		LocalTime dezoitohoras = LocalTime.parse("18:00:00");
		Sessao sessaodadezoito= new Sessao(dezoitohoras,filme2,sala);
	
		List<Sessao> sessoesDaSala= Arrays.asList(sessaodadez, sessaodadezoito);
		
		GerenciadorDeSessao gerenciador= new GerenciadorDeSessao(sessoesDaSala);
		
		Assert.assertTrue(gerenciador.cabe(new Sessao(LocalTime.parse("13:00:00"),filme2,sala)));
	}
}
