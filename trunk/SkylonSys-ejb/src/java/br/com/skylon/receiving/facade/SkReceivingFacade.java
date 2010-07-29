package br.com.skylon.receiving.facade;

import br.com.skylon.receiving.model.SkReceiving;
import java.util.List;
import javax.ejb.Local;

@Local
public interface SkReceivingFacade {
    Object mergeEntity(List<SkReceiving> removeList, Object entity);
    Object persistEntity(Object entity);

    List<SkReceiving> findAll();

    void remove(SkReceiving sskReceiving);
    
    public SkReceiving findById(String id);
}
