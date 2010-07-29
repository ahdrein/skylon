package br.com.skylon.category.facade;

import br.com.skylon.category.model.SkCategory;
import java.util.List;
import javax.ejb.Local;

@Local
public interface SkCategoryFacade {
    Object mergeEntity(List<SkCategory> removeList, Object entity);
    Object persistEntity(Object entity);

    List<SkCategory> findAll();

    void remove(SkCategory skCategory);
    
    public List<SkCategory> findByDesc(String description);
    
    public SkCategory findById(String id);
}
