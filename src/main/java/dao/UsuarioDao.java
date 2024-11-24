package dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entity.Usuario;
import jpaUtil.JPAUtil;

public class UsuarioDao {
	
	public static List<Usuario> listAll() {
		EntityManager em = JPAUtil.criarEntityManager();
		Query q = em.createQuery("select usuario from Usuario usuario");
		List<Usuario> usuarios = q.getResultList();
		em.close();
		
		return usuarios;
	}
		
	public static void save(Usuario usuario) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
		em.close();
	}
	
	public static void update(Usuario usuario) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		Usuario usuarioToUpdate = em.find(Usuario.class, usuario.getId());
		
		usuarioToUpdate.setId(usuarioToUpdate.getId());
		usuarioToUpdate.setNome(usuario.getNome());
		
		em.merge(usuarioToUpdate);
		em.getTransaction().commit();
		em.close();
	}
	
	public static void delete(Usuario usuario) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		usuario = em.find(Usuario.class, usuario.getId());
		em.remove(usuario);
		em.getTransaction().commit();
		em.close();
	}
	
	
	public static boolean validateRegister(String login, Long id) {
	    EntityManager em = JPAUtil.criarEntityManager();
	 
	    String sqlQuery = "SELECT COUNT(u) FROM Usuario u WHERE u.login = :login";
	    
	    if (id != null) {
	        sqlQuery += " AND u.id = :id"; 
	    }
	   
	    Query query = em.createQuery(sqlQuery);
	    query.setParameter("login", login);
	    
	    if (id != null) {
	        query.setParameter("id", id);
	    }
	    
	    Long count = (Long) query.getSingleResult();
	    em.close();
	    
	    return count > 0;
	}
	
	public static boolean validateLogin(String login, String senha) {
		EntityManager em = JPAUtil.criarEntityManager();
		
		String sqlQuery = "SELECT COUNT(u) FROM Usuario u WHERE u.login = :login AND u.senha = :senha";
		
		Query query = em.createQuery(sqlQuery);		
		query.setParameter("login", login);
		query.setParameter("senha", senha);
		
		Long count = (Long) query.getSingleResult();
		em.close();
		
		return count > 0;
		
	}
	
	
}
