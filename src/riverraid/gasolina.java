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
 * Clase gasolina (Imagen)
 */
 

public class gasolina {
/**
 * Variables tipo Imagen donde se guardaran las imagenes seleccionadas
 * Variables enteras que me diran en que posicion en x y y se pintara la imagen
 * Variable tipo boolean chocado que estara en false hasta que sea intersecado con otra Image
 */
    Image gaso,a;
    int posx,posy;
    boolean chocado;
/**
 * Constructor de la clase 
 * Que inicializa las variables y carga las imagenes
 * Excepcion IOException por si no se encuentra la imagen
 */
    public gasolina() 
    {
        chocado=false;
        posx=0;
        posy=0;
        try {
            a=ImageIO.read(new File("src/imagenes/gasolinera.png"));
            gaso=ImageIO.read(new File("src/imagenes/gasolinera.png"));
        } catch (IOException ex) {
           System.out.println("no se encuentra la gaso");
        }
    } 
/**
 * Metodo que restaura o vuelve a generar otra imagen 
 * Y vuelve a colocar el boolean en false para que pueda volver a ser instersectado 
 */
    public void restaurar() 
    {
        
            gaso=a;
            chocado=false;
      
    }
/**
 * Metodo que calcula el area de un rectangulo del tamaño de la imagen  
 * Retorna un nuevo Rectangle2D con las dimensiones
 */
    Rectangle2D getarea()
    {
        return new Rectangle2D.Double(posx, posy, gaso.getWidth(null), gaso.getHeight(null));
        
    }
    
}
