package Domini;

import java.util.Vector;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author David Puertas Aldea
 */


public class Algoritmo {
    
    private double cost_minim;
    private int[] solucio_mes_optima;
    private int[] posible_solucio;
    private double suma_cost_parcial;
    private boolean tinc_posible_solucio;
    private double[][] distancias;
    private double[][] similitudes;
    private int altura_minima;
    
    
 
    private boolean dentro_del_rango(double [][] costos_hongares,int i, int j){
        return (i >= 0) && (j>=0)  && (j < costos_hongares.length) && (i < costos_hongares.length);
    }
    
    private int cerca_profunditat(boolean [] files_taxades, boolean [] columnes_taxades,double [][] costos_hongares,int i,int j,int dir){
        int resultado = 0;
        if(!dentro_del_rango(costos_hongares,i,j))resultado = 0;
        else{
            if((costos_hongares[i][j] == 0) && !files_taxades[i] && !columnes_taxades[j])++resultado;
            if(dir == 1)resultado += cerca_profunditat(files_taxades,columnes_taxades,costos_hongares,i,j+1,1);
            if(dir == 2)resultado += cerca_profunditat(files_taxades,columnes_taxades,costos_hongares,i-1,j,2);
            if(dir == 3)resultado += + cerca_profunditat(files_taxades,columnes_taxades,costos_hongares,i,j-1,3);
            if(dir == 4)resultado += + cerca_profunditat(files_taxades,columnes_taxades,costos_hongares,i+1,j,4);
        }
        return resultado;
    }
    
    private int recubrir_zeros(double [][] costos_hongares,boolean [] files_taxades,boolean [] columnes_taxades,int [] ceros_fila,int [][] ceros_por_fila){
        //Inicialitzar vectors
        for(int i = 0; i < costos_hongares.length; ++i){
            files_taxades[i] = false;
            columnes_taxades[i] = false;
            ceros_fila[i] = 0;
        }
        //Per cada cero trobat taxar fila o columna
        int linees_colocades = 0;
        int right;
        int left;
        int top;
        int bottom;
        for(int i = 0; i < costos_hongares.length; ++i){
            for(int j = 0; j < costos_hongares.length;++j){
                if((costos_hongares[i][j] == 0) && !files_taxades[i] && !columnes_taxades[j]){
                    right = cerca_profunditat(files_taxades,columnes_taxades,costos_hongares,i,j,1);
                    left = cerca_profunditat(files_taxades,columnes_taxades,costos_hongares,i,j,3);
                    top = cerca_profunditat(files_taxades,columnes_taxades,costos_hongares,i,j,4);
                    bottom = cerca_profunditat(files_taxades,columnes_taxades,costos_hongares,i,j,2);
                    if((right + left) >= (top + bottom)){//Taxa fila
                        files_taxades[i] = true;
                        ++linees_colocades;
                    }
                    else{//Taxa columna
                        columnes_taxades[j] = true;
                        ++linees_colocades;
                    }
                }
                if(costos_hongares[i][j] == 0){
                    ceros_por_fila[i][ceros_fila[i]] = j;
                    ++ceros_fila[i];
                }
            }
        }
        
        return linees_colocades;
    }
    
    private void eliminar_posicio(int [][]ceros_per_fila,int [] ceros_fila,int i,int j){
        int aux;
        for(int k = j+1; k < ceros_fila[i];++k){
            aux = ceros_per_fila[i][k-1];
            ceros_per_fila[i][k-1] = ceros_per_fila[i][k];
            ceros_per_fila[i][k] = aux;
        }
        --ceros_fila[i];
    }
    
    private void actualitzar_contingut(int [][] ceros_per_fila,int[] ceros_fila,boolean [] files_taxades,boolean [] columnes_taxades,int i_e, int j_e){
        files_taxades[i_e] = true;
        columnes_taxades[j_e] = true;
        ceros_fila[i_e] = 0;
        for(int i = 0; i < ceros_per_fila.length; ++i){
            if(!files_taxades[i]){
                for(int j = 0; j < ceros_fila[i]; ++ j){
                    if(ceros_per_fila[i][j] == j_e)eliminar_posicio(ceros_per_fila,ceros_fila,i,j);
                }
            }
        }
    }
    
