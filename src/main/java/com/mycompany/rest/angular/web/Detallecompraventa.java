/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.rest.angular.web;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rodrigo
 */
@Entity
@Table(name = "detallecompraventa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detallecompraventa.findAll", query = "SELECT d FROM Detallecompraventa d"),
    @NamedQuery(name = "Detallecompraventa.findById", query = "SELECT d FROM Detallecompraventa d WHERE d.id = :id"),
    @NamedQuery(name = "Detallecompraventa.findByCantidad", query = "SELECT d FROM Detallecompraventa d WHERE d.cantidad = :cantidad")})
public class Detallecompraventa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
    @JoinColumn(name = "compraventa", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Compraventa compraventa;
    @JoinColumn(name = "producto", referencedColumnName = "id")
    @ManyToOne
    private Producto producto;

    public Detallecompraventa() {
    }

    public Detallecompraventa(Integer id) {
        this.id = id;
    }

    public Detallecompraventa(Integer id, int cantidad) {
        this.id = id;
        this.cantidad = cantidad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Compraventa getCompraventa() {
        return compraventa;
    }

    public void setCompraventa(Compraventa compraventa) {
        this.compraventa = compraventa;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
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
        if (!(object instanceof Detallecompraventa)) {
            return false;
        }
        Detallecompraventa other = (Detallecompraventa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.rest.angular.web.Detallecompraventa[ id=" + id + " ]";
    }
    
}
