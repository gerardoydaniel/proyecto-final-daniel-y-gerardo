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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * 
 * @author Gerardo, Daniel
 */

/**
 * Clase panelmapa que hereda de JPanel (Juego)
 */

public class panelmapa extends JPanel {
/**
 * Variables tipo bala, personaje, aguan, puente, enemigoavion, gasolina manejo de sonido que son para crear objetos de las clases
 * Variable tipo Image donde se guardaran las imagen seleccionada (el marco)
 * Variables enteras contadores que llevaran la cantdidas de los arreglos de los objetos de las clases
 * Variable entera SUBIR que manipula en movimiento del mapa
 * Variables enteros tanque y puntos que estan inicializados para el tanque y la puntuacion iniciales 
 * JLabels que dicen la cantidad del tanque y puntuacion
 */   
    
    personaje p;
    bala b;
    puente[] pu=new puente[10];
    orilla[] o=new orilla[100];
    agua[] a=new agua[100];
    enemigoavion[] enemigoa=new enemigoavion[10];
    gasolina gas;
    Image marco,v[]=new Image[3];
    Timer tiempobala,tiempomapa,tiempotanque;
    int tanque=100,puntos=0; ;
    mapa m;
    Random r;
    JLabel full,puntuacion;
    int n=0,gx,gy,gx1,gy1;
    int conto=0,conta=0,contpu=0,contenea=0;
    int SUBIR;
    int band = 0;
    manejodesonido ms;    
/**
 * Metodo ActionListener  que escucha el evento del tanque y las vidas para luego manejarlo
 */
    ActionListener escuchantetan=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            tanque-=1;
            full.setText(""+tanque);
            if(tanque==0){try {
                p.vidas--;v[p.vidas]=ImageIO.read(new File("src/imagenes/vacio.png"));
                tanque=100;
               } catch (IOException ex) {
                   System.out.println("No se encontro la imagen");
               }
            }
            puntuacion.setText(""+puntos);
              if (p.vidas == 0){
                 System.out.println("PERDISTE TUS 3 VIDAS VIEJO GASOLINA");
                 tiempotanque.stop();
            }
        }
    };
    
/**
 * Metodo ActionListener  que escucha el evento de la bala para luego manejarlo, llamando al metodo dectectarcolisionbala
 */
    ActionListener escuchante=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) throws NullPointerException{
            if(b!=null&&b.disparo==true)
            {
               b.posy-=5;  
            }
            repaint();
            if(b.posy<=100){b=new bala();}
            detectarcolisionbala();
            
        }
    };
    
/**
 * Constructor de la clase que inicializa todo lo que se va a visualiza en el juego
 * Los (timers, iamgenes, JLabel, sonido)
 */
    panelmapa()
    {
        try {
            m=new mapa();
            full=new JLabel(""+tanque);
            puntuacion=new JLabel(""+puntos);
            p=new personaje();
            b=new bala();
            gas=new gasolina();
            ms=new manejodesonido();
            marco=ImageIO.read(new File("src/imagenes/marco.png"));
            v[0]=ImageIO.read(new File("src/imagenes/vidas.png"));
            v[1]=ImageIO.read(new File("src/imagenes/vidas.png"));
            v[2]=ImageIO.read(new File("src/imagenes/vidas.png"));
            full.setForeground(Color.yellow);
            full.setVisible(true);
            full.setBounds(360, 50, 100, 25);
            full.setFont(new java.awt.Font("Tahoma", 0, 36));
            puntuacion.setVisible(true);
            puntuacion.setForeground(Color.yellow);
            puntuacion.setBounds(150, 50, 100, 25);
            puntuacion.setFont(new java.awt.Font("Tahoma", 0, 36));
            super.setLayout(null);
            super.add(puntuacion);
            super.add(full);
            super.setFocusable(true);
            super.setBackground(new java.awt.Color(0,127,255));
            super.setBounds(0,0,600,600);
            super.setVisible(false);
            super.addKeyListener(teclas);
            cargarmapa();
            tiempobala=new Timer(1,escuchante); 
            tiempobala.start();
            tiempotanque=new Timer(200,escuchantetan);    
        } catch (IOException ex) {
            System.out.println("No se encontro la imagen");
        }     
    }
    
