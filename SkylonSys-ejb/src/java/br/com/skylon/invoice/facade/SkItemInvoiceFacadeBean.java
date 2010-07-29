package br.com.skylon.invoice.facade;

import br.com.skylon.invoice.model.SkItemInvoice;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class SkItemInvoiceFacadeBean implements SkItemInvoiceFacade{
    @PersistenceContext
    private EntityManager em;

    public SkItemInvoiceFacadeBean() {}

    // Merge dos Objetos
    public Object mergeEntity(List<SkItemInvoice> removeList, Object entity) {
        em.clear();
        //if(removeList != null && removeList.size() > 0) {
        //    for(SkItemInvoice cd : removeList) {
        //        remove(cd);
        //    }
        //}
        return em.merge(entity);
    }

    // Persistencia dos Objetos
    public Object persistEntity(Object entity) {
        em.clear();
        em.persist(entity);
        return entity;
    }

    public List<SkItemInvoice> findAll() {
        return em.createQuery("SELECT s FROM SkItemInvoice s").getResultList();
    }

    public void remove(SkItemInvoice skItemInvoice) {
        skItemInvoice = em.find(SkItemInvoice.class, skItemInvoice.getIdItInvoice());
        em.remove(skItemInvoice);
    }
//    
//    public List<SkItemInvoice> findByItemDescription(String description){
//        String where = "";
//        Query q = null;
//        
//        if(description != null && !description.equals("")){
//            where = "Where o.sEmail LIKE :email";
//            q = em.createQuery("Select o from SkItemInvoice o " + where);
//            q.setParameter("email", description + "%");
//        }else{
//            return findAll();
//        }
//                
//        return q.getResultList();
//    }    
}
