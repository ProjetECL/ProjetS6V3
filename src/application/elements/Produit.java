package application.elements;

public class Produit extends Element{
    public Produit(String code, String nom, int quantite, String mesure, int prixAchat, int prixVente) {
        super(code, nom, quantite, mesure, prixAchat, prixVente);
    }
    
    public String toString(){
        return this.code + ";" + this.nom + ";"
               + this.quantite + ";" + this.unite + ";"
               + this.prixAchat + ";" + this.prixVente;
    }
    public String stockerL(){
        return "Cuve";
    }
    public String stockerS(){
        return "Entrepot";
    }
    public String getNom(){
        return this.nom;
    }
    
}

