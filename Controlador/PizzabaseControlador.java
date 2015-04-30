/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Persistencia.Conexion;
import Persistencia.PizzabaseJpaController;
import Modelo.Pizzabase;
import Vista.Vista_Administrador1;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bryanstm
 */
public class PizzabaseControlador {
    
    private Conexion conn;
    private PizzabaseJpaController pbc;
    private Vista_Administrador1 vistaAdmin;
    
    public PizzabaseControlador(Vista_Administrador1 ven){
        conn = new Conexion();
        pbc = new PizzabaseJpaController(conn.getConn());
        vistaAdmin = ven;
    }
    
    public boolean IngresarPizzabase(byte[] foto, Integer pizzaId, String nombre, String tamanio, String presentacion, double precio){
        Pizzabase nuevaPizzabase = new Pizzabase(pizzaId, nombre, tamanio, presentacion, precio);
        nuevaPizzabase.setFoto(foto);
        
        boolean result = false;
        
        try{
            pbc.create(nuevaPizzabase);
            result = true;
        }
        catch(Exception ex){
            Logger.getLogger(PizzabaseControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (result){
            System.out.print("TRUE METODO IngresarPizzabase()");
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

    public void setImagen(byte[] buffer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}

