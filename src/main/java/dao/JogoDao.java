package dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entity.Jogo;
import jpaUtil.JPAUtil;

public class JogoDao {
	
	public static List<Jogo> listAll() {
		EntityManager em = JPAUtil.criarEntityManager();
		Query q = em.createQuery("select jogo from Jogo jogo");
		List<Jogo> jogos = q.getResultList();
		em.close();
		return jogos;
	}
		
	public static void save(Jogo jogo) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		jogo.setDataCadastro(new Date());
		em.persist(jogo);
		em.getTransaction().commit();
		em.close();
	}
	
	public static void update(Jogo jogo) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		Jogo jogoToUpdate = em.find(Jogo.class, jogo.getId());
		
		jogoToUpdate.setId(jogoToUpdate.getId());
		jogoToUpdate.setDataPartida(jogo.getDataPartida());
		
		jogoToUpdate.setTime1(jogo.getTime1());
		jogoToUpdate.setTime2(jogo.getTime2());
		
		jogoToUpdate.setGolsTime1(jogo.getGolsTime1());
		jogoToUpdate.setGolsTime2(jogo.getGolsTime2());
		
		em.merge(jogoToUpdate);
		em.getTransaction().commit();
		em.close();
	}
	
	public static void delete(Jogo jogo) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		jogo = em.find(Jogo.class, jogo.getId());
		em.remove(jogo);
		em.getTransaction().commit();
		em.close();
	}
	
	public static List<Jogo> listByCampeonato(Long campeonatoId) {
	    EntityManager em = JPAUtil.criarEntityManager();
	    try {
	        Query query = em.createQuery("SELECT j FROM Jogo j WHERE j.campeonato.id = :campeonatoId", Jogo.class);
	        query.setParameter("campeonatoId", campeonatoId);
	        return query.getResultList();
	    } finally {
	        em.close();
	    }
	}
	
	
}
