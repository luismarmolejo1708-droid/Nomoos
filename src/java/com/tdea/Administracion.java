/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tdea;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ESTUDIANTE
 */
@Entity
@Table(name = "ADMINISTRACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Administracion.findAll", query = "SELECT a FROM Administracion a")
    , @NamedQuery(name = "Administracion.findByIdTrabajador", query = "SELECT a FROM Administracion a WHERE a.idTrabajador = :idTrabajador")
    , @NamedQuery(name = "Administracion.findByNombre", query = "SELECT a FROM Administracion a WHERE a.nombre = :nombre")
    , @NamedQuery(name = "Administracion.findByCargo", query = "SELECT a FROM Administracion a WHERE a.cargo = :cargo")
    , @NamedQuery(name = "Administracion.findByTelefono", query = "SELECT a FROM Administracion a WHERE a.telefono = :telefono")
    , @NamedQuery(name = "Administracion.findBySede", query = "SELECT a FROM Administracion a WHERE a.sede = :sede")
    , @NamedQuery(name = "Administracion.findByCorreo", query = "SELECT a FROM Administracion a WHERE a.correo = :correo")})
public class Administracion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TRABAJADOR")
    private Integer idTrabajador;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "CARGO")
    private String cargo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "TELEFONO")
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "SEDE")
    private String sede;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CORREO")
    private String correo;

    public Administracion() {
    }

    public Administracion(Integer idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public Administracion(Integer idTrabajador, String nombre, String cargo, String telefono, String sede, String correo) {
        this.idTrabajador = idTrabajador;
        this.nombre = nombre;
        this.cargo = cargo;
        this.telefono = telefono;
        this.sede = sede;
        this.correo = correo;
    }

    public Integer getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(Integer idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTrabajador != null ? idTrabajador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Administracion)) {
            return false;
        }
        Administracion other = (Administracion) object;
        if ((this.idTrabajador == null && other.idTrabajador != null) || (this.idTrabajador != null && !this.idTrabajador.equals(other.idTrabajador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tdea.Administracion[ idTrabajador=" + idTrabajador + " ]";
    }
    
}
