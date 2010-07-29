package br.com.skylon.category.facade;

import br.com.skylon.category.model.SkCategory;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class SkCategoryFacadeBean implements SkCategoryFacade{
    @PersistenceContext
    private EntityManager em;
    
    public SkCategoryFacadeBean() {}

    // Persistencia dos Objetos
    public Object mergeEntity(List<SkCategory> removeList, Object entity) {
        if(removeList != null && removeList.size() > 0) {
            for(SkCategory cd : removeList) {
                remove(cd);
            }
        }
        return em.merge(entity);
    }

    public Object persistEntity(Object entity) {
        em.persist(entity);
        return entity;
    }

    public List<SkCategory> findAll() {
        return em.createQuery("SELECT s FROM SkCategory s").getResultList();
    }

    public void remove(SkCategory skCategory) {
        skCategory = em.find(SkCategory.class, skCategory.getIdCategory());
        em.remove(skCategory);
    }
    
    public List<SkCategory> findByDesc(String description){
        String where = "";
        Query q = null;
        
        if(description != null && !description.equals("")){
            where = "Where o.sDescCategory LIKE :description";
            q = em.createQuery("Select o from SkCategory o " + where);
            q.setParameter("description", description + "%");
        }else{
            return findAll();
        }
                
        return q.getResultList();
    }
    
    public SkCategory findById(String id){
        String where = "";
        Query q = null;
        
        if(id != null && !id.equals("")){
            where = "Where o.idCategory LIKE :id";
            q = em.createQuery("Select o from SkCategory o " + where);
            q.setParameter("id", id);
        }else{
            return null;
        }
                
        return (SkCategory) q.getSingleResult();
    }    
    
}
