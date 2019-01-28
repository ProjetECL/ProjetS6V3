package application.elements;

public abstract class Element implements Liquide, Solide{
    protected String code;
    protected String nom;
    protected String unite;
    protected int quantite;
    protected int prixAchat;
    protected int prixVente;

    public Element(String code, String nom, int quantite, String mesure, int prixAchat, int prixVente) {
        this.code = code;
        this.nom = nom;
        this.quantite = quantite;
        this.unite = mesure;
        this.prixAchat = prixAchat;
        this.prixVente = prixVente;
    }
    
    public abstract String toString();
    public abstract String getNom();
}