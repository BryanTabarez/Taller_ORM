/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Persistencia.Conexion;
import Persistencia.PizzabaseJpaController;
import Modelo.Pizzabase;
import Vista.Vista_Administrador;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bryanstm
 */
public class PizzabaseControlador {
    
    private Conexion conn;
    private PizzabaseJpaController pbc;
    private Vista_Administrador vistaAdmin;
    
    public PizzabaseControlador(Vista_Administrador ven){
        conn = new Conexion();
        pbc = new PizzabaseJpaController(conn.getConn());
        vistaAdmin = ven;
    }
    
    public boolean IngresarPizzabase(Integer pizzaId, String nombre, String tamanio, String presentacion, double precio){
        Pizzabase nuevaPizzabase = new Pizzabase(pizzaId, nombre, tamanio, presentacion, precio);
        
        boolean result = false;
        
        try{
            pbc.create(nuevaPizzabase);
            result = true;
        }
        catch(Exception ex){
            Logger.getLogger(PizzabaseControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    // Sin indicar el id
//    public boolean IngresarPizzabase(String nombre, String tamanio, String presentacion, double precio){
//        Pizzabase nuevaPizzabase = new Pizzabase(nombre, tamanio, presentacion, precio);
//        
//        
//    }
//    
    
    
}

