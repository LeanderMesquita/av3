package bean;

import java.util.ArrayList;
import java.util.List;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import dao.CampeonatoDao;
import dao.JogoDao;
import entity.Campeonato;
import entity.Jogo;

@ManagedBean
public class CampeonatoBean {
	
	private Campeonato campeonato = new Campeonato();
	private List<Campeonato> campeonatos = new ArrayList<Campeonato>();
	private List<Jogo> jogos = new ArrayList<Jogo>();
	
	public String salvar() {
		
		CampeonatoDao.save(campeonato); 
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Campeonato salvo com sucesso"));
        campeonato = new Campeonato();    
       
        return null;
		
	}
	
	public void prepararAtualizacao(Campeonato campeonato) {
		this.campeonato = campeonato;
	}
	
	public String atualizar() {
		CampeonatoDao.update(campeonato);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Campeonato atualizado com sucesso"));
		campeonatos = CampeonatoDao.listAll();
		
		return null;
	}
	
	public String deletar() {
		CampeonatoDao.delete(campeonato);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Campeonato atualizado com sucesso"));
		campeonatos = CampeonatoDao.listAll();
		
		return null;
	}
	
	
	public void carregarJogos(Campeonato campeonato) {
        this.campeonato = campeonato; 
        this.jogos = JogoDao.listByCampeonato(campeonato.getId()); 
        if (jogos.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Nenhum jogo associado a este campeonato."));
        }
    }
	
	public Campeonato getCampeonato() {
		return campeonato;
	}
	
	public List<Campeonato> getCampeonatos() {
		campeonatos = CampeonatoDao.listAll();
		
		return campeonatos;
	}
	
	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}
	
	public void setCampeonatos(List<Campeonato> campeonatos) {
		this.campeonatos = campeonatos;
	}

	public List<Jogo> getJogos() {
		return jogos;
	}

	public void setJogos(List<Jogo> jogos) {
		this.jogos = jogos;
	}
	
	
}
