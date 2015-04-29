/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author bryanstm
 */
@Embeddable
public class ItempedidoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "pizza_id", nullable = false)
    private int pizzaId;
    @Basic(optional = false)
    @Column(name = "ingrediente_id", nullable = false)
    private int ingredienteId;

    public ItempedidoPK() {
    }

    public ItempedidoPK(int pizzaId, int ingredienteId) {
        this.pizzaId = pizzaId;
        this.ingredienteId = ingredienteId;
    }

    public int getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(int pizzaId) {
        this.pizzaId = pizzaId;
    }

    public int getIngredienteId() {
        return ingredienteId;
    }

    public void setIngredienteId(int ingredienteId) {
        this.ingredienteId = ingredienteId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) pizzaId;
        hash += (int) ingredienteId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItempedidoPK)) {
            return false;
        }
        ItempedidoPK other = (ItempedidoPK) object;
        if (this.pizzaId != other.pizzaId) {
            return false;
        }
        if (this.ingredienteId != other.ingredienteId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.ItempedidoPK[ pizzaId=" + pizzaId + ", ingredienteId=" + ingredienteId + " ]";
    }
    
}
