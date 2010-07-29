package br.com.skylon.cep.facade;

import br.com.skylon.cep.model.SkCep;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class SkCepFacadeBean implements SkCepFacade{
    @PersistenceContext
    private EntityManager em;

    public SkCepFacadeBean() {}

    // Persistencia dos Objetos
    public Object mergeEntity(List<SkCep> removeList, Object entity) {
        if(removeList != null && removeList.size() > 0) {
            for(SkCep cd : removeList) {
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

    public List<SkCep> findAll() {
        return em.createQuery("SELECT s FROM SkCep s").getResultList();
    }

    public void remove(SkCep skCep) {
        skCep = em.find(SkCep.class, skCep.getIdCep());
        em.remove(skCep);
    }
}
