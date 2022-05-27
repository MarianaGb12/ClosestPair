/*
 * Algorithms and Programming II                               May 24, 2022
 * Integrantes:
 * Mariana Guerrero, Sharick Bonadiez, Jhoreinis Anaya.
 *
 *
 * Synopsis:
 * Solves the Closest Pair problem with the Divide and Conquer Algorithm-nonrecursive method.
 */

package divideandconquerproyectofinal;

public class DivideandconquerProyectofinal {

    public static void main(String[] args) 
    {
     
        //Dataset que vamos a implementar.
        int[][] P = {        
                        { 2,  7}, { 4, 13}, { 5,  7}, {10,  5},
                        {13,  9}, {15,  5}, {17,  7}, {19, 10},
                        {22,  7}, {25, 10}, {29, 14}, {30,  2}
                    };
        //Inicializamos las coordenadas para i, j
        int i = 3;
        int j = 5;
        double d = distance(P, i, j);
        
        //Encuentra el closest pair 
        int[][] closestPair = new int[2][2]; 
        double d_min = closestpair(P, closestPair);
        
        //Imprime el par más cercano y las coordenadas en la consola utilizando el algoritmo de Fuerza Bruta.
        System.out.println("***Brute Force Algorithm***");
        System.out.printf("Distancia: %.12f\n", d);
        System.out.println("Distancia más corta: "+ d_min);
        System.out.println(("Coordenadas: "));
        matriz(closestPair); 
        System.out.println("-------------------------------------");
        
        //Imprime la distancia más corta y las coordenadas en la consola utilizando el algoritmo Divide y Vencerás.
        System.out.println("***Divide and Conquer Algorithm***");
        System.out.println("-Divide into halves: ");
        System.out.println(("Coordenadas: "));
        double Div=DivideYVenceras(0,P.length,P,closestPair);
        System.out.printf("Distancia más corta: ");
        System.out.println(Div);
        
        System.out.println("\n-Divide into cuadrants: ");
        System.out.printf("Coordenadas: \n");
        double cuadrants=Cuadrants(0, P.length, P, closestPair);
        System.out.println("Distancia más corta: " + cuadrants);
        
    
    }
    public static double distance(int[][] P, int i, int j) 
    {
        //Obtiene las coordenadas de la matriz. 
        int x1 = P[i][0]; 
        int y1 = P[i][1];

        int x2 = P[j][0];
        int y2 = P[j][1];
        
        //Encontramos la distancia al cuadrado de las coordenadas
        double d = (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);

        return Math.sqrt(d); //Retorna la distancia entre las partículas.
    }

