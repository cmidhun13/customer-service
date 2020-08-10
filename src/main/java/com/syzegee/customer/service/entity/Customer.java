/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syzegee.customer.service.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author WildJasmine
 */
@Entity
@Table(name = "customer")
@XmlRootElement
@Builder()
@NamedQueries({
        @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c")
        , @NamedQuery(name = "Customer.findByCustomerId", query = "SELECT c FROM Customer c WHERE c.customerId = :customerId")
        , @NamedQuery(name = "Customer.findByUserId", query = "SELECT c FROM Customer c WHERE c.userId = :userId")
        , @NamedQuery(name = "Customer.findByState", query = "SELECT c FROM Customer c WHERE c.state = :state")
        , @NamedQuery(name = "Customer.findByBusinessName", query = "SELECT c FROM Customer c WHERE c.businessName = :businessName")
        , @NamedQuery(name = "Customer.findByBusinessEmail", query = "SELECT c FROM Customer c WHERE c.businessEmail = :businessEmail")
        , @NamedQuery(name = "Customer.findByBusinessType", query = "SELECT c FROM Customer c WHERE c.businessType = :businessType")
        , @NamedQuery(name = "Customer.findByBusinessCategory", query = "SELECT c FROM Customer c WHERE c.businessCategory = :businessCategory")
        , @NamedQuery(name = "Customer.findByRegion", query = "SELECT c FROM Customer c WHERE c.region = :region")
        , @NamedQuery(name = "Customer.findByPhone", query = "SELECT c FROM Customer c WHERE c.phone = :phone")
        , @NamedQuery(name = "Customer.findByCurrency", query = "SELECT c FROM Customer c WHERE c.currency = :currency")
        , @NamedQuery(name = "Customer.findByIsActive", query = "SELECT c FROM Customer c WHERE c.isActive = :isActive")
        , @NamedQuery(name = "Customer.findByActivationStatus", query = "SELECT c FROM Customer c WHERE c.activationStatus = :activationStatus")
        , @NamedQuery(name = "Customer.findByActivationCode", query = "SELECT c FROM Customer c WHERE c.activationCode = :activationCode")
        , @NamedQuery(name = "Customer.findByCorrelationId", query = "SELECT c FROM Customer c WHERE c.correlationId = :correlationId")
        , @NamedQuery(name = "Customer.findByCreatedBy", query = "SELECT c FROM Customer c WHERE c.createdBy = :createdBy")
        , @NamedQuery(name = "Customer.findByCreatedDate", query = "SELECT c FROM Customer c WHERE c.createdDate = :createdDate")
        , @NamedQuery(name = "Customer.findByUpdatedBy", query = "SELECT c FROM Customer c WHERE c.updatedBy = :updatedBy")
        , @NamedQuery(name = "Customer.findByUpdatedDate", query = "SELECT c FROM Customer c WHERE c.updatedDate = :updatedDate")})
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "customer_id")
    private Long customerId;
    @Basic(optional = false)
    @Size(min = 1, max = 120)
    @Column(name = "user_id")
    private String userId;
    @Size(max = 120)
    @Column(name = "state")
    private String state;
    @Size(max = 1024)
    @Column(name = "business_name")
    private String businessName;
    @Size(max = 1024)
    @Column(name = "business_email")
    private String businessEmail;
    @Size(max = 120)
    @Column(name = "business_type")
    private String businessType;
    @Size(max = 120)
    @Column(name = "business_category")
    private String businessCategory;
    @Size(max = 120)
    @Column(name = "region")
    private String region;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 120)
    @Column(name = "phone")
    private String phone;
    @Size(max = 120)
    @Column(name = "currency")
    private String currency;
    @Column(name = "is_active")
    private Boolean isActive;
    @Column(name = "activation_status")
    private Boolean activationStatus;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "activation_code")
    private String activationCode;
    @Size(max = 120)
    @Column(name = "correlation_id")
    private String correlationId;
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
    @Column(name = "communication_preferences")
    private String communicationPreferences;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerId")
    private Collection<CustomerAddress> customerAddressCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerId")
    private Collection<CustomerBenefit> customerBenefitCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerId")
    private Collection<Tier> tiers;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerId")
    private Collection<CustomerUser> customerUserCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerId")
    private Collection<DomainDetails> domainDetails;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerId")
    private Collection<Packages> packages;
    @JoinColumn(name = "organization_id", referencedColumnName = "organization_id")
    @ManyToOne(optional = false)
    private Organization organizationId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerId")
    private Collection<SolicitationPackage> solicitationPackages;


    @Tolerate
    public Customer() {
    }

    @Tolerate
    public Customer(Long customerId) {
        this.customerId = customerId;
    }

    @Tolerate
    public Customer(Long customerId, String userId, String activationCode) {
        this.customerId = customerId;
        this.userId = userId;
        this.activationCode = activationCode;
    }

    public String getCommunicationPreferences() {
        return communicationPreferences;
    }

    public void setCommunicationPreferences(String communicationPreferences) {
        this.communicationPreferences = communicationPreferences;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessEmail() {
        return businessEmail;
    }

    public void setBusinessEmail(String businessEmail) {
        this.businessEmail = businessEmail;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getBusinessCategory() {
        return businessCategory;
    }

    public void setBusinessCategory(String businessCategory) {
        this.businessCategory = businessCategory;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getActivationStatus() {
        return activationStatus;
    }

    public void setActivationStatus(Boolean activationStatus) {
        this.activationStatus = activationStatus;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
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

    @XmlTransient
    public Collection<CustomerAddress> getCustomerAddressCollection() {
        return customerAddressCollection;
    }

    public void setCustomerAddressCollection(Collection<CustomerAddress> customerAddressCollection) {
        this.customerAddressCollection = customerAddressCollection;
    }

    @XmlTransient
    public Collection<CustomerBenefit> getCustomerBenefitCollection() {
        return customerBenefitCollection;
    }

    public void setCustomerBenefitCollection(Collection<CustomerBenefit> customerBenefitCollection) {
        this.customerBenefitCollection = customerBenefitCollection;
    }
    @XmlTransient
    public Collection<Tier> getTiers() {
        return tiers;
    }

    public void setTiers(Collection<Tier> tiers) {
        this.tiers = tiers;
    }
    @XmlTransient
    public Collection<SolicitationPackage> getSolicitationPackages() {
        return solicitationPackages;
    }

    public void setSolicitationPackages(Collection<SolicitationPackage> solicitationPackages) {
        this.solicitationPackages = solicitationPackages;
    }

    @XmlTransient
    public Collection<CustomerUser> getCustomerUserCollection() {
        return customerUserCollection;
    }

    public void setCustomerUserCollection(Collection<CustomerUser> customerUserCollection) {
        this.customerUserCollection = customerUserCollection;
    }
    @XmlTransient
    public Collection<DomainDetails> getDomainDetails() {
        return domainDetails;
    }

    public void setDomainDetails(Collection<DomainDetails> domainDetails) {
        this.domainDetails = domainDetails;
    }

    @XmlTransient
    public Collection<Packages> getPackages() {
        return packages;
    }

    public void setPackages(Collection<Packages> packages) {
        this.packages = packages;
    }

    public Organization getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Organization organizationId) {
        this.organizationId = organizationId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customerId != null ? customerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.customerId == null && other.customerId != null) || (this.customerId != null && !this.customerId.equals(other.customerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.syzegee.customer.events.service.Customer[ customerId=" + customerId + " ]";
    }

}
