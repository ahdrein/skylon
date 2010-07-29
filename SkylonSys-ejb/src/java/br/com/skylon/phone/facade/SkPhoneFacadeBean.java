package br.com.skylon.phone.facade;

import br.com.skylon.phone.model.SkPhone;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class SkPhoneFacadeBean implements SkPhoneFacade{
    @PersistenceContext
    private EntityManager em;

    public SkPhoneFacadeBean() {}

    // Persistencia dos Objetos
    public Object mergeEntity(List<SkPhone> removeList, Object entity) {
        if(removeList != null && removeList.size() > 0) {
            for(SkPhone cd : removeList) {
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

    public List<SkPhone> findAll() {
        return em.createQuery("SELECT s FROM SkPhone s").getResultList();
    }

    public void remove(SkPhone skPhone) {
        skPhone = em.find(SkPhone.class, skPhone.getIdPhone());
        em.remove(skPhone);
    }
}
