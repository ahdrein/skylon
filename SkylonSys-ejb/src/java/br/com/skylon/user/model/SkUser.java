package br.com.skylon.user.model;

import br.com.skylon.address.model.SkAddress;
import br.com.skylon.invoice.model.SkInvoice;
import br.com.skylon.language.model.SkLanguage;
import br.com.skylon.phone.model.SkPhone;
import br.com.skylon.receiving.model.SkReceiving;
import br.com.skylon.util.Crypto;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "SK_USER") @SequenceGenerator(name="seq_id_skuser", sequenceName="seq_id_skuser", allocationSize=1)
//@NamedQueries({@NamedQuery(name = "SkUser.findByIdUser", query = "SELECT s FROM SkUser s WHERE s.idUser = :idUser"), @NamedQuery(name = "SkUser.findBySEmail", query = "SELECT s FROM SkUser s WHERE s.sEmail = :sEmail"), @NamedQuery(name = "SkUser.findBySPassword", query = "SELECT s FROM SkUser s WHERE s.sPassword = :sPassword"), @NamedQuery(name = "SkUser.findBySName", query = "SELECT s FROM SkUser s WHERE s.sName = :sName"), @NamedQuery(name = "SkUser.findByDDateUpdateSenha", query = "SELECT s FROM SkUser s WHERE s.dDateUpdateSenha = :dDateUpdateSenha"), @NamedQuery(name = "SkUser.findByDLastUpdate", query = "SELECT s FROM SkUser s WHERE s.dLastUpdate = :dLastUpdate"), @NamedQuery(name = "SkUser.findByNTentativasLogon", query = "SELECT s FROM SkUser s WHERE s.nTentativasLogon = :nTentativasLogon"), @NamedQuery(name = "SkUser.findBySQuestion", query = "SELECT s FROM SkUser s WHERE s.sQuestion = :sQuestion"), @NamedQuery(name = "SkUser.findBySAnswer", query = "SELECT s FROM SkUser s WHERE s.sAnswer = :sAnswer"), @NamedQuery(name = "SkUser.findBySActive", query = "SELECT s FROM SkUser s WHERE s.sActive = :sActive"), @NamedQuery(name = "SkUser.findBySSexy", query = "SELECT s FROM SkUser s WHERE s.sSexy = :sSexy"), @NamedQuery(name = "SkUser.findBySDepartment", query = "SELECT s FROM SkUser s WHERE s.sDepartment = :sDepartment"), @NamedQuery(name = "SkUser.findBySCharged", query = "SELECT s FROM SkUser s WHERE s.sCharged = :sCharged"), @NamedQuery(name = "SkUser.findByAdicional", query = "SELECT s FROM SkUser s WHERE s.adicional = :adicional"), @NamedQuery(name = "SkUser.findByCodLanguage", query = "SELECT s FROM SkUser s WHERE s.codLanguage = :codLanguage"), @NamedQuery(name = "SkUser.findBySCpf", query = "SELECT s FROM SkUser s WHERE s.sCpf = :sCpf"), @NamedQuery(name = "SkUser.findByLastLogin", query = "SELECT s FROM SkUser s WHERE s.lastLogin = :lastLogin"), @NamedQuery(name = "SkUser.findBySRg", query = "SELECT s FROM SkUser s WHERE s.sRg = :sRg"), @NamedQuery(name = "SkUser.findByIdBank", query = "SELECT s FROM SkUser s WHERE s.idBank = :idBank")})
@NamedQueries({
    @NamedQuery(name = "SkUser.findByLogin", query = "select u from SkUser u where u.sEmail = :email and u.sPassword = :pass")
})
public class SkUser implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "ID_USER", nullable = false) @GeneratedValue(generator="seq_id_skuser")
    private BigDecimal idUser;
    
    @Column(name = "S_EMAIL", nullable = false)
    private String sEmail;
    
    @Column(name = "S_PASSWORD", nullable = false)
    private String sPassword;
    
    @Column(name = "S_NAME", nullable = false)
    private String sName;
    
    @Column(name = "D_DATE_UPDATE_SENHA")
    @Temporal(TemporalType.DATE)
    private Date dDateUpdateSenha;
    
    @Column(name = "D_LAST_UPDATE")
    @Temporal(TemporalType.DATE)
    private Date dLastUpdate;
    
    @Column(name = "N_TENTATIVAS_LOGON")
    private BigInteger nTentativasLogon;
    
    @Column(name = "S_QUESTION")
    private String sQuestion;
    
    @Column(name = "S_ANSWER")
    private String sAnswer;
    
    @Column(name = "S_ACTIVE", nullable = false)
    private String sActive;
    
    @Column(name = "S_SEXY")
    private String sSexy;
    
    @Column(name = "S_DEPARTMENT")
    private String sDepartment;
    
    @Column(name = "S_CHARGED")
    private String sCharged;
    
    @Column(name = "ADICIONAL")
    private String adicional;
    
    @Column(name = "COD_LANGUAGE")
    private String codLanguage;
    
    @Column(name = "S_CPF")
    private String sCpf;
    
    @Column(name = "LAST_LOGIN", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date lastLogin;
    
    @Column(name = "S_RG")
    private String sRg;
    
    @Lob
    @Column(name = "IMG_USER")
    private Serializable imgUser;
    
    @Column(name = "ID_BANK")
    private BigInteger idBank;

    @ManyToOne @JoinColumn(name = "ID_LANGUAGE")
    private SkLanguage language;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser")
    private Collection<SkPhone> skPhoneCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser")
    private Collection<SkInvoice> skInvoiceCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser")
    private Collection<SkAddress> skAddressCollection;
    
    @OneToMany(mappedBy = "idUser")
    private Collection<SkReceiving> skReceivingCollection;

    public SkUser() {
    }

    public SkUser(BigDecimal idUser) {
        this.idUser = idUser;
    }

    public SkUser(BigDecimal idUser, String sEmail, String sPassword, String sName, String sActive, Date lastLogin) {
        this.idUser = idUser;
        this.sEmail = sEmail;
        this.sPassword = sPassword;
        this.sName = sName;
        this.sActive = sActive;
        this.lastLogin = lastLogin;
    }

    public BigDecimal getIdUser() {
        return idUser;
    }

    public void setIdUser(BigDecimal idUser) {
        this.idUser = idUser;
    }

    public String getSEmail() {
        return sEmail;
    }

    public void setSEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    public String getSPassword() {
        return sPassword;
    }

    public void setSPassword(String sPassword) {
        this.sPassword = Crypto.toMD5(sPassword);
    }

    public String getSName() {
        return sName;
    }

    public void setSName(String sName) {
        this.sName = sName;
    }

    public Date getDDateUpdateSenha() {
        return dDateUpdateSenha;
    }

    public void setDDateUpdateSenha(Date dDateUpdateSenha) {
        this.dDateUpdateSenha = dDateUpdateSenha;
    }

    public Date getDLastUpdate() {
        return dLastUpdate;
    }

    public void setDLastUpdate(Date dLastUpdate) {
        this.dLastUpdate = dLastUpdate;
    }

    public BigInteger getNTentativasLogon() {
        return nTentativasLogon;
    }

    public void setNTentativasLogon(BigInteger nTentativasLogon) {
        this.nTentativasLogon = nTentativasLogon;
    }

    public String getSQuestion() {
        return sQuestion;
    }

    public void setSQuestion(String sQuestion) {
        this.sQuestion = sQuestion;
    }

    public String getSAnswer() {
        return sAnswer;
    }

    public void setSAnswer(String sAnswer) {
        this.sAnswer = sAnswer;
    }

    public String getSActive() {
        return sActive;
    }

    public void setSActive(String sActive) {
        this.sActive = sActive;
    }

    public String getSSexy() {
        return sSexy;
    }

    public void setSSexy(String sSexy) {
        this.sSexy = sSexy;
    }

    public String getSDepartment() {
        return sDepartment;
    }

    public void setSDepartment(String sDepartment) {
        this.sDepartment = sDepartment;
    }

    public String getSCharged() {
        return sCharged;
    }

    public void setSCharged(String sCharged) {
        this.sCharged = sCharged;
    }

    public String getAdicional() {
        return adicional;
    }

    public void setAdicional(String adicional) {
        this.adicional = adicional;
    }

    public String getCodLanguage() {
        return codLanguage;
    }

    public void setCodLanguage(String codLanguage) {
        this.codLanguage = codLanguage;
    }

    public String getSCpf() {
        return sCpf;
    }

    public void setSCpf(String sCpf) {
        this.sCpf = sCpf;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getSRg() {
        return sRg;
    }

    public void setSRg(String sRg) {
        this.sRg = sRg;
    }

    public Serializable getImgUser() {
        return imgUser;
    }

    public void setImgUser(Serializable imgUser) {
        this.imgUser = imgUser;
    }

    public BigInteger getIdBank() {
        return idBank;
    }

    public void setIdBank(BigInteger idBank) {
        this.idBank = idBank;
    }
    
    public SkLanguage getLanguage() { 
        return language; 
    }
    
    public void setLanguage(SkLanguage language) { 
        this.language = language; 
    }

    public Collection<SkPhone> getSkPhoneCollection() {
        return skPhoneCollection;
    }

    public void setSkPhoneCollection(Collection<SkPhone> skPhoneCollection) {
        this.skPhoneCollection = skPhoneCollection;
    }

    public Collection<SkInvoice> getSkInvoiceCollection() {
        return skInvoiceCollection;
    }

    public void setSkInvoiceCollection(Collection<SkInvoice> skInvoiceCollection) {
        this.skInvoiceCollection = skInvoiceCollection;
    }

    public Collection<SkAddress> getSkAddressCollection() {
        return skAddressCollection;
    }

    public void setSkAddressCollection(Collection<SkAddress> skAddressCollection) {
        this.skAddressCollection = skAddressCollection;
    }

    public Collection<SkReceiving> getSkReceivingCollection() {
        return skReceivingCollection;
    }

    public void setSkReceivingCollection(Collection<SkReceiving> skReceivingCollection) {
        this.skReceivingCollection = skReceivingCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SkUser)) {
            return false;
        }
        SkUser other = (SkUser) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }

    //@Override
    //public String toString() {
    //    return "br.com.skylon.all.model.SkUser[idUser=" + idUser + "]";
    //}

}
