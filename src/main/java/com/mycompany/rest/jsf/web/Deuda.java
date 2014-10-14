/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.rest.jsf.web;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author alvarenga
 */
@Entity
@Table(name = "deuda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Deuda.findAll", query = "SELECT d FROM Deuda d"),
    @NamedQuery(name = "Deuda.findById", query = "SELECT d FROM Deuda d WHERE d.id = :id"),
    @NamedQuery(name = "Deuda.findByFechaCreacion", query = "SELECT d FROM Deuda d WHERE d.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "Deuda.findByMontoTotal", query = "SELECT d FROM Deuda d WHERE d.montoTotal = :montoTotal"),
    @NamedQuery(name = "Deuda.findByMontoPagado", query = "SELECT d FROM Deuda d WHERE d.montoPagado = :montoPagado"),
    @NamedQuery(name = "Deuda.findBySaldo", query = "SELECT d FROM Deuda d WHERE d.saldo = :saldo")})
public class Deuda implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "monto_total")
    private int montoTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "monto_pagado")
    private int montoPagado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "saldo")
    private int saldo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "deuda")
    private Collection<Pago> pagoCollection;
    @JoinColumn(name = "compra", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Compra compra;

    public Deuda() {
    }

    public Deuda(Integer id) {
        this.id = id;
    }

    public Deuda(Integer id, Date fechaCreacion, int montoTotal, int montoPagado, int saldo) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.montoTotal = montoTotal;
        this.montoPagado = montoPagado;
        this.saldo = saldo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(int montoTotal) {
        this.montoTotal = montoTotal;
    }

    public int getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(int montoPagado) {
        this.montoPagado = montoPagado;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Pago> getPagoCollection() {
        return pagoCollection;
    }

    public void setPagoCollection(Collection<Pago> pagoCollection) {
        this.pagoCollection = pagoCollection;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Deuda)) {
            return false;
        }
        Deuda other = (Deuda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.rest.jsf.web.Deuda[ id=" + id + " ]";
    }
    
}
