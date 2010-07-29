package br.com.skylon.payment.model;

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
@Table(name = "SK_PAYMENT") @SequenceGenerator(name="seq_id_skpayment", sequenceName="seq_id_skpayment", allocationSize=1)
@NamedQueries({@NamedQuery(name = "SkPayment.findByIdPayment", query = "SELECT s FROM SkPayment s WHERE s.idPayment = :idPayment"), @NamedQuery(name = "SkPayment.findBySDescPayment", query = "SELECT s FROM SkPayment s WHERE s.sDescPayment = :sDescPayment")})
public class SkPayment implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "ID_PAYMENT", nullable = false) @GeneratedValue(generator="seq_id_skpayment")
    private BigDecimal idPayment;
    
    @Column(name = "S_DESC_PAYMENT")
    private String sDescPayment;

    public SkPayment() {
    }

    public SkPayment(BigDecimal idPayment) {
        this.idPayment = idPayment;
    }

    public BigDecimal getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(BigDecimal idPayment) {
        this.idPayment = idPayment;
    }

    public String getSDescPayment() {
        return sDescPayment;
    }

    public void setSDescPayment(String sDescPayment) {
        this.sDescPayment = sDescPayment;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPayment != null ? idPayment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SkPayment)) {
            return false;
        }
        SkPayment other = (SkPayment) object;
        if ((this.idPayment == null && other.idPayment != null) || (this.idPayment != null && !this.idPayment.equals(other.idPayment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.skylon.all.model.SkPayment[idPayment=" + idPayment + "]";
    }

}
