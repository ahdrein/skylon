package br.com.skylon.language.controller;

import br.com.skylon.language.facade.SkLanguageFacade;
import br.com.skylon.language.model.SkLanguage;

import java.util.List;
import javax.ejb.EJB;

public class SkLanguageController {
    @EJB
    private SkLanguageFacade facade;

    public SkLanguageController() { 
    }
    
    public List<SkLanguage> getAll() {
        return facade.findAll();
    }
}
