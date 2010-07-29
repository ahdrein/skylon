package br.com.skylon.invoice.facade;

import br.com.skylon.invoice.model.SkInvoice;
import br.com.skylon.invoice.model.SkItemInvoice;
import java.util.List;
import javax.ejb.Local;

@Local
public interface SkInvoiceFacade {
    Object mergeEntity(List<SkItemInvoice> removeList, Object entity);
    
    Object persistEntity(Object entity);

    List<SkInvoice> findAll();
    
    List<SkItemInvoice> findAllItem();
    
    public List<SkInvoice> findInvoice(String user, String invoice);

    void removeSkInvoice(SkInvoice skInvoice);
    
    void removeSkItemInvoice(SkItemInvoice skItemInvoice);
    
    public SkInvoice findById(String id);
}
