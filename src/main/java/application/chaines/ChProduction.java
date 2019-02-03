package application.chaines;


import application.elements.*;
import application.fichiers.CSVV;
import application_projet.model.AssociationChProdElement;
import utils.Log;

import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.function.Consumer;

/**
 *
 * @author lea
 */
public class ChProduction {
   // private static final String TAG = "ChProduction";
    private String code;
    private String nom;
    private List<AssociationChProdElement> entree = new ArrayList<AssociationChProdElement>();
    private List<AssociationChProdElement> sortie = new ArrayList<AssociationChProdElement>();
    private int nivActivation;
    private String moyenStock;
    private boolean dispoStock;

    public ChProduction(String code, String nom) {
        this.code = code;
        this.nom = nom;
    }

    public ChProduction(String code, String nom, List<AssociationChProdElement> entree, List<AssociationChProdElement> sortie) {
        this.code = code;
        this.nom = nom;
        this.entree = entree;
        this.sortie = sortie;
    }



    public int setNiveau(int n) {
        this.nivActivation = n;
        return getNiveau();
    }

    public List<AssociationChProdElement> getSortie() {
        return sortie;
    }

    public List<AssociationChProdElement> getEntree() {
        return entree;
    }


    public int getNiveau() {
        return this.nivActivation;
    }

    /**
     * Méthode qui permet de rechercher dans le fichier, la ligne contenant la chaine de production dont le code et le nom
     * sont renseignés et de l'afficher
     *
     * @param code
     * @param nom
     * @return la ligne du fichier en String
     * @throws IOException
     */
    public String choixChaineProduction(String code, String nom) throws IOException {
        ChProduction ch;
        String chaine = null;
        BufferedReader reader = null;
        try {
            /**
             * Faire attention au chemin des fichiers et a celui du ou des fichiers que l'utilisateur souhaite creer
             */
            reader = new BufferedReader(new FileReader("/CSV/chaines.csv"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] dataLigne = line.split(";");
                if (dataLigne[0].equals(code) && dataLigne[1].equals(nom)) {
                    for (int i = 0; i < dataLigne.length; i++) {
                        chaine += dataLigne[i] + ";";
                        // "code;nom;elementsenentree;elementensortie;"
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            reader.close();
        }
        return chaine;
    }

    /**
     * Créer une liste de chaines de production selon le fichier chaines.csv
     *
     * @return la liste
     */
  public static List<ChProduction> csvVersChainesTest() {

        List<ChProduction> chaines = new ArrayList<ChProduction>();

        CSVV.lecture("src/chaines.csv").forEach(csvRecord -> {
            //Log.d(TAG,csvRecord.toMap().toString());
            List<AssociationChProdElement> entree = new ArrayList<>();
            List<AssociationChProdElement> sortie = new ArrayList<>();
            List<AssociationChProdElement> asso = AssociationChProdElement.csvVersAssociation();
            asso.forEach(new Consumer<AssociationChProdElement>() {
                @Override
                public void accept(AssociationChProdElement a) {
                    if (csvRecord.get("code").equals(a.getIdChaineProd())) {
                        switch (a.getTypeElement()) {
                            case ENTREE:
                                entree.add(a);
                                break;
                            case SORTIE:
                                sortie.add(a);
                                break;
                        }
                    }
                }
            });
            chaines.add(new ChProduction(csvRecord.get("code"), csvRecord.get("nom"), entree, sortie));

        });

        return chaines;
    }

        /**
         * Méthode de recherche d'éléments de même que celle pour les chaines de production
         */

       @Override
        public String toString(){
            return "ChProduction{" +
                    "code='" + code + '\'' +
                    ", nom='" + nom + '\'' +
                    ", entree=" + entree +
                    ", sortie=" + sortie +
                    ", nivActivation=" + nivActivation +
                    ", moyenStock='" + moyenStock + '\'' +
                    ", dispoStock=" + dispoStock +
                    '}';
        }


}

