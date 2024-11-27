package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entity.Time;
import jpaUtil.JPAUtil;

public class TimeDao {
	
	public static List<Time> listAll() {
		EntityManager em = JPAUtil.criarEntityManager();
		Query q = em.createQuery("select time from Time time");
		List<Time> times = q.getResultList();
		em.close();
		return times;
	}
		
	public static void save(Time time) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		em.persist(time);
		em.getTransaction().commit();
		em.close();
	}
	
	public static void update(Time time) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		Time timeToUpdate = em.find(Time.class, time.getId());
		
		timeToUpdate.setId(timeToUpdate.getId());
		timeToUpdate.setDerrotas(time.getDerrotas());
		timeToUpdate.setEmpates(time.getEmpates());
		timeToUpdate.setNome(time.getNome());
		timeToUpdate.setVitorias(time.getVitorias());
		timeToUpdate.setSaldoDeGols(time.getSaldoDeGols());
		
		em.merge(timeToUpdate);
		em.getTransaction().commit();
		em.close();
	}
	
	public static void delete(Time time) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		time = em.find(Time.class, time.getId());
		em.remove(time);
		em.getTransaction().commit();
		em.close();
	}
	
	public static Time findById(Long id) {
		EntityManager em = JPAUtil.criarEntityManager();
        try {
            return em.find(Time.class, id); 
        } finally {
            em.close(); 
        }
    }
}
