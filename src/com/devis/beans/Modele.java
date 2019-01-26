package com.devis.beans;


public class Modele {

  private long idModele;
  private String reference;
  private String descriptif;
  private double prixUnitaireHt;
  private long marqueId;
  private long categorieId;


  public long getIdModele() {
    return idModele;
  }

  public void setIdModele(long idModele) {
    this.idModele = idModele;
  }


  public String getReference() {
    return reference;
  }

  public void setReference(String reference) {
    this.reference = reference;
  }


  public String getDescriptif() {
    return descriptif;
  }

  public void setDescriptif(String descriptif) {
    this.descriptif = descriptif;
  }


  public double getPrixUnitaireHt() {
    return prixUnitaireHt;
  }

  public void setPrixUnitaireHt(double prixUnitaireHt) {
    this.prixUnitaireHt = prixUnitaireHt;
  }


  public long getMarqueId() {
    return marqueId;
  }

  public void setMarqueId(long marqueId) {
    this.marqueId = marqueId;
  }


  public long getCategorieId() {
    return categorieId;
  }

  public void setCategorieId(long categorieId) {
    this.categorieId = categorieId;
  }

}
