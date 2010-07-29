package br.com.skylon.product.model;

import br.com.skylon.category.model.SkCategory;
import br.com.skylon.invoice.model.SkItemInvoice;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
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

@Entity
@Table(name = "SK_PRODUCT" ) @SequenceGenerator(name="seq_id_skproduct", sequenceName="seq_id_skproduct", allocationSize=1)
@NamedQueries({@NamedQuery(name = "SkProduct.findByIdProduct", query = "SELECT s FROM SkProduct s WHERE s.idProduct = :idProduct"), @NamedQuery(name = "SkProduct.findBySDescCurtaProd", query = "SELECT s FROM SkProduct s WHERE s.sDescCurtaProd = :sDescCurtaProd"), @NamedQuery(name = "SkProduct.findBySDescLongaProd", query = "SELECT s FROM SkProduct s WHERE s.sDescLongaProd = :sDescLongaProd"), @NamedQuery(name = "SkProduct.findByNVlrVenda", query = "SELECT s FROM SkProduct s WHERE s.nVlrVenda = :nVlrVenda"), @NamedQuery(name = "SkProduct.findByNVlrCusto", query = "SELECT s FROM SkProduct s WHERE s.nVlrCusto = :nVlrCusto"), @NamedQuery(name = "SkProduct.findByNQtdeEstoque", query = "SELECT s FROM SkProduct s WHERE s.nQtdeEstoque = :nQtdeEstoque"), @NamedQuery(name = "SkProduct.findByNWeight", query = "SELECT s FROM SkProduct s WHERE s.nWeight = :nWeight")})
public class SkProduct implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "ID_PRODUCT", nullable = false) @GeneratedValue(generator="seq_id_skproduct")
    private BigDecimal idProduct;
    
    @Column(name = "S_DESC_CURTA_PROD")
    private String sDescCurtaProd;
    
    @Column(name = "S_DESC_LONGA_PROD")
    private String sDescLongaProd;
    
    @Column(name = "N_VLR_VENDA")
    private Integer nVlrVenda;
    
    @Column(name = "N_VLR_CUSTO")
    private Integer nVlrCusto;
    
    @Lob
    @Column(name = "IMG_PROD")
    private Serializable imgProd;
    
    @Column(name = "N_QTDE_ESTOQUE")
    private Integer nQtdeEstoque;
    
    @Lob
    @Column(name = "S_DETALHE")
    private String sDetalhe;
    
    @Column(name = "N_WEIGHT")
    private Integer nWeight;
    
    @OneToMany(mappedBy = "idProduct")
    private Collection<SkItemInvoice> skItemInvoiceCollection;
    
    @JoinColumn(name = "ID_CATEGORY", referencedColumnName = "ID_CATEGORY")
    @ManyToOne
    private SkCategory idCategory;

    public SkProduct() {
    }

    public SkProduct(BigDecimal idProduct) {
        this.idProduct = idProduct;
    }

    public BigDecimal getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(BigDecimal idProduct) {
        this.idProduct = idProduct;
    }

    public String getSDescCurtaProd() {
        return sDescCurtaProd;
    }

    public void setSDescCurtaProd(String sDescCurtaProd) {
        this.sDescCurtaProd = sDescCurtaProd;
    }

    public String getSDescLongaProd() {
        return sDescLongaProd;
    }

    public void setSDescLongaProd(String sDescLongaProd) {
        this.sDescLongaProd = sDescLongaProd;
    }

    public Integer getNVlrVenda() {
        return nVlrVenda;
    }

    public void setNVlrVenda(Integer nVlrVenda) {
        this.nVlrVenda = nVlrVenda;
    }

    public Integer getNVlrCusto() {
        return nVlrCusto;
    }

    public void setNVlrCusto(Integer nVlrCusto) {
        this.nVlrCusto = nVlrCusto;
    }

    public Serializable getImgProd() {
        return imgProd;
    }

    public void setImgProd(Serializable imgProd) {
        this.imgProd = imgProd;
    }

    public Integer getNQtdeEstoque() {
        return nQtdeEstoque;
    }

    public void setNQtdeEstoque(Integer nQtdeEstoque) {
        this.nQtdeEstoque = nQtdeEstoque;
    }

    public String getSDetalhe() {
        return sDetalhe;
    }

    public void setSDetalhe(String sDetalhe) {
        this.sDetalhe = sDetalhe;
    }

    public Integer getNWeight() {
        return nWeight;
    }

    public void setNWeight(Integer nWeight) {
        this.nWeight = nWeight;
    }

    public Collection<SkItemInvoice> getSkItemInvoiceCollection() {
        return skItemInvoiceCollection;
    }

    public void setSkItemInvoiceCollection(Collection<SkItemInvoice> skItemInvoiceCollection) {
        this.skItemInvoiceCollection = skItemInvoiceCollection;
    }

    public SkCategory getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(SkCategory idCategory) {
        this.idCategory = idCategory;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProduct != null ? idProduct.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SkProduct)) {
            return false;
        }
        SkProduct other = (SkProduct) object;
        if ((this.idProduct == null && other.idProduct != null) || (this.idProduct != null && !this.idProduct.equals(other.idProduct))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.skylon.Model.src.address.SkProduct[idProduct=" + idProduct + "]";
    }

}