    private static double closestpair(int[][] P, int[][] closestPair) 
    {
        
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
    public static void matriz(int P[][]) 
    {
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
    static void bubbleSortforX(int[][] P) 
    {  
        int n = P.length;  
         for(int i=0; i < n; i++){  
            for(int j=0; j < (n-i-1); j++){  
                //compara para eje y
                if(P[j][0] > P[j+1][0]){ 
                            //matriz temporal para guardar números del array que se busca cambiar
                            int[] temp=P[j];
                            P[j] = P[j+1];  
                            P[j+1] = temp;  
                        }
                        if(P[j][0] == P[j+1][0]){ 
                            //compara y cambia con respecto a valores de y
                            if(P[j][1] > P[j+1][1]){ 
                                int[] temp= P[j];
                                P[j] = P[j+1];  
                                P[j+1] = temp;  
                            }
                        }
                          
                }  
         }  
    }
    
    //Encuentra el par más cercano en [b,c) utilizando el algoritmo Divide y vencerás no recursivo, dividiendo en mitades.
    public static double DivideYVenceras(int inicio, int fin, int P[][], int closestPair[][] )
    {
        
        //Inicializamos n con respecto a la cantidad de elementos que almacena las filas.  
        int n=P.length; 
        
        //Divide el dataset en subsets
        int[][] subb=new int [n/2][2]; 
        int [][]subc=new int[n/2][2];
        
        //Encuentra el par más cercano para el subset de b
        for (int i=0; i<(n/2);i++){
            for(int j=0; j!=2;j++){
                subb[i][j]=P[i][j];
            
            }
        }
        
        //Encuentra el par más cercano para el subset de c
        for (int i=(n/2);i<P.length;i++){
            for(int j=0;j<2;j++){
            subc[i-P.length/2][j]=P[i][j];
            }
        }
        
        
        //Imprime el par más cercano desde [b,c} en la consola. 
        double Dis1=closestpair(subb,closestPair);
        double Dis2=closestpair(subc, closestPair);
        double min=0; //Inicializa la distancia mínima
        
        //Obtiene la distancia más corta entre ambos subsets.
        if(Dis2>Dis1){
            min=Dis1;
        }else{
            min=Dis2;
        }
       
        int x1, x2,y1, y2; //Crea las variables para las coordenadas
        double d; //Crea la variable para la distancia entre las partículas
        
        for(int i=0;i!=subb.length;i++){
            for(int j=0;j!=subc.length;j++){
                
                x1=subb[i][0]; //Coordenadas de x para b
                y1=subb[i][1]; //Coordenadas de y para b
                x2=subc[j][0]; //Coordenadas de x para c
                y2=subc[j][1]; //Coordenadas de y para c 
               
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
       matriz(closestPair); //Imprime las coordenadas del par más cercano en la consola
       return min; //Retorna la distancia más corta entre el par más cercano.
    }
    
    //Ordena primeramente con respecto a X, y secundariamente con respecto a y.
    static void bubbleSortforY(int[][] P) 
    {  
        int n = P.length;  
         for(int i=0; i < n; i++){  
            for(int j=0; j < (n-i-1); j++){ 
                //compara para eje y
                if(P[j][1] > P[j+1][1]){ 
                            //matriz temporal para guardar números del array que se busca cambiar
                            int[] temp=P[j];
                            P[j] = P[j+1];  
                            P[j+1] = temp;  
                        }
                        if(P[j][0] == P[j+1][0]){ 
                            //compara y cambia con respecto a valores de x
                            if(P[j][0] > P[j+1][0]){ 
                                int[] temp= P[j];
                                P[j] = P[j+1];  
                                P[j+1] = temp;  
                            }
                        }
                          
                }  
         }  
    }
    
    //Encuentra el par más cercano en [b,e) utilizando el algoritmo Divide y vencerás no recursivo, dividiendo en cuadrantes.
    public static double Cuadrants(int inicio, int fin, int P[][], int closestPair[][]) 
    {
        
        //Inicializamos n con respecto a la cantidad de elementos que almacena las filas. 
        int n = P.length;
        
        //Divide el dataset en cuadrantes
        int quadb[][] = new int[n / 4][2];
        int quadc[][] = new int[n / 4][2];
        int quadd[][] = new int[n / 4][2];
        int quade[][] = new int[n / 4][2];
        bubbleSortforY(P);
        
        //Encuentra el par más cercano para el cuadrante de b
        for (int i = 0; i < (n / 4); i++) {
            for (int j = 0; j != 2; j++) {
                quadb[i][j] = P[i][j];

            }
        }
        //Encuentra el par más cercano para el cuadrante de c
        for (int i = (n / 4); i < (n / 2); i++) {
            for (int j = 0; j != 2; j++) {
                quadc[i - P.length / 4][j] = P[i][j];

            }
        }
        
        //Encuentra el par más cercano para el cuadrante de d
        for (int i = (n / 2); i < 3 * (n / 4); i++) {
            for (int j = 0; j != 2; j++) {
                quadd[i - n / 2][j] = P[i][j];

            }
        }
        
        //Encuentra el par más cercano para el cuadrante de e
        for (int i = 3 * (n / 4); i < P.length; i++) {
            for (int j = 0; j != 2; j++) {
                quade[i - 3 * (n / 4)][j] = P[i][j];

            }
        }
        //Imprime el par más cercano desde [b,e} en la consola.
        double Dis1 = closestpair(quadb, closestPair);
        double Dis2 = closestpair(quadc, closestPair);
        double Dis3 = closestpair(quadd, closestPair);
        double Dis4 = closestpair(quade, closestPair);
        double min = 0;
        
        //Obtiene la distancia más corta entre los cuadrantes
        if (Dis1 < Dis2 && Dis1 < Dis3 && Dis1 < Dis4) {
            min = closestpair(quadb, closestPair);
        } else if (Dis2 < Dis1 && Dis2 < Dis3 && Dis2 < Dis4) {
            min = closestpair(quadc, closestPair);
        } else if (Dis3 < Dis1 && Dis3 < Dis2 && Dis3 < Dis4) {
            min = closestpair(quadd, closestPair);
        } else if (Dis4 < Dis1 && Dis4 < Dis3 && Dis4 < Dis3) {
            min = closestpair(quade, closestPair);
        }
        
        int x1, x2, y1, y2; //Crea las variables para las coordenadas
        double d_min; //Crea la variable para la distancia más corta entre partículas
        
        for (int i = 0; i != quadb.length; i++) {
            for (int j = 0; j != quadc.length; j++) {
                for (int k = 0; k != quadd.length; k++) {
                    for (int l = 0; l != quade.length; l++) {
                        x1 = quadb[i][0]; //Coordenadas de x para b
                        y1 = quadb[i][1]; //Coordenadas de y para b
                        x2 = quadc[l][0]; //Coordenadas de x para c
                        y2 = quadc[l][1]; //Coordenadas de y para c
                        
                        //Encuentra la distancia al cuadrado de las coordenadas
                        d_min = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));

                        //Obtiene la distancia más corta (excluimos las distancias mayores que d_min)
                        //Compara y reemplaza la distancia al cuadrado y la cambia de ser necesario
                        if (d_min < min) {
                            min = d_min;
                            //Guardamos las coordenadas del par más cercano
                            closestPair[0][0] = x1;
                            closestPair[0][1] = y1;
                            closestPair[1][0] = x2;
                            closestPair[1][1] = y2;

                        }
                    }
                    x1 = quadb[i][0]; //Coordenadas de x para b
                    y1 = quadb[i][1]; //Coordenadas de y para b
                    x2 = quadd[k][0]; //Coordenadas de x para d
                    y2 = quadd[k][1]; //Coordenadas de y para d
                    
                    //Encuentra la distancia al cuadrado de las coordenadas
                    d_min = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
                    
                    //Obtiene la distancia más corta (excluimos las distancias mayores que d_min)
                    //Compara y reemplaza la distancia al cuadrado y la cambia de ser necesario
                    if (d_min < min) {
                        min = d_min;
                        //Guardamos las coordenadas del par más cercano
                        closestPair[0][0] = x1;
                        closestPair[0][1] = y1;
                        closestPair[1][0] = x2;
                        closestPair[1][1] = y2;
                    }
                }
                
                x1 = quadb[i][0]; //Coordenadas de x para b
                y1 = quadb[i][1]; //Coordenadas de y para b
                x2 = quade[j][0]; //Coordenadas de x para e
                y2 = quade[j][1]; //Coordenadas de y para e
                
                //Encuentra la distancia al cuadrado de las coordenadas
                d_min = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));

                //Obtiene la distancia más corta (excluimos las distancias mayores que d_min)
                //Compara y reemplaza la distancia al cuadrado y la cambia de ser necesario
                if (d_min < min) {
                    min = d_min;
                    //Guardamos las coordenadas del par más cercano 
                    closestPair[0][0] = x1;
                    closestPair[0][1] = y1;
                    closestPair[1][0] = x2;
                    closestPair[1][1] = y2;
                }

            }

        }

        for (int i = 0; i != quadc.length; i++) {
            for (int j = 0; j < quadd.length; j++) {
                for (int k = 0; k < quade.length; k++) {
                    x1 = quadc[i][0]; //Coordenadas de x para c
                    y1 = quadc[i][1]; //Coordenadas de y para c
                    x2 = quadd[k][0]; //Coordenadas de x para d
                    y2 = quadd[k][1]; //Coordenadas de y para d
                    
                    //Encuentra la distancia al cuadrado de las coordenadas
                    d_min = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
           
                    //Obtiene la distancia más corta (excluimos las distancias mayores que d_min)
                    //Compara y reemplaza la distancia al cuadrado y la cambia de ser necesario
                    if (d_min < min) {
                        min = d_min;
                        //Guardamos las coordenadas del par más cercano
                        closestPair[0][0] = x1;
                        closestPair[0][1] = y1;
                        closestPair[1][0] = x2;
                        closestPair[1][1] = y2;

                    }

                }
                x1 = quadc[i][0]; //Coordenadas de x para c
                y1 = quadc[i][1]; //Coordenadas de y para c
                x2 = quade[j][0]; //Coordenadas de x para e
                y2 = quade[j][1]; //Coordenadas de y para e
                
                //Encuentra la distancia al cuadrado de las coordenadas
                d_min = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
                
                //Obtiene la distancia más corta (excluimos las distancias mayores que d_min)
                //Compara y reemplaza la distancia al cuadrado y la cambia de ser necesario
                if (d_min < min) {
                    min = d_min;
                    //Guardamos las coordenadas del par más cercano
                    closestPair[0][0] = x1;
                    closestPair[0][1] = y1;
                    closestPair[1][0] = x2;
                    closestPair[1][1] = y2;

                }
            }
        }

        for (int i = 0; i < quadd.length; i++) {
            for (int j = 0; j < quade.length; j++) {
                x1 = quadd[i][0]; //Coordenadas de x para d
                y1 = quadd[i][1]; //Coordenadas de y para d
                x2 = quade[j][0]; //Coordenadas de x para e
                y2 = quade[j][1]; //Coordenadas de y para e
                
                //Encuentra la distancia al cuadrado de las coordenadas
                d_min = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
                
                //Obtiene la distancia más corta (excluimos las distancias mayores que d_min)
                //Compara y reemplaza la distancia al cuadrado y la cambia de ser necesario
                if (d_min < min) {
                    min = d_min;
                    //Guardamos las coordenadas del par más cercano
                    closestPair[0][0] = x1;
                    closestPair[0][1] = y1;
                    closestPair[1][0] = x2;
                    closestPair[1][1] = y2;

                }
            }

        }
        
        matriz(closestPair); //Imprime las coordenadas del par más cercano en la consola
        return min; //Retorna la distancia más corta entre el par más cercano.
    }
    
}
