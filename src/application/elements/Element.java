package application.elements;

public class Element{
    protected String code;
    protected String nom;
    protected String unite;
    protected int quantite;
    protected int prixAchat;
    protected int prixVente;
    /**
     * Constructeur qui permet d'initialiser l'élément qui intéresse l'utilisateur
     * @param code de l'élément (ex : E001)
     * @param nom de l'élément
     * @param quantite
     * @param mesure (kilogramme, littre...)
     * @param prixAchat
     * @param prixVente
     */
    public Element(String code, String nom, int quantite, String mesure, int prixAchat, int prixVente) {
        this.code = code;
        this.nom = nom;
        this.quantite = quantite;
        this.unite = mesure;
        this.prixAchat = prixAchat;
        this.prixVente = prixVente;
    }
    /**
     * Création d'une chaîne de caractère avec les informations de l'élément en question
     */
    public String toString(){
        return this.code + ";" + this.nom + ";"
               + this.quantite + ";" + this.unite + ";"
               + this.prixAchat + ";" + this.prixVente;
    }
    /**
     * Accès au nom de l'élément
     * @return le nom de l'élément
     */
    public String getNom(){
        return this.nom;
    }
    /**
     * Accès au code de l'élément
     * @return le code de l'élément
     */
    public String getCode() {
    	return this.code;
    }
}