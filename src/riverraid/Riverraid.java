/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riverraid;

/**
 *
 * @author Gerardo, Daniel
 */

/**
 * Clase Riverraid Principal (Main)
 */
 
public class Riverraid {

    /**
     * @param args the command line arguments
     */
    
/**
 * Main que pone a correr el proyecto, llamando al constructor de la clase frame
 * Y imprimiendo en consola los punatjes que obtiende depende de lo que vayas destruyendo para saber a medida que se va anavanzando en el juego
 */   
    
    public static void main(String[] args) {
        // TODO code application logic here
        new frame();
        System.out.println(" ");
        System.out.println("Disparar Enemigos, Puntos: +20");
        System.out.println("Disparar Puentes, Puntos: +10");
        System.out.println("Disparar Gasolina, Puntos: -10");
      
    } 
}
