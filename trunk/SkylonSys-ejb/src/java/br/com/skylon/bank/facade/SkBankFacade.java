package br.com.skylon.bank.facade;

import br.com.skylon.bank.model.SkBank;
import java.util.List;
import javax.ejb.Local;

@Local
public interface SkBankFacade {
    Object mergeEntity(List<SkBank> removeList, Object entity);
    Object persistEntity(Object entity);

    List<SkBank> findAll();
    
    public SkBank findByDescBank(String descBank);
    
    public List<SkBank> findByDesc(String description);
    
    public SkBank findById(String id);

    void remove(SkBank skBank);
    
}
