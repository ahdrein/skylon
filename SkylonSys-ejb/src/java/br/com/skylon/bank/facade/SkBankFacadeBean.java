package br.com.skylon.bank.facade;

import br.com.skylon.bank.model.SkBank;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class SkBankFacadeBean implements SkBankFacade{
    @PersistenceContext
    private EntityManager em;

    public SkBankFacadeBean() {}

    // Persistencia dos Objetos
    public Object mergeEntity(List<SkBank> removeList, Object entity) {
        if(removeList != null && removeList.size() > 0) {
            for(SkBank cd : removeList) {
                remove(cd);
            }
        }
        return em.merge(entity);
    }

    //
    public Object persistEntity(Object entity) {
        em.persist(entity);
        return entity;
    }

    public List<SkBank> findAll() {
        return em.createQuery("SELECT s FROM SkBank s").getResultList();
    }
    
    public SkBank findByDescBank(String descBank){
        String where = "";
        Query q = null;
        
        if(descBank != null && !descBank.equals("")){
            where = "Where o.sDescBank LIKE :descBanks";
            q = em.createQuery("Select o from SkBank o " + where);
            q.setParameter("sDescBank", descBank);
        }
                
        return (SkBank) q.getSingleResult();
    }      

    public List<SkBank> findByDesc(String descBank){
        String where = "";
        Query q = null;
        
        if(descBank != null && !descBank.equals("")){
            where = "Where o.sDescBank LIKE :descBanks";
            q = em.createQuery("Select o from SkBank o " + where);
            q.setParameter("sDescBank", descBank + "%");
        }else{
            return findAll();
        }
                
        return q.getResultList();
    }
    
    public SkBank findById(String id){
        String where = "";
        Query q = null;
        
        if(id != null && !id.equals("")){
            where = "Where o.idBank LIKE :id";
            q = em.createQuery("Select o from SkBank o " + where);
            q.setParameter("id", id);
        }else{
            return null;
        }
        return (SkBank) q.getSingleResult();
    }    
    
    public void remove(SkBank skBank) {
        skBank = em.find(SkBank.class, skBank.getIdBank());
        em.remove(skBank);
    }
}