    private double hungarian(double[][] costos_hongares,double [][] costos_hongares_inicial){
        //Buscar el minimo valor de cada fila
        double [] minimo = new double [costos_hongares.length];
        for(int i = 0 ; i < costos_hongares.length; ++i){
            minimo[i] = costos_hongares[i][0];
            for(int j = 1; j < costos_hongares.length; ++j){
                if(costos_hongares[i][j] < minimo[i])minimo[i] = costos_hongares[i][j];
            }
        }
        //Restar en cada elemento de costos_hongares el minimo de esa fila
        for(int i = 0; i < costos_hongares.length;++i){
            for(int j = 0; j < costos_hongares.length;++j){
                costos_hongares[i][j] -= minimo[i];
            }
        }
        //Buscar el minimo de cada columna
        for(int i = 0; i < costos_hongares.length;++i){
            minimo[i] = costos_hongares[0][i];
            for(int j = 1; j < costos_hongares.length;++j){
                if(costos_hongares[j][i] < minimo[i])minimo[i] = costos_hongares[j][i];
            }
        }
        //Restar en cada elemento de costos_hongares el minimo de esa columna
        for(int i = 0; i < costos_hongares.length;++i){
            for(int j = 0; j < costos_hongares.length;++j){
                costos_hongares[i][j] -= minimo[j];
            }
            
        }
        boolean [] files_taxades = new boolean[costos_hongares.length];
        boolean [] columnes_taxades = new boolean[costos_hongares.length];
        int [] ceros_fila = new int [costos_hongares.length];
        int [][] ceros_per_fila = new int [costos_hongares.length][costos_hongares.length];
        
        int lineas_colocadas = recubrir_zeros(costos_hongares,files_taxades,columnes_taxades,ceros_fila,ceros_per_fila);
        
        double minim_no_cubert = 0;//Indiferent la inicialitzacio d'aquesta variable
        boolean primer;
        
        while(lineas_colocadas < costos_hongares.length){
            
            
            
            //Buscar minimo numero no cubierto
            primer = true;
            for(int i = 0; i < costos_hongares.length;++i){
                for(int j = 0; j < costos_hongares.length; ++j){
                    if(!files_taxades[i] && !columnes_taxades[j]){
                        if(!primer){
                            if(minim_no_cubert > costos_hongares[i][j]) minim_no_cubert = costos_hongares[i][j];
                        }
                        else{
                            minim_no_cubert = costos_hongares[i][j];
                            primer = false;
                        }
                    }
                }
            }
            
            //Sumar el minimo numero no cubierto a cada numero cubierto
            for(int i = 0; i < costos_hongares.length; ++i){
                for(int j = 0; j < costos_hongares.length; ++j){
                    if(files_taxades[i])costos_hongares[i][j] += minim_no_cubert;
                    if(columnes_taxades[j])costos_hongares[i][j] += minim_no_cubert;
                }
            }
            
            //Buscar minimo elemento de la matriz
            primer = true;
            double minim = 0; //Indiferent el valor d'inici
            for(int i = 0; i < costos_hongares.length;++i){
                for(int j = 0; j < costos_hongares.length;++j){
                    if(!primer){
                        if(minim > costos_hongares[i][j])minim = costos_hongares[i][j];
                    }
                    else{
                        minim = costos_hongares[i][j];
                        primer = false;
                    }
                }
            }
            //Resta minim element de la matriu a tots els elements
            for(int i = 0; i < costos_hongares.length;++i){
                for(int j = 0; j < costos_hongares.length;++j){
                    costos_hongares[i][j] -= minim;
                }
            }
            
            lineas_colocadas = recubrir_zeros(costos_hongares,files_taxades,columnes_taxades,ceros_fila,ceros_per_fila);
        }
        
       
        
        //Assignar i calcular coste
         double cost = 0;
         int assignacions_realitzades = 0;
         int i;
         boolean trobat;
         //Inicializar filas_taxadas i columnas taxadas
         for(i = 0; i < costos_hongares.length; ++i){
             files_taxades[i] = false;
             columnes_taxades[i] = false;
         }
         
         while(assignacions_realitzades != costos_hongares.length){
             i = 0;
             trobat = false;
             while(!trobat && i < costos_hongares.length){
                 trobat = !files_taxades[i] && (ceros_fila[i] == 1);
                 if(trobat){
                     cost += costos_hongares_inicial[i][ceros_per_fila[i][0]];
                     ++assignacions_realitzades;
                     actualitzar_contingut(ceros_per_fila,ceros_fila,files_taxades,columnes_taxades,i,ceros_per_fila[i][0]);
                 }
                 else ++i;
             }
             if(!trobat){//Forsar assignacio
                 int fila_minim_nombre_elements = 0;//Es indiferent l'inicialitzacio
                 boolean primera_fila = true;
                 for(i = 0; i < costos_hongares.length;++i){
                     if(primera_fila && !files_taxades[i]){
                         fila_minim_nombre_elements = i;
                         primera_fila = false;
                     }
                     else if(!files_taxades[i] && ceros_fila[fila_minim_nombre_elements] > ceros_fila[i])fila_minim_nombre_elements = i;
                 }
                 ++assignacions_realitzades;
                 cost += costos_hongares_inicial[fila_minim_nombre_elements][ceros_per_fila[fila_minim_nombre_elements][0]];
                 
                 actualitzar_contingut(ceros_per_fila,ceros_fila,files_taxades,columnes_taxades,fila_minim_nombre_elements,ceros_per_fila[fila_minim_nombre_elements][0]);
                 
             } 
         }
         
        return cost;
    }
    
