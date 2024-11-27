package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

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

    @OneToMany(mappedBy = "time1")
    private List<Jogo> jogosComoTime1;

    @OneToMany(mappedBy = "time2")
    private List<Jogo> jogosComoTime2;

    public Time(Long id, String nome) {
        this.id = id;
        this.nome = nome;
        this.pontuacao = 0;
        this.derrotas = 0;
        this.empates = 0;
        this.vitorias = 0;
        this.saldoDeGols = 0;
    }

    public Time() {
        this.pontuacao = 0;
        this.derrotas = 0;
        this.empates = 0;
        this.vitorias = 0;
        this.saldoDeGols = 0;
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

    public List<Jogo> getJogosComoTime1() {
        return jogosComoTime1;
    }

    public void setJogosComoTime1(List<Jogo> jogosComoTime1) {
        this.jogosComoTime1 = jogosComoTime1;
    }

    public List<Jogo> getJogosComoTime2() {
        return jogosComoTime2;
    }

    public void setJogosComoTime2(List<Jogo> jogosComoTime2) {
        this.jogosComoTime2 = jogosComoTime2;
    }

    @Override
    public String toString() {
        return nome;
    }
}
