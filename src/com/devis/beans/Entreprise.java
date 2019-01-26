package com.devis.beans;


public class Entreprise {

  private long idEntreprise;
  private String siret;
  private String raisonSociale;
  private String numTva;
  private String tel;
  private String fax;
  private long adresseEntrepriseId;


  public long getIdEntreprise() {
    return idEntreprise;
  }

  public void setIdEntreprise(long idEntreprise) {
    this.idEntreprise = idEntreprise;
  }


  public String getSiret() {
    return siret;
  }

  public void setSiret(String siret) {
    this.siret = siret;
  }


  public String getRaisonSociale() {
    return raisonSociale;
  }

  public void setRaisonSociale(String raisonSociale) {
    this.raisonSociale = raisonSociale;
  }


  public String getNumTva() {
    return numTva;
  }

  public void setNumTva(String numTva) {
    this.numTva = numTva;
  }


  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }


  public String getFax() {
    return fax;
  }

  public void setFax(String fax) {
    this.fax = fax;
  }


  public long getAdresseEntrepriseId() {
    return adresseEntrepriseId;
  }

  public void setAdresseEntrepriseId(long adresseEntrepriseId) {
    this.adresseEntrepriseId = adresseEntrepriseId;
  }

}