/**
 * Metodo ActionListener que escucha el evento que baja el mapa en cual el actionPerformed ir moviendo el mapa (bajandolo y colocando otro arriba) 
 */
    ActionListener bajarmapa=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            for(int i=0;i<conto;i++)
            {
                o[i].posy++;
                if(o[i].posy>=600){o[i].posy-=SUBIR;}
            }
        for(int j=0;j<conta;j++)
                {
                   a[j].posy++;
                   if(a[j].posy>=600){a[j].posy-=SUBIR;}
                }
        for(int i=0;i<contpu;i++)
        {
            pu[i].posy++;
            if(pu[i].posy>=600)
            {
                pu[i].posy-=SUBIR;
                pu[i].restaurar(pu[i].posx,pu[i].posy);
            }
        }
        for(int j=0;j<contenea;j++)
        {
            enemigoa[j].posy++;
            if(enemigoa[j].posy>=600)
            {
                enemigoa[j].posy-=SUBIR;
                enemigoa[j].restaurar(enemigoa[j].posx,enemigoa[j].posy);
            }
        }
        if(p.posy<=520){p.posy++;}
        gas.posy++;
        if(gas.posy>=600){gas.posy-=SUBIR; gas.restaurar();}
        }
    };
/**
 * Metodo cargar mapa, que carga el mapa con las imagenes desde la clase mapa
 */
    public void cargarmapa()
    {
        int equis=0,ye=500;
        conta=0;conto=0;contpu=0;contenea=0;
        SUBIR=m.info.length*100;
        for(int i=0;i<m.info.length;i++)
        {
            
           for(int j=0;j<m.info[0].length;j++)
           {
               if(m.info[i][j]==1){o[conto]=new orilla(); o[conto].posx=equis;o[conto].posy=ye;conto++;}
               if(m.info[i][j]==0){a[conta]=new agua(); a[conta].posx=equis;a[conta].posy=ye;conta++;}
               if(m.info[i][j]==3){a[conta]=new agua(); a[conta].posx=equis;a[conta].posy=ye;conta++;
               pu[contpu]=new puente();pu[contpu].posx=equis;pu[contpu].posy=ye;contpu++; }
               if(m.info[i][j]==2){a[conta]=new agua();a[conta].posx=equis;a[conta].posy=ye;conta++;
               enemigoa[contenea]=new enemigoavion();enemigoa[contenea].posx=equis;enemigoa[contenea].posy=ye+10;contenea++;}
               if(m.info[i][j]==5){o[conto]=new orilla(); o[conto].posx=equis;o[conto].posy=ye;o[conto].otraimagen(5);conto++;}
               if(m.info[i][j]==4){o[conto]=new orilla(); o[conto].posx=equis;o[conto].posy=ye;o[conto].otraimagen(4);conto++;}
               if(m.info[i][j]==7){o[conto]=new orilla(); o[conto].posx=equis;o[conto].posy=ye;o[conto].otraimagen(7);conto++;}
               if(m.info[i][j]==6){o[conto]=new orilla(); o[conto].posx=equis;o[conto].posy=ye;o[conto].otraimagen(6);conto++;}
               if(m.info[i][j]==9){a[conta]=new agua();a[conta].posx=equis;a[conta].posy=ye;conta++;gas.posx=equis;gas.posy=ye;}
               equis+=100;
           }
           equis=0;
           ye-=100;
        }
    }    
/**
 * Metodo que utiliza el metodo ActionListener para bajar el mapa en un timer para que se vaya repitiendo
 */
    public void movermapa()
    {
        tiempomapa=new Timer(13,bajarmapa);
        tiempomapa.start();
        tiempotanque.start();
        for(int i=0;i<contenea;i++)
        {
            enemigoa[i].mover.start();
        }
    }
