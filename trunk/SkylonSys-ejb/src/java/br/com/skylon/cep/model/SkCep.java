package br.com.skylon.cep.model;

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
@Table(name = "SK_CEP") @SequenceGenerator(name="seq_id_skcep", sequenceName="seq_id_skcep", allocationSize=1)
@NamedQueries({@NamedQuery(name = "SkCep.findByIdCep", query = "SELECT s FROM SkCep s WHERE s.idCep = :idCep"), @NamedQuery(name = "SkCep.findBySCep", query = "SELECT s FROM SkCep s WHERE s.sCep = :sCep"), @NamedQuery(name = "SkCep.findBySAddress", query = "SELECT s FROM SkCep s WHERE s.sAddress = :sAddress"), @NamedQuery(name = "SkCep.findBySBairro", query = "SELECT s FROM SkCep s WHERE s.sBairro = :sBairro")})
public class SkCep implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "ID_CEP", nullable = false) @GeneratedValue(generator="seq_id_skcep")
    private BigDecimal idCep;
    
    @Column(name = "S_CEP")
    private String sCep;
    
    @Column(name = "S_ADDRESS")
    private String sAddress;
    
    @Column(name = "S_BAIRRO")
    private String sBairro;

    public SkCep() {
    }

    public SkCep(BigDecimal idCep) {
        this.idCep = idCep;
    }

    public BigDecimal getIdCep() {
        return idCep;
    }

    public void setIdCep(BigDecimal idCep) {
        this.idCep = idCep;
    }

    public String getSCep() {
        return sCep;
    }

    public void setSCep(String sCep) {
        this.sCep = sCep;
    }

    public String getSAddress() {
        return sAddress;
    }

    public void setSAddress(String sAddress) {
        this.sAddress = sAddress;
    }

    public String getSBairro() {
        return sBairro;
    }

    public void setSBairro(String sBairro) {
        this.sBairro = sBairro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCep != null ? idCep.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SkCep)) {
            return false;
        }
        SkCep other = (SkCep) object;
        if ((this.idCep == null && other.idCep != null) || (this.idCep != null && !this.idCep.equals(other.idCep))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.skylon.all.model.SkCep[idCep=" + idCep + "]";
    }

}
