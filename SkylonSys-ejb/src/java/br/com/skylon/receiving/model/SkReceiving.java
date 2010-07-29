package br.com.skylon.receiving.model;

import br.com.skylon.user.model.SkUser;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "SK_RECEIVING") @SequenceGenerator(name="seq_id_skreceiving", sequenceName="seq_id_skreceiving", allocationSize=1)
@NamedQueries({@NamedQuery(name = "SkReceiving.findByIdReceiving", query = "SELECT s FROM SkReceiving s WHERE s.idReceiving = :idReceiving"), @NamedQuery(name = "SkReceiving.findByIdReceivingSeq", query = "SELECT s FROM SkReceiving s WHERE s.idReceivingSeq = :idReceivingSeq"), @NamedQuery(name = "SkReceiving.findByDDateReceb", query = "SELECT s FROM SkReceiving s WHERE s.dDateReceb = :dDateReceb"), @NamedQuery(name = "SkReceiving.findByDDatePagto", query = "SELECT s FROM SkReceiving s WHERE s.dDatePagto = :dDatePagto"), @NamedQuery(name = "SkReceiving.findByNValue", query = "SELECT s FROM SkReceiving s WHERE s.nValue = :nValue")})
public class SkReceiving implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "ID_RECEIVING", nullable = false) @GeneratedValue(generator="seq_id_skreceiving")
    private BigDecimal idReceiving;
    
    @Column(name = "ID_RECEIVING_SEQ")
    private Integer idReceivingSeq;
    
    @Column(name = "D_DATE_RECEB")
    @Temporal(TemporalType.DATE)
    private Date dDateReceb;
    
    @Column(name = "D_DATE_PAGTO")
    @Temporal(TemporalType.DATE)
    private Date dDatePagto;
    
    @Column(name = "N_VALUE")   
    private Integer nValue;
    
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID_USER")
    @ManyToOne
    private SkUser idUser;

    public SkReceiving() {
    }

    public SkReceiving(BigDecimal idReceiving) {
        this.idReceiving = idReceiving;
    }

    public BigDecimal getIdReceiving() {
        return idReceiving;
    }

    public void setIdReceiving(BigDecimal idReceiving) {
        this.idReceiving = idReceiving;
    }

    public Integer getIdReceivingSeq() {
        return idReceivingSeq;
    }

    public void setIdReceivingSeq(Integer idReceivingSeq) {
        this.idReceivingSeq = idReceivingSeq;
    }

    public Date getDDateReceb() {
        return dDateReceb;
    }

    public void setDDateReceb(Date dDateReceb) {
        this.dDateReceb = dDateReceb;
    }

    public Date getDDatePagto() {
        return dDatePagto;
    }

    public void setDDatePagto(Date dDatePagto) {
        this.dDatePagto = dDatePagto;
    }

    public Integer getNValue() {
        return nValue;
    }

    public void setNValue(Integer nValue) {
        this.nValue = nValue;
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
        hash += (idReceiving != null ? idReceiving.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SkReceiving)) {
            return false;
        }
        SkReceiving other = (SkReceiving) object;
        if ((this.idReceiving == null && other.idReceiving != null) || (this.idReceiving != null && !this.idReceiving.equals(other.idReceiving))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.skylon.all.model.SkReceiving[idReceiving=" + idReceiving + "]";
    }

}
