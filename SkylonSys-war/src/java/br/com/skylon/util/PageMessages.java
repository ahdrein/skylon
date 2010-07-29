package br.com.skylon.util;

import br.com.skylon.login.controller.LoginController;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class PageMessages {
    public PageMessages() { }
    
    public static void showError(String message) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public static void showInfo(String message) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, message, message);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public static void showWarning(String message) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, message, message);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public static void showError(String bundle, String key) {
        ResourceBundle rb = ResourceBundle.getBundle(bundle, LoginController.getInstance().getLoginLanguage().getLocale());
        
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, rb.getString(key), rb.getString(key));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public static void showInfo(String bundle, String key) {
        ResourceBundle rb = ResourceBundle.getBundle(bundle, LoginController.getInstance().getLoginLanguage().getLocale());
        
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, rb.getString(key), rb.getString(key));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public static void showWarning(String bundle, String key) {
        ResourceBundle rb = ResourceBundle.getBundle(bundle, LoginController.getInstance().getLoginLanguage().getLocale());
        
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, rb.getString(key), rb.getString(key));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}