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
    
    /**
     * Méthode qui permet de rechercher dans le fichier, la ligne contenant la chaine de production dont le code et le nom 
     * sont renseignés et de l'afficher
     * @param code
     * @param nom
     * @return la ligne du fichier en String
     * @throws IOException
     */
    public String choixChaineProduction(String code, String nom) throws IOException{
    	ChProduction ch;
    	String chaine = null;
    	BufferedReader reader = null;
    	try {
    		/**
    		 * Faire attention au chemin des fichiers et a celui du ou des fichiers que l'utilisateur souhaite creer
    		 */
    		reader = new BufferedReader(new FileReader("/CSV/chaines.csv"));
    		String line;
    		while ((line = reader.readLine())!= null) {
    			String[] dataLigne = line.split(";");
    			if (dataLigne[0].equals(code)&& dataLigne[1].equals(nom)) {
    				for (int i=0; i<dataLigne.length;i++) {
    					chaine+=dataLigne[i]+";";
    				}
    			}
    		}
    	}catch(FileNotFoundException ex) {
    		ex.printStackTrace();
    	}finally {
    		reader.close();
    	}
    	return chaine;
    }
}

