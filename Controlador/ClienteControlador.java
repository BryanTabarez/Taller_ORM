/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Persistencia.Conexion;
import Persistencia.PizzabaseJpaController;
import Modelo.Pizzabase;
import Vista.Vista_Cliente;

/**
 *
 * @author bryanstm
 */
public class ClienteControlador {
    
    private Conexion conn;
    private PizzabaseJpaController pbc;
    private Vista_Cliente vistaCliente;
    
    public ClienteControlador(Vista_Cliente ven){
        conn = new Conexion();
        pbc = new PizzabaseJpaController(conn.getConn());
        vistaCliente = ven;
    }
    
    
}