/**
 * Metodo Detenertodo que para todos los timers haciendo parecer que el juego se ha detenido
 */
    public void detenertodo()
    {
    tiempomapa.stop();n=1;tiempobala.stop();
                    for(int i=0;i<contenea;i++)
                    {
                        enemigoa[i].mover.stop();
                    }
                    tiempotanque.stop();
    }
/**
 * Metodo KeyListener que atrapa los eventos que hagan las teclas, haciendo asi el movimiento del personaje, el disparo de la bala
 * El detener el juego, tambien el bajar o acelerar la velocidad del juego
 * Clausula throws para manejar la exception NullPointerException
 */    
    KeyListener teclas=new KeyListener() {
        
        @Override 
        public void keyTyped(KeyEvent e) {     
        }

        @Override
        public void keyPressed(KeyEvent e) throws NullPointerException{
            
            if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP ) {
                tiempomapa.setDelay(1); 
            }
            if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
                tiempomapa.setDelay(30);
            }
            if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
                p.posx+=5;
                if(b.disparo==true){b.posx+=5;}
            }
            if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
                p.posx-=5;
                if(b.disparo==true){b.posx-=5;}
            }
            if(e.getKeyCode()==KeyEvent.VK_SPACE)
            {
                if(b.disparo!=true)
                {
                    ms.manejodisparo();
                    b=new bala();
                b.posx=p.posx;
                b.posy=p.posy;
                b.disparo=true;
                }   
            }
            if(e.getKeyCode()==KeyEvent.VK_ENTER)
            {
                if(n==0)
                {
                    tiempomapa.stop();n=1;tiempobala.stop();
                    for(int i=0;i<contenea;i++){enemigoa[i].mover.stop();}
                    tiempotanque.stop();
                }
                else{
                    tiempomapa.restart();n=0;tiempobala.restart();
                for(int i=0;i<contenea;i++)
                {
                    enemigoa[i].mover.restart();
                }
                tiempotanque.restart();
                }  
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            
           if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP)
            { 
             tiempomapa.setDelay(13);
            }
            if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN )
            {
             tiempomapa.setDelay(13);   
            }
        }
    };
/**
 * Metodo sobreescrito paintComponent que me va a pintar la imagen
 */     
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        Graphics2D g2=(Graphics2D) g;
        
        for(int j=0;j<conta;j++)
        {
           g2.drawImage(a[j].agu,a[j].posx,a[j].posy,this);
        }
        
        for(int i=0;i<conto;i++)
        {
          g2.drawImage(o[i].fondo,o[i].posx,o[i].posy,this);
        }
        
        for(int i=0;i<contpu;i++)
        {
            if(pu[i].pu!=null)
            {
             g2.drawImage(pu[i].pu,pu[i].posx,pu[i].posy, this);
            }  
        }
        
        for(int i=0;i<contenea;i++)
        {
            g2.drawImage(enemigoa[i].enea,enemigoa[i].posx,enemigoa[i].posy, this);
        }
        
        g2.drawImage(gas.gaso,gas.posx,gas.posy, this);
        g2.drawImage(p.perso,p.posx,p.posy, this);

        if(b!=null&&b.disparo==true)
        {
            g2.drawImage(b.bal,b.posx+22,b.posy+5 , this);
        }
        g2.drawImage(marco,0,-20, this);
        g2.drawImage(v[0],460,50, this);
        g2.drawImage(v[1],500,50, this);
        g2.drawImage(v[2],540,50, this);
    }
