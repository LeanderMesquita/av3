package bean;

import java.util.ArrayList;
import java.util.List;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import dao.JogoDao;
import entity.Jogo;


@ManagedBean
public class JogoBean {

	private Jogo jogo = new Jogo();
	private List<Jogo> jogos = new ArrayList<Jogo>();
	
	private String salvar() {
		
		JogoDao.save(jogo); 
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Jogo salvo com sucesso"));
        jogo = new Jogo();    
       
        return null;
		
	}
	
	private void prepararAtualizacao(Jogo jogo) {
		this.jogo = jogo;
	}
	
	private String atualizar() {
		JogoDao.update(jogo);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Jogo atualizado com sucesso"));
		jogos = JogoDao.listAll();
		
		return null;
	}
	
	private String deletar() {
		JogoDao.delete(jogo);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Jogo atualizado com sucesso"));
		jogos = JogoDao.listAll();
		
		return null;
	}
	
	private Jogo getJogo() {
		return jogo;
	}
	
	private List<Jogo> getJogos() {
		jogos = JogoDao.listAll();
		
		return jogos;
	}
	
	private void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}
	
	private void setJogos(List<Jogo> jogos) {
		this.jogos = jogos;
	}
}
