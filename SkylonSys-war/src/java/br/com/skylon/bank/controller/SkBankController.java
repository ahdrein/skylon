package br.com.skylon.bank.controller;

import br.com.skylon.bank.facade.SkBankFacade;
import br.com.skylon.bank.model.SkBank;
import br.com.skylon.util.PageMessages;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.component.UIParameter;
import javax.faces.component.html.HtmlCommandLink;
import javax.faces.event.AbortProcessingException;
import javax.faces.model.ListDataModel;

public class SkBankController implements ActionListener{
    private final String bundle = "br.com.skylon.bank.resource.labels";

    @EJB
    private SkBankFacade facade;

    private SkBank bank;
    private List<SkBank> removeList;

    public boolean neditable;
    private boolean showResult;

    private String findDescription;

    public SkBank getFirstBank() {
        List<SkBank> l = facade.findAll();
        if(l != null && l.size() > 0) {
            return l.get(0);
        }
        return null;
    }

    public void actionDeselect(ActionEvent e) {
    }

    public SkBank getBank() {
        return bank;
    }

    public void setBank(SkBank Bank) {
        this.bank = Bank;
    }

    public boolean isNeditable() { 
        return neditable; 
    }

    public List<SkBank> getAll() {
        return facade.findAll();
    }

    public List<SkBank> getResultList() {
        if(showResult)
            return facade.findByDesc(getFindDescription());

        return null;
    }

    public String find() {
        showResult = true;
        return "bank-find";
    }

    public String add() {
        bank = new SkBank();

        neditable = false;
        return "bank-edit";
    }

    public String edit() {
        //Bank = (SkBank) table.getSelectedRowData();

        removeList = new ArrayList<SkBank>();

        if(bank == null) {
            PageMessages.showWarning(bundle, "bank.message.not_selected");
            return "";
        } else {
            neditable = false;
            return "bank-edit";
        }
    }

    public String view() {
        //conversion = (SoaConversionGroup) table.getSelectedRowData();
        //conversionDetail = null;
        removeList = new ArrayList<SkBank>();

        if(bank == null) {
            PageMessages.showWarning(bundle, "bank.message.not_selected");
            return "";
        } else {
            neditable = true;
            return "bank-edit";
        }
    }

    public String remove(ActionEvent e) throws Exception {
        //Bank = (SkBank) e.getID() .getSelectedRowData();

        if(bank == null) {
            PageMessages.showWarning(bundle, "bank.message.not_selected");
        } else {
            facade.remove(bank);
            PageMessages.showInfo(bundle, "bank.message.delete");
        }
        return "";
    }

    public String save() throws Exception {
        try{
            if(bank.getIdBank() == null) {
                facade.persistEntity(bank);
            } else {
                facade.mergeEntity(removeList, bank);
            }
        }catch(Exception e){
            PageMessages.showError("Erro na gravação dos dados: \n" + e.getMessage());
            return "bank-find";
        }

        PageMessages.showInfo(bundle, "bank.message.save");
        return "bank-find";
    }

    public void processAction(ActionEvent anEvent) throws AbortProcessingException {
  
        UIParameter component = (UIParameter) anEvent.getComponent().findComponent("bankId");
        System.out.println("param: " + component.getValue());

        this.bank = facade.findById(component.getValue().toString());
        
    }    
    public String getFindDescription() {
        return findDescription;
    }

    public void setFindDescription(String findDescription) {
        this.findDescription = findDescription;
    }

}
