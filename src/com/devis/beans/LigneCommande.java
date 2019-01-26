package com.devis.beans;


public class LigneCommande {

  private long devisId;
  private long modeleId;
  private long quantite;
  private double montantHt;


  public long getDevisId() {
    return devisId;
  }

  public void setDevisId(long devisId) {
    this.devisId = devisId;
  }


  public long getModeleId() {
    return modeleId;
  }

  public void setModeleId(long modeleId) {
    this.modeleId = modeleId;
  }


  public long getQuantite() {
    return quantite;
  }

  public void setQuantite(long quantite) {
    this.quantite = quantite;
  }


  public double getMontantHt() {
    return montantHt;
  }

  public void setMontantHt(double montantHt) {
    this.montantHt = montantHt;
  }

}
