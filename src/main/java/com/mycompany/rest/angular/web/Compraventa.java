/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.rest.angular.web;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author rodrigo
 */
@Entity
@Table(name = "compraventa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Compraventa.findAll", query = "SELECT c FROM Compraventa c"),
    @NamedQuery(name = "Compraventa.findById", query = "SELECT c FROM Compraventa c WHERE c.id = :id"),
    @NamedQuery(name = "Compraventa.findByDeudaTotal", query = "SELECT c FROM Compraventa c WHERE c.deudaTotal = :deudaTotal"),
    @NamedQuery(name = "Compraventa.findBySaldo", query = "SELECT c FROM Compraventa c WHERE c.saldo = :saldo"),
    @NamedQuery(name = "Compraventa.findByPago", query = "SELECT c FROM Compraventa c WHERE c.pago = :pago")})
public class Compraventa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "deuda_total")
    private int deudaTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "saldo")
    private int saldo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pago")
    private int pago;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compraventa")
    private Collection<Detallecompraventa> detallecompraventaCollection;
    @JoinColumn(name = "cliente", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cliente cliente;
    @JoinColumn(name = "proveedor", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Proveedor proveedor;
    @OneToMany(mappedBy = "compraventa")
    private Collection<Pago> pagoCollection;

    public Compraventa() {
    }

    public Compraventa(Integer id) {
        this.id = id;
    }

    public Compraventa(Integer id, int deudaTotal, int saldo, int pago) {
        this.id = id;
        this.deudaTotal = deudaTotal;
        this.saldo = saldo;
        this.pago = pago;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getDeudaTotal() {
        return deudaTotal;
    }

    public void setDeudaTotal(int deudaTotal) {
        this.deudaTotal = deudaTotal;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public int getPago() {
        return pago;
    }

    public void setPago(int pago) {
        this.pago = pago;
    }

    @XmlTransient
    public Collection<Detallecompraventa> getDetallecompraventaCollection() {
        return detallecompraventaCollection;
    }

    public void setDetallecompraventaCollection(Collection<Detallecompraventa> detallecompraventaCollection) {
        this.detallecompraventaCollection = detallecompraventaCollection;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    @XmlTransient
    public Collection<Pago> getPagoCollection() {
        return pagoCollection;
    }

    public void setPagoCollection(Collection<Pago> pagoCollection) {
        this.pagoCollection = pagoCollection;
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
        if (!(object instanceof Compraventa)) {
            return false;
        }
        Compraventa other = (Compraventa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.rest.angular.web.Compraventa[ id=" + id + " ]";
    }
    
}