    private double[][] copiar_matriu(double [][] hungarian){
        double[][] copia = new double [hungarian.length][hungarian.length];
        for(int i = 0; i < hungarian.length; ++i){
            for(int j = 0; j < hungarian.length; ++j){
                copia[i][j] = hungarian[i][j];
            }
        }
        return copia;
    }

    private boolean cota_gilmore_lawler(int posicio_a_colocar){
        //Construir matrius reduides
        int mida_matriu = posible_solucio.length-(posicio_a_colocar+1);
        double [][] reduida_distancies = new double[mida_matriu][mida_matriu];
        double [][] reduida_similituds = new double[mida_matriu][mida_matriu];
        for(int j = 0; j < mida_matriu;++j){
            for(int k = 0; k < mida_matriu;++k){
            reduida_distancies[j][k] = distancias[posicio_a_colocar + 1 + j][posicio_a_colocar + 1 + k];
            reduida_similituds[j][k] = similitudes[posible_solucio[posicio_a_colocar + 1 + j]][posible_solucio[posicio_a_colocar + 1 + k]];
            }
        }
        //Construir matriu C1 m(N-m)^2
        int[][] c1 = new int[mida_matriu][mida_matriu];
        for(int j = 0; j < mida_matriu; ++ j){
            for(int k = 0; k < mida_matriu; ++k){
                c1[j][k] = 0;
                for(int z = 0; z < (posicio_a_colocar+1); ++z){
                    c1[j][k] += similitudes[posible_solucio[j]][posible_solucio[z]] * distancias[k + posicio_a_colocar + 1][z];
                }
            }
        }
        //Construir matriu C2 cost (N-m)^3
        int[][] c2 = new int[mida_matriu][mida_matriu];
        for(int j = 0;j < mida_matriu; ++j){
            for(int k = 0;k < mida_matriu; ++k){
                c2[j][k] = 0;
                for(int z = 0; z < mida_matriu; ++z){
                    c2[j][k] += reduida_similituds[j][z] * reduida_distancies[k][mida_matriu - 1 - z];
                }
            }
        }

        //Construir matriu hungarian a partir de C1+C2
        double [][] hungarian = new double [mida_matriu][mida_matriu];
        for(int j = 0;j < mida_matriu; ++j){
            for(int k = 0; k < mida_matriu; ++k){
                hungarian[j][k] = c1[j][k] + c2[j][k];
            }
        }   
        double cost_hungarian = hungarian(hungarian,copiar_matriu(hungarian)); 
        return ((suma_cost_parcial + cost_hungarian) < cost_minim);
    }

