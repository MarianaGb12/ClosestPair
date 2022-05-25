/*
 * Algorithms and Programming II                               May 17, 2022
 * Integrantes:
 * Mariana Guerrero, Sharick Bonadiez, Jhoreinis Anaya.
 *
 *
 * Synopsis:
 * Solves the Closest Pair problem with the Divide and Conquer Algorithm-Non recursive.
 */

package divideandconquerproject;
    
import java.util.Random;

public class DivideandConquerProject {

    public static void main(String[] args) {
        //Dataset que vamos a implementar.
        int[][] P = {
                        { 1, 2}, { 1, 11}, { 7,  8}, { 9,  9}, {12, 13},
                        {13, 4}
                    };
        //Inicializamos las coordenadas para i, j
        int i = 3;
        int j = 5;
        double d = distance(P, i, j);
        
        //Encuentra el closest pair 
        int[][] closestPair = new int[2][2]; 
        double d_min = closestpair(P, closestPair);
        
        //Imprime el par más cercano y las coordenadas en la consola utilizando el algoritmo de Fuerza Bruta.
        System.out.println("**Brute Force Algorithm**");
        System.out.printf("Distancia: %.4f\n", d);
        System.out.printf("Distancia más corta: %.4f\n", d_min);
        System.out.println(("Coordenadas: "));
        matriz(closestPair);
        System.out.println("");
        
        //Imprime la distancia más corta y las coordenadas en la consola utilizando el algoritmo Divide y Vencerás.
        System.out.println("**Divide and Conquer Algorithm**");
        System.out.println(("Coordenadas: "));
        double Div=DivideYVenceras(0,P.length,P,closestPair);
        System.out.printf("Distancia más corta: ");
        System.out.println(Div);
    }
    
    //Encuentra la distancia más corta implementando el algoritmo de Fuerza Bruta
    public static double distance(int[][] P, int i, int j) {
        //Obtiene las coordenadas de la matriz. 
        int x1 = P[i][0]; 
        int y1 = P[i][1];

        int x2 = P[j][0];
        int y2 = P[j][1];
        
        //Encontramos la distancia al cuadrado de las coordenadas
        double d = (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);

        return Math.sqrt(d); //Retorna la distancia entre las partículas.
    }

    private static double closestpair(int[][] P, int[][] closestPair) {
        
        //Determinamos N con respecto a la cantidad de elementos que almacena las filas. 
        int N = P.length;
        
        //Tomamos el valor máximo posible en formato Double .
        double d_min = Double.MAX_VALUE; 
        double d; //Distancia desde x a y
        //Inicializamos las variables de las coordenadas para x,y.
        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;

       
        for (int i = 0; i != (N - 1); ++i) {
            for (int j = (i + 1); j != N; ++j) {
                
                //Obtiene las coordenadas de la matriz. 
                x1 = P[i][0]; //coordendas de x en i
                y1 = P[i][1]; //coordenadas de y en i

                x2 = P[j][0]; //coordenadas de x en j 
                y2 = P[j][1]; //coordenadas de y en j

                //Encuentra la distancia al cuadrado de las coordenadas.
                d = (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);

                //Obtiene la distancia más corta (excluimos las distancias mayores que d_min)
                if (d < d_min) { 
                    d_min = d;
                    
                   //Guardamos las coordenadas del par más cercano.
                    closestPair[0][0] = x1;
                    closestPair[0][1] = y1;
                    closestPair[1][0] = x2;
                    closestPair[1][1] = y2;
                }
            }

        }
        return (Math.sqrt(d_min)); //Retorna la distancia mínima.
    }
    
    //Permite visualizar el dataset que vamos a implementar
    public static void matriz(int P[][]) {
        for (int[] P_ : P) {

            for (int y = 0; y < P_.length; y++) {
                System.out.print(P_[y]);
                if (y != P_.length - 1) {
                    System.out.print("\t");
                }
            }
            System.out.println("");
        }
    }
    
    //Ordena primeramente con respecto a X, y secundariamente con respecto a y.
    public static void X(int P[][]) {
        int m;
        m = P.length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m - i - 1; j++) {
                if (P[j][0] > P[j + 1][0]) {
                    int[] filas = P[j];
                    P[j] = P[j + 1];
                    P[j + 1] = filas;
                }
                if (P[j][0] == P[j + 1][0]) {
                    if (P[j][1] > P[j + 1][1]) {
                        int[] filas = P[j];
                        P[j] = P[j + 1];
                        P[j + 1] = filas;
                    }
                }
            }
        }
    }
    
    //Encuentra el par más cercano en [b,c) utilizando el algoritmo Divide y vencerás.
    public static double DivideYVenceras(int inicio, int fin, int P[][], int closestPair[][] ){
        
        //Inicializamos n con respecto a la cantidad de elementos que almacena las filas.  
        int n=P.length; 
        
        //Divide el dataset en subsets
        int[][] b=new int [n/2][2]; 
        int [][]c=new int[n/2][2];
        
        //Encuentra el par más cercano para el subset de b
        for (int i=0; i<b.length;i++){
            for(int j=0; j<2;j++){
                b[i][j]=P[i][j];
            
            }
        }
        
        //Encuentra el par más cercano para el subset de c
        for (int i=0;i!=c.length;i++){
            for(int j=0;j<2;j++){
            c[i][j]=P[i+c.length][j];
            }
        }
        
        //Imprime el par más cercano desde [b,c} en la consola. 
        double Dis1=closestpair(b,closestPair);
        double Dis2=closestpair(c, closestPair);
        double min=0; //Inicializa la distancia mínima
        
        //Obtiene la distancia más corta entre ambos subsets.
        if(Dis2>Dis1){
            min=Dis1;
        }else{
            min=Dis2;
        }
       
        int x1, x2,y1, y2; //Crea las variables para las coordenadas
        double d; //Crea la variable de la distancia entre las partículas
        
        for(int i=0;i!=b.length;i++){
            for(int j=0;j!=c.length;j++){
                
                x1=b[i][0]; //Coordenadas de x para b
                y1=b[i][1]; //Coordenadas de y para b
                x2=c[j][0]; //Coordenadas de x para c
                y2=c[j][1]; //Coordenadas de y para c 
               
               //Encuentra la distancia al cuadrado de las coordenadas
                d = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));

                //Obtiene la distancia más corta (excluimos las distancias mayores que d_min)
                if (d < min) {
                    min = d; 
                    //Guardamos las coordenadas del par más cercano.
                    closestPair[0][0] = x1;
                    closestPair[0][1] = y1;
                    closestPair[1][0] = x2;
                    closestPair[1][1] = y2;
                }
           }
       
       
       }
       matriz(closestPair);
        return min; //Retorna la distancia más corta entre el par más cercano.
    }
}

