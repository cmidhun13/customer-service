/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syzegee.customer.service.entity;

import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author WildJasmine
 */
@Entity
@Table(name = "domain_details")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DomainDetails.findAll", query = "SELECT d FROM DomainDetails d")
    , @NamedQuery(name = "DomainDetails.findBySiteId", query = "SELECT d FROM DomainDetails d WHERE d.siteId = :siteId")
    , @NamedQuery(name = "DomainDetails.findBySiteCode", query = "SELECT d FROM DomainDetails d WHERE d.siteCode = :siteCode")
    , @NamedQuery(name = "DomainDetails.findByTemplateCode", query = "SELECT d FROM DomainDetails d WHERE d.templateCode = :templateCode")
    , @NamedQuery(name = "DomainDetails.findBySiteName", query = "SELECT d FROM DomainDetails d WHERE d.siteName = :siteName")
    , @NamedQuery(name = "DomainDetails.findBySiteDesc", query = "SELECT d FROM DomainDetails d WHERE d.siteDesc = :siteDesc")
    , @NamedQuery(name = "DomainDetails.findByDomainName", query = "SELECT d FROM DomainDetails d WHERE d.domainName = :domainName")
    , @NamedQuery(name = "DomainDetails.findByTagline", query = "SELECT d FROM DomainDetails d WHERE d.tagline = :tagline")
    , @NamedQuery(name = "DomainDetails.findByIsActive", query = "SELECT d FROM DomainDetails d WHERE d.isActive = :isActive")
    , @NamedQuery(name = "DomainDetails.findByCorrelationId", query = "SELECT d FROM DomainDetails d WHERE d.correlationId = :correlationId")
    , @NamedQuery(name = "DomainDetails.findByState", query = "SELECT d FROM DomainDetails d WHERE d.state = :state")
    , @NamedQuery(name = "DomainDetails.findByCreatedBy", query = "SELECT d FROM DomainDetails d WHERE d.createdBy = :createdBy")
    , @NamedQuery(name = "DomainDetails.findByCreatedDate", query = "SELECT d FROM DomainDetails d WHERE d.createdDate = :createdDate")
    , @NamedQuery(name = "DomainDetails.findByUpdatedBy", query = "SELECT d FROM DomainDetails d WHERE d.updatedBy = :updatedBy")
    , @NamedQuery(name = "DomainDetails.findByUpdatedDate", query = "SELECT d FROM DomainDetails d WHERE d.updatedDate = :updatedDate")})
public class DomainDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "site_id")
    private Long siteId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "site_code")
    private String siteCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "template_code")
    private String templateCode;
    @Size(max = 120)
    @Column(name = "site_name")
    private String siteName;
    @Size(max = 120)
    @Column(name = "site_desc")
    private String siteDesc;
    @Size(max = 120)
    @Column(name = "domain_name")
    private String domainName;
    @Column(columnDefinition = "TEXT",name = "logo")
    private String logo;
    @Size(max = 120)
    @Column(name = "tagline")
    private String tagline;
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
    public DomainDetails() {
    }

    public DomainDetails(Long siteId) {
        this.siteId = siteId;
    }

    public DomainDetails(Long siteId, String siteCode, String templateCode) {
        this.siteId = siteId;
        this.siteCode = siteCode;
        this.templateCode = templateCode;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteDesc() {
        return siteDesc;
    }

    public void setSiteDesc(String siteDesc) {
        this.siteDesc = siteDesc;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (siteId != null ? siteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DomainDetails)) {
            return false;
        }
        DomainDetails other = (DomainDetails) object;
        if ((this.siteId == null && other.siteId != null) || (this.siteId != null && !this.siteId.equals(other.siteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.syzegee.customer.service.entity.DomainDetails[ siteId=" + siteId + " ]";
    }
    
}