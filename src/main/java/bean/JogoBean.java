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
public class JogoBean {

	private Jogo jogo = new Jogo();
	private List<Jogo> jogos = new ArrayList<Jogo>();
	Long campeonatoId;
	
	public String salvar() {
		
		if (jogo.getTime1().equals(jogo.getTime2())) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Jogos não podem ser do mesmo nome."));
			return null;
		}
		
		Campeonato campeonato = CampeonatoDao.findById(campeonatoId);
		jogo.setCampeonato(campeonato);
		JogoDao.save(jogo); 
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Jogo salvo com sucesso"));
        jogo = new Jogo();    
       
        return null;
		
	}
	
	public void prepararAtualizacao(Jogo jogo) {
		this.jogo = jogo;
	}
	
	public String atualizar() {
		
		if (jogo.getTime1().equals(jogo.getTime2())) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Jogos não podem ser do mesmo nome."));
			return null;
		}
		
		Campeonato campeonato = CampeonatoDao.findById(campeonatoId);
		jogo.setCampeonato(campeonato);
		JogoDao.update(jogo);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Jogo atualizado com sucesso"));
		jogos = JogoDao.listAll();
		
		return null;
	}
	
	public String deletar() {
		JogoDao.delete(jogo);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Jogo atualizado com sucesso"));
		jogos = JogoDao.listAll();
		
		return null;
	}
	
	public Jogo getJogo() {
		return jogo;
	}
	
	public List<Jogo> getJogos() {
		jogos = JogoDao.listAll();
		
		return jogos;
	}
	
	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}
	
	public void setJogos(List<Jogo> jogos) {
		this.jogos = jogos;
	}

	public Long getCampeonatoId() {
		return campeonatoId;
	}

	public void setCampeonatoId(Long campeonatoId) {
		this.campeonatoId = campeonatoId;
	}
	
	
}
