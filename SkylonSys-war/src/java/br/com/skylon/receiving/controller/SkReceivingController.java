package br.com.skylon.receiving.controller;

import br.com.skylon.receiving.model.SkReceiving;
import br.com.skylon.receiving.facade.SkReceivingFacade;
import br.com.skylon.util.PageMessages;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.component.UIParameter;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;

public class SkReceivingController {
    private final String bundle = "br.com.skylon.receiving.resource.labels";

    @EJB
    private SkReceivingFacade facade;

    private SkReceiving receiving;
    private List<SkReceiving> removeList;

    public boolean editable;
    private boolean showResult;

    private String findIdReceiving;

    public SkReceiving getFirstReceiving() {
        List<SkReceiving> l = facade.findAll();
        if(l != null && l.size() > 0) {
            return l.get(0);
        }
        return null;
    }

    public void actionDeselect(ActionEvent e) {
    }

    public SkReceiving getReceiving() {
        return receiving;
    }

    public void setReceiving(SkReceiving Receiving) {
        this.receiving = Receiving;
    }

    public boolean isEditable() { return editable; }

    public List<SkReceiving> getAll() {
        return facade.findAll();
    }

    public List<SkReceiving> getResultList() {
        if(showResult)
            if(findIdReceiving != null && !findIdReceiving.equals(""))
                return (List<SkReceiving>)facade.findById(findIdReceiving);
            else
                return facade.findAll();

        return null;
    }

    public String find() {
        showResult = true;
        return "";
    }

    public String add() {
        receiving = new SkReceiving();

        editable = true;
        return "receiving-edit";
    }

    public String edit() {
        removeList = new ArrayList<SkReceiving>();

        if(receiving == null) {
            PageMessages.showWarning(bundle, "receiving.message.not_selected");
            return "";
        } else {
            editable = true;
            return "receiving-edit";
        }
    }

    public String view() {
        //conversion = (SoaConversionGroup) table.getSelectedRowData();
        //conversionDetail = null;
        //removeList = new ArrayList<SoaConversionDetail>();

        if(receiving == null) {
            PageMessages.showWarning(bundle, "receiving.message.not_selected");
            return "";
        } else {
            editable = false;
            return "receiving-edit";
        }
    }

    public String remove() throws Exception {
        if(receiving == null) {
            PageMessages.showWarning(bundle, "receiving.message.not_selected");
        } else {
            facade.remove(receiving);
            PageMessages.showInfo(bundle, "receiving.message.delete");
        }
        return "";
    }

    public String save() throws Exception {
        if(receiving.getIdReceiving() == null) {
            facade.persistEntity(receiving);
        } else {
            facade.mergeEntity(removeList, receiving);
        }

        PageMessages.showInfo(bundle, "receiving.message.save");
        return "receiving-find";
    }

    public String getFindIdReceiving() {
        return findIdReceiving;
    }

    public void setFindIdReceiving(String findIdReceiving) {
        this.findIdReceiving = findIdReceiving;
    }
    public void processAction(ActionEvent anEvent) throws AbortProcessingException {

        UIParameter component = (UIParameter) anEvent.getComponent().findComponent("itemId");
        System.out.println("param: " + component.getValue());

        this.receiving = facade.findById(component.getValue().toString());
        
    }
}
