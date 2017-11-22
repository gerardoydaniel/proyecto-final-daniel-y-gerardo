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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.Timer;

/**
 *
 * @author Gerardo, Daniel
 */
/**
 * Clase enemigoavion (Imagen)
 */
 

public class enemigoavion {
/**
 * Variables tipo Image donde se guardaran las imagenes
 * Variables n, inicializada en cero, que es donde se guardara el random que dira que enemigo se cargara
 * Variable vel que me indica la velocidad que se movera cada enemigo en pixeles que variara por el tamaño de cada uno
 * Variables enteras posx, posy para inicilaizar la posicion donde sestara el disparo, dir que va a ser 1 o -1 donde de dira hacia donde ira en enemigo en en eje x y cambiara cada vez que llegue al limite del agua
 * Variable tipo Random r donde se geneerara un numero aleatoreo que me dira que enemigo se mostrara
 * Variable tipo Timer mover que es donde se hara el evento de mover el enemigo y cambiar de imagen depende si va en dir 1 o -1
 */
    Image enea,ene1,ene2,ene3,ene4;
    int posx,posy,dir,n=0,vel;
    boolean chocado;
    Random r;
    Timer mover;
/**
 * Constructor de la clase
 * Carga las imagenes y  pone a ejecutar el evento
 * Excepcion IOException por si no se encuentra la imagen
 */
   public  enemigoavion()
    {
        try {
            posx=0;
            posy=0;
            dir=1;
            chocado=false;
            r=new Random();
            n=r.nextInt(2);
            ene1=ImageIO.read(new File("src/imagenes/avionenemigo2.png"));
            ene2=ImageIO.read(new File("src/imagenes/enemigo.png"));
            ene3=ImageIO.read(new File("src/imagenes/avionenemigo2reve.png"));
            ene4=ImageIO.read(new File("src/imagenes/enemigoreve.png"));
            if(n==1){enea=ene4;vel=1;}    
            if(n==0){enea=ene3;vel=2;}            
        } catch (IOException ex) {
            System.out.println("enemigo no encontrado");
        }
        mover=new Timer(10,escuchante);
    }
/**
 * Metodo ActionListener que me dice que realizara el timer cada determinado tiempo 
 * La dirreccion del enemigo que va a cambiar cuando llegue al limite  y se cambiara la imagen
 */
    ActionListener escuchante= new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
           posx+=(vel*dir);
           if(posx>=400)
           {
               dir*=-1;
               if(enea==ene4){enea=ene2;}
               if(enea==ene3){enea=ene1;}
           }
           if(posx<=100)
           {
               dir*=-1;
               if(enea==ene1){enea=ene3;}
               if(enea==ene2){enea=ene4;}
           }
        }
    };
/**
 * Metodo restaurar que recibe por parametros dos valores enteros equis y ye
 * Que me diran en que posicion se generara un nuevo enemigo luego de ser intersectado 
 * Volviendo a colocar el boolean false para saber que ya no esta chocado y pintando el mismo enemigo que tenia lo cual me lo dice n
 */
   public void restaurar(int equis,int ye)
   {
       posx=equis;
       posy=ye;
       chocado=false;
        
            if(n==0){enea=ene3;}
            if(n==1){enea=ene4;}
        
   }
/**
 * Metodo que calcula el area de un rectangulo del tamaño de la imagen  
 * Retorna un nuevo Rectangle2D con las dimensiones
 */
   Rectangle2D getarea()
   {
        return new Rectangle2D.Double(posx, posy, enea.getWidth(null), enea.getHeight(null));
   
   }
           
}
