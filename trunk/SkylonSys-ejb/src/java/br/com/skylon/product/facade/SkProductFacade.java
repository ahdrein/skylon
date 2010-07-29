package br.com.skylon.product.facade;

import br.com.skylon.product.model.SkProduct;
import java.util.List;
import javax.ejb.Local;

@Local
public interface SkProductFacade {

    Object mergeEntity(List<SkProduct> removeList, Object entity);
    Object persistEntity(Object entity);

    List<SkProduct> findAll();
    
    void remove(SkProduct skProduct);
    
    List<SkProduct> findByDesc(String description);
    
    public SkProduct findById(String id);
}
