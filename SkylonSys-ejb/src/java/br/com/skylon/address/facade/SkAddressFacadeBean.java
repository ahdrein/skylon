package br.com.skylon.address.facade;

import br.com.skylon.address.model.SkAddress;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class SkAddressFacadeBean implements SkAddressFacade{
    @PersistenceContext
    private EntityManager em;

    public SkAddressFacadeBean() {}

    // Persistencia dos Objetos
    public Object mergeEntity(List<SkAddress> removeList, Object entity) {
        if(removeList != null && removeList.size() > 0) {
            for(SkAddress cd : removeList) {
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

    public List<SkAddress> findAll() {
        return em.createQuery("SELECT s FROM SkAddress s").getResultList();
    }

    public void remove(SkAddress skAddress) {
        skAddress = em.find(SkAddress.class, skAddress.getIdAddress());
        em.remove(skAddress);
    }
}
