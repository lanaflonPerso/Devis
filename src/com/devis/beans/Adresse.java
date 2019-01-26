package com.devis.beans;


public class Adresse {

  private long idAdresse;
  private String ligne1;
  private String ligne2;
  private String codePostal;
  private String ville;
  private long paysId;


  public long getIdAdresse() {
    return idAdresse;
  }

  public void setIdAdresse(long idAdresse) {
    this.idAdresse = idAdresse;
  }


  public String getLigne1() {
    return ligne1;
  }

  public void setLigne1(String ligne1) {
    this.ligne1 = ligne1;
  }


  public String getLigne2() {
    return ligne2;
  }

  public void setLigne2(String ligne2) {
    this.ligne2 = ligne2;
  }


  public String getCodePostal() {
    return codePostal;
  }

  public void setCodePostal(String codePostal) {
    this.codePostal = codePostal;
  }


  public String getVille() {
    return ville;
  }

  public void setVille(String ville) {
    this.ville = ville;
  }


  public long getPaysId() {
    return paysId;
  }

  public void setPaysId(long paysId) {
    this.paysId = paysId;
  }

}
