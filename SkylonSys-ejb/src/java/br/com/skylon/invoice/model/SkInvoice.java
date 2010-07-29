package br.com.skylon.invoice.model;

import br.com.skylon.user.model.SkUser;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SK_INVOICE") @SequenceGenerator(name="seq_id_skinvoice", sequenceName="seq_id_skinvoice", allocationSize=1)
@NamedQueries({@NamedQuery(name = "SkInvoice.findByIdInvoice", query = "SELECT s FROM SkInvoice s WHERE s.idInvoice = :idInvoice"), @NamedQuery(name = "SkInvoice.findByIdPayment", query = "SELECT s FROM SkInvoice s WHERE s.idPayment = :idPayment")})
public class SkInvoice implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "ID_INVOICE", nullable = false) @GeneratedValue(generator="seq_id_skinvoice")
    private BigDecimal idInvoice;
    
    @Column(name = "ID_PAYMENT", nullable = false)
    private BigInteger idPayment;
    
    @OneToMany(mappedBy = "idInvoice", cascade=CascadeType.ALL)
    private Collection<SkItemInvoice> skItemInvoiceCollection;
    
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID_USER")
    @ManyToOne
    private SkUser idUser;

    public SkInvoice() {
    }

    public SkInvoice(BigDecimal idInvoice) {
        this.idInvoice = idInvoice;
    }

    public SkInvoice(BigDecimal idInvoice, BigInteger idPayment) {
        this.idInvoice = idInvoice;
        this.idPayment = idPayment;
    }

    public BigDecimal getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(BigDecimal idInvoice) {
        this.idInvoice = idInvoice;
    }

    public BigInteger getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(BigInteger idPayment) {
        this.idPayment = idPayment;
    }

    public Collection<SkItemInvoice> getSkItemInvoiceCollection() {
        return skItemInvoiceCollection;
    }

    public void setSkItemInvoiceCollection(Collection<SkItemInvoice> skItemInvoiceCollection) {
        this.skItemInvoiceCollection = skItemInvoiceCollection;
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
        hash += (idInvoice != null ? idInvoice.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SkInvoice)) {
            return false;
        }
        SkInvoice other = (SkInvoice) object;
        if ((this.idInvoice == null && other.idInvoice != null) || (this.idInvoice != null && !this.idInvoice.equals(other.idInvoice))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.skylon.all.model.SkInvoice[idInvoice=" + idInvoice + "]";
    }

}
