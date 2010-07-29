package br.com.skylon.invoice.facade;

import br.com.skylon.invoice.model.SkItemInvoice;
import java.util.List;
import javax.ejb.Local;

@Local
public interface SkItemInvoiceFacade {
    Object mergeEntity(List<SkItemInvoice> removeList, Object entity);
    Object persistEntity(Object entity);

    List<SkItemInvoice> findAll();
    
//    List<SkItemInvoice> findByItemDescription(String description);

    void remove(SkItemInvoice skInvoice);
    
}
