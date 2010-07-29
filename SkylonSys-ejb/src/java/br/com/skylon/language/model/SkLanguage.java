
package br.com.skylon.language.model;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "SK_LANGUAGE") @SequenceGenerator(name="seq_id_sklanguage", sequenceName="seq_id_sklanguage", allocationSize=1)
@NamedQuery(name = "SkLanguage.findAll", query = "select o from SkLanguage o")
public class SkLanguage implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id 
    @Column(name="ID_LANGUAGE", nullable = false) @GeneratedValue(generator="seq_id_sklanguage")
    private BigDecimal idLanguage;
    
    @Column(name="S_DESCRIPTION")
    private String sDescription;
    
    @Column(name="S_LANGUAGE")
    private String sLanguage;
    
    @Column(name="S_COUNTRY")
    private String sCountry;

    public SkLanguage() { }

    @Id
    public BigDecimal getIdLanguage() { return idLanguage; }
    public void setIdLanguage(BigDecimal idLanguage) { this.idLanguage = idLanguage; }

    public String getSDescription() { return sDescription; }
    public void setSDescription(String sDescription) { this.sDescription = sDescription; }

    public void setSLanguage(String sLanguage) { this.sLanguage = sLanguage; }
    public String getSLanguage() { return sLanguage; }

    public void setSCountry(String sCountry) { this.sCountry = sCountry; }
    public String getSCountry() { return sCountry; }
    
    @Transient
    public Locale getLocale() {
        return new Locale(sLanguage, sCountry);
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLanguage != null ? idLanguage.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SkLanguage)) {
            return false;
        }
        SkLanguage other = (SkLanguage) object;
        if ((this.idLanguage == null && other.idLanguage != null) || (this.idLanguage != null && !this.idLanguage.equals(other.idLanguage))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.skylon.all.model.SkLanguage[idLanguage=" + idLanguage + "]";
    }
    
}
