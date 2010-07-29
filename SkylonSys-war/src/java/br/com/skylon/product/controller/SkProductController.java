package br.com.skylon.product.controller;

import br.com.skylon.product.facade.SkProductFacade;
import br.com.skylon.product.model.SkProduct;
import br.com.skylon.util.PageMessages;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

public class SkProductController implements ActionListener{
    private final String bundle = "br.com.skylon.product.resource.labels";
    
    @EJB
    private SkProductFacade facade;

    private SkProduct product;
    private List<SkProduct> removeList;
    
    public boolean neditable;
    private boolean showResult;
    private boolean showResult2;
    
    private String findDescription;
    private String findDescription2;
    private boolean select;
  
    public SkProduct getFirstProduct() {
        List<SkProduct> l = facade.findAll();
        if(l != null && l.size() > 0) {
            return l.get(0);
        }
        return null;
    }
    
    public List<SkProduct> getResultList(){
        if(showResult)
            return facade.findByDesc(findDescription);
        
        if(showResult2)
            return facade.findByDesc(findDescription2);
        
        return null;
    }

    public SkProduct getProduct() {
        return product;
    }

    public void setProduct(SkProduct product) {
        this.product = product;
    }
    
    public boolean isNeditable() { 
        return neditable; 
    }

    public List<SkProduct> getAll() {
        return facade.findAll();
    }

    public String find() {
        showResult = true;
        showResult2 = false;
        
        return "product-find";
    }

    public String findProducts() {
        showResult = false;
        showResult2 = true;
        
        return "product-find";
    }
    public String add() {
        product = new SkProduct();
        
        neditable = false;
        return "product-edit";
    }

    public String edit() {
        //product = (SkProduct) table.getSelectedRowData();
        
        //FacesContext contex = FacesContext.getCurrentInstance();
        
        removeList = new ArrayList<SkProduct>();
        
        if(product == null) {
            PageMessages.showWarning(bundle, "product.message.not_selected");
            return "";
        } else {
            neditable = false;
            return "product-edit";
        }
    }

    public String view() {
        //product = (SkProduct) table.getSelectedRowData();

        //conversionDetail = null;
        //removeList = new ArrayList<SoaConversionDetail>();
        
        if(product == null) {
            PageMessages.showWarning(bundle, "product.message.not_selected");
            return "";
        } else {
            neditable = true;
            return "product-edit";
        }
    }

    public String remove(){
        //product = (SkProduct) e.getID() .getSelectedRowData();
        
        if(product == null) {
            PageMessages.showWarning(bundle, "product.message.not_selected");
        } else {
            facade.remove(product);
            PageMessages.showInfo(bundle, "product.message.delete");
        }
        return "";
    }
    
    public String save() throws Exception {
        if(product.getIdProduct() == null) {
            facade.persistEntity(product);
        } else {
            facade.mergeEntity(removeList, product);
        }

        PageMessages.showInfo(bundle, "product.message.save");
        return "product-find";
    }

    public String getFindDescription() {
        return findDescription;
    }

    public void setFindDescription(String findDescription) {
        this.findDescription = findDescription;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
    public void processAction(ActionEvent anEvent) throws AbortProcessingException {
        
        UIParameter component = (UIParameter) anEvent.getComponent().findComponent("productId");
        System.out.println("param: " + component.getValue());

        this.product = facade.findById(component.getValue().toString());
        
    }


}
