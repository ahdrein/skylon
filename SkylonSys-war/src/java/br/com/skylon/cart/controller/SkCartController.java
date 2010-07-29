
package br.com.skylon.cart.controller;

import br.com.skylon.invoice.facade.SkInvoiceFacade;
import br.com.skylon.invoice.model.SkInvoice;
import br.com.skylon.invoice.model.SkItemInvoice;
import br.com.skylon.login.controller.LoginController;
import br.com.skylon.product.facade.SkProductFacade;
import br.com.skylon.product.model.SkProduct;
import br.com.skylon.util.PageMessages;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.component.UIParameter;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;

public class SkCartController {

    private final String bundle = "br.com.skylon.cart.resource.labels";
    
    @EJB
    private SkProductFacade facade;

    @EJB
    private SkInvoiceFacade facadeInvoice;
    
    private SkProduct product;
    
    private ArrayList<SkProduct> list;
    private List<SkProduct> removeList;
    
    private SkInvoice invoice;
    private List<SkItemInvoice> removeInvoice;
    
    private SkItemInvoice itemInvoice;
    private List<SkItemInvoice> itemInvoiceList;
    
    private BigInteger nQtdeItInvoice;
    
    private boolean showResult;
    
    private String findDescription;
    
    public SkCartController(){
        nQtdeItInvoice = new BigInteger("1");
        itemInvoiceList = new ArrayList<SkItemInvoice>();
    }
  
    public List<SkProduct> getResultList(){
        return facade.findByDesc(findDescription);
    }

    public List<SkProduct> getResultPartList(){
        List<SkProduct> l = facade.findAll();
        List<SkProduct> g = new ArrayList<SkProduct>();
        int contador = 0;
        if(l != null) {

            for(SkProduct c : l){
                contador++;
                try{
                    if((contador % 2) == 0) {
                        g.add(c);
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        return g;
    }

    public List<SkProduct> getResulImpartList(){
        List<SkProduct> l = facade.findAll();
        List<SkProduct> g = new ArrayList<SkProduct>();
        int contador = 0;
        if(l != null) {
            for(SkProduct c : l){
                contador++;
                try{
                    if((contador % 2) == 1) {
                        g.add(c);
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        return g;

    }
    
    public SkProduct getProduct() {
        return product;
    }

    public void setProduct(SkProduct product) {
        this.product = product;
    }
    
    public BigInteger getNQtdeItInvoice() {
        return nQtdeItInvoice;
    }

    public void setNQtdeItInvoice(BigInteger nQtdeItInvoice) {
        this.nQtdeItInvoice = nQtdeItInvoice;
    }    
    
    public List<SkProduct> getAll() {
        return facade.findAll();
    }

    public String find() {
        showResult = true;
        
        return "cart-find";
    }

    public String buy() {
        removeInvoice = new ArrayList<SkItemInvoice>();
        
        if(LoginController.getInstance().getLoggedUser() == null
                || LoginController.getInstance().getLoggedUser().equals("")){
            PageMessages.showWarning(bundle, "cart.message.not_loged");
            return "";
        }
        
        invoice = new SkInvoice();

        itemInvoice = new SkItemInvoice();
        itemInvoice.setIdProduct(product);
        itemInvoice.setNQtdeItInvoice(nQtdeItInvoice);
        itemInvoice.setIdInvoice(invoice);
        itemInvoiceList.clear();
        itemInvoiceList.add(itemInvoice);
                
        invoice.setSkItemInvoiceCollection(itemInvoiceList);
        invoice.setIdPayment(new BigInteger("1"));
        invoice.setIdUser(LoginController.getInstance().getLoggedUser());
        
        if(list == null)
            list = new ArrayList<SkProduct>();
        
        list.add(product);
                
        return "cart-confirm";
    }

    public String remove(ActionEvent anEvent){
        UIParameter component = (UIParameter) anEvent.getComponent().findComponent("cartId");

        invoice = facadeInvoice.findById(component.getValue().toString());

        if(invoice == null) {
            PageMessages.showWarning(bundle, "cart.message.not_selected");
        } else {
            facadeInvoice.removeSkInvoice(invoice);
            PageMessages.showInfo(bundle, "cart.message.delete");
        }
        return "";
    }
    
    public String save() throws Exception {
        
        try{
            if(invoice.getIdInvoice() == null) {
                facadeInvoice.persistEntity(invoice);
            } else {
                facadeInvoice.mergeEntity(removeInvoice, invoice);
            }
        }catch(Exception e){
            
            if(LoginController.getInstance().getLoggedUser().getIdUser() == null
               || LoginController.getInstance().getLoggedUser().equals("")){
                PageMessages.showError("Usuario nao logado verifique !" );
                return "login";
            }else{
                PageMessages.showError("Erro na gravação dos dados: \n" + e.getMessage());
                return "cart-find";
            }
            
        }

        PageMessages.showInfo(bundle, "cart.message.save");
        return "cart-find";
    }

    public String getFindDescription() {
        return findDescription;
    }

    public void setFindDescription(String findDescription) {
        this.findDescription = findDescription;
    }

    public String info(){
         if(product == null) {
            PageMessages.showWarning(bundle, "cart.message.not_selected");
            return "";
        } else {
            return "cart-info";
        }
    }
    
    public void processAction(ActionEvent anEvent) throws AbortProcessingException {

        UIParameter component = (UIParameter) anEvent.getComponent().findComponent("cartId");

        product = facade.findById(component.getValue().toString());
        
        
    }



}
