package br.com.skylon.invoice.facade;

import br.com.skylon.invoice.model.SkInvoice;
import br.com.skylon.invoice.model.SkItemInvoice;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class SkInvoiceFacadeBean implements SkInvoiceFacade{
    @PersistenceContext
    private EntityManager em;

    public SkInvoiceFacadeBean() {}

    // Persistencia dos Objetos
    public Object mergeEntity(List<SkItemInvoice> removeList, Object entity) {
        em.clear();
        try{
        if(removeList != null && removeList.size() > 0) {
  
            for(SkItemInvoice cd : removeList) {
                removeSkItemInvoice(cd);
            }
        }
        }catch(Exception e){
            e.printStackTrace();
        }
        return em.merge(entity);
    }

    public Object persistEntity(Object entity) {
        em.clear();
        em.persist(entity);
        return entity;
    }

    public List<SkInvoice> findAll() {
        return em.createQuery("SELECT s FROM SkInvoice s").getResultList();
    }

    public List<SkItemInvoice> findAllItem() {
        return em.createQuery("SELECT s FROM SkItemInvoice s").getResultList();
    }
    
    public void removeSkInvoice(SkInvoice skInvoice) {
        skInvoice = em.find(SkInvoice.class, skInvoice.getIdInvoice());
        em.remove(skInvoice);
    }
    
    public void removeSkItemInvoice(SkItemInvoice skItemInvoice) {
        try {
            skItemInvoice = em.find(SkItemInvoice.class, skItemInvoice.getIdInvoice());
            em.remove(skItemInvoice);
        } catch(Exception e) {}
    }

    public List<SkInvoice> findInvoice(String user, String invoice) {
        String where = "";
        Query q = null;
        
        if(user != null && !user.equals("")){
            where = "Where o.idUser = s.idUser";
            q = em.createQuery("Select o from SkInvoice o " + where);
            q.setParameter("email", user + "%");
        }else{
            q = em.createQuery("Select o from SkInvoice o");
            //q = em.createQuery("Select o, o.idUser.sEmail from SkInvoice o");
            //Select o.sNumInvoice, o.Usuario.sEmail from SkInvoice o Where o = :invoice
            //return findAll();
        }
                
        return q.getResultList();
    }
    
    public SkInvoice findById(String id){
        String where = "";
        Query q = null;
        
        if(id != null && !id.equals("")){
            where = "Where o.idInvoice LIKE :id";
            q = em.createQuery("Select o from SkInvoice o " + where);
            q.setParameter("id", id);
        }else{
            
            return null;
        }
                
        return (SkInvoice) q.getSingleResult();
    }
    
}
