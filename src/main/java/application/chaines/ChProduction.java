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
	private static final String TAG = "ChProduction";
	private String code;
    private String nom;
    private List<AssociationChProdElement> entree = new ArrayList<AssociationChProdElement>();
    private  List<AssociationChProdElement> sortie = new ArrayList<AssociationChProdElement>();
    private int nivActivation;
    private String moyenStock;
    private boolean dispoStock;

    public ChProduction(String code, String nom) {
        this.code = code;
        this.nom = nom;
    }

	public ChProduction(String code, String nom, List<AssociationChProdElement> entree,List<AssociationChProdElement> sortie) {
		this.code = code;
		this.nom = nom;
		this.entree=entree;
		this.sortie=sortie;
	}



	public void addElementEntree(Element e) throws IOException{
        //utiliser la partie CSV
    }
    
    public int setNiveau(int n){
        this.nivActivation = n;
        return getNiveau();
    }

	public List<AssociationChProdElement> getSortie() {
		return sortie;
	}

	public List<AssociationChProdElement> getEntree() {
		return entree;
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

	/**
	 * Créer une liste de chaines de production selon le fichier chaines.csv
	 * @return la liste
	 */
    public static List<ChProduction> csvVersChainesTest() {
    	
    	List<ChProduction> chaines = new ArrayList<ChProduction>();
    	
		CSVV.lecture("src/chaines.csv").forEach(csvRecord -> {
			Log.d(TAG,csvRecord.toMap().toString());
			List<AssociationChProdElement> entree = new ArrayList<>();
			List<AssociationChProdElement> sortie = new ArrayList<>();
			List<AssociationChProdElement>  asso = AssociationChProdElement.csvVersAssociation();
			asso.forEach(new Consumer<AssociationChProdElement>() {
				@Override
				public void accept(AssociationChProdElement a ){
					if(csvRecord.get("code").equals(a.getIdChaineProd())){
						switch (a.getTypeElement()){
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

			chaines.add(new ChProduction(csvRecord.get("code"),csvRecord.get("nom"),entree,sortie));
		});
    		
		return chaines;
    }
    
    
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
     * Méthode de recherche d'éléments de même que celle pour les chaines de production
     */

	@Override
	public String toString() {
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

