/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riverraid;
/**
 * Librerias utilizadas en la clase  
 */
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Gerardo, Daniel
 */

/**
 * Clase personaje (Imagen)
 */
 
public class personaje {
/**
 * Variable tipo Image donde se guardaran las imagen seleccionada (perso)
 * Variables enteras posx, posy, que me diran en que posicion en x y y se pintara la imagen
 * Variable entera vidas que me dice cuantas vidas tiene mi personaje 
 */        
    int posx,posy;
    int vidas;
    Image perso;
/**
 * Constructor de la clase 
 * Que inicializa las variables y carga la imagen
 * Excepcion IOException si no se encuentra la imagen
 */    
    personaje()
    {
        vidas=3;
        posx=270;
        posy=520;
        try {
            perso= ImageIO.read(new File("src/imagenes/avionsito.png"));
        } catch (IOException ex) {
            System.out.println("imagen personajes no encontrada");
        }
    }
/**
 * Metodo que calcula el area de un rectangulo del tamaño de la imagen  
 * Retorna un nuevo Rectangle2D con las dimensiones
 */           
    Rectangle2D getarea()
    {
        return new Rectangle2D.Double(posx,posy,perso.getWidth(null),perso.getHeight(null));
        
    }
}
