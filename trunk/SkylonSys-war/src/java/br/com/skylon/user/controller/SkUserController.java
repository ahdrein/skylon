package br.com.skylon.user.controller;

import br.com.skylon.bank.facade.SkBankFacade;
import br.com.skylon.bank.model.SkBank;
import br.com.skylon.language.facade.SkLanguageFacade;
import br.com.skylon.language.model.SkLanguage;
import br.com.skylon.user.facade.SkUserFacade;
import br.com.skylon.user.model.SkUser;
import br.com.skylon.util.Crypto;
import br.com.skylon.util.PageMessages;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.component.UIInput;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

public class SkUserController {
    private final String bundle = "br.com.skylon.user.resource.labels";

    @EJB
    private SkUserFacade facade;
    
    @EJB
    private SkLanguageFacade facadeLanguage;
    
    @EJB
    private SkBankFacade facadeBank;

    private SkUser user;
    private List<SkUser> removeList;

    public boolean neditable;
    private boolean showResult;
    
    private String newPass;
    private String confirmationPass;
    
    private String findEmail;
    private boolean admin;

    private String descricaoBank;
    private String descricaoLanguage;
            
    public SkUser getFirstUser() {
        List<SkUser> l = facade.findAll();
        if(l != null && l.size() > 0) {
            return l.get(0);
        }
        return null;
    }

    public void select(ActionEvent e) {
        user = (SkUser)FacesContext.getCurrentInstance().getExternalContext().getContext();
        System.out.println(user.getSEmail());
    }
    
    public SkUser getUser() {
        return user;
    }

    public void setUser(SkUser user) {
        this.user = user;
    }
    
    public String getFindEmail() {
        return findEmail;
    }

    public void setFindEmail(String findEmail) {
        this.findEmail = findEmail;
    }

    public boolean isNeditable() { 
        return neditable; 
    }
    
    public boolean isAdmin() {
        return true;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }    

    public List<SkUser> getAll() {
        return facade.findAll();
    }

    //public List<SelectItem> getAllBank(){
    //    List<SelectItem> lists = new ArrayList<SelectItem>();
    //    List<SkBank> banks = facadeBank.findAll();
    //    for(SkBank c: banks){
    //        SelectItem item = new SelectItem(c.getIdBank(), c.getSDescBank());
    //        lists.add(item);
    //    }
    //    return lists;
    //}

    public List<String> getAllBank(){
        List<String> lists = new LinkedList<String>();
        List<SkBank> banks = facadeBank.findAll();
        for(SkBank c: banks){
            lists.add(c.getSDescBank());
        }
        return lists;
    }
    
    public void getIdBank(){
        user.setIdBank(facadeBank.findByDescBank(descricaoBank).getIdBank().toBigInteger());
    }
            
    public List<String> getAllLanguage(){
        List<String> lists = new LinkedList<String>();
        List<SkLanguage> languages = facadeLanguage.findAll();
        for(SkLanguage c: languages){
            lists.add(c.getSDescription());
        }
        return lists;
    }            

    public void getLanguage(){
        user.setLanguage(facadeLanguage.findByDescLanguage(getDescricaoLanguage()));
    }
    
    public List<SkUser> getResultList() {
        if(showResult)
            return facade.findByEmail(findEmail);

        return null;
    }

    public String find() {
        showResult = true;
        return "user-find";
    }

    public String add() {
        user = new SkUser();

        neditable = false;
        return "user-edit";
    }

    public String edit() {
        //user = (SkUser)FacesContext.getCurrentInstance().getExternalContext().getContext();
        
        removeList = new ArrayList<SkUser>();

        if(user == null) {
            PageMessages.showWarning(bundle, "user.message.not_selected");
            return "";
        } else {
            neditable = false;
            return "user-edit";
        }
    }

    public String view() {
        if(user == null) {
            PageMessages.showWarning(bundle, "user.message.not_selected");
            return "";
        } else {
            neditable = true;
            return "user-edit";
        }
    }

    public String remove(){
        //user = (SkUser)FacesContext.getCurrentInstance().getExternalContext().getContext();
        
        if(user == null) {
            PageMessages.showWarning(bundle, "user.message.not_selected");
        } else {
            facade.remove(user);
            PageMessages.showInfo(bundle, "user.message.delete");
        }
        return "";
    }

    public String save() throws Exception {
        
        if(user.getSPassword() != null && getConfirmationPass() != null) {
            if(! user.getSPassword().equals(confirmationPass)) {
                PageMessages.showError(bundle, "user.incorrect_repeat");
                return "";
            } else {
                try{
                    facade.mergeEntity(removeList, user);
                }catch(EJBException ejbe){
                    PageMessages.showError(ejbe.getStackTrace().toString());
                    return "";
                }
                
                //PageMessages.showInfo(bundle, "user.ok");
            }
        } else {
            PageMessages.showError(bundle, "user.incomplete");
            return "";
        }
        
        if(user.getIdUser() == null) {
            try{facade.persistEntity(user);}
            //catch(Exception e){e.printStackTrace();}
            catch(EJBException ej){ej.printStackTrace();}
        } else {
            facade.mergeEntity(removeList, user);
        }

        PageMessages.showInfo(bundle, "user.message.save");
        return "user-find";
    }
    
    public String updatePassword() {
        if(getNewPass() != null && getConfirmationPass() != null) {
            if(! newPass.equals(confirmationPass)) {
                PageMessages.showError(bundle, "user.incorrect_repeat");
            } else {
                user.setDLastUpdate(new Date());
                user.setDDateUpdateSenha(new Date());
                user.setSPassword(getNewPass());
                facade.mergeEntity(removeList, user);
                
                PageMessages.showInfo(bundle, "user.ok");
            }
        } else {
            PageMessages.showError(bundle, "user.incomplete");
        }
        
        return "user-edit";
    }    

    public void validateEmail(FacesContext context,UIInput toValidate) {
        String email = (String) toValidate.getValue();
        if (email.indexOf('@') == -1) {
                toValidate.setValid(false);
                PageMessages.showInfo(bundle, "user.email_invalido");
        } else {
                toValidate.setValid(true);
        }
    }

    public void processAction(ActionEvent anEvent) throws AbortProcessingException {
        
        UIParameter component = (UIParameter) anEvent.getComponent().findComponent("userId");

        this.user = facade.findById(component.getValue().toString());
   
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getConfirmationPass() {
        return confirmationPass;
    }

    public void setConfirmationPass(String confirmationPass) {
        this.confirmationPass = Crypto.toMD5(confirmationPass);
    }

    public String getDescricaoBank() {
        return descricaoBank;
    }

    public void setDescricaoBank(String descricaoBank) {
        this.descricaoBank = descricaoBank;
    }

    public String getDescricaoLanguage() {
        return descricaoLanguage;
    }

    public void setDescricaoLanguage(String descricaoLanguage) {
        this.descricaoLanguage = descricaoLanguage;
    }


}
