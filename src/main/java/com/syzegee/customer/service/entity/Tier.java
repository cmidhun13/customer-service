/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syzegee.customer.service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author WildJasmine
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tier")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tier.findAll", query = "SELECT t FROM Tier t")
    , @NamedQuery(name = "Tier.findByTierId", query = "SELECT t FROM Tier t WHERE t.tierId = :tierId")
    , @NamedQuery(name = "Tier.findByTierName", query = "SELECT t FROM Tier t WHERE t.tierName = :tierName")
    , @NamedQuery(name = "Tier.findByIsActive", query = "SELECT t FROM Tier t WHERE t.isActive = :isActive")
    , @NamedQuery(name = "Tier.findByCorrelationId", query = "SELECT t FROM Tier t WHERE t.correlationId = :correlationId")
    , @NamedQuery(name = "Tier.findByState", query = "SELECT t FROM Tier t WHERE t.state = :state")
    , @NamedQuery(name = "Tier.findByCreatedBy", query = "SELECT t FROM Tier t WHERE t.createdBy = :createdBy")
    , @NamedQuery(name = "Tier.findByCreatedDate", query = "SELECT t FROM Tier t WHERE t.createdDate = :createdDate")
    , @NamedQuery(name = "Tier.findByUpdatedBy", query = "SELECT t FROM Tier t WHERE t.updatedBy = :updatedBy")
    , @NamedQuery(name = "Tier.findByUpdatedDate", query = "SELECT t FROM Tier t WHERE t.updatedDate = :updatedDate")})
public class Tier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tier_id")
    private Long tierId;
    @Size(max = 120)
    @Column(name = "tier_name")
    private String tierName;
    @Column(name = "is_active")
    private Boolean isActive;
    @Size(max = 120)
    @Column(name = "correlation_id")
    private String correlationId;
    @Size(max = 120)
    @Column(name = "state")
    private String state;
    @Size(max = 120)
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Size(max = 120)
    @Column(name = "updated_by")
    private String updatedBy;
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    @ManyToOne(optional = false)
    private Customer customerId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tierId")
    private Collection<Packages> packages;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tierId != null ? tierId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tier)) {
            return false;
        }
        Tier other = (Tier) object;
        if ((this.tierId == null && other.tierId != null) || (this.tierId != null && !this.tierId.equals(other.tierId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.syzegee.customer.service.entity.Tier[ tierId=" + tierId + " ]";
    }
    
}
