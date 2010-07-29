package br.com.skylon.user.facade;

import br.com.skylon.bank.model.SkBank;
import br.com.skylon.user.model.SkUser;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class SkUserFacadeBean implements SkUserFacade{
    @PersistenceContext
    private EntityManager em;

    public SkUserFacadeBean() {}

    // Persistencia dos Objetos
    public Object mergeEntity(List<SkUser> removeList, Object entity) {
        if(removeList != null && removeList.size() > 0) {
            for(SkUser cd : removeList) {
                remove(cd);
            }
        }
        return em.merge(entity);
    }

    public Object persistEntity(Object entity) {
        try{
           em.persist(entity);
        }
        catch(Exception e ){
            e.printStackTrace();
            em.refresh(entity);
        }
        return entity;
    }

    public List<SkUser> findAll() {
        return em.createQuery("SELECT s FROM SkUser s").getResultList();
    }
    
  

    public void remove(SkUser skUser) {
        skUser = em.find(SkUser.class, skUser.getIdUser());
        em.remove(skUser);
    }
    
    public List<SkUser> findByEmail(String email){
        String where = "";
        Query q = null;
        
        if(email != null && !email.equals("")){
            where = "Where o.sEmail LIKE :email";
            q = em.createQuery("Select o from SkUser o " + where);
            q.setParameter("email", email + "%");
        }else{
            return findAll();
        }
                
        return q.getResultList();
    }

    public SkUser findUserByLogin(SkUser user) throws Exception {
        Query q = em.createNamedQuery("SkUser.findByLogin");
        q.setParameter("email", user.getSEmail());
        q.setParameter("pass", user.getSPassword());
       
        SkUser result = (SkUser) q.getSingleResult();
        
        System.out.println("Teste login: " + result.getSEmail());
        System.out.println("Teste active: " + result.getSActive());
        
        //FacesContext context = FacesContext.getCurrentInstance();
        //HttpSession session = (HttpSession) context.getExternalContext();
        
        //System.out.println("Teste LastAcessed: " + session.getLastAccessedTime());
        //System.out.println("Teste Atributes: " + session.getAttributeNames());
        
        
        if(result.getSActive() == null || result.getSActive().equals("N"))
            throw new Exception("Active = N");
        
        
        return result;
    }

    public SkUser findById(String id){
        String where = "";
        Query q = null;
        
        if(id != null && !id.equals("")){
            where = "Where o.idUser LIKE :id";
            q = em.createQuery("Select o from SkUser o " + where);
            q.setParameter("id", id);
        }else{
            return null;
        }
                
        return (SkUser) q.getSingleResult();
    }        
}
