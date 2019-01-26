package com.devis.beans;


public class Devis {

  private long idDevis;
  private String numDevis;
  private java.sql.Date dateDevis;
  private java.sql.Date dateFinValidite;
  private String commentaire;
  private long clientInterlocuteurId;
  private long typeLivraisonId;
  private long entrepriseContactId;
  private long entrepriseId;
  private long factureId;


  public long getIdDevis() {
    return idDevis;
  }

  public void setIdDevis(long idDevis) {
    this.idDevis = idDevis;
  }


  public String getNumDevis() {
    return numDevis;
  }

  public void setNumDevis(String numDevis) {
    this.numDevis = numDevis;
  }


  public java.sql.Date getDateDevis() {
    return dateDevis;
  }

  public void setDateDevis(java.sql.Date dateDevis) {
    this.dateDevis = dateDevis;
  }


  public java.sql.Date getDateFinValidite() {
    return dateFinValidite;
  }

  public void setDateFinValidite(java.sql.Date dateFinValidite) {
    this.dateFinValidite = dateFinValidite;
  }


  public String getCommentaire() {
    return commentaire;
  }

  public void setCommentaire(String commentaire) {
    this.commentaire = commentaire;
  }


  public long getClientInterlocuteurId() {
    return clientInterlocuteurId;
  }

  public void setClientInterlocuteurId(long clientInterlocuteurId) {
    this.clientInterlocuteurId = clientInterlocuteurId;
  }


  public long getTypeLivraisonId() {
    return typeLivraisonId;
  }

  public void setTypeLivraisonId(long typeLivraisonId) {
    this.typeLivraisonId = typeLivraisonId;
  }


  public long getEntrepriseContactId() {
    return entrepriseContactId;
  }

  public void setEntrepriseContactId(long entrepriseContactId) {
    this.entrepriseContactId = entrepriseContactId;
  }


  public long getEntrepriseId() {
    return entrepriseId;
  }

  public void setEntrepriseId(long entrepriseId) {
    this.entrepriseId = entrepriseId;
  }


  public long getFactureId() {
    return factureId;
  }

  public void setFactureId(long factureId) {
    this.factureId = factureId;
  }

}
