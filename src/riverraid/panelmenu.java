/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riverraid;
/**
 * Librerias utilizadas en la clase  
 */
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Gerardo, Daniel
 */
      
/**
 * Clase panelmenu que hereda de JPanel (Menu)
 */

public class panelmenu extends JPanel{
/**
 * Variables tipo JButton donde se crearan nuevos botones
 * Variable tipo Image donde se guardara el fondo que se le colocara al panel 
 */       
    JButton iniciar,instrucciones,top,creditos,salir;
    Image fondo;
/**
 * Constructor de la clase 
 * Se le añaden los componentes al contenedor y se muestra
 * Que inicializa las variables y carga la imagen
 * Dandole las coordenadas donde saldran y su tamaño
 * Y se le utilizan su respectivo ActionListener al boton salir para terminar el programa cuando se le presente un evento (ActionPerformed)
 * Excepcion IOException si no se encuentra la imagen
 */         
    panelmenu()
    {
        iniciar=new JButton("Iniciar");iniciar.setBounds(250,160,100, 30);
        instrucciones=new JButton("Instrucciones");instrucciones.setBounds(235 , 205,130, 30);
        top=new JButton("TOP 10 jugadores");top.setBounds(235, 330, 140, 25);
        creditos=new JButton("Creditos");creditos.setBounds(250, 375, 100, 30);
        salir=new JButton("Salir");salir.setBounds(250, 535, 100, 25);
        salir.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) {System.exit(0); }
        });
        
        super.setLayout(null);
        super.setFocusable(true);
        super.setBackground(java.awt.Color.green);
        super.setBounds(0,0,600,600);
        super.add(top);
        super.add(instrucciones);
        super.add(iniciar);
        super.add(creditos);
        super.add(salir);
        super.setVisible(true);
        
        try {
            fondo = ImageIO.read(new File("src/imagenes/avionesfondo.png"));
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
        
        g2.drawImage(fondo,0,0, this);
        
    }
    
    
    
}
