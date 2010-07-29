
package br.com.skylon.user.facade;

import br.com.skylon.bank.model.SkBank;
import br.com.skylon.user.model.SkUser;
import java.util.List;
import javax.ejb.Local;

@Local
public interface SkUserFacade {
    Object mergeEntity(List<SkUser> removeList, Object entity);
    Object persistEntity(Object entity);

    List<SkUser> findAll();
    
    void remove(SkUser skUser);
    
    public List<SkUser> findByEmail(String email);
    
    public SkUser findUserByLogin(SkUser user) throws Exception;
    
    public SkUser findById(String id);
}
