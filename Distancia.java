/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vialactea;

/**
 *
 * @author Classe compartida
 */
public class Distancia {
        //PRE:  Cert.
        //POST: Retorna el valor del paramatre mÃ©s petit.
	private static float minim(float a, float b, float c) {
		return Math.min(Math.min(a, b), c);
	}
        //PRE:  Cert.
        /*POST: Retorna un float, que es la distancia de levensthein entre els strings str1 i str2.
                La distancia de levensthein es el numero minim d'operacions per a convertir
                un string A a un string B. Aquestes operacions son: borrar caracter, afegir
                caracter i canviar el caracter. Totes aquestes operacions incrementen la distancia 
                de levennsthein en 1.*/
	public static float levensthein(String str1,String str2) {
		float[][] distance = new float[str1.length() + 1][str2.length() + 1];
		for (int i = 0; i <= str1.length(); i++) distance[i][0] = i;
		for (int j = 1; j <= str2.length(); j++) distance[0][j] = j;
		for (int i = 1; i <= str1.length(); i++)
			for (int j = 1; j <= str2.length(); j++)
				distance[i][j] = minim(
						distance[i - 1][j] + 1,
						distance[i][j - 1] + 1,
						distance[i - 1][j - 1]+ ((str1.charAt(i - 1) == str2.charAt(j - 1)) ? 0 : 1));
		return distance[str1.length()][str2.length()];    
	}
        //PRE:  Cert.
        /*POST: Retorna un float que es la distancia euclida entre dos punts tridimensionals:
              punt1=(x1,y1,z1) i punt2=(x2,y2,z2).
              La distancia euclidea entre dos punts es la distancia en linea recta.*/
	public static float euclidea(double x1, double y1, double z1, double x2, double y2, double z2) {
		double d  = ((x2-x1)*(x2-x1))+((z2-z1)*(z2-z1))+((y2-y1)*(y2-y1));
		return (float)Math.sqrt(d);
	}        
        //PRE:  Cert.
        /*POST: Retorna un float que es la distancia euclida entre dos punts bidimensionals.
                punt1=(x1,z1) i punt2=(x2,z2).
                La distancia euclidea entre dos punts es la distancia en linea recta.*/
	public static float euclidea(double x1, double z1, double x2, double z2) {
		return (float)Math.hypot(x2-x1, z2-z1);
	}
}