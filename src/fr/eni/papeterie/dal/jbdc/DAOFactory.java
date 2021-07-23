package fr.eni.papeterie.dal.jbdc;

public class DAOFactory {
    public static ArticleDAO getArticleDAO() {
        ArticleDAO aDAO = new ArticleDaoJdbcImpl();
        return aDAO;
    }
}

//DAOFactory.getArticleDAO(); car static
