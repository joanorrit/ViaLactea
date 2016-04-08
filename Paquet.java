/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vialactea;

import java.io.Serializable;
import java.util.*;

/**
 *
 * @author macia
 */
public class Paquet implements Serializable{
    
    private  boolean[][] paquets;
    private int Npaquet;
    private final Random r;
    private Cjt_Recursos recursos;
    private  int Nrecursos;
    private final long serialVersionUID = 7898088926259016259L;
    public Paquet(int N, Cjt_Recursos crec){
        Npaquet = N;
        recursos = crec;
        Nrecursos = crec.getNrecusrsos();
        paquets = new boolean[Npaquet][Nrecursos];
        r = new Random();        
    }
    
    public boolean [][] get_matriu() {
        return paquets;
    }
    
    public void init(){
        for(int i=0; i<Npaquet; ++i){
            for (int j=0; j < Nrecursos; ++j) {
                paquets[i][j] = false;
            }
        }
    }
     public void write(){
        for(int i=0; i<Npaquet; ++i){
            for(int j=0; j< Nrecursos; ++j){
                System.out.print(paquets[i][j] + " ");
            }
            System.out.println();
        }
    }
     
    public void escriu_Paquet(Cjt_Recursos cjtrecursos) {
        Recurs r = new Recurs();
        for(int i=0; i<Npaquet; ++i){
            System.out.println("El paquet " + i + " conte els recursos: ");
            for(int j=0; j< Nrecursos; ++j){
                if (paquets[i][j]) System.out.print(cjtrecursos.getRecurs(j).getNom() + " ");
            }     
            System.out.println();
        }    
    }
    
    public boolean valida_matriu() {
        int[] valid = new int[Nrecursos];
        for(int a=0;a < Nrecursos; ++a) valid[a] = 0;
        for (int i=0; i < Npaquet; ++i) {
            for (int j=0; j < Nrecursos; ++j) {
                if (paquets[i][j] == true) valid[j] = 1;
           }
        }
        int sum = 0;
        for(int k=0; k < Nrecursos; ++k) {
            sum += valid[k];
        }
        return (sum == Nrecursos);
    }
    
    public void rellenar_manual() {
        Recurs r = new Recurs();
        int id;
        String nom;
        Scanner input = new Scanner(System.in);
        for(int i=0; i<Npaquet; ++i){
                System.out.println("Introdueixi el numero de recursos del paquet " + i + ":");
                int nreci = input.nextInt();
                while (nreci >= Nrecursos) {
                    System.out.println("Introdueixi un valor mes petit que els recursos totals.");
                    nreci = input.nextInt();
                }
                for (int k = 0; k < nreci; ++k) {
                    System.out.println("Introdueixi el nom del recurs:");
                    nom = input.next();
                    r.setnom(nom);
                    if (!recursos.validaVectUniv(nom)) {
                        System.out.println("Recurs repetit o no existent, torni a introduir el nom.");
                        --k;
                    }
                    else {
                        id = recursos.buscaId(nom);
                        System.out.println("Id = " + id );
                        r.setid(id);
                        paquets[i][id] = true;
                    }                    
                }
        }
        if (!valida_matriu()) {
            System.out.println("Error al crear matriu Random.");
            System.out.println("Si us plau, torni a crear-la.");
            rellenar_manual();
        }
    }
    
    public void rellenar_Randomly() {
        for(int i=0; i<Npaquet; ++i){
            for(int j=0; j< Nrecursos; ++j){
                int id_rec = r.nextInt(Nrecursos);
                paquets[i][id_rec] = true;
            }
        }
        if (!valida_matriu()) {
            //System.out.println("Error al crear matriu Random.");
            //System.out.println("Si us plau, torni a crear-la.");
            rellenar_Randomly();
        }
    }
    public int Consultar_Npaquet(){
        return Npaquet;
    }
    public  void Afegir_Npaquet(int n){
        Npaquet = n;
    }
    public void Afegir_Nrecurs(int n){
        Nrecursos = n;
    }
    public void Consultar_contingut(int id, Cjt_Recursos cjtrecursos) {
        Recurs[] vrec = new Recurs[Nrecursos];
        Recurs r = new Recurs();
        for (int i = 0; i < Nrecursos; ++i) {
            if (paquets[id][i]) {
                r = cjtrecursos.getRecurs(i);
                vrec[i] = r;
            }
        }
        for (int j = 0; j < vrec.length; ++j){
             if (vrec[j] != null) System.out.print(vrec[j].getNom() + " ");
        }
        System.out.println();
    }

    public String getRecursosPaquet(int id, Cjt_Recursos crec) {
        //To change body of generated methods, choose Tools | Templates.
        Recurs[] vrec = new Recurs[Nrecursos];
        Recurs r = new Recurs();
        for (int i = 0; i < Nrecursos; ++i) {
            if (paquets[id][i]) {
                r = crec.getRecurs(i);
                vrec[i] = r;
            }
        }
        String s = " ";
        for (int j = 0; j < vrec.length; ++j){
             if (vrec[j] != null) s = s + vrec[j].getNom() + " ";
        }
        return s;
    }
}
