/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riverraid;

/**
 *
 * @author Gerardo, Daniel
 */

/**
 * Clase mapa (Mapa)
 */
 
public class mapa {   
/**
 * Matriz entera que es donde se colocaran y visualizaran en el mapa cada Image 
 * Variables enteras posx, posy, que me indicaran posicion en x y y 
 */        
    int info[][];
    int posx,posy;
    public   mapa(){
                info =new int[][]{ 
                    {4,0,0,0,0,6},
                    {4,0,0,0,0,6},
                    {1,5,3,3,7,1},
                    {4,0,0,0,0,6},
                    {4,0,0,0,0,6},
                    {4,0,0,2,0,6},
                    {4,0,0,0,0,6},
                    {4,0,2,0,0,6},
                    {4,0,0,0,0,6},
                    {1,3,5,0,0,6},
                    {4,0,0,0,0,6},
                    {4,0,0,2,0,6},
                    {4,0,0,7,3,1},
                    {4,0,0,0,0,6},
                    {4,0,0,9,0,6},
                    {4,0,0,0,0,6},
                    {4,3,3,3,3,6},
                    {4,0,0,0,0,6},
                    {4,2,0,0,0,6},
                    {4,0,2,0,0,6},
                    {4,0,0,2,0,6},
                    
                };
    }
}
