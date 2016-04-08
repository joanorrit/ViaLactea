/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vialactea;

import java.io.Serializable;

/**
 *
 * @author jonatan.lopez.gonzalez
 */
public class Posicio implements Serializable{
    private double x;
    private double y;
    public Posicio() {
        x = 0;
        y = 0;
    }
    public void setPosx(double ab) {
        x = ab;
    }
    public void setPosy(double ord) {
        y = ord;
    }
    public double getPosx() {
        return x;
    }
    public double getPosy() {
        return y;
    }
}
