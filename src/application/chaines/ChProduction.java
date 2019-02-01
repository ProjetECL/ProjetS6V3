package application.chaines;


import application.elements.*;
import application.fichiers.CSVV;

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
    private HashMap<String, Integer> entree = new HashMap();
    private HashMap<String, Integer> sortie = new HashMap();
    private int nivActivation;
    private String moyenStock;
    private boolean dispoStock;

    public ChProduction(String code, String nom) {
        this.code = code;
        this.nom = nom;
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
    					// "code;nom;elementsenentree;elementensortie;"
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
    
    /*public static List<Element> csvVersChaines() {
    	
    	List<Element> chaines = new ArrayList<Element>();
    	
		CSVV.lecture("chaines.csv").forEach(csvRecord -> {
			
			chaines.add(new Element(csvRecord.get("code"), Integer.parseInt(csvRecord.get("quantite")), csvRecord.get("nom"), csvRecord.get("unite"), Integer.parseInt(csvRecord.get("achat")), Integer.parseInt(csvRecord.get("vente"))));
		});
		
		
		System.out.println(chaines.toString());
    		
		return chaines;
    }*/
    
    
    /**
     * 1) récupère les données des cases du CSV
     * 2) on s'occupe de la case 2 : tous les éléments en entrée
     * 3) Séparer par les virgules et les stocker dans un tableau 
     * 4) (E001, 2),(E002,2) ==> {(E001, 2), (E002,2)}
     *                            [0]   [1]   [2]  [3]
     * 4) Bis : on supprime les parenthèses ouvrantes des cases paires et les parenthèses fermantes des cases impaires
     * 5) a) On récupère les codes des éléments --> clé de la HashMap (sous forme de tableau?)
     *    b) On récupère les quantités respectives des éléments (sous forme de tableau?)
     * 6) Remplir la HashMap
     * 7) Refaire pour les éléments en sortie
     * @throws IOException 
     * 
     */
    
    
   /**
    * Separation des donnees d'un String selon ","
    * Stockage de ces donnees dans une arraylist
    * @param texte que l'on souhaite traiter
    * @param sep qui separe les elements du texte
    * @return une liste 
    */
    public static ArrayList<String> sepCasesVirgule(String texte, String sep){		
		StringTokenizer tokenizer = new StringTokenizer(texte, sep);
		ArrayList<String> tmp = new ArrayList<String>();
		
		while(tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			tmp.add(token);
		}    	
    	return tmp;
    }
    /**
     * Recuperer le code de l'element present dans les entrees de la chaine de production
     * @param s : String dans lequel on cherche à retirer la parenthese ouvrante
     * @return le code de l'element
     */
    public static String getCodeElement(String s) {
		char[] tab = new char[5];
		tab = s.toCharArray();
		String s2 = "";
		for (int i=0; i<4; i++) {
			tab[i]=tab[i+1];
			s2+=tab[i];
		}
		return s2;
    }
    /**
     * Recuperer la quantite de l'element present dans les entrees de la chaine de production
     * @param ss : String dans lequel on cherche à retirer la parenthese fermante
     * @return la quantite de l'element
     */
    public static int getQuantiteElement(String ss) {
		char[] tab2 = ss.toCharArray();
		String ss2="";
		int j=0;
		do {
			ss2+=tab2[j];
			j++;
		}while (j<tab2.length && tab2[j]!=')');
		int val = Integer.parseInt(ss2);
		return val;
		
    }
    /**
     * Créer une liste de chaines de production selon le fichier chaines.csv
     * @return la liste
     * @throws IOException
     */
    public static List<ChProduction> csvVersChaines() throws IOException{
    	List<ChProduction> liste = new ArrayList<ChProduction>();
    	
    	BufferedReader reader = null;
    	try {
    		reader = new BufferedReader(new FileReader("chaines.csv"));
    		String line;
    		
    		//Parcours de l'ensemble des lignes du fichiers une par une
    		 
    		
    		while ((line = reader.readLine()) != null) {
    			String[] dataLigne = line.split(";");
    			
    			//Creation de la Chaine de production de cette ligne
    			ChProduction ch = new ChProduction(dataLigne[0], dataLigne[1]);
    			
    			/**
    			 * ELEMENTS EN ENTREE
    			 */
    			//Elements en entree avec leur quantite respectives
    			String donneesEntree = dataLigne[2];
    			
    			//Initialisation des ArrayList pour les code des elements et leur quantite
    			ArrayList<String> elE = new ArrayList<String>();
    			ArrayList<Integer> qtE = new ArrayList<Integer>();
    			
    			//Tableau avec les donnees entre les virgules dans chaque case
    			ArrayList<String> tmp = sepCasesVirgule(donneesEntree, ",");
    			elE.add(getCodeElement(tmp.get(0)));
    			for(int k=1;k<tmp.size(); k++) {
    				if (k%2==0) {
    					elE.add(getCodeElement(tmp.get(k)));
    				}
    				else {
    					qtE.add(getQuantiteElement(tmp.get(k)));
    				}
    			}
    			
    			//Ajout infos à la HashMap entree
    			for (int i=0; i<elE.size(); i++) {
    				ch.entree.put(elE.get(i), qtE.get(i));
    			}
    			
    			
    			/**
    			 * ELEMENTS EN SORTIE
    			 */
    			//Elements en sortie avec leur quantite respectives
    			String donneesSortie = dataLigne[3];
    			
    			//Initialisation des ArrayList pour les code des elements et leur quantite
    			ArrayList<String> elS = new ArrayList<String>();
    			ArrayList<Integer> qtS = new ArrayList<Integer>();
    			
    			//Tableau avec les donnees entre les virgules dans chaque case
    			ArrayList<String> tmp2 = sepCasesVirgule(donneesSortie, ",");
    			elS.add(getCodeElement(tmp2.get(0)));
    			for(int j=1;j<tmp2.size(); j++) {
    				if (j%2==0) {
    					elS.add(getCodeElement(tmp2.get(j)));
    				}
    				else {
    					qtS.add(getQuantiteElement(tmp2.get(j)));
    				}
    			}
    			
    			//Ajout infos à la HashMap sortie
    			for (int i=0; i<elE.size(); i++) {
    				ch.sortie.put(elS.get(i), qtS.get(i));
    			}
    			
    			/**
    			 * Ajout de la chaine de production à la liste
    			 */
    			
    			liste.add(ch);
	
    		}
    	}catch(FileNotFoundException ex) {
    		ex.printStackTrace();
    	}finally {
    		reader.close();
    	}
    	return liste;
    }
    
    
    
    /**
     * Méthode de recherche d'éléments de même que celle pour les chaines de production
     */
    
    
    
}

