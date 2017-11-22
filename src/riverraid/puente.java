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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Gerardo, Daniel
 */

/**
 * Clase puente (Imagen)
 */

public class puente {
/**
 * Variable tipo Image donde se guardaran las imagen seleccionada
 * Variables enteras posx, posy, que me diran en que posicion en x y y se pintara la imagen
 * Variable tipo boolean chocado que estara en false hasta que sea intersecado con otra Image
 */    
    Image pu;
    int posx;
    int posy;
    boolean chocado;
/**
 * Constructor de la clase 
 * Que inicializa las variables y carga la imagen
 * Excepcion IOException si no se encuentra la imagen
 */    
    public puente()
    {
        chocado=false;
        posx=-1000;
        posy=-1000;
        try {
            pu=ImageIO.read(new File("src/imagenes/puente.png"));
        } catch (IOException ex) {
            System.out.println("puente no encontrado");
        }
    }
/**
 * Metodo restaurar que recibe por parametros dos valores enteros equis y ye
 * Que me diran en que posicion se generara un nuevo puente luego de ser intersectado 
 * Volviendo a colocar el boolean false para saber que ya no esta chocado 
 * /**
 * Metodo restaurar que recibe por parametros dos valores enteros equis y ye
 * Que me diran en que posicion se generara un nuevo enemigo luego de ser intersectado 
 * Volviendo a colocar el boolean false para saber que ya no esta chocado y pintando el mismo enemigo que tenia lo cual me lo dice n
 * Volviendo a acargar la imagen de nuevo
 */
    public void restaurar(int equis,int ye )
    {
        try {
            posx=equis;
            posy=ye;
            chocado=false;
            pu=ImageIO.read(new File("src/imagenes/puente.png"));
        } catch (IOException ex) {
            Logger.getLogger(puente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
/**
 * Metodo que calcula el area de un rectangulo del tamaño de la imagen  
 * Retorna un nuevo Rectangle2D con las dimensiones
 */       
    Rectangle2D getarea()
    {
        return new Rectangle2D.Double(posx-10, posy, pu.getWidth(null),pu.getHeight(null));
        
    }
}
