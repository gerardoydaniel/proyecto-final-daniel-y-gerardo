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
public class orilla extends mapa{
/**
 * Variable donde se guardara la imagen
 */
    Image fondo;
/**
 * constructor de la clase donde se carga la imagen del agua 
 * Excepcion IOException por si no se encuentra la imagen
 */
    public orilla()
    {
        posx=0;
        posy=0;
        try {
            fondo = ImageIO.read(new File("src/imagenes/gramafondo.png"));
        } catch (IOException ex) {
            System.out.println("imagen no encontrada");
        }  
    }
/**
 * Metodo otraimagen que recibe por parametros un entero id que me dira  que imagen se generara  
 * Excepcion IOException por si no se encuentra la imagen 
 */   
       public void otraimagen(int ind)
       {
         try{
             if(ind==4){fondo=ImageIO.read(new File("src/imagenes/gramaladoizq.png"));}
             if(ind==5){fondo=ImageIO.read(new File("src/imagenes/gramaizqs.png"));}
             if(ind==6){fondo=ImageIO.read(new File("src/imagenes/gramaladodere.png"));}
             if(ind==7){fondo=ImageIO.read(new File("src/imagenes/gramaders.png"));}
           }catch(IOException e){System.out.println("alguna de las orillas no encontradas");}
        }
/**
 * Metodo que calcula el area de un rectangulo del tamaño de la imagen  
 * Retorna un nuevo Rectangle2D con las dimensiones
 */       
    Rectangle2D getarea()
    {
        return new Rectangle2D.Double(posx,posy,fondo.getWidth(null),fondo.getHeight(null));
    }
}
