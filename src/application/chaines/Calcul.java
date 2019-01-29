package application.chaines;


import application.elements.*;
import application.fichiers.CSV;
import static application.fichiers.CSV.SEP;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author lea
 */
public class Calcul {
    /**
     * @param liste de tous les �l�ments de la HashMap
     * @param e l'�l�ment dont on cherche � d�terminer son stock
     * @return le stock de cet �l�ment de la cha�ne de production
     */
    public int defStock(HashMap<String, Element> liste, Element e){
        Iterator i = liste.keySet().iterator();
        int total=0;
        while (i.hasNext()){
            String nom = liste.get(liste.keySet()).getNom();
            if (nom.equals(e.getNom())) {
                total++;
            }
        }
        return total;
    }
    /**
     * @param liste de tous les �l�ments d'une liste de noms d'�l�ments
     * @param nomElement (nom de l'�l�ment) dont on cherche � d�terminer son stock
     * @return le stock de cet �l�ment
     */
    public int defStock(ArrayList<String> liste, String nomElement){
        int total=0;
        for (int i=0; i<liste.size(); i++){
            String nom = liste.get(i);
            if (nom.equals(nomElement)) {
                total++;
            }
        }
        return total;
    }
    /**
     * M�thode qui permet de stocker au m�me endroit les �l�ments et le nombre 
     * en stock selon les donn�es du fichier CSV
     * @throws IOException 
     */
    public void resumeStock() throws IOException{
        /**
         * Stockage de chaque �l�ment par leur nom dans un tableau dynamique
         */
        ArrayList<String> tableau = new ArrayList();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/CSV/elements.csv"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] dataLigne = line.split(SEP);
                tableau.add(dataLigne[1]);
            }
        }catch(FileNotFoundException ex) {
                ex.printStackTrace();
        }finally {
                reader.close();
        }
        /**
         * Parcours de ce tableau dynamique pour d�finir les r�currences de noms
         * Stockage dans une HashMap
         */
        HashMap<String, Integer> map = new HashMap();
        for (int i=0; i<tableau.size(); i++){
            String comparateur = tableau.get(i);
            int total = this.defStock(tableau, comparateur);
            map.put(comparateur, total);
        }
    }
}

