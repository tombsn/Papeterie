package fr.eni.papeterie.bo;

public class Stylo extends Article {

    String couleur;


    public Stylo(Integer idArticle, String ref, String marque, String designation, float pU, int qte, String couleur) {
        super(idArticle, ref, marque, designation, pU, qte);
        this.couleur = couleur;
    }

    public Stylo(String marque, String ref, String designation, float pU, int qte, String couleur) {
        super(marque, ref, designation, pU, qte);
        this.couleur = couleur;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    @Override
    public String toString() {
        return super.toString() + "Stylo{" +
                "couleur='" + couleur + '\'' +
                '}';
    }
}