/**
 * Metodo detectarcolisonbala que dectecta todas las colisones que se puedan realizar ayudandose con los metodos Rectangle2D
 * Cargando imagenes o borrando depende de las intersecciones que sucedan
 */ 
    public void detectarcolisionbala()
    {
        gx = gas.posx;
        gy = gas.posy;
        gx1 = gas.posx - 155;
        gy1 = gas.posy - 155;
        
        if(p.getarea().intersects(gas.getarea()))
            {
                gas.posx = gx;
                gas.posy = gy;    
                
                if(gas.chocado==false)
                {
                    tanque=100;
                     try {
                            gas.gaso=ImageIO.read(new File("src/imagenes/vacio.png"));  
                         } catch (IOException ex) {
                                                    System.out.println("No se encontro la imagen");
                                                  }
                }
            }
        
        if(b.getarea().intersects(gas.getarea()))
        {      
                band ++;
                gas.posx=gx1;
                gas.posy=gy1;
                if (band==2){
                 band=0;
                }
                
                if(gas.chocado==false)
                {
                    puntos-=10; 
                    puntuacion.setText(""+puntos);
                    try {
                        gas.gaso=ImageIO.read(new File("src/imagenes/vacio.png")); 
                        b=new bala();
                        gas.chocado=true;
                        }    catch (IOException ex) {
                                                    System.out.println("No se encontro la imagen");
                                                    }
                }
        }
        
        for(int i=0;i<conto;i++)
        {                       
            if(p.getarea().intersects(o[i].getarea()))
            {
                try {
                     ms.manejodeexplosion();
                    p.vidas--;
                    v[p.vidas]=ImageIO.read(new File("src/imagenes/vacio.png"));
                    p.posx=270;p.posy=370;                 
                } catch (IOException ex) {
                    System.out.println("No se encontro la imagen");
                }
            }
            
           if(b.getarea().intersects(o[i].getarea()))
           {
              b=new bala();
           } 
        }
        
        for(int j=0;j<contpu;j++)
        {
            if(p.getarea().intersects(pu[j].getarea()))
            {                 
                try {
                    if(pu[j].chocado==false)
                    {
                         ms.manejodeexplosion();
                    p.vidas--;
                    v[p.vidas]=ImageIO.read(new File("src/imagenes/vacio.png"));
                    p.posx=270;p.posy=370;
                    
                     pu[j].pu=ImageIO.read(new File("src/imagenes/vacio.png"));pu[j].chocado=true;
                    }
                } catch (IOException ex) {
                   System.out.println("No se encontro la imagen");
                }
                     
            }
            
            if(b.getarea().intersects(pu[j].getarea()))
            {
              puntos+=10; 
              puntuacion.setText(""+puntos);
              
                if(pu[j].chocado==false)
                {
                    try {
                        b=new bala();
                        pu[j].chocado=true;
                        pu[j].pu=ImageIO.read(new File("src/imagenes/vacio.png"));
                    }
                    catch (IOException ex) {
                         System.out.println("No se encontro la imagen");
                    }
                }
            }
        }
        
        for(int j=0;j<contenea;j++)
        {
            if(p.getarea().intersects(enemigoa[j].getarea()))
            {                
                try {
                    if(enemigoa[j].chocado==false)
                    {
                        ms.manejodeexplosion();
                    p.vidas--;
                    v[p.vidas]=ImageIO.read(new File("src/imagenes/vacio.png"));
                    p.posx=270;p.posy=370;
                    enemigoa[j].enea=ImageIO.read(new File("src/imagenes/vacio.png"));
                    enemigoa[j].chocado=true;
                    }        
                } catch (IOException ex) {
                   System.out.println("No se encontro la imagen");
                }    
            }
            if(b.getarea().intersects(enemigoa[j].getarea()))
            {
                 puntos+=20; 
                 puntuacion.setText(""+puntos);
                 
                if(enemigoa[j].chocado==false)
                {
                    try {
                        enemigoa[j].chocado=true;
                        enemigoa[j].enea=ImageIO.read(new File("src/imagenes/vacio.png"));
                        b=new bala();
                    } catch (IOException ex) {
                       System.out.println("No se encontro la imagen");
                    }
                } 
            }
        }
    } 
}
