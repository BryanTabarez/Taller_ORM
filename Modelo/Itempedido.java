/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    @NamedQuery(name = "Itempedido.findAll", query = "SELECT i FROM Itempedido i"),
    @NamedQuery(name = "Itempedido.findByPizzaId", query = "SELECT i FROM Itempedido i WHERE i.itempedidoPK.pizzaId = :pizzaId"),
    @NamedQuery(name = "Itempedido.findByIngredienteId", query = "SELECT i FROM Itempedido i WHERE i.itempedidoPK.ingredienteId = :ingredienteId")})
public class Itempedido implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ItempedidoPK itempedidoPK;
    @JoinColumn(name = "pizza_id", referencedColumnName = "pizza_id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pizzabase pizzabase;
    @JoinColumn(name = "ingrediente_id", referencedColumnName = "ingrediente_id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ingredienteadicional ingredienteadicional;
    @JoinColumn(name = "factura_id", referencedColumnName = "factura_id", nullable = false)
    @ManyToOne(optional = false)
    private Factura facturaId;

    public Itempedido() {
    }

    public Itempedido(ItempedidoPK itempedidoPK) {
        this.itempedidoPK = itempedidoPK;
    }

    public Itempedido(int pizzaId, int ingredienteId) {
        this.itempedidoPK = new ItempedidoPK(pizzaId, ingredienteId);
    }

    public ItempedidoPK getItempedidoPK() {
        return itempedidoPK;
    }

    public void setItempedidoPK(ItempedidoPK itempedidoPK) {
        this.itempedidoPK = itempedidoPK;
    }

    public Pizzabase getPizzabase() {
        return pizzabase;
    }

    public void setPizzabase(Pizzabase pizzabase) {
        this.pizzabase = pizzabase;
    }

    public Ingredienteadicional getIngredienteadicional() {
        return ingredienteadicional;
    }

    public void setIngredienteadicional(Ingredienteadicional ingredienteadicional) {
        this.ingredienteadicional = ingredienteadicional;
    }

    public Factura getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(Factura facturaId) {
        this.facturaId = facturaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itempedidoPK != null ? itempedidoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Itempedido)) {
            return false;
        }
        Itempedido other = (Itempedido) object;
        if ((this.itempedidoPK == null && other.itempedidoPK != null) || (this.itempedidoPK != null && !this.itempedidoPK.equals(other.itempedidoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Itempedido[ itempedidoPK=" + itempedidoPK + " ]";
    }
    
}
