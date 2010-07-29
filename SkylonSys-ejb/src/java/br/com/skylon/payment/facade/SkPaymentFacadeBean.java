package br.com.skylon.payment.facade;

import br.com.skylon.payment.model.SkPayment;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class SkPaymentFacadeBean implements SkPaymentFacade{
    @PersistenceContext
    private EntityManager em;

    public SkPaymentFacadeBean() {}

    // Persistencia dos Objetos
    public Object mergeEntity(List<SkPayment> removeList, Object entity) {
        if(removeList != null && removeList.size() > 0) {
            for(SkPayment cd : removeList) {
                remove(cd);
            }
        }
        return em.merge(entity);
    }

    public Object persistEntity(Object entity) {
        em.persist(entity);
        return entity;
    }

    public List<SkPayment> findAll() {
        return em.createQuery("SELECT s FROM SkPayment s").getResultList();
    }

    public void remove(SkPayment skPayment) {
        skPayment = em.find(SkPayment.class, skPayment.getIdPayment());
        em.remove(skPayment);
    }
    
    public List<SkPayment> findByDesc(String description){
        String where = "";
        Query q = null;
        
        if(description != null && !description.equals("")){
            where = "Where o.sDescPayment LIKE :description";
            q = em.createQuery("Select o from SkCategory o " + where);
            q.setParameter("description", description + "%");
        }else{
            return findAll();
        }
                
        return q.getResultList();
    }
}
