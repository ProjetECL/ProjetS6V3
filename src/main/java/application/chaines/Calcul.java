package application.chaines;


import application.elements.*;
import application.fichiers.CSV;
import application_projet.model.AssociationChProdElement;
import com.sun.xml.internal.bind.v2.runtime.output.NamespaceContextImpl;
import javafx.collections.ObservableList;

import static application.fichiers.CSV.SEP;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;

/**
 *
 * @author lea
 */
public class Calcul {

    private List<ChProduction> chaines ;
    private HashMap<String,Element> elements;

    /**
     *  Actualiser la liste de chaine de production lorsqu'il y a un changement
     */
    public void setChaineProductionList(List<ChProduction> chaineProductionList) {
        this.chaines = chaineProductionList;
    }

    /**
     * Evaluer toute la production
     * @return La liste des elements avec le stock actualisé selon la production
     * @throws Exception
     */
    public List<Element> evaluer()throws Exception{
        Element element;
        for (Iterator iter =  Element.csvVersElement().iterator(); iter.hasNext();){
           System.out.println(iter.next());
           element = (Element) iter.next();
           this.elements.put(element.getCode(),element);
        }

        for(Iterator iter = chaines.iterator();iter.hasNext();){
            this.evaluerChaine((ChProduction)iter.next());
        }
        return (List)this.elements.values();
    }

    /**
     * Evaluer la production d'une chaine de production
     * @param ch
     * @throws Exception
     */
    private void evaluerChaine(ChProduction ch)throws Exception{
        for(AssociationChProdElement asso : ch.getEntree()){
            elements.get(asso.getIdElement()).quantite -= asso.getQuantite()*ch.getNiveau();
            if(elements.get(asso.getIdElement()).quantite<0 && elements.get(asso.getIdElement()).getPrixAchat()==-1){
                throw new Exception();
            }
        }
    }

    /**
     * Determine la liste des elements a acheter
     * @return une liste d'element
     * @throws Exception
     */
    public List<Element> achat() throws Exception {
        List<Element> elemAchat = new ArrayList<Element>() {
        };
        for(Element element : this.evaluer()){
            if(element.quantite<0){
                elemAchat.add(element);
            }
        }
        return elemAchat;
    }

    /**
     * Calcul l'efficacite de toute la production
     * @return un int representant l'efficacite de la production
     * @throws Exception
     */
    public int efficacite() throws Exception {
        int efficacite=0;
        for(ChProduction chaine : this.chaines){
            for(AssociationChProdElement asso : chaine.getSortie()){
                efficacite+=elements.get(asso.getIdElement()).getPrixVente();
            }
            for(Element element : this.achat()){
                efficacite-=element.getPrixAchat();
            }
        }
        return efficacite;
    }


    /**
     * @param liste de tous les �l�ments de la HashMap
     * @param e l'�l�ment dont on cherche � d�terminer son stock
     * @return le stock de cet �l�ment de la cha�ne de production
     */
//    public int defStock(HashMap<String, Integer> liste, Element e){
//        Iterator i = liste.keySet().iterator();
//        int total=0;
//        while (i.hasNext()){
////            String nom = liste.get(liste.keySet()).getNom();
////            if (nom.equals(e.getNom())) {
////                total++;
////            }
//        }
//        return total;
//    }
    /**
     * @param liste de tous les �l�ments d'une liste de noms d'�l�ments
     * @param nomElement (nom de l'�l�ment) dont on cherche � d�terminer son stock
     * @return le stock de cet �l�ment
     */
//    public int defStock(ArrayList<String> liste, String nomElement){
//        int total=0;
//        for (int i=0; i<liste.size(); i++){
//            String nom = liste.get(i);
//            if (nom.equals(nomElement)) {
//                total++;
//            }
//        }
//        return total;
//    }
    /**
     * M�thode qui permet de stocker au m�me endroit les �l�ments et le nombre 
     * en stock selon les donn�es du fichier CSV
     * @throws IOException 
     */
//    public void resumeStock() throws IOException{
//        /**
//         * Stockage de chaque �l�ment par leur nom dans un tableau dynamique
//         */
//        ArrayList<String> tableau = new ArrayList();
//        BufferedReader reader = null;
//        try {
//            reader = new BufferedReader(new FileReader("/CSV/elements.csv"));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] dataLigne = line.split(SEP);
//                tableau.add(dataLigne[1]);
//            }
//        }catch(FileNotFoundException ex) {
//                ex.printStackTrace();
//        }finally {
//                reader.close();
//        }
//        /**
//         * Parcours de ce tableau dynamique pour d�finir les r�currences de noms
//         * Stockage dans une HashMap
//         */
//        HashMap<String, Integer> map = new HashMap();
//        for (int i=0; i<tableau.size(); i++){
//            String comparateur = tableau.get(i);
//            int total = this.defStock(tableau, comparateur);
//            map.put(comparateur, total);
//        }
    }



