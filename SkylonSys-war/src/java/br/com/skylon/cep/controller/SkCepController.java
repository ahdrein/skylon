package br.com.skylon.cep.controller;

import br.com.skylon.cep.facade.SkCepFacade;
import br.com.skylon.cep.model.SkCep;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

public class SkCepController {
    private final String bundle = "br.com.skylon.cep.resource.labels";

    @EJB
    private SkCepFacade facade;

    private SkCep Cep;
    private List<SkCep> removeList;

    public boolean editable;
    private boolean showResult;

    private String findName;
    private String findDescription;

    public SkCep getFirstCep() {
        List<SkCep> l = facade.findAll();
        if(l != null && l.size() > 0) {
            return l.get(0);
        }
        return null;
    }

    public void actionDeselect(ActionEvent e) {
    }

    public SkCep getCep() {
        return Cep;
    }

    public void setCep(SkCep Cep) {
        this.Cep = Cep;
    }

    public boolean isEditable() { return editable; }

    public List<SkCep> getAll() {
        return facade.findAll();
    }

//    public List<SkCep> getResultList() {
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
        Cep = new SkCep();

        editable = true;
        return "Cep-edit";
    }

    public String edit() {
        //Cep = (SkCep) table.getSelectedRowData();

        removeList = new ArrayList<SkCep>();

        if(Cep == null) {
            //PageMessages.showWarning(bundle, "conversion.message.not_selected");
            return "";
        } else {
            editable = true;
            return "Cep-edit";
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
        //Cep = (SkCep) e.getID() .getSelectedRowData();

        if(Cep == null) {
            //PageMessages.showWarning(bundle, "conversion.message.not_selected");
        } else {
            facade.remove(Cep);
            //PageMessages.showInfo(bundle, "conversion.message.delete");
        }
        return "";
    }

    public String save() throws Exception {
        if(Cep.getIdCep() == null) {
            facade.persistEntity(Cep);
        } else {
            facade.mergeEntity(removeList, Cep);
        }

        //PageMessages.showInfo(bundle, "conversion.message.save");
        return "conversion-find";
    }

}
