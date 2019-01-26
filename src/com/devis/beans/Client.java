package com.devis.beans;


public class Client {

  private long idClient;
  private String libelle;
  private long adresseAdminId;


  public long getIdClient() {
    return idClient;
  }

  public void setIdClient(long idClient) {
    this.idClient = idClient;
  }


  public String getLibelle() {
    return libelle;
  }

  public void setLibelle(String libelle) {
    this.libelle = libelle;
  }


  public long getAdresseAdminId() {
    return adresseAdminId;
  }

  public void setAdresseAdminId(long adresseAdminId) {
    this.adresseAdminId = adresseAdminId;
  }

}
