package br.com.skylon.product.facade;

import br.com.skylon.product.model.SkProduct;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class SkProductFacadeBean implements SkProductFacade{
    @PersistenceContext
    private EntityManager em;

    public SkProductFacadeBean() {}

    // Persistencia dos Objetos
    public Object mergeEntity(List<SkProduct> removeList, Object entity) {
        if(removeList != null && removeList.size() > 0) {
            for(SkProduct cd : removeList) {
                remove(cd);
            }
        }
        return em.merge(entity);
    }

    public Object persistEntity(Object entity) {
        em.persist(entity);
        return entity;
    }

    public List<SkProduct> findAll() {
        return em.createQuery("SELECT s FROM SkProduct s").getResultList();
    }

    public void remove(SkProduct skProduct) {
        skProduct = em.find(SkProduct.class, skProduct.getIdProduct());
        em.remove(skProduct);
    }
    
    public List<SkProduct> findByDesc(String description){
        String where = "";
        Query q = null;
        
        if(description != null && !description.equals("")){
            where = "Where o.sDescCurtaProd LIKE :description";
            q = em.createQuery("Select o from SkProduct o " + where);
            q.setParameter("description", description + "%");
        }else{
            return findAll();
        }
                
        return q.getResultList();
    }
    
    public SkProduct findById(String id){
        String where = "";
        Query q = null;
        
        if(id != null && !id.equals("")){
            where = "Where o.idProduct LIKE :id";
            q = em.createQuery("Select o from SkProduct o " + where);
            q.setParameter("id", id);
        }else{
            return null;
        }
                
        return (SkProduct) q.getSingleResult();
    }        
}
