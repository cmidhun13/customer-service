/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syzegee.customer.service.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * @author WildJasmine
 */
@Entity
@Table(name = "packages")
@Data
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Packages.findAll", query = "SELECT p FROM Packages p")
        , @NamedQuery(name = "Packages.findByPackageId", query = "SELECT p FROM Packages p WHERE p.packageId = :packageId")
        , @NamedQuery(name = "Packages.findByPackageName", query = "SELECT p FROM Packages p WHERE p.packageName = :packageName")
        , @NamedQuery(name = "Packages.findByIsActive", query = "SELECT p FROM Packages p WHERE p.isActive = :isActive")
        , @NamedQuery(name = "Packages.findByCorrelationId", query = "SELECT p FROM Packages p WHERE p.correlationId = :correlationId")
        , @NamedQuery(name = "Packages.findByState", query = "SELECT p FROM Packages p WHERE p.state = :state")
        , @NamedQuery(name = "Packages.findByCreatedBy", query = "SELECT p FROM Packages p WHERE p.createdBy = :createdBy")
        , @NamedQuery(name = "Packages.findByCreatedDate", query = "SELECT p FROM Packages p WHERE p.createdDate = :createdDate")
        , @NamedQuery(name = "Packages.findByUpdatedBy", query = "SELECT p FROM Packages p WHERE p.updatedBy = :updatedBy")
        , @NamedQuery(name = "Packages.findByUpdatedDate", query = "SELECT p FROM Packages p WHERE p.updatedDate = :updatedDate")})
public class Packages implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "package_id")
    private Long packageId;
    @Size(max = 120)
    @Column(name = "package_name")
    private String packageName;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "packageId")
    private Collection<PackagesBenefit> packagesBenefits;
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    @ManyToOne(optional = false)
    private Customer customerId;
    @JoinColumn(name = "tier_id", referencedColumnName = "tier_id")
    @ManyToOne(optional = false)
    private Tier tierId;

    @Override
    public String toString() {
        return "com.syzegee.customer.service.entity.Packages[ packageId=" + packageId + " ]";
    }

}
