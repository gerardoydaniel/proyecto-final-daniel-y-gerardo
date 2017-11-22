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
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Gerardo, Daniel
 */

/**
 * Clase paneltop que hereda de JPanel (TOP)
 */

public class paneltop extends JPanel{
/**
 * Variable tipo JButton donde se creara un nuevo boton
 * Arreglo de JLabel donde se creara un nuevo vector de labels
 * Variables tipo Srting y Stringtokenizer, al igual que BufferedReader, FileReader que se util;izaran para el manejo del archivo (lectura y mostrar)
 * Variables enteras que se utilizaran como cobntadores de las lineas y tokens que tendra el archivo 
 */      
    JButton volver;
    FileReader fr;
    BufferedReader br;
    StringTokenizer tokenizer;
    String nombres[]=new String[100];
    int puntos[]=new int[100];
    String linea;
    JLabel l[]=new JLabel[20];
    int contn=0,contp=0;
    Image gano,fondo,perdio;
    boolean ganar=false;
    boolean pierdes=false;
    
/**
 * Constructor de la clase 
 * Se le añaden los componentes al contenedor y se muestra
 * Se llama al metodo de la clase cleerarchivo donde se maneja el archivo
 * Dandole las coordenadas donde saldran y su tamaño, color de fondo al panel
 */           
    public paneltop()
    {
        try {
            volver=new JButton("Volver");
            volver.setBounds(50, 520, 100, 30);
            gano=ImageIO.read(new File("src/imagenes/ganador.png"));
            fondo=ImageIO.read(new File("src/imagenes/avionesfondo.png"));
            perdio=ImageIO.read(new File("src/imagenes/perdedor.png"));
            for(int i=0;i<10;i++)
            {
                l[i]=new JLabel("------------");
                l[i].setFont(new java.awt.Font("Monospaced", Font.ROMAN_BASELINE, 30));
                
                l[i].setBounds(200, i*50, 1000, 40);
                l[i].setVisible(true);
                l[i].setForeground(Color.white);
                super.add(l[i]);
                
            }
            leerarchivo();
            super.setVisible(false);
            super.setLayout(null);
            super.add(volver);
            super.setBounds(0, 0, 600, 600);
            super.setBackground(Color.blue);
        } catch (IOException ex) {
            System.out.println("No se encuentra el archivo");
        }
    }
/**
 * Metodo leer archivo
 * Donde se maneja el archivo (lee, guarda y muestra)
 * Excepcion FileNotFoundException si no se encuentra el archivo
 */         
    public void leerarchivo()
    {
        int aux;
        String saux;
        contn=0;contp=0;
        try {
            
            fr=new FileReader("Datos.txt");
            br=new BufferedReader(fr);
            linea=br.readLine();
            while(linea!=null)
            {
                tokenizer=new StringTokenizer(linea,"-");
                nombres[contn]=new String();
                nombres[contn++]=tokenizer.nextToken();
                puntos[contp++]=Integer.parseInt(tokenizer.nextToken());
                linea=br.readLine();
            }
                br.close();
               for(int g=0;g<20;g++){
                for(int i=0;i<contn;i++)
                {
                    for(int j=0;j<contn;j++)
                    {
                        if(i!=j)
                        {
                            if(puntos[j]>=puntos[i])
                            {
                                aux=puntos[i];
                                puntos[i]=puntos[j];
                                puntos[j]=aux;
                                saux=nombres[i];
                                nombres[i]=nombres[j];
                                nombres[j]=saux;
                            }
                        }
                    }
                }
              }
            int otro = contn;
            contn-=1;
            contp-=1;
            if(otro>10){otro=10;}
                for(int i=0;i<otro;i++)
                {  
                    l[i].setText((i+1)+".-"+
                            nombres[contn--]+"-"+
                            puntos[contp--]);   
                }     
                repaint();
        } catch (FileNotFoundException ex) {
            System.out.println("No se encontro el archivo");
        } catch (IOException ex) {
           System.out.println("No se encontro el archivo");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        Graphics2D g2=(Graphics2D)g;
        g2.drawImage(fondo, 0,0, this);
        if(ganar==true){g2.drawImage(gano,500,-30,this);g2.drawImage(gano,0,-20,this);}
        if(pierdes==true){g2.drawImage(perdio,500,-30,this);g2.drawImage(perdio,0,-20,this);}
    }
    
    
}
 