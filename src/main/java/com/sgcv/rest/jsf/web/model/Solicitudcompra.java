/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgcv.rest.jsf.web.model;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alvarenga
 */
@Entity
@Table(name = "solicitudcompra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Solicitudcompra.findAll", query = "SELECT s FROM Solicitudcompra s"),
    @NamedQuery(name = "Solicitudcompra.findById", query = "SELECT s FROM Solicitudcompra s WHERE s.id = :id"),
    @NamedQuery(name = "Solicitudcompra.findByFecha", query = "SELECT s FROM Solicitudcompra s WHERE s.fecha = :fecha"),
    @NamedQuery(name = "Solicitudcompra.findByHora", query = "SELECT s FROM Solicitudcompra s WHERE s.hora = :hora")})
public class Solicitudcompra implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="solicitud_id_seq")
    @SequenceGenerator(name="solicitud_id_seq", sequenceName="solicitud_id_seq", allocationSize=1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @JoinColumn(name = "producto", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Producto producto;

    public Solicitudcompra() {
    }

    public Solicitudcompra(Integer id) {
        this.id = id;
    }

    public Solicitudcompra(Integer id, Date fecha, Date hora) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
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
        if (!(object instanceof Solicitudcompra)) {
            return false;
        }
        Solicitudcompra other = (Solicitudcompra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sgcv.rest.jsf.web.model.Solicitudcompra[ id=" + id + " ]";
    }
    
}
