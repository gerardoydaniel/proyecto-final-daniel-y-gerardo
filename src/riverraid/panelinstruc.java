/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riverraid;
/**
 * Librerias utilizadas en la clase  
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Gerardo, Daniel
 */

/**
 * Clase panelinstruc que hereda de JPanel (Instrucciones)
 */

public class panelinstruc extends JPanel{
/**
 * Variable tipo JButton donde se creara un nuevo boton
 * Arreglo de JLabel donde se creara un nuevo vector de labels
 * Variables tipo Image donde se guardaran las imagenes que se le colocaran al panel 
 */      
    JButton volver;
    JLabel l[]=new JLabel[6];
    Image teclas, barra;
/**
 * Constructor de la clase 
 * Se le añaden los componentes al contenedor y se muestra
 * Que inicializa las variables y cargan las imagenes
 * Dandole las coordenadas donde saldran y su tamaño, color de fondo al panel
 * Excepcion IOException si no se encuentra la imagen
 */       
   public panelinstruc()
    {
         for(int i=0;i<6;i++)
        {   
            if(i==0){l[i]=new JLabel("Acelerar"); l[i].setBounds(220, 35, 1000, 40); }
            if(i==1){l[i]=new JLabel("Izquierda"); l[i].setBounds(0, 170, 1000, 60);}
            if(i==2){l[i]=new JLabel("Frenar"); l[i].setBounds(240, 250, 1000, 40);}
            if(i==3){l[i]=new JLabel("Derecha "); l[i].setBounds(415, 170, 1000, 60);}
            if(i==4){l[i]=new JLabel("Disparar"); l[i].setBounds(220, 460, 1000, 60);}
            if(i==5){l[i]=new JLabel("'ENTER' Pausa "); l[i].setBounds(160, 300, 1000, 60);}
            l[i].setFont(new java.awt.Font("Tahoma", 0, 45));
            l[i].setVisible(true);
            l[i].setForeground(Color.black);
            super.add(l[i]);
        }
        volver=new JButton("Volver");
        volver.setBounds(50, 500, 100, 30);
        super.setVisible(false);
        super.setLayout(null);
        super.add(volver);
        super.setBounds(0, 0, 600, 600);
        super.setBackground(Color.white);
         try {
            barra = ImageIO.read(new File("src/imagenes/espacio.png"));
            teclas = ImageIO.read(new File("src/imagenes/flechas_serias.png"));
        } catch (IOException ex) {
            System.out.println("la imagen no se encuentra");
        }
    }
/**
 * Metodo sobreescrito paintComponent que me va a pintar la imagen
 */ 
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        Graphics2D g2=(Graphics2D) g;
        
        g2.drawImage(barra,100,360, this);
        g2.drawImage(teclas,200,100, this);
    }
}
