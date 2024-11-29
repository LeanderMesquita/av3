package bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.PersistenceException;

import dao.TimeDao;
import entity.Time;

@ManagedBean
public class TimeBean {
	
	private Time time = new Time();
	private List<Time> times = new ArrayList<Time>();
	
	public String salvar() {
		
		TimeDao.save(time); 
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Time salvo com sucesso"));
        time = new Time();    
       
        return null;
		
	}
	
	public void prepararAtualizacao(Time time) {
		this.time = time;
	}
	
	public String atualizar() {
		try {
			TimeDao.update(time);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Time atualizado com sucesso"));
			times = TimeDao.listAll();
		}
		catch(PersistenceException  e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Não se pode atualizar o nome enquanto tiver jogo ativo."));
		}
		
		
		return null;
	}
	
	public String deletar(Time time) {
		try {
			TimeDao.delete(time);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Time deletado com sucesso"));
			times = TimeDao.listAll();
		}catch(PersistenceException  e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Time ainda possui jogos ativos, exclua os jogos!"));
		}
		
		return null;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public List<Time> getTimes() {
		times = TimeDao.listAll();
		return times;
	}

	public void setTimes(List<Time> times) {
		this.times = times;
	}
}
