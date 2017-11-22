/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package riverraid;
/**
 * librerias     
 */

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author Gerardo, Daniel
 */

/**
 * Clase frame que hereda de JFrame (Ventana)
 */

public class frame extends JFrame{
/**
 * Variables tipo panelmenu. paneltop, panelcreditos, panelinstruc, manejo de sonido que son para crear objetos de las clases que luego van a ser utilizados accediendo a sus metodos y atributos
 * Variable tipo PrintWriter, FileWriter, BufferedWriter para manejar el archivo (crea, leer, guardar, escribir)
 * Variables tipo Timer que son para validar si se pierde o gane y las vidas
 * Variable tipo String nombre que es la que se va a utilizar como ayuda en el manejo de archivos
 */         
    panelmenu pm;
    panelmapa panelm;
    panelinstruc pi;
    panelcreditos pc;
    paneltop pt; 
    Timer t,tiempovidas;
    String nombre = "anonimo";
    FileWriter fw;
    BufferedWriter bw;
    PrintWriter pw;
    manejodesonido ms;
/**
 * Constructor de la clase que inicializa las variables 
 * Se acomodan los sugares de los botones y se llaman a sus ActionListener
 */     
    frame()
    {
        pm=new panelmenu();
        panelm=new panelmapa();
        pi=new panelinstruc();
        pc=new panelcreditos();
        pt=new paneltop();
        ms=new manejodesonido();
        pm.iniciar.addActionListener(iniciarb);
        pi.volver.addActionListener(instruccionesb);
        pm.instrucciones.addActionListener(instruccionesb);
        pm.creditos.addActionListener(creditosb);
        pc.volver.addActionListener(creditosb);
        pm.top.addActionListener(topb);
        pt.volver.addActionListener(topb);
        super.setTitle("River Raid");
        super.setBackground(Color.yellow);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setBounds(0,0,600,600);
        super.getContentPane().setLayout(null);
        super.add(pt);
        super.add(pc);
        super.add(pi);
        super.add(pm);
        super.add(panelm);
        super.setResizable(false);
        super.setVisible(true);
    }
/**
 * Metodo ActionListener que escucha y luego maneja el evento con el boton iniciar
 */     
    ActionListener iniciarb =new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            nombre=JOptionPane.showInputDialog("intrduzco su nombre:");
            if( nombre.length() > 10){
            JOptionPane.showMessageDialog(null,"Maximo 10 caracteres");           
            nombre=JOptionPane.showInputDialog("intrduzco su nombre:");
            } 
            //ms.reprodmenu.stop();
            ms.manejodejuego();
            pm.setVisible(false);
            panelm.setVisible(true);
            panelm.requestFocus();
            panelm.movermapa();
            t=new Timer(1000*60,new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   pt.ganar=true;
                   repaint();
                   panelm.setVisible(false);
                   pt.setVisible(true);
                   pt.volver.setVisible(false);
                   panelm.detenertodo();
                   ms.reprod.stop();
                   ms.manejofin();
                   t.stop();
                   JOptionPane.showMessageDialog(null,nombre+" tu tiempo a acabado\nTu Puntuacion:"+panelm.puntos);
                   creararchivo();
                   pt.leerarchivo();
                }
            });
            t.start();
            tiempovidas=new Timer(1,new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(panelm.p.vidas==0){panelm.setVisible(false);
                    pt.pierdes=true;
                    repaint();
                   pt.setVisible(true);
                   pt.volver.setVisible(false);
                   panelm.detenertodo();
                   tiempovidas.stop();
                   ms.reprod.stop();
                   ms.manejofin();
                    t.stop();
                    JOptionPane.showMessageDialog(null,nombre+" perdiste todas tus vidas\nTu Puntuacion:"+panelm.puntos);
                    creararchivo();
                    pt.leerarchivo();
                    }  
                }
            });
            tiempovidas.start();
        }
    };
/**
 * Metodo creararchivo donde se elige el archivo y donde se va a guardar (ruta que se le va a dar) y se empieza a escribir 
 */    
    public void creararchivo()
    {
        try {
            fw=new FileWriter("Datos.txt",true);
            bw=new BufferedWriter(fw);
            pw=new PrintWriter(bw);
            pw.println(nombre+"-"+panelm.puntos+"-");
            pw.close();
        } catch (IOException ex) {
            Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
/**
 * Metodo ActionListener que escucha y luego maneja el evento con el boton creditos
 */      
    ActionListener creditosb=new ActionListener()
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==pm.creditos)
            {
                 pm.setVisible(false);
            pc.setVisible(true);
            }
            if(e.getSource()==pc.volver)
            {
                 pm.setVisible(true);
            pc.setVisible(false);
            }
        }
        
    };
/**
 * Metodo ActionListener que escucha y luego maneja el evento con el boton instrucciones
 */      
    ActionListener instruccionesb=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==pm.instrucciones)
            {
              pm.setVisible(false);
              pi.setVisible(true);
            }
            if(e.getSource()==pi.volver)
            {
              pm.setVisible(true);
              pi.setVisible(false);
            }
           
        }
    };
/**
 * Metodo ActionListener que escucha y luego maneja el evento con el boton top 10 jugadores 
 */      
    ActionListener topb=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==pm.top)
            {
                pm.setVisible(false);
                pt.setVisible(true);
            }
            if(e.getSource()==pt.volver)
            {
              pm.setVisible(true);
              pt.setVisible(false);
            }
        }
    };
}
