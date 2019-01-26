package com.devis.beans;


public class ClientInterlocuteur {

  private long idClientInterlocuteur;
  private String civilite;
  private String nom;
  private String prenom;
  private String tel;
  private String email;
  private long clientId;
  private long adresseLivraisonId;


  public long getIdClientInterlocuteur() {
    return idClientInterlocuteur;
  }

  public void setIdClientInterlocuteur(long idClientInterlocuteur) {
    this.idClientInterlocuteur = idClientInterlocuteur;
  }


  public String getCivilite() {
    return civilite;
  }

  public void setCivilite(String civilite) {
    this.civilite = civilite;
  }


  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }


  public String getPrenom() {
    return prenom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }


  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public long getClientId() {
    return clientId;
  }

  public void setClientId(long clientId) {
    this.clientId = clientId;
  }


  public long getAdresseLivraisonId() {
    return adresseLivraisonId;
  }

  public void setAdresseLivraisonId(long adresseLivraisonId) {
    this.adresseLivraisonId = adresseLivraisonId;
  }

}
