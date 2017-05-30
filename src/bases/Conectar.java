/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author ped90
 */
public class Conectar{
   
    ParametrosBas jug = new ParametrosBas();
    ArrayList<ParametrosBas>lista = new ArrayList<>();

    private Connection conectar;
    ResultSet result;
    private final String ruta ="/home/ped90/Descargas/lista.db";
    
    public void cargaArray(){
        
            lista.add(new ParametrosBas(
            JOptionPane.showInputDialog("Inserta el Nombre"),
            JOptionPane.showInputDialog("Inserta el Apellido "),
            JOptionPane.showInputDialog("Inserta el DNI"),
            JOptionPane.showInputDialog("Inserta la Edad")));
            
    }
    
    public void driversBase(){
        
        try {
            Class.forName("org.sqlite.JDBC");
                    } catch (ClassNotFoundException ex) {
            System.out.println("DRIVERS NOT FOUND: "+ex.getMessage());
        }
        
    }
    
    public void conectarBase(){
        
        try {
            conectar = DriverManager.getConnection("jdbc:sqlite:"+ruta);
            System.out.println("La conexión se ha realizado correctamente a la ruta:"+ruta);
        } catch (SQLException ex) {
            System.out.println("Error de conexión verifique si la ruta está indicada correctamente: "+ex.getMessage()+" a "+ruta);
        }
        
    }
    
    public void insertarJugadores(){
        
        try {
            PreparedStatement ps = conectar.prepareStatement("Insert into Jugadores(Nombre, Apellidos, DNI, Edad) values(?,?,?,?)");
            ps.setString(1,lista.get(lista.size()-1).getNombre());
            ps.setString(2,lista.get(lista.size()-1).getApellidos());
            ps.setString(3,lista.get(lista.size()-1).getDni());
            ps.setString(4,lista.get(lista.size()-1).getEdad());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("Fallo del sistema al insertar :"+ex.getMessage());
        }
        
    }
    
    public void visualizarJugadores(){
        
        try {
            PreparedStatement ver = conectar.prepareStatement("Select * from Jugadores");
           result = ver.executeQuery();
           while(result.next()){
               System.out.println("Nombre  "+":"+ result.getString("Nombre"));
               System.out.println("Apellidos "+" :"+result.getString("Apellidos"));
               System.out.println("dni"+" :"+result.getString("DNI"));
               System.out.println("edad"+" :"+result.getInt("Edad"));
               System.out.println();
           }
        } catch (SQLException ex) {
            System.out.println("Error al leer  la Base de Datos: "+ex.getMessage());
        }
        
    }
    
    public void borrarJugador(){
        
        Integer reg=Integer.parseInt(JOptionPane.showInputDialog("Inserte el número del DNI para borrar la fila correspondiente:")); 
        try{ 
            Statement st = conectar.createStatement(); 
            st.execute("delete from Jugadores where dni="+reg.toString()); 
            System.out.println("Fila borrada con éxito"); 
        }catch(SQLException ex){ 
            System.out.println("Error al borrar la fila, compruebe que ha introducido bien el DNI: "+ex.getMessage()); 
        }
        
    }
    
    public void actualizarJugador(){ 
        Integer reg=Integer.parseInt(JOptionPane.showInputDialog("Inserte el número del DNI para actualizar la fila correspondiente:")); 
        String nom=JOptionPane.showInputDialog("Inserte el nombre a actualizar:"); 
        String ape=JOptionPane.showInputDialog("Inserte el apellido a actualizar:"); 
        String dni=JOptionPane.showInputDialog("Inserte el DNI a actualizar:"); 
        String eda=JOptionPane.showInputDialog("Inserte la edad a actualizar:"); 
       try{ 
            PreparedStatement actualiza = conectar.prepareStatement("update jugadores set nombre='"+nom+"',apellidos='"+ape+"',dni='"+dni+"',edad='"+eda+"'where dni="+reg.toString());
                actualiza.execute();
                System.out.println("Registro actualizado"); 
        }catch(SQLException ex){ 
            System.out.println("Error al actualizar el registro, verifique que ha introducido bien los datos a actualizar: "+ex.getMessage());
        }
       
    }
    
    public void cerrarBase(){
        
        try {
                conectar.close();
                System.out.println("Seguridad: Base de datos cerrada con éxito.");
            } catch (SQLException ex) {
                Logger.getLogger(Conectar.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
     
}

