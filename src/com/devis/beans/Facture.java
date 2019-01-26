package com.devis.beans;


public class Facture {

  private long idFacture;
  private java.sql.Date dateFacturation;
  private long delaiPaiementId;
  private java.sql.Date datePaiement;
  private double totalHt;
  private double tauxTva100;
  private double totalTtc;
  private long statutFactureId;
  private long modePaiementId;


  public long getIdFacture() {
    return idFacture;
  }

  public void setIdFacture(long idFacture) {
    this.idFacture = idFacture;
  }


  public java.sql.Date getDateFacturation() {
    return dateFacturation;
  }

  public void setDateFacturation(java.sql.Date dateFacturation) {
    this.dateFacturation = dateFacturation;
  }


  public long getDelaiPaiementId() {
    return delaiPaiementId;
  }

  public void setDelaiPaiementId(long delaiPaiementId) {
    this.delaiPaiementId = delaiPaiementId;
  }


  public java.sql.Date getDatePaiement() {
    return datePaiement;
  }

  public void setDatePaiement(java.sql.Date datePaiement) {
    this.datePaiement = datePaiement;
  }


  public double getTotalHt() {
    return totalHt;
  }

  public void setTotalHt(double totalHt) {
    this.totalHt = totalHt;
  }


  public double getTauxTva100() {
    return tauxTva100;
  }

  public void setTauxTva100(double tauxTva100) {
    this.tauxTva100 = tauxTva100;
  }


  public double getTotalTtc() {
    return totalTtc;
  }

  public void setTotalTtc(double totalTtc) {
    this.totalTtc = totalTtc;
  }


  public long getStatutFactureId() {
    return statutFactureId;
  }

  public void setStatutFactureId(long statutFactureId) {
    this.statutFactureId = statutFactureId;
  }


  public long getModePaiementId() {
    return modePaiementId;
  }

  public void setModePaiementId(long modePaiementId) {
    this.modePaiementId = modePaiementId;
  }

}
