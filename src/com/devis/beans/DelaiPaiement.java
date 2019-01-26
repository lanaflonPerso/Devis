package com.devis.beans;


public class DelaiPaiement {

  private long idDelaiPaiement;
  private String codeDelaiPaiement;
  private String libelle;
  private long delaiJours;


  public long getIdDelaiPaiement() {
    return idDelaiPaiement;
  }

  public void setIdDelaiPaiement(long idDelaiPaiement) {
    this.idDelaiPaiement = idDelaiPaiement;
  }


  public String getCodeDelaiPaiement() {
    return codeDelaiPaiement;
  }

  public void setCodeDelaiPaiement(String codeDelaiPaiement) {
    this.codeDelaiPaiement = codeDelaiPaiement;
  }


  public String getLibelle() {
    return libelle;
  }

  public void setLibelle(String libelle) {
    this.libelle = libelle;
  }


  public long getDelaiJours() {
    return delaiJours;
  }

  public void setDelaiJours(long delaiJours) {
    this.delaiJours = delaiJours;
  }

}
