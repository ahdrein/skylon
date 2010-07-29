package br.com.skylon.phone.model;

import br.com.skylon.user.model.SkUser;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SK_PHONE") @SequenceGenerator(name="seq_id_skphone", sequenceName="seq_id_skphone", allocationSize=1)
@NamedQueries({@NamedQuery(name = "SkPhone.findByIdPhone", query = "SELECT s FROM SkPhone s WHERE s.idPhone = :idPhone"), @NamedQuery(name = "SkPhone.findBySPhone", query = "SELECT s FROM SkPhone s WHERE s.sPhone = :sPhone"), @NamedQuery(name = "SkPhone.findBySRamal", query = "SELECT s FROM SkPhone s WHERE s.sRamal = :sRamal"), @NamedQuery(name = "SkPhone.findBySType", query = "SELECT s FROM SkPhone s WHERE s.sType = :sType")})
public class SkPhone implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "ID_PHONE", nullable = false) @GeneratedValue(generator="seq_id_skphone")
    private BigDecimal idPhone;
    
    @Column(name = "S_PHONE")
    private String sPhone;
    
    @Column(name = "S_RAMAL")
    private String sRamal;
    
    @Column(name = "S_TYPE")
    private String sType;
    
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID_USER")
    @ManyToOne
    private SkUser idUser;

    public SkPhone() {
    }

    public SkPhone(BigDecimal idPhone) {
        this.idPhone = idPhone;
    }

    public BigDecimal getIdPhone() {
        return idPhone;
    }

    public void setIdPhone(BigDecimal idPhone) {
        this.idPhone = idPhone;
    }

    public String getSPhone() {
        return sPhone;
    }

    public void setSPhone(String sPhone) {
        this.sPhone = sPhone;
    }

    public String getSRamal() {
        return sRamal;
    }

    public void setSRamal(String sRamal) {
        this.sRamal = sRamal;
    }

    public String getSType() {
        return sType;
    }

    public void setSType(String sType) {
        this.sType = sType;
    }

    public SkUser getIdUser() {
        return idUser;
    }

    public void setIdUser(SkUser idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPhone != null ? idPhone.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SkPhone)) {
            return false;
        }
        SkPhone other = (SkPhone) object;
        if ((this.idPhone == null && other.idPhone != null) || (this.idPhone != null && !this.idPhone.equals(other.idPhone))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.skylon.all.model.SkPhone[idPhone=" + idPhone + "]";
    }

}
