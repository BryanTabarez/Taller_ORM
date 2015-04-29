/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bryanstm
 */
@Entity
@Table(catalog = "bryanstm", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vegetal.findAll", query = "SELECT v FROM Vegetal v"),
    @NamedQuery(name = "Vegetal.findByIngredienteId", query = "SELECT v FROM Vegetal v WHERE v.ingredienteId = :ingredienteId"),
    @NamedQuery(name = "Vegetal.findByNombre", query = "SELECT v FROM Vegetal v WHERE v.nombre = :nombre"),
    @NamedQuery(name = "Vegetal.findByPrecioporcion", query = "SELECT v FROM Vegetal v WHERE v.precioporcion = :precioporcion"),
    @NamedQuery(name = "Vegetal.findByCantidad", query = "SELECT v FROM Vegetal v WHERE v.cantidad = :cantidad"),
    @NamedQuery(name = "Vegetal.findByTipo", query = "SELECT v FROM Vegetal v WHERE v.tipo = :tipo"),
    @NamedQuery(name = "Vegetal.findByVegetalId", query = "SELECT v FROM Vegetal v WHERE v.vegetalId = :vegetalId"),
    @NamedQuery(name = "Vegetal.findByCarbohidratos", query = "SELECT v FROM Vegetal v WHERE v.carbohidratos = :carbohidratos")})
public class Vegetal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "ingrediente_id", nullable = false)
    private int ingredienteId;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String nombre;
    @Basic(optional = false)
    @Column(nullable = false, length = 20)
    private String precioporcion;
    @Basic(optional = false)
    @Column(nullable = false)
    private int cantidad;
    @Basic(optional = false)
    @Column(nullable = false, length = 20)
    private String tipo;
    @Lob
    private byte[] foto;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "vegetal_id", nullable = false)
    private Integer vegetalId;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String carbohidratos;

    public Vegetal() {
    }

    public Vegetal(Integer vegetalId) {
        this.vegetalId = vegetalId;
    }

    public Vegetal(Integer vegetalId, int ingredienteId, String nombre, String precioporcion, int cantidad, String tipo, String carbohidratos) {
        this.vegetalId = vegetalId;
        this.ingredienteId = ingredienteId;
        this.nombre = nombre;
        this.precioporcion = precioporcion;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.carbohidratos = carbohidratos;
    }

    public int getIngredienteId() {
        return ingredienteId;
    }

    public void setIngredienteId(int ingredienteId) {
        this.ingredienteId = ingredienteId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecioporcion() {
        return precioporcion;
    }

    public void setPrecioporcion(String precioporcion) {
        this.precioporcion = precioporcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public Integer getVegetalId() {
        return vegetalId;
    }

    public void setVegetalId(Integer vegetalId) {
        this.vegetalId = vegetalId;
    }

    public String getCarbohidratos() {
        return carbohidratos;
    }

    public void setCarbohidratos(String carbohidratos) {
        this.carbohidratos = carbohidratos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vegetalId != null ? vegetalId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vegetal)) {
            return false;
        }
        Vegetal other = (Vegetal) object;
        if ((this.vegetalId == null && other.vegetalId != null) || (this.vegetalId != null && !this.vegetalId.equals(other.vegetalId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Vegetal[ vegetalId=" + vegetalId + " ]";
    }
    
}
