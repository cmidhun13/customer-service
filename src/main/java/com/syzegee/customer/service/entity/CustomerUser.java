/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syzegee.customer.service.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;


@Builder
@Data
@Entity
@Table(name = "customer_user")
@XmlRootElement
public class CustomerUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "customer_user_id")
    private Long customerUserId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "customer_id")
    private Long customerId;
    @Size(max = 120)
    @Column(name = "email_id")
    private String emailId;
    @Size(max = 120)
    @Column(name = "first_name")
    private String firstName;
    @Size(max = 120)
    @Column(name = "last_name")
    private String lastName;
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

    @Tolerate
    public CustomerUser() {
    }
    @Tolerate
    public CustomerUser(Long customerUserId) {
        this.customerUserId = customerUserId;
    }

    @Override
    public String toString() {
        return "com.syzegee.customer.service.entity.CustomerUser[ customerUserId=" + customerUserId + " ]";
    }

}
