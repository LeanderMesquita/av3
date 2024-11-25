package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entity.Campeonato;
import jpaUtil.JPAUtil;

public class CampeonatoDao {

	public static List<Campeonato> listAll() {
		EntityManager em = JPAUtil.criarEntityManager();
		Query q = em.createQuery("select campeonato from Campeonato campeonato");
		List<Campeonato> campeonatos = q.getResultList();
		em.close();
		
		return campeonatos;
	}
		
	public static void save(Campeonato campeonato) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		em.persist(campeonato);
		em.getTransaction().commit();
		em.close();
	}
	
	public static void update(Campeonato campeonato) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		Campeonato campeonatoToUpdate = em.find(Campeonato.class, campeonato.getId());
		
		campeonatoToUpdate.setId(campeonatoToUpdate.getId());
		campeonatoToUpdate.setNome(campeonato.getNome());
		campeonatoToUpdate.setJogos(campeonato.getJogos());
		
		em.merge(campeonatoToUpdate);
		em.getTransaction().commit();
		em.close();
	}
	
	public static void delete(Campeonato campeonato) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		campeonato = em.find(Campeonato.class, campeonato.getId());
		em.remove(campeonato);
		em.getTransaction().commit();
		em.close();
	}
	
	public static Campeonato findById(Long id) {
		EntityManager em = JPAUtil.criarEntityManager();
        try {
            return em.find(Campeonato.class, id); 
        } finally {
            em.close(); 
        }
    }
	
}
