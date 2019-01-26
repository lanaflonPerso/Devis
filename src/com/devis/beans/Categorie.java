package com.devis.beans;


public class Categorie {

  private long idCategorie;
  private String libelle;
  private long categorieParenteId;


  public long getIdCategorie() {
    return idCategorie;
  }

  public void setIdCategorie(long idCategorie) {
    this.idCategorie = idCategorie;
  }


  public String getLibelle() {
    return libelle;
  }

  public void setLibelle(String libelle) {
    this.libelle = libelle;
  }


  public long getCategorieParenteId() {
    return categorieParenteId;
  }

  public void setCategorieParenteId(long categorieParenteId) {
    this.categorieParenteId = categorieParenteId;
  }

}
