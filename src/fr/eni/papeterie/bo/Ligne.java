package fr.eni.papeterie.bo;

public class Ligne {

    protected int qte;

    private Article article;


    public Ligne(Article article, int qte) {
        this.article = article;
        this.qte = qte;
    }

    public Article getArticle() {
        return article;
    }

    private void setArticle(Article article) {
        this.article = article;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public float getPrix() {
        return this.article.getPrixUnitaire();} //This pour préciser que c'est l'article de l'instance


    @Override
    public String toString() {
        return "Ligne{" +
                "qte=" + qte +
                ", article=" + article +
                '}';
    }
}




