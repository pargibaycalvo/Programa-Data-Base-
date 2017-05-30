/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bases;

import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author ped90
 */
public class Bases {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        // TODO code application logic here
        Conectar obx = new Conectar();

         int opcion;
         do{
         opcion= Integer.parseInt(JOptionPane.showInputDialog("1) Conexión de la Base de Datos. \n2) Insertar Jugador nuevo. \n3) Visualizar Jugadores."
                                                                                                 + " \n4) Borrar Jugador.  \n5) Actualizar Jugador.  \n6) Cerrar Base de datos. \n0) Salir del programa."));
         switch(opcion){
            case 1:
                obx.conectarBase();
                obx.driversBase();
                break;
            case 2:
               obx.cargaArray();
               obx.insertarJugadores();
                break;
            case 3:
                obx.visualizarJugadores();
                break;
            case 4:
                obx.borrarJugador();
                break;
            case 5:
                obx.actualizarJugador();
                break;
            case 6:
                obx.cerrarBase();
                break;
            case 0:
                JOptionPane.showMessageDialog(null,"Pulse para salir del programa");
                System.exit(0); 
                break;
            default:
                JOptionPane.showMessageDialog(null,"Error");
        }
    }while(opcion!=0);
    }
        
    
}
      
    
   
