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
}
