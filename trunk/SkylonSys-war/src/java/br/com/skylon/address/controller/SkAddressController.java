package br.com.skylon.address.controller;

import br.com.skylon.address.facade.SkAddressFacade;
import br.com.skylon.address.model.SkAddress;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

public class SkAddressController {
    private final String bundle = "br.com.skylon.address.resource.labels";

    @EJB
    private SkAddressFacade facade;

    private SkAddress Address;
    private List<SkAddress> removeList;

    public boolean editable;
    private boolean showResult;

    private String findName;
    private String findDescription;

    public SkAddress getFirstAddress() {
        List<SkAddress> l = facade.findAll();
        if(l != null && l.size() > 0) {
            return l.get(0);
        }
        return null;
    }

    public void actionDeselect(ActionEvent e) {
    }

    public SkAddress getAddress() {
        return Address;
    }

    public void setAddress(SkAddress Address) {
        this.Address = Address;
    }

    public boolean isEditable() { return editable; }

    public List<SkAddress> getAll() {
        return facade.findAll();
    }

//    public List<SkAddress> getResultList() {
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
        Address = new SkAddress();

        editable = true;
        return "Address-edit";
    }

    public String edit() {
        //Address = (SkAddress) table.getSelectedRowData();

        removeList = new ArrayList<SkAddress>();

        if(Address == null) {
            //PageMessages.showWarning(bundle, "conversion.message.not_selected");
            return "";
        } else {
            editable = true;
            return "Address-edit";
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
        //Address = (SkAddress) e.getID() .getSelectedRowData();

        if(Address == null) {
            //PageMessages.showWarning(bundle, "conversion.message.not_selected");
        } else {
            facade.remove(Address);
            //PageMessages.showInfo(bundle, "conversion.message.delete");
        }
        return "";
    }

    public String save() throws Exception {
        if(Address.getIdAddress() == null) {
            facade.persistEntity(Address);
        } else {
            facade.mergeEntity(removeList, Address);
        }

        //PageMessages.showInfo(bundle, "conversion.message.save");
        return "conversion-find";
    }

}
