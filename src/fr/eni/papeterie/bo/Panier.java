package fr.eni.papeterie.bo;

import java.util.ArrayList;
import java.util.List;

public class Panier {

    private float montant;
    private List<Ligne> lignesPanier;

    public Panier() {
        this.montant = 0;
        this.lignesPanier = new ArrayList<>();
    }

    public float getMontant() {
        return montant;
    }

    public List<Ligne> getLignesPanier() {
        return lignesPanier;
    }

    public Ligne getLigne(int index){
        return this.lignesPanier.get(index);
    }

    public void addLigne(Article article, int qte){
        Ligne ligneAajouter = new Ligne(article, qte);
        lignesPanier.add(ligneAajouter);
        this.montant = this.montant + (ligneAajouter.getPrix() * ligneAajouter.getQte());
        //this.montant += (ligneAajouter.getPrix() * ligneAajouter.getQte());
    }

    public void removeLigne(int index) {
        Ligne ligneAsupprimer = this.getLigne(index);
        this.montant -= (ligneAsupprimer.getPrix() * ligneAsupprimer.getQte());
    }

    public void updateLigne (int index, int newQte) {
        this.getLigne(index).setQte(newQte);
    }
}
