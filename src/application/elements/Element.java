package application.elements;

import java.util.ArrayList;
import java.util.List;

import application.fichiers.CSVV;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Element {

   public String code;
   public float quantite;
   protected String nom;
   protected String unite;
   protected int prixAchat;
   protected int prixVente;

   public Element() {
	   this.code = null;
	   this.quantite = 0;
       this.nom = null;
       this.unite = null;
       this.prixAchat = 0;
       this.prixVente = 0;
   }

   public Element(String code, float quantite, String nom, String unite, int prixAchat, int prixVente) {
	   
	   this.code = code;
	   this.quantite = quantite;
       this.nom = nom;
       this.unite = unite;
       this.prixAchat = prixAchat;
       this.prixVente = prixVente;
   }

	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public float getQuantite() {
		return quantite;
	}
	
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getUnite() {
		return unite;
	}
	
	public void setUnite(String unite) {
		this.unite = unite;
	}
	
	public int getPrixAchat() {
		return prixAchat;
	}
	
	public void setPrixAchat(int prixAchat) {
		this.prixAchat = prixAchat;
	}
	
	public int getPrixVente() {
		return prixVente;
	}
	
	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	@Override
	public String toString() {
		return "Person [code=" + code + ", quantite=" + quantite + ", nom=" + nom + ", unite=" + unite + ", prixAchat="
				+ prixAchat + ", prixVente=" + prixVente + "]";
	}
	
	public static List<Element> csvVersElement() {
    	
    	List<Element> elements = new ArrayList<Element>();
    	
		CSVV.lecture("elements.csv").forEach(csvRecord -> {
			
			elements.add(new Element(csvRecord.get("code"), Integer.parseInt(csvRecord.get("quantite")), csvRecord.get("nom"), csvRecord.get("unite"), Integer.parseInt(csvRecord.get("achat")), Integer.parseInt(csvRecord.get("vente"))));
		});
		
		System.out.println(elements.toString());
    		
		return elements;
    }

}