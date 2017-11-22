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
 * Clase agua que hereda de mapa (Imagen)
 */
public class agua extends mapa{
 /**
 * Variable donde se guardara la imagen
 */
    Image agu;
 /**
 * constructor de la clase donde se carga la imagen del agua 
 * Excepcion IOException por si no se encuentra la imagen
 */
    public agua()
    {
        posx=0;
        posy=0;
        try {
            agu=ImageIO.read(new File("src/imagenes/agua.png"));
        } catch (IOException ex) {
             System.out.println("No se encuentra la imagen");
        }
    }
 /**
 * Metodo que calcula el area de un rectangulo del tamaño de la imagen
 * Retorna un nuevo Rectangle2D con las dimensiones
 */
    Rectangle2D getarea()
    {   return new Rectangle2D.Double(posx,posy,agu.getWidth(null),agu.getHeight(null));
}
}
