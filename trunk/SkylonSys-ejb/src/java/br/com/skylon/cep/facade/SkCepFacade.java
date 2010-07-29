package br.com.skylon.cep.facade;

import br.com.skylon.cep.model.SkCep;
import java.util.List;
import javax.ejb.Local;

@Local
public interface SkCepFacade {
    Object mergeEntity(List<SkCep> removeList, Object entity);
    Object persistEntity(Object entity);

    List<SkCep> findAll();
    //List<SoaConversionGroup> findByNameAndDesc(String name, String description);

    void remove(SkCep skCep);
}
