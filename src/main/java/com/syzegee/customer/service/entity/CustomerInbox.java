/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syzegee.customer.service.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author WildJasmine
 */
@Entity
@Table(name = "customer_inbox")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerInbox.findAll", query = "SELECT c FROM CustomerInbox c")
    , @NamedQuery(name = "CustomerInbox.findByCustomerInboxId", query = "SELECT c FROM CustomerInbox c WHERE c.customerInboxId = :customerInboxId")
    , @NamedQuery(name = "CustomerInbox.findByCustomerId", query = "SELECT c FROM CustomerInbox c WHERE c.customerId = :customerId")
    , @NamedQuery(name = "CustomerInbox.findByCorrelationId", query = "SELECT c FROM CustomerInbox c WHERE c.correlationId = :correlationId")
    , @NamedQuery(name = "CustomerInbox.findByCustomerRequestId", query = "SELECT c FROM CustomerInbox c WHERE c.customerRequestId = :customerRequestId")
    , @NamedQuery(name = "CustomerInbox.findByStatus", query = "SELECT c FROM CustomerInbox c WHERE c.status = :status")
    , @NamedQuery(name = "CustomerInbox.findByDetails", query = "SELECT c FROM CustomerInbox c WHERE c.details = :details")})
public class CustomerInbox implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "customer_inbox_id")
    private Long customerInboxId;
    @Basic(optional = false)
    @Column(name = "customer_id")
    private long customerId;
    @Size(max = 120)
    @Column(name = "correlation_id")
    private String correlationId;
    @Size(max = 120)
    @Column(name = "customer_request_id")
    private String customerRequestId;
    @Size(max = 120)
    @Column(name = "status")
    private String status;
    @Size(max = 120)
    @Column(name = "details")
    private String details;

    public CustomerInbox() {
    }

    public CustomerInbox(Long customerInboxId) {
        this.customerInboxId = customerInboxId;
    }

    public CustomerInbox(Long customerInboxId, long customerId) {
        this.customerInboxId = customerInboxId;
        this.customerId = customerId;
    }

    public Long getCustomerInboxId() {
        return customerInboxId;
    }

    public void setCustomerInboxId(Long customerInboxId) {
        this.customerInboxId = customerInboxId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getCustomerRequestId() {
        return customerRequestId;
    }

    public void setCustomerRequestId(String customerRequestId) {
        this.customerRequestId = customerRequestId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customerInboxId != null ? customerInboxId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerInbox)) {
            return false;
        }
        CustomerInbox other = (CustomerInbox) object;
        if ((this.customerInboxId == null && other.customerInboxId != null) || (this.customerInboxId != null && !this.customerInboxId.equals(other.customerInboxId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.syzegee.customer.service.entity.CustomerInbox[ customerInboxId=" + customerInboxId + " ]";
    }
    
}
