package bean;

import java.util.ArrayList;
import java.util.List;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import dao.CampeonatoDao;
import dao.JogoDao;
import dao.TimeDao;
import entity.Campeonato;
import entity.Jogo;
import entity.Time;


@ManagedBean
public class JogoBean {

	private Jogo jogo = new Jogo();
	private List<Jogo> jogos = new ArrayList<Jogo>();
	Long campeonatoId;
	Long time1Id;
	Long time2Id;
	Long timeId;
	
	 public JogoBean() {
	        jogos = new ArrayList<>();
	    }
	
	public String salvar() {
		
		Campeonato campeonato = CampeonatoDao.findById(campeonatoId);
		Time time1 = TimeDao.findById(time1Id);
		Time time2 = TimeDao.findById(time2Id);
		
		if (time1.getNome().equals(time2.getNome())) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Jogos não podem ser do mesmo nome."));
			return null;
		}
		
		jogo.setCampeonato(campeonato);
		jogo.setTime1(time1);
		jogo.setTime2(time2);
		
		setTimeMetricas(time1, jogo);
		TimeDao.update(time1);
		setTimeMetricas(time2, jogo);
		TimeDao.update(time2);
		
		JogoDao.save(jogo); 
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Jogo salvo com sucesso"));
        jogo = new Jogo();    
       
        return null;
		
	}
	
	public void prepararAtualizacao(Jogo jogo) {
		this.jogo = jogo;
	}
	
	public String atualizar() {
		
		Campeonato campeonato = CampeonatoDao.findById(campeonatoId);
		Time time1 = TimeDao.findById(time1Id);
		Time time2 = TimeDao.findById(time2Id);
		
		if (time1.getNome().equals(time2.getNome())) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Jogos não podem ser do mesmo nome."));
			return null;
		}
		
		jogo.setCampeonato(campeonato);
		jogo.setTime1(time1);
		jogo.setTime2(time2);
		
		setTimeMetricas(time1, jogo);
		TimeDao.update(time1);
		setTimeMetricas(time2, jogo);
		TimeDao.update(time2);
		
		JogoDao.update(jogo);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Jogo atualizado com sucesso"));
		jogos = JogoDao.listAll();
		
		return null;
	}
	
	public String deletar(Jogo jogo) {
		JogoDao.delete(jogo);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Jogo deletado com sucesso"));
		jogos = JogoDao.listAll();
		
		return null;
	}
	
	public void filtrarJogosPorTime() {
		if (timeId != null) {
            jogos = JogoDao.findAllGamesByTimeId(timeId);
        } else {
        	jogos = new ArrayList<>();
        }
    }
	
	
	public Jogo getJogo() {
		return jogo;
	}
	
	public List<Jogo> getJogos() {
		String pagina = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        if (pagina.contains("listagem_jogo")) {
            jogos = JogoDao.listAll();
        }
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

	public Long getTime1Id() {
		return time1Id;
	}

	public void setTime1Id(Long time1Id) {
		this.time1Id = time1Id;
	}

	public Long getTime2Id() {
		return time2Id;
	}

	public void setTime2Id(Long time2Id) {
		this.time2Id = time2Id;
	}
	
	public Long getTimeId() {
		return timeId;
	}

	public void setTimeId(Long timeId) {
		this.timeId = timeId;
	}

	public void setTimeMetricas(Time time, Jogo jogo) {
	    if (time.getNome().equals(jogo.getTime1().getNome())) {
	        time.setSaldoDeGols(time.getSaldoDeGols() + jogo.getGolsTime1());

	        if (jogo.getGolsTime1() < jogo.getGolsTime2()) {
	            time.setDerrotas(time.getDerrotas() + 1);
	        } else if (jogo.getGolsTime1().equals(jogo.getGolsTime2())) {
	            time.setEmpates(time.getEmpates() + 1);
	        } else {
	            time.setVitorias(time.getVitorias() + 1);
	        }

	    } else {
	        time.setSaldoDeGols(time.getSaldoDeGols() + jogo.getGolsTime2());

	        if (jogo.getGolsTime2() < jogo.getGolsTime1()) {
	            time.setDerrotas(time.getDerrotas() + 1);
	        } else if (jogo.getGolsTime1().equals(jogo.getGolsTime2())) {
	            time.setEmpates(time.getEmpates() + 1);
	        } else {
	            time.setVitorias(time.getVitorias() + 1);
	        }
	    }
	}

	
}
