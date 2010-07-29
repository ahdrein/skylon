package br.com.skylon.phone.facade;

import br.com.skylon.phone.model.SkPhone;
import java.util.List;
import javax.ejb.Local;

@Local
public interface SkPhoneFacade {
    Object mergeEntity(List<SkPhone> removeList, Object entity);
    Object persistEntity(Object entity);

    List<SkPhone> findAll();
    //List<SoaConversionGroup> findByNameAndDesc(String name, String description);

    void remove(SkPhone skPhone);
}
