/*
 * Algorithms and Programming II                               May 10, 2022
 * Integrantes:
 * Mariana Guerrero, Sharick Bonadiez, Jhoreinis Anaya.
 *
 *
 * Synopsis:
 * Solves the Closest Pair problem with the Brute Force Algorithm.
 */

package fuerzabruta;

public class FuerzaBruta 
{
        //Encuentra el par más cercano mediante el algoritmo de Fuerza Bruta
        public static void main (String[] args)
        {    
            //Dataset que vamos a implementar.
             int [][] P = {
			{ 2,  7}, { 4, 13}, { 5,  7}, {10,  5},
			{13,  9}, {15,  5}, {17,  7}, {19, 10},
			{22,  7}, {25, 10}, {29, 14}, {30,  2}
		};
             
            //Inicializamos las coordenadas para i, j
            int i=3;
            int j=5;
            double d= distance(P,i,j);
            
            //Encuentra el closest pair 
            int [][] closestPair = new int [2][2];
            double d_min = closestpair(P, closestPair);
            
            System.out.println("***Brute Force Algorithm***");
            //Imprime la distancia más corta del par más cercano en la consola
            System.out.printf("Distancia: %.4f\n" , d);
            System.out.printf("Distancia más corta: %.4f\n" , d_min);
            
            //Imprime las coordenadas del par más cercano en la consola
            System.out.println(("Coordenadas: \n") + closestPair[0][0]  +("  ")+   closestPair[0][1] +
                            ("\n")+ + closestPair[1][0]  +("  ")+  closestPair[1][1] );

        }
        
        public static double distance(int [][]P, int i, int j)
        {
            //Obtiene las coordenadas de la matriz. 
            int x1= P[i][0];
            int y1=P[i][1];
            
            int x2= P[j][0];
            int y2=P[j][1];
            
            //Encontramos la distancia al cuadrado de las coordenadas
            double d = (x2 - x1) * (x2 - x1) +(y2 - y1) * (y2 - y1);
            
            return Math.sqrt(d);  //Retorna la distancia entre las partículas
        } 
        
        private static double closestpair( int [][]P,  int [][] closestPair)	
        {
            int N=P.length; //Determinamos N con respecto a la cantidad de elementos que almacena las filas. 
            double d_min = Double.MAX_VALUE; //Tomamos en cuenta el valor máximo posible en formato Double .
            double d; //Crea la variable de distancia
            //Inicializa las variables de las coordenadas
            int x1=0;
            int y1=0; 
            int x2=0; 
            int y2=0;
            
            for (int i = 0; i != (N - 1); ++i){
                for (int j = (i + 1); j != N; ++j){
                    
                    //Obtiene las coordenadas de la matriz. 
                    x1= P[i][0]; //coordendas de x en i
                    y1=P[i][1]; //coordenadas de y en i

                    x2= P[j][0]; //coordenadas de x en j
                    y2=P[j][1]; //coordenadas de y en j
                    
                    //Encuentra la distancia al cuadrado de las coordenadas.
                    d = (x2 - x1) * (x2 - x1) +(y2 - y1) * (y2 - y1);
                    
                    //Obtiene la distancia más corta (excluimos las distancias mayores que d_min)
                    if (d < d_min){
			d_min = d;
                        
                        //Guardamos las coordenadas del par más cercano.
			closestPair[0][0] = x1;
                        closestPair[0][1]= y1;
			closestPair[1][0] = x2;
                        closestPair[1][1]= y2;
                    }
		}

            }
            return (Math.sqrt(d_min)); //Retorna la distancia mínima.
	} 
       
}
