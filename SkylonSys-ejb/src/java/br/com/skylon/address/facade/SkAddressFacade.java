package br.com.skylon.address.facade;

import br.com.skylon.address.model.SkAddress;
import java.util.List;
import javax.ejb.Local;

@Local
public interface SkAddressFacade {

    Object mergeEntity(List<SkAddress> removeList, Object entity);
    Object persistEntity(Object entity);

    List<SkAddress> findAll();
    //List<SoaConversionGroup> findByNameAndDesc(String name, String description);

    void remove(SkAddress skAddress);
}
