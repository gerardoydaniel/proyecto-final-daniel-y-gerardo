/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riverraid;
/**
 * librerias     
 */
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Gerardo, Daniel
 */

/**
 * Clase manejodesonido (Sonido)
 */
 
public class manejodesonido  {
/**
 * Variables tipos Clip para manejar el sonido
 */    
  Clip reprod,reprodfin,reprodmenu,reproddispa,reprodexplo;
/**
 * Constructor de la clase 
 */  
    public manejodesonido(){};
/**
 * Metodo manejodejuego le da sonido al juego mientras se ejecuta
 */   
        public void manejodejuego()
        {
          AudioInputStream flujo=null;
            try {
                flujo = AudioSystem.getAudioInputStream(new File("src/sonidos/RTypJ-Stage2.mid"));
                AudioFormat format=flujo.getFormat();
                DataLine.Info info;
                info=new DataLine.Info(Clip.class,format,(int)(flujo.getFrameLength()*format.getFrameSize()));
                reprod=(Clip)AudioSystem.getLine(info);
                reprod.open(flujo);
                reprod.start();     
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                Logger.getLogger(manejodesonido.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    flujo.close();
                } catch (IOException ex) {
                    Logger.getLogger(manejodesonido.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
/**
 * Metodo manejofin maneja el sonido al terminar el juego
 */  
        public void manejofin()
        {
            AudioInputStream flujo=null;
            try {
                flujo = AudioSystem.getAudioInputStream(new File("src/sonidos/dlrrgameover.mid"));
                AudioFormat format=flujo.getFormat();
                DataLine.Info info;
                info=new DataLine.Info(Clip.class,format,(int)(flujo.getFrameLength()*format.getFrameSize()));
                reprodfin=(Clip)AudioSystem.getLine(info);
                reprodfin.open(flujo);
                reprodfin.loop(Clip.LOOP_CONTINUOUSLY);
                
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                Logger.getLogger(manejodesonido.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    flujo.close();
                } catch (IOException ex) {
                    Logger.getLogger(manejodesonido.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
/**
 * Metodo manejodedisparo maneja el sonido cuando ocurre un disparo 
 */   
        public void manejodisparo()
        {
            AudioInputStream flujo=null;
            try {
                flujo = AudioSystem.getAudioInputStream(new File("src/sonidos/GE_KF7_Soviet.wav"));
                AudioFormat format=flujo.getFormat();
                DataLine.Info info;
                info=new DataLine.Info(Clip.class,format,(int)(flujo.getFrameLength()*format.getFrameSize()));
                reproddispa=(Clip)AudioSystem.getLine(info);
                reproddispa.open(flujo);
                reproddispa.start();
                
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                Logger.getLogger(manejodesonido.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    flujo.close();
                } catch (IOException ex) {
                    Logger.getLogger(manejodesonido.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } 

/**
 * Metodo manejodeexplosion maneja el sonido cuando ocurre una interseccion
 */           
        public void manejodeexplosion()
        {
         AudioInputStream flujo=null;
            try {
                flujo = AudioSystem.getAudioInputStream(new File("src/sonidos/explosin.wav"));
                AudioFormat format=flujo.getFormat();
                DataLine.Info info;
                info=new DataLine.Info(Clip.class,format,(int)(flujo.getFrameLength()*format.getFrameSize()));
                reprodexplo=(Clip)AudioSystem.getLine(info);
                reprodexplo.open(flujo);
                reprodexplo.start();    
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                      Logger.getLogger(manejodesonido.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                try {
                    flujo.close();
                } catch (IOException ex) {
                 Logger.getLogger(manejodesonido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
