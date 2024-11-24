package bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import dao.UsuarioDao;
import entity.Usuario;

@ManagedBean
public class UsuarioBean {
    private Usuario user = new Usuario(); 
    private List<Usuario> users = new ArrayList<Usuario>();
    
    
    public String verificarLogin() {
        boolean valido = UsuarioDao.validateLogin(user.getLogin(), user.getSenha());

        if (valido) {
            return "opcoes?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login ou senha inválidos.", null));
            return null; 
        }
    }

	public String salvar() {
    	
    	if(UsuarioDao.validateRegister(user.getLogin(), null)) {
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Usuário com o mesmo login já existe."));
    		return null;
    	}
    	
		UsuarioDao.save(user); 
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Usuário salvo com sucesso"));
        user = new Usuario();    
       
        return null;
        
    }

  
    public void prepararAtualizacao(Usuario user) {
        this.user = user; 
    }

   
    public String atualizar() {
    	
    	if(UsuarioDao.validateRegister(user.getLogin(), user.getId())) {
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Usuário com o mesmo login já existe."));
    		return null;
    	}
    	
		UsuarioDao.update(user); 
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Usuário atualizado com sucesso"));
        users = UsuarioDao.listAll(); 
     
        return null;
    }

   
    public String deletar(Usuario user) {
        UsuarioDao.delete(user); 
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Usuário deletado com sucesso"));
        users = UsuarioDao.listAll();
        
        return null;
    }
    
    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public List<Usuario> getUsers() {
        users = UsuarioDao.listAll(); 
        return users;
    }

    public void setUsers(List<Usuario> users) {
        this.users = users;
    }
    

}
