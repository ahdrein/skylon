package br.com.skylon.invoice.controller;

import br.com.skylon.invoice.facade.SkItemInvoiceFacade;
import br.com.skylon.invoice.model.SkItemInvoice;
import br.com.skylon.util.PageMessages;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

public class SkItemInvoiceController {
    private final String bundle = "br.com.skylon.invoice.resource.labels";

    @EJB
    private SkItemInvoiceFacade facade;

    private SkItemInvoice itemInvoice;
    private List<SkItemInvoice> removeList;

    public boolean editable;
    private boolean showResult;

    private String findItemDescription;

    public SkItemInvoice getItemInvoice() {
        return itemInvoice;
    }

    public void setItemInvoice(SkItemInvoice ItemInvoice) {
        this.itemInvoice = ItemInvoice;
    }

    public boolean isEditable() { return editable; }

    public List<SkItemInvoice> getAll() {
        return facade.findAll();
    }

    public List<SkItemInvoice> getResultList() {
//        if(showResult)
//            return facade.findByItemDesc(findItemDescription);

        return null;
    }

    public String find() {
        showResult = true;
        
        return "invoice-find";
    }

    public String add() {
        itemInvoice = new SkItemInvoice();

        editable = true;
        return "invoice-edit";
    }

    public String edit() {
        //ItemInvoice = (SkItemInvoice) table.getSelectedRowData();

        removeList = new ArrayList<SkItemInvoice>();

        if(itemInvoice == null) {
            PageMessages.showWarning(bundle, "conversion.message.not_selected");
            return "";
        } else {
            editable = true;
            return "ItemInvoice-edit";
        }
    }

    public String view() {
//        conversion = (SoaConversionGroup) table.getSelectedRowData();
//        conversionDetail = null;
        removeList = new ArrayList<SkItemInvoice>();

        if(itemInvoice == null) {
            PageMessages.showWarning(bundle, "invoice.message.not_selected");
            return "";
        } else {
            editable = false;
            return "invoice-edit";
        }
        
    }

    public String remove(ActionEvent e) throws Exception {
        //ItemInvoice = (SkItemInvoice) e.getID() .getSelectedRowData();

        if(itemInvoice == null) {
            PageMessages.showWarning(bundle, "invoice.message.not_selected");
        } else {
            facade.remove(itemInvoice);
            PageMessages.showInfo(bundle, "invoice.message.delete");
        }
        return "";
    }

    public String save() throws Exception {
        if(itemInvoice.getIdItInvoice() == null) {
            facade.persistEntity(itemInvoice);
        } else {
            facade.mergeEntity(removeList, itemInvoice);
        }

        //PageMessages.showInfo(bundle, "conversion.message.save");
        return "invoice-find";
    }

}
