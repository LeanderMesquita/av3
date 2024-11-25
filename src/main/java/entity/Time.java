package entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Time {

	@Id
	@GeneratedValue
	private Long id;
	
	private String nome;
	private Integer saldoDeGols;
	private Integer vitorias;
	private Integer derrotas;
	private Integer empates;
	private Integer pontuacao;
	
	@ManyToMany(mappedBy = "times")
	private List<Jogo> jogos;
	
	public Time(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
		this.pontuacao = 0;
	}
	
	public Time() {
		this.jogos = new ArrayList<Jogo>();
	}
	
	public void calcularPontuacao() {
		int totalVitorias = vitorias != null ? vitorias : 0;
		int totalEmpates = empates != null ? empates : 0;
		this.pontuacao = (totalVitorias * 3) + (totalEmpates * 1);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getSaldoDeGols() {
		return saldoDeGols;
	}

	public void setSaldoDeGols(Integer saldoDeGols) {
		this.saldoDeGols = saldoDeGols;
	}

	public Integer getVitorias() {
		return vitorias;
	}

	public void setVitorias(Integer vitorias) {
		this.vitorias = vitorias;
	}

	public Integer getDerrotas() {
		return derrotas;
	}

	public void setDerrotas(Integer derrotas) {
		this.derrotas = derrotas;
	}

	public Integer getEmpates() {
		return empates;
	}

	public void setEmpates(Integer empates) {
		this.empates = empates;
	}

	public Integer getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(Integer pontuacao) {
		this.pontuacao = pontuacao;
	}

	public List<Jogo> getJogos() {
		return jogos;
	}

	public void setJogos(List<Jogo> jogos) {
		this.jogos = jogos;
	}

	@Override
	public String toString() {
		return nome;
	}
	
	
}
