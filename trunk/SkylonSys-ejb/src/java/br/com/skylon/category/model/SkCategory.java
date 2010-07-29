package br.com.skylon.category.model;

import br.com.skylon.product.model.SkProduct;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SK_CATEGORY") @SequenceGenerator(name="seq_id_skcategory", sequenceName="seq_id_skcategory", allocationSize=1)
@NamedQueries({@NamedQuery(name = "SkCategory.findByIdCategory", query = "SELECT s FROM SkCategory s WHERE s.idCategory = :idCategory"), @NamedQuery(name = "SkCategory.findBySDescCategory", query = "SELECT s FROM SkCategory s WHERE s.sDescCategory = :sDescCategory")})
public class SkCategory implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "ID_CATEGORY", nullable = false) @GeneratedValue(generator="seq_id_skcategory")
    private BigDecimal idCategory;
    
    @Column(name = "S_DESC_CATEGORY")
    private String sDescCategory;
    
    @OneToMany(mappedBy = "idCategory")
    private Collection<SkProduct> skProductCollection;

    public SkCategory() {
    }

    public SkCategory(BigDecimal idCategory) {
        this.idCategory = idCategory;
    }

    public BigDecimal getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(BigDecimal idCategory) {
        this.idCategory = idCategory;
    }

    public String getSDescCategory() {
        return sDescCategory;
    }

    public void setSDescCategory(String sDescCategory) {
        this.sDescCategory = sDescCategory;
    }

    public Collection<SkProduct> getSkProductCollection() {
        return skProductCollection;
    }

    public void setSkProductCollection(Collection<SkProduct> skProductCollection) {
        this.skProductCollection = skProductCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategory != null ? idCategory.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SkCategory)) {
            return false;
        }
        SkCategory other = (SkCategory) object;
        if ((this.idCategory == null && other.idCategory != null) || (this.idCategory != null && !this.idCategory.equals(other.idCategory))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.skylon.all.model.SkCategory[idCategory=" + idCategory + "]";
    }

}
