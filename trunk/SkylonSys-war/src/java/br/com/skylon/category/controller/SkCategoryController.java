package br.com.skylon.category.controller;

import br.com.skylon.category.facade.SkCategoryFacade;
import br.com.skylon.category.model.SkCategory;
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

public class SkCategoryController implements ActionListener{
    private final String bundle = "br.com.skylon.category.resource.labels";

    @EJB
    private SkCategoryFacade facade;

    private SkCategory category;
    private List<SkCategory> removeList;

    private ListDataModel categoryByName;
    
    public boolean neditable;
    private boolean showResult;

    private String findDescCategory;

    public SkCategory getFirstCategory() {
        List<SkCategory> l = facade.findAll();
        if(l != null && l.size() > 0) {
            return l.get(0);
        }
        return null;
    }

    public void actionSelect(ActionEvent event) {
        //System.out.println("teste : " + event);
        HtmlCommandLink link = (HtmlCommandLink)event.getSource();
        
        System.out.println("TTTT " + link);
        
        //getService().getPersistentService().remove( ((Integer)link.getValue()).toString() );		
    }

    public SkCategory getCategory() {
        return category;
    }

    public void setCategory(SkCategory Category) {
        this.category = Category;
    }

    public boolean isNeditable() { return neditable; }

    public List<SkCategory> getAll() {
        return facade.findAll();
    }

    public List<SkCategory> getResultList() {
        if(showResult)
            return facade.findByDesc(findDescCategory);

        return null;
    }

    public String find() {
        showResult = true;
        
        return "category-find";
    }

    public String add() {
        category = new SkCategory();

        neditable = false;
        return "category-edit";
    }

    public String edit() {
        //Category = (SkCategory) table.getSelectedRowData();

        removeList = new ArrayList<SkCategory>();

        if(category == null) {
            PageMessages.showWarning(bundle, "category.message.not_selected");
            return "";
        } else {
            neditable = false;
            return "category-edit";
        }
    }

    public String view() {
        //category = (SkCategory) table.getSelectedRowData();

        //removeList = new ArrayList<SoaConversionDetail>();

        if(category == null) {
            PageMessages.showWarning(bundle, "category.message.not_selected");
            return "";
        } else {
            neditable = true;
            return "category-edit";
        }
    }

    public String remove() {
        //Category = (SkCategory) e.getID() .getSelectedRowData();

        if(category == null) {
            PageMessages.showWarning(bundle, "category.message.not_selected");
        } else {
            facade.remove(category);
            PageMessages.showInfo(bundle, "category.message.delete");
        }
        return "";
    }

    public String save() throws Exception {
        try{
            if(category.getIdCategory() == null) {
                facade.persistEntity(category);
            } else {
                facade.mergeEntity(removeList, category);
            }
        }catch(Exception e){
            PageMessages.showError("Erro na gravação dos dados: \n" + e.getMessage());
            return "category-find";
        }
        PageMessages.showInfo(bundle, "category.message.save");
        return "category-find";
    }

    public String getFindDescCategory() {
        return findDescCategory;
    }

    public void setFindDescCategory(String findDescCategory) {
        this.findDescCategory = findDescCategory;
    }

    public void processAction(ActionEvent anEvent) throws AbortProcessingException {
        //        UIComponent tmpComponet = anEvent.getComponent();
        //        
        //        while (null != tmpComponet && (tmpComponet instanceof UIData)) {
        //            tmpComponet = tmpComponet.getParent();
        //        }
        //        
        //        if(tmpComponet != null && (tmpComponet instanceof UIData)){
        //            Object tmpRowData = ((UIData)tmpComponet).getRowData();
        //            
        //            if(tmpRowData instanceof SkCategory){
        //                category = (SkCategory)tmpRowData;
        //            }
        //        }
        
        UIParameter component = (UIParameter) anEvent.getComponent().findComponent("itemId");
        System.out.println("param: " + component.getValue());

        this.category = facade.findById(component.getValue().toString());
        
    }

    
    //public String editar(){
    //    category = (SkCategory)this.categoryByName.getRowData();
        
    //    return "category-edit";
    //}
    
//    public void actionSelect(ActionEvent event) {
//        int current = event.getID();
//        
//        HtmlCommandLink link = (HtmlCommandLink)event.getSource();
//        
//        System.out.println("TTTT " + link);
//        
//        FacesContext contex = FacesContext.getCurrentInstance();
//        
//        contex.getViewRoot().setLocale((Locale)Locale.getDefault());
//        //ccontex,getViewRoot().setLocale((Locale)locales.get(current));
//    }    

}
