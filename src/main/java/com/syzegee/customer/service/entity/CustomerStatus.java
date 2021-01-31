package com.syzegee.customer.service.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
@Entity
@Data
@Table(name = "customer_status")

public class CustomerStatus  implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "customer_status_id")
    private Long customerStatusId;

    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    @ManyToOne(optional = false)
    private Customer customerId;

    @Column(name = "correlation_id")
    private String correlationId;
    @Column(name = "on_boarding_status")
    private Boolean onBoardStatus;

    @Column(name = "marketing_automation_status")
    private Boolean marketingAutomationStatus;
    @Column(name = "cms_site_update_status")
    private Boolean cmsSetUpStatus;


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customerStatusId != null ? customerStatusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerAddress)) {
            return false;
        }
        CustomerStatus other = (CustomerStatus) object;
        if ((this.customerStatusId == null && other.customerStatusId != null) || (this.customerStatusId != null && !this.customerStatusId.equals(other.customerStatusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.syzegee.customer.service.entity.CustomerStatus[ customerAddressId=" + customerStatusId + " ]";
    }

}