    private void assignar_element_posicio(int posicio_a_colocar, int i){
        int aux = posible_solucio[i];
        posible_solucio[i] = posible_solucio[posicio_a_colocar];
        posible_solucio[posicio_a_colocar] = aux;
    }

    private void desasignar_element_posicio(int posicio_a_colocar,int i){
        int aux = posible_solucio[i];
        posible_solucio[i] = posible_solucio[posicio_a_colocar];
        posible_solucio[posicio_a_colocar] = aux;
    }
 
    private int obtenir_cost_del_nou_element(int posicio_a_colocar){
        int resultat = 0;
        for(int j = 0; j < posicio_a_colocar; ++j){
            resultat += ((this.distancias[j][posicio_a_colocar] * this.similitudes[posible_solucio[j]][posible_solucio[posicio_a_colocar]])+(this.distancias[posicio_a_colocar][j] * this.similitudes[posible_solucio[posicio_a_colocar]][posible_solucio[j]]));
        }
        return resultat;
    }

    private void algoritmo(int posicio_a_colocar){
	  //Cas Base
	  if(posicio_a_colocar == posible_solucio.length){
	    if(!tinc_posible_solucio){
	      cost_minim = suma_cost_parcial;
              solucio_mes_optima = posible_solucio.clone();
              tinc_posible_solucio = true;
	    }
            else {
	      cost_minim = suma_cost_parcial;
	      solucio_mes_optima = posible_solucio.clone();
	    }
	  }
          else{
	    //Cas recursiu
            int cost_iessim = 0;
	    for(int i = posicio_a_colocar; i < posible_solucio.length; ++i){
                assignar_element_posicio(posicio_a_colocar,i);
                cost_iessim = obtenir_cost_del_nou_element(posicio_a_colocar); // obtenir el cost del nou element incorporat a la posible solucio
                suma_cost_parcial += cost_iessim;
                boolean cota_gilmore = (suma_cost_parcial < cost_minim);
                if(tinc_posible_solucio && cota_gilmore && (((this.distancias.length) - posicio_a_colocar) > altura_minima))cota_gilmore = cota_gilmore_lawler(posicio_a_colocar);
                if(!tinc_posible_solucio || cota_gilmore){
                    algoritmo(posicio_a_colocar+1);
                }
                desasignar_element_posicio(posicio_a_colocar,i);//tornar a posar l'element que s'habia inserit en la solucio parcial al lloc original
                suma_cost_parcial -= cost_iessim; 
            }
             
	  }
     }
    
    /**
     * 
     @pre   El format de les dos matrius es el seguent: El contingut son double entre 0 i 1. El vector d'enters
     *      si te una mida = 0, llavors comensara per la solucio 1,2,...,n-1, si per el contrari solucio_inicial
     *      te una mida diferent de 0, aquest ha de contenir els enters 1,2,3....,n-1 en el ordre en que es vulgui
     *      inicialitzar la solucio inicial.
     @post  Retorna un vector de enters de mida distancias.lenght(), en el que la posicio iessima del vector es la mateixa que
     *      la posicio iessima de la matriu distancias i en contingut del vector resultat hi ha el jessim de la matriu similitudes,
     *      Es a dir que per l'iessim del vector resultant retorna el jessim element relacionan dos elements segons els costos
     *      indicats en les matrius
     */
    public int[] calcular(double[][] distancias, double[][] similitudes, int[] solucio_inicial,int altura_minima_gilmore){
       cost_minim = 0;
       suma_cost_parcial = 0;
       solucio_mes_optima = new int[0];
       tinc_posible_solucio = false;
       this.distancias = distancias;
       this.similitudes = similitudes;
       this.altura_minima = altura_minima_gilmore;
       
        if(solucio_inicial.length == 0){
            posible_solucio = new int[distancias.length];
            for(int i = 0; i < distancias.length; ++i) posible_solucio[i] = i;
        }
        else{
            posible_solucio = solucio_inicial;
        }
        System.out.println("arribodosdddd");
        algoritmo(0);
        System.out.println("arribodos");
        return solucio_mes_optima;
    }
}