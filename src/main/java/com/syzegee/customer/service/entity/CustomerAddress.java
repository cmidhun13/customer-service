/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syzegee.customer.service.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author WildJasmine
 */
@Entity
@Table(name = "customer_address")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerAddress.findAll", query = "SELECT c FROM CustomerAddress c")
    , @NamedQuery(name = "CustomerAddress.findByCustomerAddressId", query = "SELECT c FROM CustomerAddress c WHERE c.customerAddressId = :customerAddressId")
    , @NamedQuery(name = "CustomerAddress.findByState", query = "SELECT c FROM CustomerAddress c WHERE c.state = :state")
    , @NamedQuery(name = "CustomerAddress.findByAddressLine1", query = "SELECT c FROM CustomerAddress c WHERE c.addressLine1 = :addressLine1")
    , @NamedQuery(name = "CustomerAddress.findByAddressLine2", query = "SELECT c FROM CustomerAddress c WHERE c.addressLine2 = :addressLine2")
    , @NamedQuery(name = "CustomerAddress.findByAddressLine3", query = "SELECT c FROM CustomerAddress c WHERE c.addressLine3 = :addressLine3")
    , @NamedQuery(name = "CustomerAddress.findByCity", query = "SELECT c FROM CustomerAddress c WHERE c.city = :city")
    , @NamedQuery(name = "CustomerAddress.findBySate", query = "SELECT c FROM CustomerAddress c WHERE c.sate = :sate")
    , @NamedQuery(name = "CustomerAddress.findByZipCode", query = "SELECT c FROM CustomerAddress c WHERE c.zipCode = :zipCode")
    , @NamedQuery(name = "CustomerAddress.findByCountryCode", query = "SELECT c FROM CustomerAddress c WHERE c.countryCode = :countryCode")
    , @NamedQuery(name = "CustomerAddress.findByIsActive", query = "SELECT c FROM CustomerAddress c WHERE c.isActive = :isActive")
    , @NamedQuery(name = "CustomerAddress.findByCorrelationId", query = "SELECT c FROM CustomerAddress c WHERE c.correlationId = :correlationId")
    , @NamedQuery(name = "CustomerAddress.findByCreatedBy", query = "SELECT c FROM CustomerAddress c WHERE c.createdBy = :createdBy")
    , @NamedQuery(name = "CustomerAddress.findByCreatedDate", query = "SELECT c FROM CustomerAddress c WHERE c.createdDate = :createdDate")
    , @NamedQuery(name = "CustomerAddress.findByUpdatedBy", query = "SELECT c FROM CustomerAddress c WHERE c.updatedBy = :updatedBy")
    , @NamedQuery(name = "CustomerAddress.findByUpdatedDate", query = "SELECT c FROM CustomerAddress c WHERE c.updatedDate = :updatedDate")})
public class CustomerAddress implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "customer_address_id")
    private Long customerAddressId;
    @Size(max = 120)
    @Column(name = "state")
    private String state;
    @Size(max = 120)
    @Column(name = "address_line1")
    private String addressLine1;
    @Size(max = 120)
    @Column(name = "address_line2")
    private String addressLine2;
    @Size(max = 120)
    @Column(name = "address_line3")
    private String addressLine3;
    @Size(max = 120)
    @Column(name = "city")
    private String city;
    @Size(max = 120)
    @Column(name = "sate")
    private String sate;
    @Size(max = 120)
    @Column(name = "zip_code")
    private String zipCode;
    @Size(max = 120)
    @Column(name = "country_code")
    private String countryCode;
    @Column(name = "is_active")
    private Boolean isActive;
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
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    @ManyToOne(optional = false)
    private Customer customerId;

    public CustomerAddress() {
    }

    public CustomerAddress(Long customerAddressId) {
        this.customerAddressId = customerAddressId;
    }

    public Long getCustomerAddressId() {
        return customerAddressId;
    }

    public void setCustomerAddressId(Long customerAddressId) {
        this.customerAddressId = customerAddressId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSate() {
        return sate;
    }

    public void setSate(String sate) {
        this.sate = sate;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
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
        hash += (customerAddressId != null ? customerAddressId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerAddress)) {
            return false;
        }
        CustomerAddress other = (CustomerAddress) object;
        if ((this.customerAddressId == null && other.customerAddressId != null) || (this.customerAddressId != null && !this.customerAddressId.equals(other.customerAddressId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.syzegee.customer.service.entity.CustomerAddress[ customerAddressId=" + customerAddressId + " ]";
    }
    
}
