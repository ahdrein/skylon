package br.com.skylon.payment.facade;

import br.com.skylon.payment.model.SkPayment;
import java.util.List;
import javax.ejb.Local;

@Local
public interface SkPaymentFacade {
    Object mergeEntity(List<SkPayment> removeList, Object entity);
    Object persistEntity(Object entity);

    List<SkPayment> findAll();
    
    void remove(SkPayment skPayment);
    
    List<SkPayment> findByDesc(String description);
}
