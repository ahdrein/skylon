package br.com.skylon.receiving.facade;

import br.com.skylon.receiving.model.SkReceiving;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class SkReceivingFacadeBean implements SkReceivingFacade{
    @PersistenceContext
    private EntityManager em;

    public SkReceivingFacadeBean() {}

    // Persistencia dos Objetos
    public Object mergeEntity(List<SkReceiving> removeList, Object entity) {
        if(removeList != null && removeList.size() > 0) {
            for(SkReceiving cd : removeList) {
                remove(cd);
            }
        }
        return em.merge(entity);
    }

    public Object persistEntity(Object entity) {
        em.persist(entity);
        return entity;
    }

    public List<SkReceiving> findAll() {
        return em.createQuery("SELECT s FROM SkReceiving s").getResultList();
    }

    public void remove(SkReceiving skReceiving) {
        skReceiving = em.find(SkReceiving.class, skReceiving.getIdReceiving());
        em.remove(skReceiving);
    }
    
    public List<SkReceiving> findBI(String id){
        String where = "";
        Query q = null;
        
        if(id != null && !id.equals("")){
            where = "Where o.idReceiving LIKE :id";
            q = em.createQuery("Select o from SkReceiving o " + where);
            q.setParameter("id", id);
        }else{
            return findAll();
        }
                
        return q.getResultList();
    }
    
    public SkReceiving findById(String id){
        String where = "";
        Query q = null;
        
        if(id != null && !id.equals("")){
            where = "Where o.idCategory LIKE :id";
            q = em.createQuery("Select o from SkReceiving o " + where);
            q.setParameter("id", id);
        }else{
            return null;
        }
                
        return (SkReceiving) q.getSingleResult();
    }        
}
