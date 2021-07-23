package fr.eni.papeterie.dal.jbdc;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDaoJdbcImpl implements ArticleDAO {

    final String SQL_DELETE = "DELETE FROM Articles WHERE idArticle=?;";
    final String SQL_UPDATE = "UPDATE Articles" +
            " SET reference=? , marque=?, designation=?, prixUnitaire=?, qteStock=?, grammage=?, couleur=?, type=? " +
            " WHERE idArticle=?;";
    final String SQL_INSERT = "INSERT INTO Articles(reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type)"
            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    final String SQL_SELECT_BY_MARQUE = "SELECT idArticle, reference, marque, designation, " +
            " prixUnitaire, qteStock, grammage, couleur, type" +
            " FROM Articles WHERE marque=?;";
    final String SQL_SELECT_BY_MOTCLE = "SELECT idArticle, reference, marque, designation, " +
            " prixUnitaire, qteStock, grammage, couleur, type" +
            " FROM Articles WHERE marque LIKE ? OR designation LIKE ?;";

    @Override
    public Article selectById(int id) throws DALException{

        Article article = null;

        try (Connection connection = JdbcTools.getConnection()) {
            //Etat préparé
            String sql = "SELECT idArticle, reference, marque, designation, " +
                    " prixUnitaire, qteStock, grammage, couleur, type" +
                    " FROM Articles WHERE idArticle=?;";
            PreparedStatement ep = connection.prepareStatement(sql);
            ep.setInt(1, id);
            ResultSet rs = ep.executeQuery();

            //Lire le ResultSet
            while (rs.next()) {
                int identitfiant = rs.getInt("idArticle");
                String reference = rs.getString("reference");
                String marque = rs.getString("marque");
                String designation = rs.getString("designation");
                float prixUnitaire = rs.getFloat("prixUnitaire");
                int qteStock = rs.getInt("qteStock");
                int grammage = rs.getInt("grammage");
                String couleur = rs.getString("couleur");
                String type = rs.getString("type");
                if (type.equalsIgnoreCase("STYLO")) {
                    article = new Stylo(identitfiant, reference, marque, designation, prixUnitaire, qteStock, couleur);
                } else {
                    article = new Ramette(identitfiant, reference, marque, designation, prixUnitaire, qteStock, grammage);
                }
            }
        } catch (Exception e) {
            throw new DALException("Erreur dans la requête selectById");
        }
        return article;
    }

    @Override
    public void delete(int id) throws DALException{
        //Connexion
        try (Connection connection = JdbcTools.getConnection()) {
            //Etat Préparé
            PreparedStatement reqDelete = connection.prepareStatement(this.SQL_DELETE);
            //Points d'interrogation
            reqDelete.setInt(1, id);
            //Execute Update
            reqDelete.executeUpdate();
        } catch (SQLException e) {
            throw new DALException("Erreur dans la requête Delete.");
        }
    }

    @Override
    public List<Article> selectByMarque(String marque) throws DALException {
        //Déclaration nouvelle liste
        List<Article> articles = new ArrayList<>();
        Article article = null;
        try(Connection connection = JdbcTools.getConnection()) {
            PreparedStatement ep = connection.prepareStatement(this.SQL_SELECT_BY_MARQUE);
            ep.setString(1, marque);
            ResultSet rs = ep.executeQuery();

            while (rs.next()) {
                int identitfiant = rs.getInt("idArticle");
                String reference = rs.getString("reference");
                marque = rs.getString("marque");
                String designation = rs.getString("designation");
                float prixUnitaire = rs.getFloat("prixUnitaire");
                int qteStock = rs.getInt("qteStock");
                int grammage = rs.getInt("grammage");
                String couleur = rs.getString("couleur");
                String type = rs.getString("type");
                if (type.equals("STYLO")) {
                    article = new Stylo(identitfiant, reference, marque, designation, prixUnitaire, qteStock, couleur);
                } else {
                    article = new Ramette(identitfiant, reference, marque, designation, prixUnitaire, qteStock, grammage);
                }
                articles.add(article);
            }
        } catch (Exception e) {
            throw new DALException("Erreur dans la requête selectByMarque");
        }
        return articles;
    }

    @Override
    public List<Article> selectByMotCle(String motCle) throws  DALException {
        List<Article> articles = new ArrayList<>();
        Article article = null;
        try (Connection connection = JdbcTools.getConnection()) {
            PreparedStatement ep = connection.prepareStatement(this.SQL_SELECT_BY_MOTCLE);
            ep.setString(1, motCle);
            ep.setString(2, motCle);
            ResultSet rs = ep.executeQuery();

            while (rs.next()) {
                int identitfiant = rs.getInt("idArticle");
                String reference = rs.getString("reference");
                String marque = rs.getString("marque");
                String designation = rs.getString("designation");
                float prixUnitaire = rs.getFloat("prixUnitaire");
                int qteStock = rs.getInt("qteStock");
                int grammage = rs.getInt("grammage");
                String couleur = rs.getString("couleur");
                String type = rs.getString("type");
                if (type.equals("STYLO")) {
                    article = new Stylo(identitfiant, reference, marque, designation, prixUnitaire, qteStock, couleur);
                } else {
                    article = new Ramette(identitfiant, reference, marque, designation, prixUnitaire, qteStock, grammage);
                }
                articles.add(article);
            }

        } catch (Exception e) {
            throw new DALException("Erreur dans la requête selectByMotCle");
        }

        return articles;
    }

    @Override
    public void update(Article article) throws DALException {
        //Connexion
        try( Connection connection = JdbcTools.getConnection()) {
            //Etat préparé
            PreparedStatement reqUpdate = connection.prepareStatement(this.SQL_UPDATE);
            //Points d'intérrogation
            reqUpdate.setString(1, article.getReference() );
            reqUpdate.setString(2, article.getMarque());
            reqUpdate.setString(3, article.getDesignation() );
            reqUpdate.setFloat(4, article.getPrixUnitaire() );
            reqUpdate.setFloat(5, article.getQteStock() );
            reqUpdate.setInt(9, article.getIdArticle() );
            if (article instanceof Stylo) {
                reqUpdate.setString(8, "STYLO");
                reqUpdate.setString(7, ((Stylo) article).getCouleur());
                reqUpdate.setNull(6, Types.INTEGER);
            } else {
                reqUpdate.setString(8, "RAMETTE");
                reqUpdate.setInt(6, ((Ramette) article).getGrammage());
                reqUpdate.setNull(7, Types.VARCHAR);
            }
            //Execute Update
            reqUpdate.executeUpdate();
        } catch (SQLException e) {
            throw new DALException("Erreur dans la requête Update");
        }

    }

    @Override
    public List<Article> selectAll() throws DALException{
        List<Article> articles = new ArrayList<>();
        Article article =null;
        //connection
        try (Connection connection = JdbcTools.getConnection()) {
            //Etat préparé
            String sql2 = " SELECT idArticle, reference, marque, designation," +
                          " prixUnitaire, qteStock, grammage, couleur, type" +
                          " FROM Articles;";
            PreparedStatement reqSelectAll = connection.prepareStatement(sql2);
            ResultSet rs = reqSelectAll.executeQuery();

            //Lire le résultat
            while (rs.next()) {
                if (rs.getString("type").trim().equalsIgnoreCase("STYLO")) {
                    article = new Stylo(
                            rs.getInt("idArticle"),
                            rs.getString("reference"),
                            rs.getString("marque"),
                            rs.getString("designation"),
                            rs.getFloat("prixUnitaire"),
                            rs.getInt("qteStock"),
                            rs.getString("couleur")
                    );
                } else {
                    article = new Ramette(
                            rs.getInt("idArticle"),
                            rs.getString("reference"),
                            rs.getString("marque"),
                            rs.getString("designation"),
                            rs.getFloat("prixUnitaire"),
                            rs.getInt("qteStock"),
                            rs.getInt("grammage")
                    );
                }
                //ajout d'un article à la liste Articles
                articles.add(article);
            }
        }catch(Exception e) {
            throw new DALException("Erreur dans la requête selectAll");
        }

        return articles;
    }

    @Override
    public void insert(Article article) throws DALException{
        try (Connection connection = JdbcTools.getConnection()) {
            PreparedStatement etatPrepare = connection.prepareStatement(this.SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            etatPrepare.setString(1, article.getReference());
            etatPrepare.setString(2, article.getMarque());
            etatPrepare.setString(3, article.getDesignation());
            etatPrepare.setFloat(4, article.getPrixUnitaire());
            etatPrepare.setInt(5, article.getQteStock());
            if ( article instanceof Stylo) {
                etatPrepare.setNull(6, Types.INTEGER);
                etatPrepare.setString(7, ((Stylo) article).getCouleur());
                etatPrepare.setString(8, "STYLO");
            } else {
                etatPrepare.setInt(6, ((Ramette)article).getGrammage());
                etatPrepare.setNull(7, Types.VARCHAR);
                etatPrepare.setString(8, "RAMETTE");
            }

            etatPrepare.executeUpdate();
            ResultSet clesGenerees = etatPrepare.getGeneratedKeys(); //Récupérer les colonnes auto incrémentées
            if (clesGenerees.next()) {
                int idGenere = clesGenerees.getInt(1);
                article.setIdArticle(idGenere);
            }

        } catch (SQLException e) {
            throw new DALException("Erreur dans la méthode Insert");
        }
    }

}


