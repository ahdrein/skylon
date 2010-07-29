package br.com.skylon.language.facade;

import br.com.skylon.language.model.SkLanguage;

import java.util.List;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class SkLanguageFacadeBean implements SkLanguageFacade {
    @PersistenceContext
    private EntityManager em;

    public SkLanguageFacadeBean() { }

    public Object mergeEntity(Object entity) {
        return em.merge(entity);
    }

    public Object persistEntity(Object entity) {
        em.persist(entity);
        return entity;
    }

    public List<SkLanguage> findAll() {
        //System.out.println("============================BLA");
        //System.out.println("============================" + em);
        //System.out.println("============================" + em.createNamedQuery("SkLanguage.findAll"));
        return em.createNamedQuery("SkLanguage.findAll").getResultList();
    }
    
    public SkLanguage findLanguage(String language, String country) {
        Query q;
        q = em.createQuery("select l from SkLanguage l where l.sLanguage = :language AND l.sCountry = :country");
        q.setParameter("language", language);
        q.setParameter("country", country);
            
        return (SkLanguage) q.getSingleResult();
    }

    public SkLanguage findByDescLanguage(String descLanguage){
        String where = "";
        Query q = null;
        
        if(descLanguage != null && !descLanguage.equals("")){
            where = "Where o.sDescription LIKE :descLanguage";
            q = em.createQuery("Select o from SkLanguage o " + where);
            q.setParameter("sDescription", descLanguage);
        }
                
        return (SkLanguage) q.getSingleResult();
    }
    
    public void remove(SkLanguage skLanguage) {
        skLanguage = em.find(SkLanguage.class, skLanguage.getIdLanguage());
        em.remove(skLanguage);
    }

}
