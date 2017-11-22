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
 * Clase panelcreditos que hereda de JPanel (Creditos)
 */

public class panelcreditos extends JPanel{
/**
 * Variable tipo JButton donde se creara un nuevo boton
 * Arreglo de JLabel donde se creara un nuevo vector de labels
 * Variable tipo Image donde se guardara el fondo que se le colocara al panel 
 */    
    JButton volver;
    JLabel[] l = new JLabel[5];
    Image gotas;
/**
 * Constructor de la clase 
 * Se le añaden los componentes al contenedor y se muestra
 * Que inicializa las variables y carga la imagen
 * Dandole las coordenadas donde saldran y su tamaño
 * Excepcion IOException si no se encuentra la imagen
 */     
 public panelcreditos()
    {
        for(int i=0;i<5;i++)
        {   
            if(i==0){l[i]=new JLabel("Obra y Arte de:"); l[i].setBounds(200, 0, 1000, 25);}
            if(i==1){l[i]=new JLabel("Jose Castañeda"); l[i].setBounds(200, 160, 1000, 25);}
            if(i==2){l[i]=new JLabel("CI 26.209.402"); l[i].setBounds(200, 205, 1000, 25);}
            if(i==3){l[i]=new JLabel("Daniel Mora"); l[i].setBounds(200, 330, 1000, 25);}
            if(i==4){l[i]=new JLabel("26.493.706"); l[i].setBounds(200, 375, 1000, 25);}
            l[i].setFont(new java.awt.Font("Tahoma", 0, 30)); 
            l[i].setVisible(true);
            l[i].setForeground(Color.black);
            super.add(l[i]);
        }
        volver=new JButton("Volver");
        volver.setBounds(160, 500, 100, 30);
        super.setVisible(false);
        super.setLayout(null);
        super.add(volver);
        super.setBounds(0, 0, 600, 600);
          try {
            gotas = ImageIO.read(new File("src/imagenes/avionesfondo.png"));
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
        g2.drawImage(gotas,0,0, this);
    }
}
 
