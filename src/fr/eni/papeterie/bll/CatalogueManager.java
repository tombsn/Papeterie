package fr.eni.papeterie.bll;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;
import fr.eni.papeterie.dal.jbdc.ArticleDAO;
import fr.eni.papeterie.dal.jbdc.DALException;
import fr.eni.papeterie.dal.jbdc.DAOFactory;

import java.util.List;

public class CatalogueManager {

    private ArticleDAO daoArticle;

    //Constructeur.
    private CatalogueManager() {
        this.daoArticle = DAOFactory.getArticleDAO(); //daoArticle représente la DAL
    }

    private static CatalogueManager instance;

    //Création de l'instance
    public static CatalogueManager getInstance() {
        if (instance == null) {
            instance = new CatalogueManager();
        }
        return instance;
    }

    public List<Article> getCatalogue() throws BLLException{
        List<Article> catalogue = null;
        try {
           catalogue = daoArticle.selectAll();
        } catch (DALException e) {
            throw new BLLException("Erreur dans la méthode getCatalogue");
        }return catalogue;
    }

    public void addArticle(Article a) throws BLLException{
        try {
             this.validerArticle(a);
             daoArticle.insert(a);
        }catch (DALException e) {
            throw new BLLException("Erreur dans la méthode addArticle");
        }
    }

    public void updateArticle(Article a) throws BLLException {
        try {
            this.validerArticle(a);
            daoArticle.update(a);
        } catch (DALException e) {
            throw new BLLException("Erreur dans la méthode updateArticle");
        }
    }

    public void removeArticle(int index) throws BLLException {
        try {
            daoArticle.delete(index);
        }catch (DALException e) {
            throw new BLLException("Erreur dans la méthode removeArticle");
        }
    }


    //Contrainte métier
    private void validerArticle(Article a) throws BLLException{

        if (a.getReference() == null || a.getReference().equalsIgnoreCase("")) {
            throw new BLLException("Erreur dans la référence");
        }

        if ( a.getMarque() == null || a.getMarque().equalsIgnoreCase("")) {
            throw new BLLException("Erreur dans la marque");
        }

        if (a.getDesignation() == null || a.getDesignation().equalsIgnoreCase("")) {
            throw new BLLException("Erreur dans la désignation");
        }

        if (a.getPrixUnitaire() <= 0f) {
            throw new BLLException("Erreur prix unitaire");
        }
        if (a.getQteStock() < 0 ) {
            throw new BLLException("Erreur quantité stock");
        }

         if (a instanceof Ramette) {
             if (((Ramette) a).getGrammage() <= 0 ) {
                 throw new BLLException("Erreur de grammage");
             }
         } else {
             if (((Stylo)a).getCouleur() == null) { //Le IF de cette méthode prépare l'avenir
                 throw new BLLException("Erreur de couleur");
             }
         }
    }



    public Article getArticle(int index) throws BLLException {
        Article article =null;
        try {
            article = daoArticle.selectById(index);
        } catch (DALException e) {
            throw new BLLException("Erreur dans la méthode getArticle");
        }
        return article;
    }

}
