/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author georgerr
 */
public class Conexion {
    
    private EntityManagerFactory conn;
    
    public Conexion (){
        
        conn = Persistence.createEntityManagerFactory("PizzeriaPU");
        
    }
    
    public EntityManagerFactory getConn(){
        
        return conn;
    }
    
    
}
