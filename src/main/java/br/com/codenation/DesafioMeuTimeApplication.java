package br.com.codenation;

import br.com.codenation.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.exceptions.TimeNaoEncontradoException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



public class DesafioMeuTimeApplication implements MeuTimeInterface {

	private List<Time> Times = new ArrayList<Time>();
	private List<Jogador> Jogadores = new ArrayList<Jogador>();

	private Time getTime(long idTime){
		for(Time time:Times)
			if(time.exist(idTime))
				return time;
		throw new TimeNaoEncontradoException();
	}

	private Jogador getJogador(long idJogador){
		for(Jogador jogador:Jogadores)
			if(jogador.exist(idJogador))
				return jogador;
		throw new JogadorNaoEncontradoException();
	}

	private List<Long> getJogadores(long idTime){
		ArrayList<Long> lstJogadores = new ArrayList<Long>();
		for(Jogador jogador:Jogadores)
			if(jogador.getIdTime()==idTime)
				lstJogadores.add(jogador.getId());
		return lstJogadores;
	}

	private List<Jogador> getJogadoresbyTime(long idTime){
		getTime(idTime);
		List<Jogador> lstJogadores = new ArrayList<Jogador>();
		for(Jogador jogador:Jogadores)
			if(jogador.getIdTime()==idTime)
				lstJogadores.add(jogador);
			return lstJogadores;
	}

	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		for (Time time : Times)
			if (time.exist(id))
				throw new IdentificadorUtilizadoException();

		Times.add(new Time(id,nome, dataCriacao,corUniformePrincipal, corUniformeSecundario));

	}

	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		getTime(idTime);
		for(Jogador jogador:Jogadores)
			if(jogador.exist(id))
				throw new IdentificadorUtilizadoException();

		Jogadores.add(new Jogador(id,idTime,nome,dataNascimento,nivelHabilidade,salario));
	}

	public void definirCapitao(Long idJogador) {
		long idTime = getJogador(idJogador).getIdTime();

		getTime(idTime).setCapitao(idJogador);

	}

	public Long buscarCapitaoDoTime(Long idTime) {
		if(getTime(idTime).getCapitao()!=null)
			return getTime(idTime).getCapitao();
		else
			throw new CapitaoNaoInformadoException();
	}

	public String buscarNomeJogador(Long idJogador) {
		return getJogador(idJogador).getNome();
	}

	public String buscarNomeTime(Long idTime) {

		return getTime(idTime).getNome();
	}

	public List<Long> buscarJogadoresDoTime(Long idTime) {
		getTime(idTime);
		return getJogadores(idTime);

	}

	public Long buscarMelhorJogadorDoTime(Long idTime) {
		getTime(idTime);
		return Collections.max(getJogadoresbyTime(idTime), Comparator.comparing(j -> j.getNivelHabilidade())).getId();

	}

	public Long buscarJogadorMaisVelho(Long idTime) {
		getTime(idTime);
		return Collections.min(getJogadoresbyTime(idTime), Comparator.comparing(j -> j.getDataNascimento())).getId();

	}

	public List<Long> buscarTimes() {
		if (Times.isEmpty())
			return new ArrayList<Long>();


		List<Long> lstTimes = new ArrayList<>();
		Times.sort(Comparator.comparing(Time::getId));
		Times.forEach(t->lstTimes.add(t.getId()));

		return lstTimes;
	}

	public Long buscarJogadorMaiorSalario(Long idTime) {
		getTime(idTime);
		return Collections.max(getJogadoresbyTime(idTime), Comparator.comparing(j -> j.getSalario())).getId();

	}

	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		getJogador(idJogador);
		return getJogador(idJogador).getSalario();
	}

	public List<Long> buscarTopJogadores(Integer top) {

		if (Jogadores.isEmpty()||Jogadores.size()<top)
			return new ArrayList<Long>();


		Jogadores.sort(Comparator.comparing(Jogador::getNivelHabilidade).reversed());
		List<Jogador> topJogadores = Jogadores.subList(0,top);
		topJogadores.sort(Comparator.comparing(Jogador::getId));
		if(topJogadores.size()>top)topJogadores.remove(top);
		List<Long> Tops = new ArrayList<>();
		topJogadores.sort(Comparator.comparing(Jogador::getNivelHabilidade).reversed());
		topJogadores.forEach(j-> Tops.add(j.getId()));

		return Tops;
	}

}
