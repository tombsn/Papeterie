package fr.eni.papeterie.bo;

public abstract class Article {

    private Integer idArticle;
    private String reference, marque, designation;
    private float prixUnitaire;
    private int qteStock;


    public Article(Integer idArticle, String ref, String marque, String designation, float pU, int qte) {
        this.idArticle = idArticle;
        this.reference = ref;
        this.marque = marque;
        this.designation = designation;
        this.prixUnitaire = pU;
        this.qteStock = qte;
    }

    public Article() {
    }

    public Article(String ref, String marque, String designation, float pU, int qte) {
        this.reference = ref;
        this.marque = marque;
        this.designation = designation;
        this.prixUnitaire = pU;
        this.qteStock = qte;
    }

    public Integer getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(Integer idArticle) {
        this.idArticle = idArticle;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public float getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(float prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public int getQteStock() {
        return qteStock;
    }

    public void setQteStock(int qteStock) {
        this.qteStock = qteStock;
    }

    @Override
    public String toString() {
        return "Article{" +
                "idArticle=" + idArticle +
                ", reference='" + reference + '\'' +
                ", marque='" + marque + '\'' +
                ", designation='" + designation + '\'' +
                ", prixUnitaire=" + prixUnitaire +
                ", qteStock=" + qteStock +
                '}';
    }
}
