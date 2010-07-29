package br.com.skylon.invoice.controller;

import br.com.skylon.invoice.facade.SkInvoiceFacade;
import br.com.skylon.invoice.model.SkInvoice;
import br.com.skylon.invoice.model.SkItemInvoice;
import br.com.skylon.product.facade.SkProductFacade;
import br.com.skylon.user.facade.SkUserFacade;
import br.com.skylon.util.PageMessages;
import javax.faces.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.component.UIParameter;
import javax.faces.event.AbortProcessingException;

public class SkInvoiceController {
    private final String bundle = "br.com.skylon.invoice.resource.labels";

    @EJB
    private SkInvoiceFacade facade;

    @EJB
    private SkUserFacade facadeUser;
    
    @EJB
    private SkProductFacade facadeProduct;    
    
    private SkInvoice invoice;
    private List<SkItemInvoice> removeList;

    public boolean neditable;
    private boolean showResult;

    private String findUser;
    private String findInvoice;

    public SkInvoice getFirstInvoice() {
        List<SkInvoice> l = facade.findAll();
        if(l != null && l.size() > 0) {
            return l.get(0);
        }
        return null;
    }

    public SkInvoice getInvoice() {
        return invoice;
    }

    public void setInvoice(SkInvoice Invoice) {
        this.invoice = Invoice;
    }

    public boolean isNeditable() { 
        return neditable; 
    }

    public List<SkInvoice> getAll() {
        return facade.findAll();
    }

    public List<SkInvoice> getResultList() {
        if(showResult)
            return facade.findInvoice(findUser,findInvoice);
        
        return null;
    }
    
    public String find() {
        showResult = true;
        return "invoice-find";
    }

    public String add() {
        invoice = new SkInvoice();

        neditable = true;
        return "invoice-edit";
    }

    public String edit() {
        //Invoice = (SkInvoice) table.getSelectedRowData();

        removeList = new ArrayList<SkItemInvoice>();

        if(invoice == null) {
            PageMessages.showWarning(bundle, "conversion.message.not_selected");
            return "";
        } else {
            neditable = false;
            return "invoice-edit";
        }
    }

    public String view() {
//        conversion = (SoaConversionGroup) table.getSelectedRowData();
//        conversionDetail = null;
        removeList = new ArrayList<SkItemInvoice>();

        if(invoice == null) {
            PageMessages.showWarning(bundle, "conversion.message.not_selected");
            return "";
        } else {
            neditable = false;
            return "invoice-edit";
        }
    }

    public String remove(ActionEvent e) throws Exception {
        //Invoice = (SkInvoice) e.getID() .getSelectedRowData();

        if(invoice == null) {
            PageMessages.showWarning(bundle, "conversion.message.not_selected");
        } else {
            facade.removeSkInvoice(invoice);
            PageMessages.showInfo(bundle, "conversion.message.delete");
        }
        return "";
    }

    public String save() throws Exception {
        if(invoice.getIdInvoice() == null) {
            facade.persistEntity(invoice);
        } else {
            facade.mergeEntity(removeList, invoice);
        }

        PageMessages.showInfo(bundle, "conversion.message.save");
        return "invoice-find";
    }

    public void processAction(ActionEvent anEvent) throws AbortProcessingException {
        
        UIParameter component = (UIParameter) anEvent.getComponent().findComponent("invoiceId");

        invoice = facade.findById(component.getValue().toString());
   
    }

    public String getFindInvoice() {
        return findInvoice;
    }

    public void setFindInvoice(String findInvoice) {
        this.findInvoice = findInvoice;
    }

    public String getFindUser() {
        return findUser;
    }

    public void setFindUser(String findUser) {
        this.findUser = findUser;
    }

}
