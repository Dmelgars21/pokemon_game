/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.grupo_games.pokemon_game.db;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author xepux
 */
public class Db_local {
    protected Connection conexion;
    private String url="jdbc:mysql://127.0.0.1:3306/pokemon_game";
    private String user="root";
    private String ps="mireya12";
    
    public Db_local(){
        try{
            conexion=DriverManager.getConnection(url, user,ps);
            if(conexion!=null)
               System.out.println("Conexion Exitosa");            
        }catch(Exception e){
            System.out.println("Error en conexion"+e.getMessage());
        }
        
    }

    public Connection getConexion() {
        return conexion;
    }
    
    public void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Conexión cerrada");
            }
        } catch (Exception e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
    
    
    
}
