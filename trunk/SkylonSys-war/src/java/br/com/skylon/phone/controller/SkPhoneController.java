package br.com.skylon.phone.controller;

import br.com.skylon.phone.facade.SkPhoneFacade;
import br.com.skylon.phone.model.SkPhone;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

public class SkPhoneController {
    private final String bundle = "br.com.skylon.phone.resource.labels";

    @EJB
    private SkPhoneFacade facade;

    private SkPhone Phone;
    private List<SkPhone> removeList;

    public boolean editable;
    private boolean showResult;

    private String findName;
    private String findDescription;

    public SkPhone getFirstPhone() {
        List<SkPhone> l = facade.findAll();
        if(l != null && l.size() > 0) {
            return l.get(0);
        }
        return null;
    }

    public void actionDeselect(ActionEvent e) {
    }

    public SkPhone getPhone() {
        return Phone;
    }

    public void setPhone(SkPhone Phone) {
        this.Phone = Phone;
    }

    public boolean isEditable() { return editable; }

    public List<SkPhone> getAll() {
        return facade.findAll();
    }

//    public List<SkPhone> getResultList() {
//        if(showResult)
//            return facade.findByNameAndDesc(findName, findDescription);
//
//        return null;
//    }

    public String find() {
        showResult = true;
        return "";
    }

    public String add() {
        Phone = new SkPhone();

        editable = true;
        return "Phone-edit";
    }

    public String edit() {
        //Phone = (SkPhone) table.getSelectedRowData();

        removeList = new ArrayList<SkPhone>();

        if(Phone == null) {
            //PageMessages.showWarning(bundle, "conversion.message.not_selected");
            return "";
        } else {
            editable = true;
            return "Phone-edit";
        }
    }
//
//    public String view() {
//        conversion = (SoaConversionGroup) table.getSelectedRowData();
//        conversionDetail = null;
//        removeList = new ArrayList<SoaConversionDetail>();
//
//        if(conversion == null) {
//            PageMessages.showWarning(bundle, "conversion.message.not_selected");
//            return "";
//        } else {
//            editable = false;
//            return "conversion-edit";
//        }
//    }

    public String remove(ActionEvent e) throws Exception {
        //Phone = (SkPhone) e.getID() .getSelectedRowData();

        if(Phone == null) {
            //PageMessages.showWarning(bundle, "conversion.message.not_selected");
        } else {
            facade.remove(Phone);
            //PageMessages.showInfo(bundle, "conversion.message.delete");
        }
        return "";
    }

    public String save() throws Exception {
        if(Phone.getIdPhone() == null) {
            facade.persistEntity(Phone);
        } else {
            facade.mergeEntity(removeList, Phone);
        }

        //PageMessages.showInfo(bundle, "conversion.message.save");
        return "conversion-find";
    }

}
