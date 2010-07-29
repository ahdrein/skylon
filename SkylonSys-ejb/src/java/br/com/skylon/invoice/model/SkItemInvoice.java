package br.com.skylon.invoice.model;

import br.com.skylon.product.model.SkProduct;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SK_ITEM_INVOICE") @SequenceGenerator(name="seq_id_skitem_invoice", sequenceName="seq_id_skitem_invoice", allocationSize=1)
@NamedQueries({@NamedQuery(name = "SkItemInvoice.findByIdItInvoice", query = "SELECT s FROM SkItemInvoice s WHERE s.idItInvoice = :idItInvoice"), @NamedQuery(name = "SkItemInvoice.findByNQtdeItInvoice", query = "SELECT s FROM SkItemInvoice s WHERE s.nQtdeItInvoice = :nQtdeItInvoice")})
public class SkItemInvoice implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "ID_IT_INVOICE", nullable = false) @GeneratedValue(generator="seq_id_skitem_invoice")
    private BigDecimal idItInvoice;
    
    @Column(name = "N_QTDE_IT_INVOICE")
    private BigInteger nQtdeItInvoice;
    
    @ManyToOne(cascade=CascadeType.ALL )
    @JoinColumn(name = "ID_INVOICE", referencedColumnName = "ID_INVOICE", nullable = false)
    private SkInvoice idInvoice;
    
    @JoinColumn(name = "ID_PRODUCT", referencedColumnName = "ID_PRODUCT")
    @ManyToOne
    private SkProduct idProduct;

    
    public SkItemInvoice() {
    }

    public SkItemInvoice(BigDecimal idItInvoice) {
        this.idItInvoice = idItInvoice;
    }

    public BigDecimal getIdItInvoice() {
        return idItInvoice;
    }

    public void setIdItInvoice(BigDecimal idItInvoice) {
        this.idItInvoice = idItInvoice;
    }

    public BigInteger getNQtdeItInvoice() {
        return nQtdeItInvoice;
    }

    public void setNQtdeItInvoice(BigInteger nQtdeItInvoice) {
        this.nQtdeItInvoice = nQtdeItInvoice;
    }

    public SkInvoice getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(SkInvoice idInvoice) {
        this.idInvoice = idInvoice;
    }

    public SkProduct getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(SkProduct idProduct) {
        this.idProduct = idProduct;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idItInvoice != null ? idItInvoice.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SkItemInvoice)) {
            return false;
        }
        SkItemInvoice other = (SkItemInvoice) object;
        if ((this.idItInvoice == null && other.idItInvoice != null) || (this.idItInvoice != null && !this.idItInvoice.equals(other.idItInvoice))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.skylon.all.model.SkItemInvoice[idItInvoice=" + idItInvoice + "]";
    }

}
