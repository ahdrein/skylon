package br.com.skylon.payment.controller;

import br.com.skylon.payment.facade.SkPaymentFacade;
import br.com.skylon.payment.model.SkPayment;
import br.com.skylon.util.PageMessages;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

public class SkPaymentController {
    private final String bundle = "br.com.skylon.payment.resource.labels";

    @EJB
    private SkPaymentFacade facade;

    private SkPayment payment;
    private List<SkPayment> removeList;

    public boolean editable;
    private boolean showResult;

    private String findDescPayment;

    public SkPayment getFirstPayment() {
        List<SkPayment> l = facade.findAll();
        if(l != null && l.size() > 0) {
            return l.get(0);
        }
        return null;
    }

    public void actionDeselect(ActionEvent e) {
    }

    public SkPayment getPayment() {
        return payment;
    }

    public void setPayment(SkPayment payment) {
        this.payment = payment;
    }

    public boolean isEditable() { return editable; }

    public List<SkPayment> getAll() {
        return facade.findAll();
    }

    public List<SkPayment> getResultList() {
        if(showResult)
            return facade.findByDesc(getFindDescPayment());

        return null;
    }
    
    public String find() {
        showResult = true;
        return "";
    }

    public String add() {
        payment = new SkPayment();

        editable = true;
        return "payment-edit";
    }

    public String edit() {
        //payment = (SkPayment) table.getSelectedRowData();

        removeList = new ArrayList<SkPayment>();

        if(payment == null) {
            PageMessages.showWarning(bundle, "payment.message.not_selected");
            return "";
        } else {
            editable = true;
            return "payment-edit";
        }
    }

    public String view() {
        //conversion = (SoaConversionGroup) table.getSelectedRowData();
        //conversionDetail = null;
        //removeList = new ArrayList<SoaConversionDetail>();

        if(payment == null) {
            PageMessages.showWarning(bundle, "payment.message.not_selected");
            return "";
        } else {
            editable = false;
            return "payment-edit";
        }
    }

    public String remove(ActionEvent e) throws Exception {
        //payment = (SkPayment) e.getID() .getSelectedRowData();

        if(payment == null) {
            PageMessages.showWarning(bundle, "payment.message.not_selected");
        } else {
            facade.remove(payment);
            PageMessages.showInfo(bundle, "payment.message.delete");
        }
        return "";
    }

    public String save() throws Exception {
        if(payment.getIdPayment() == null) {
            facade.persistEntity(payment);
        } else {
            facade.mergeEntity(removeList, payment);
        }

        PageMessages.showInfo(bundle, "payment.message.save");
        return "payment-find";
    }

    public String getFindDescPayment() {
        return findDescPayment;
    }

    public void setFindDescPayment(String findDescPayment) {
        this.findDescPayment = findDescPayment;
    }

}
