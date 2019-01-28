package application.chaines;


import application.elements.*;
import java.io.*;
import java.lang.*;
import java.util.*;
/**
 *
 * @author lea
 */
public class ChProduction {
    private String code;
    private String nom;
    private HashMap<String, Element> entree = new HashMap();
    private HashMap<String, Element> sortie = new HashMap();
    private int nivActivation;
    private String moyenStock;
    private boolean dispoStock;

    public ChProduction(String code, String nom, int nivActivation, String moyenStock, boolean dispoStock) {
        this.code = code;
        this.nom = nom;
        this.nivActivation = nivActivation;
        this.moyenStock = moyenStock;
        this.dispoStock = dispoStock;
    }
    
    public void addElementEntree(Element e) throws IOException{
        //utiliser la partie CSV
    }
    
    public int setNiveau(int n){
        this.nivActivation = n;
        return getNiveau();
    }
    
    public int getNiveau(){
        return this.nivActivation;
    }
}

