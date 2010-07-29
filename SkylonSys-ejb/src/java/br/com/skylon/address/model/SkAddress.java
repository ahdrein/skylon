package br.com.skylon.address.model;

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
@Table(name = "SK_ADDRESS") @SequenceGenerator(name="seq_id_skaddress", sequenceName="seq_id_skaddress", allocationSize=1)
@NamedQueries({@NamedQuery(name = "SkAddress.findByIdAddress", query = "SELECT s FROM SkAddress s WHERE s.idAddress = :idAddress"), @NamedQuery(name = "SkAddress.findBySCep", query = "SELECT s FROM SkAddress s WHERE s.sCep = :sCep"), @NamedQuery(name = "SkAddress.findBySType", query = "SELECT s FROM SkAddress s WHERE s.sType = :sType"), @NamedQuery(name = "SkAddress.findBySAddress", query = "SELECT s FROM SkAddress s WHERE s.sAddress = :sAddress"), @NamedQuery(name = "SkAddress.findBySBairro", query = "SELECT s FROM SkAddress s WHERE s.sBairro = :sBairro"), @NamedQuery(name = "SkAddress.findByNNumber", query = "SELECT s FROM SkAddress s WHERE s.nNumber = :nNumber")})
public class SkAddress implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID_ADDRESS", nullable = false) @GeneratedValue(generator="seq_id_skaddress")
    private BigDecimal idAddress;
    @Column(name = "S_CEP")
    private String sCep;
    @Column(name = "S_TYPE")
    private String sType;
    @Column(name = "S_ADDRESS")
    private String sAddress;
    @Column(name = "S_BAIRRO")
    private String sBairro;
    @Column(name = "N_NUMBER")
    private Integer nNumber;
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID_USER")
    @ManyToOne
    private SkUser idUser;

    public SkAddress() {
    }

    public SkAddress(BigDecimal idAddress) {
        this.idAddress = idAddress;
    }

    public BigDecimal getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(BigDecimal idAddress) {
        this.idAddress = idAddress;
    }

    public String getSCep() {
        return sCep;
    }

    public void setSCep(String sCep) {
        this.sCep = sCep;
    }

    public String getSType() {
        return sType;
    }

    public void setSType(String sType) {
        this.sType = sType;
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

    public Integer getNNumber() {
        return nNumber;
    }

    public void setNNumber(Integer nNumber) {
        this.nNumber = nNumber;
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
        hash += (idAddress != null ? idAddress.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SkAddress)) {
            return false;
        }
        SkAddress other = (SkAddress) object;
        if ((this.idAddress == null && other.idAddress != null) || (this.idAddress != null && !this.idAddress.equals(other.idAddress))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.skylon.all.model.SkAddress[idAddress=" + idAddress + "]";
    }

}
