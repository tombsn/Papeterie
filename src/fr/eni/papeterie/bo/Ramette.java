package fr.eni.papeterie.bo;

public class Ramette extends Article{

    int grammage;

    public Ramette(Integer idArticle, String ref, String marque, String designation, float pU, int qte, int grammage) {
        super(idArticle, ref, marque, designation, pU, qte);
        this.grammage = grammage;
    }

    public Ramette (String marque, String ref , String designation, float pU, int qte, int grammage){
        super(marque, ref, designation, pU, qte);
        this.grammage = grammage;
    }

    public int getGrammage() {
        return grammage;
    }

    public void setGrammage(int grammage) {
        this.grammage = grammage;
    }

    @Override
    public String toString() {
        return super.toString() + "Ramette{" +
                "grammage=" + grammage +
                '}';
    }
}
