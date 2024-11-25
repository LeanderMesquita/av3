package entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Jogo {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Temporal(TemporalType.DATE)
    @Column(name = "data_partida", nullable = false)
    private Date dataPartida;
	
	@Temporal(TemporalType.DATE)
    @Column(name = "data_cadastro", nullable = false)
    private Date dataCadastro;
	
	@Column(nullable = false)
    private String time1; 
	
	@Column(nullable = false)
    private String time2; 
	
	@ManyToOne
	@JoinColumn(name = "campeonato_id", nullable = false)
    private Campeonato campeonato; 
	
	@Column(name = "gols_time1")
    private Integer golsTime1;
	
	@Column(name = "gols_time2")
    private Integer golsTime2;

    public Jogo() {}

    
    public Jogo(Integer id, Date dataPartida, Date dataCadastro, String time1, String time2, Integer golsTime1, Integer golsTime2) {
        this.id = id;
        this.dataPartida = dataPartida;
        this.dataCadastro = dataCadastro;
        this.time1 = time1;
        this.time2 = time2;
        this.golsTime1 = golsTime1;
        this.golsTime2 = golsTime2;
    }

   
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataPartida() {
        return dataPartida;
    }

    public void setDataPartida(Date dataPartida) {
        this.dataPartida = dataPartida;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    public String getTime2() {
        return time2;
    }

    public void setTime2(String time2) {
        this.time2 = time2;
    }

    public Campeonato getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(Campeonato campeonato) {
        this.campeonato = campeonato;
    }

    public Integer getGolsTime1() {
        return golsTime1;
    }

    public void setGolsTime1(Integer golsTime1) {
        this.golsTime1 = golsTime1;
    }

    public Integer getGolsTime2() {
        return golsTime2;
    }

    public void setGolsTime2(Integer golsTime2) {
        this.golsTime2 = golsTime2;
    }
}