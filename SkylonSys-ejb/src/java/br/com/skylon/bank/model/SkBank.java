package br.com.skylon.bank.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SK_BANK") @SequenceGenerator(name="seq_id_skbank", sequenceName="seq_id_skbank", allocationSize=1)
@NamedQueries({@NamedQuery(name = "SkBank.findByIdBank", query = "SELECT s FROM SkBank s WHERE s.idBank = :idBank"), @NamedQuery(name = "SkBank.findBySDescBank", query = "SELECT s FROM SkBank s WHERE s.sDescBank = :sDescBank"), @NamedQuery(name = "SkBank.findByNCodBank", query = "SELECT s FROM SkBank s WHERE s.nCodBank = :nCodBank")})
public class SkBank implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "ID_BANK", nullable = false) @GeneratedValue(generator="seq_id_skbank")
    private BigDecimal idBank;
    
    @Column(name = "S_DESC_BANK")
    private String sDescBank;
    
    @Column(name = "N_COD_BANK")
    private Integer nCodBank;

    public SkBank() {
    }

    public SkBank(BigDecimal idBank) {
        this.idBank = idBank;
    }

    public BigDecimal getIdBank() {
        return idBank;
    }

    public void setIdBank(BigDecimal idBank) {
        this.idBank = idBank;
    }

    public String getSDescBank() {
        return sDescBank;
    }

    public void setSDescBank(String sDescBank) {
        this.sDescBank = sDescBank;
    }

    public Integer getNCodBank() {
        return nCodBank;
    }

    public void setNCodBank(Integer nCodBank) {
        this.nCodBank = nCodBank;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBank != null ? idBank.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SkBank)) {
            return false;
        }
        SkBank other = (SkBank) object;
        if ((this.idBank == null && other.idBank != null) || (this.idBank != null && !this.idBank.equals(other.idBank))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.skylon.all.model.SkBank[idBank=" + idBank + "]";
    }

}
