package com.devis.beans;


public class EntrepriseContact {

  private long idEntrepriseContact;
  private long entrepriseId;
  private String civilite;
  private String nom;
  private String prenom;
  private String tel;
  private String fax;
  private String email;


  public long getIdEntrepriseContact() {
    return idEntrepriseContact;
  }

  public void setIdEntrepriseContact(long idEntrepriseContact) {
    this.idEntrepriseContact = idEntrepriseContact;
  }


  public long getEntrepriseId() {
    return entrepriseId;
  }

  public void setEntrepriseId(long entrepriseId) {
    this.entrepriseId = entrepriseId;
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


  public String getFax() {
    return fax;
  }

  public void setFax(String fax) {
    this.fax = fax;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

}
