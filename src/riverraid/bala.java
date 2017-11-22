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
 * Clase bala (Imagen)
 */

public class bala {
/**
 * Variable bal donde se guardara la imagen
 * Variable boolean disparo para saber si hay un disparo ejecutandose
 * Variables enteras posx, posy para inicilaizar la posicion donde sestara el disparo 
 */
    Image bal;
    boolean disparo;
    int posx,posy;
/**
 * Constructor donde se inicializan los valores y se carga la imagen
 * Excepcion IOException por si no se encuentra la imagen
 */
    public bala()
    {
        disparo=false;
        posy=-100;
        posx=-100;
        try {
            bal=ImageIO.read(new File("src/imagenes/bala.png"));
        } catch (IOException ex) {
            System.out.println("no encontrada la imagen de bala");
        }
    }
/**
 * Metodo que calcula el area de un rectangulo del tamaño de la imagen  
 * Retorna un nuevo Rectangle2D con las dimensiones
 */
    Rectangle2D getarea()
    {
        return new Rectangle2D.Double(posx+9, posy, bal.getWidth(null), bal.getHeight(null));
        
    }
}
