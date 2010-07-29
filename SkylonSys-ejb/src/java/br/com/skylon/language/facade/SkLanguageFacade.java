package br.com.skylon.language.facade;

import br.com.skylon.language.model.SkLanguage;

import java.util.List;

import javax.ejb.Local;

@Local
public interface SkLanguageFacade {
    public static final String JNDI_NAME = "SkLanguageFacade";
    
    Object mergeEntity(Object entity);

    Object persistEntity(Object entity);

    List<SkLanguage> findAll()
            ;
    SkLanguage findLanguage(String lang, String country);
    
    public SkLanguage findByDescLanguage(String descLanguage);

    void remove(SkLanguage skLanguage);
}
