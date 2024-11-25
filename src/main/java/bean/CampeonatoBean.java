package bean;

import java.util.ArrayList;
import java.util.List;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import dao.CampeonatoDao;
import entity.Campeonato;

@ManagedBean
public class CampeonatoBean {
	
	private Campeonato campeonato = new Campeonato();
	private List<Campeonato> campeonatos = new ArrayList<Campeonato>();
	
	private String salvar() {
		
		CampeonatoDao.save(campeonato); 
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Campeonato salvo com sucesso"));
        campeonato = new Campeonato();    
       
        return null;
		
	}
	
	private void prepararAtualizacao(Campeonato campeonato) {
		this.campeonato = campeonato;
	}
	
	private String atualizar() {
		CampeonatoDao.update(campeonato);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Campeonato atualizado com sucesso"));
		campeonatos = CampeonatoDao.listAll();
		
		return null;
	}
	
	private String deletar() {
		CampeonatoDao.delete(campeonato);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Campeonato atualizado com sucesso"));
		campeonatos = CampeonatoDao.listAll();
		
		return null;
	}
	
	private Campeonato getCampeonato() {
		return campeonato;
	}
	
	private List<Campeonato> getCampeonatos() {
		campeonatos = CampeonatoDao.listAll();
		
		return campeonatos;
	}
	
	private void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}
	
	private void setCampeonatos(List<Campeonato> campeonatos) {
		this.campeonatos = campeonatos;
	}
}
