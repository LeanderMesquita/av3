package entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity
public class Campeonato {

	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	
	@OneToMany(mappedBy = "campeonato", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Jogo> jogos;
	
	public Campeonato() {
		this.jogos = new ArrayList<>();
	}
	
	public Campeonato(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
		this.jogos = new ArrayList<>();
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
	
	public List<Jogo> getJogos() {
        return jogos;
    }

    public void setJogos(List<Jogo> jogos) {
        this.jogos = jogos;
    }
	
    public void adicionarJogo(Jogo jogo) {
        this.jogos.add(jogo);
        jogo.setCampeonato(this); 
    }
	
}
