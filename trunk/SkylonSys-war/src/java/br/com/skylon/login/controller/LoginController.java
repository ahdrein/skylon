package br.com.skylon.login.controller;

import br.com.skylon.language.facade.SkLanguageFacade;
import br.com.skylon.language.model.SkLanguage;
import br.com.skylon.user.facade.SkUserFacade;
import br.com.skylon.user.model.SkUser;
import br.com.skylon.util.PageMessages;
import java.util.Locale;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class LoginController {

    private final String bundle = "br.com.skylon.login.resource.labels";

    @EJB
    private SkUserFacade facade;
    
    @EJB
    private SkLanguageFacade skLanguageFacade;
    
    private static LoginController loginController;
    
    private SkUser loggedUser;
    private String slogin = "Login";

    public LoginController() { 
        loggedUser = new SkUser();
        if(LoginController.loginController == null){
            LoginController.loginController = this;
        }
    }
    
    public static LoginController getInstance(){
        return LoginController.loginController;
    }
    
    public SkUser getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(SkUser loggedUser) {
        this.loggedUser = loggedUser;
    }
    
    public Locale getLoginLocale() {
        if(loggedUser != null && loggedUser.getLanguage() != null) {
            return loggedUser.getLanguage().getLocale();
        }
        
        return Locale.getDefault();
    }
    public SkLanguage getLoginLanguage() {
        LoginController controller = (LoginController)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{login}").getValue(FacesContext.getCurrentInstance());
        
        if(controller != null && controller.getLoggedUser() != null && controller.getLoggedUser().getLanguage() != null) {
            return controller.getLoggedUser().getLanguage();
        }
        
        //@EJB
        //SkLanguageFacade skLanguageFacade = (SkLanguageFacade) ServiceLocator.getInstance().lookup(SkLanguageFacade.JNDI_NAME);
        //SkLanguageFacade skLanguageFacade = new SkLanguageFacadeBean();
        return skLanguageFacade.findLanguage(Locale.getDefault().getLanguage(), Locale.getDefault().getCountry());
    }
    
    public String login() {
        try {
            if(slogin.equals("Login")){
                loggedUser = facade.findUserByLogin(loggedUser);

    //            if(loggedUser.getDefaultMenu() != null && loggedUser.getDefaultMenu().getSAction() != null) {
    //                //MenuController controller = (MenuController)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{menu}").getValue(FacesContext.getCurrentInstance());
    //                
    //                if(loggedUser.getDefaultMenu().getMenuParent() == null) {
    //                    controller.setSelected(loggedUser.getDefaultMenu());
    //                    controller.setChildrenSelected(null);
    //                } else {
    //                    controller.setSelected(loggedUser.getDefaultMenu().getMenuParent());
    //                    controller.setChildrenSelected(loggedUser.getDefaultMenu());
    //                }
    //                
    //                return loggedUser.getDefaultMenu().getSAction();
    //            }
                slogin = "Logout";
                PageMessages.showInfo("br.com.skylon.login.resource.labels", "login.successful");    
            }else{
                slogin = "Login";
                return this.logout();
            }
            return "home";
        } catch(Exception e) {
            loggedUser = new SkUser();
            
            PageMessages.showError("br.com.skylon.login.resource.labels", "login.invalid");
            return "";
        }
    }
    
    public String logout() {
        
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        session.invalidate();
        
        return "login";
    }

    public String getSlogin() {
        return slogin;
    }

    public void setSlogin(String slogin) {
        this.slogin = slogin;
    }
    
    
}
