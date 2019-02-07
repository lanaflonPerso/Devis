/*==============================================================*/
/* DBMS name:      Microsoft SQL Server 2012                    */
/* Created on:     07/02/2019 13:53:24                          */
/*==============================================================*/


if exists (select 1
          from sysobjects
          where id = object_id('tp_devis.entreprise_contact_BEFORE_INSERT')
          and type = 'TR')
   drop trigger tp_devis.entreprise_contact_BEFORE_INSERT
go

if exists (select 1
          from sysobjects
          where id = object_id('tp_devis.ligne_commande_BEFORE_INSERT')
          and type = 'TR')
   drop trigger tp_devis.ligne_commande_BEFORE_INSERT
go

if exists (select 1
          from sysobjects
          where  id = object_id('tp_devis.calculer_facture_total_ht')
          and type in ('P','PC'))
   drop procedure tp_devis.calculer_facture_total_ht
go

if exists (select 1
          from sysobjects
          where  id = object_id('tp_devis.calculer_ligne_commande_montant_ht')
          and type in ('P','PC'))
   drop procedure tp_devis.calculer_ligne_commande_montant_ht
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('tp_devis.adresse') and o.name = 'fk_adresse_pays')
alter table tp_devis.adresse
   drop constraint fk_adresse_pays
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('tp_devis.categorie') and o.name = 'fk_categorie_categorie')
alter table tp_devis.categorie
   drop constraint fk_categorie_categorie
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('tp_devis.client') and o.name = 'fk_client_adresse')
alter table tp_devis.client
   drop constraint fk_client_adresse
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('tp_devis.client_interlocuteur') and o.name = 'fk_client_interlocuteur_adresse')
alter table tp_devis.client_interlocuteur
   drop constraint fk_client_interlocuteur_adresse
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('tp_devis.client_interlocuteur') and o.name = 'fk_client_interlocuteur_client')
alter table tp_devis.client_interlocuteur
   drop constraint fk_client_interlocuteur_client
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('tp_devis.devis') and o.name = 'fk_devis_client_interlocuteur')
alter table tp_devis.devis
   drop constraint fk_devis_client_interlocuteur
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('tp_devis.devis') and o.name = 'fk_devis_entreprise_contact')
alter table tp_devis.devis
   drop constraint fk_devis_entreprise_contact
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('tp_devis.devis') and o.name = 'fk_devis_facture')
alter table tp_devis.devis
   drop constraint fk_devis_facture
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('tp_devis.devis') and o.name = 'fk_devis_type_livraison')
alter table tp_devis.devis
   drop constraint fk_devis_type_livraison
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('tp_devis.entreprise') and o.name = 'fk_entreprise_adresse')
alter table tp_devis.entreprise
   drop constraint fk_entreprise_adresse
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('tp_devis.entreprise_contact') and o.name = 'fk_entreprise_contact_entreprise')
alter table tp_devis.entreprise_contact
   drop constraint fk_entreprise_contact_entreprise
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('tp_devis.facture') and o.name = 'fk_facture_delai_paiement')
alter table tp_devis.facture
   drop constraint fk_facture_delai_paiement
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('tp_devis.facture') and o.name = 'fk_facture_mode_paiement')
alter table tp_devis.facture
   drop constraint fk_facture_mode_paiement
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('tp_devis.facture') and o.name = 'fk_facture_statut_facture')
alter table tp_devis.facture
   drop constraint fk_facture_statut_facture
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('tp_devis.ligne_commande') and o.name = 'fk_ligne_commande_devis')
alter table tp_devis.ligne_commande
   drop constraint fk_ligne_commande_devis
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('tp_devis.ligne_commande') and o.name = 'fk_ligne_commande_modele')
alter table tp_devis.ligne_commande
   drop constraint fk_ligne_commande_modele
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('tp_devis.modele') and o.name = 'fk_modele_categorie')
alter table tp_devis.modele
   drop constraint fk_modele_categorie
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('tp_devis.modele') and o.name = 'fk_modele_marque')
alter table tp_devis.modele
   drop constraint fk_modele_marque
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('tp_devis.adresse')
            and   name  = 'fk_adresse_pays_idx'
            and   indid > 0
            and   indid < 255)
   drop index tp_devis.adresse.fk_adresse_pays_idx
go

alter table tp_devis.adresse
   drop constraint PK_adresse
go

if exists (select 1
            from  sysobjects
           where  id = object_id('tp_devis.adresse')
            and   type = 'U')
   drop table tp_devis.adresse
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('tp_devis.categorie')
            and   name  = 'fk_categorie_categorie_idx'
            and   indid > 0
            and   indid < 255)
   drop index tp_devis.categorie.fk_categorie_categorie_idx
go

alter table tp_devis.categorie
   drop constraint PK_categorie
go

if exists (select 1
            from  sysobjects
           where  id = object_id('tp_devis.categorie')
            and   type = 'U')
   drop table tp_devis.categorie
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('tp_devis.client')
            and   name  = 'fk_client_adresse_idx'
            and   indid > 0
            and   indid < 255)
   drop index tp_devis.client.fk_client_adresse_idx
go

alter table tp_devis.client
   drop constraint PK_client
go

if exists (select 1
            from  sysobjects
           where  id = object_id('tp_devis.client')
            and   type = 'U')
   drop table tp_devis.client
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('tp_devis.client_interlocuteur')
            and   name  = 'fk_client_interlocuteur_client_idx'
            and   indid > 0
            and   indid < 255)
   drop index tp_devis.client_interlocuteur.fk_client_interlocuteur_client_idx
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('tp_devis.client_interlocuteur')
            and   name  = 'fk_client_interlocuteur_adresse_idx'
            and   indid > 0
            and   indid < 255)
   drop index tp_devis.client_interlocuteur.fk_client_interlocuteur_adresse_idx
go

alter table tp_devis.client_interlocuteur
   drop constraint PK_client_interlocuteur
go

if exists (select 1
            from  sysobjects
           where  id = object_id('tp_devis.client_interlocuteur')
            and   type = 'U')
   drop table tp_devis.client_interlocuteur
go

alter table tp_devis.delai_paiement
   drop constraint PK_delai_paiement
go

alter table tp_devis.delai_paiement
   drop constraint code_delai_paiement_UNIQUE
go

if exists (select 1
            from  sysobjects
           where  id = object_id('tp_devis.delai_paiement')
            and   type = 'U')
   drop table tp_devis.delai_paiement
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('tp_devis.devis')
            and   name  = 'fk_devis_type_livraison_idx'
            and   indid > 0
            and   indid < 255)
   drop index tp_devis.devis.fk_devis_type_livraison_idx
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('tp_devis.devis')
            and   name  = 'fk_devis_facture_idx'
            and   indid > 0
            and   indid < 255)
   drop index tp_devis.devis.fk_devis_facture_idx
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('tp_devis.devis')
            and   name  = 'fk_devis_entreprise_contact_idx'
            and   indid > 0
            and   indid < 255)
   drop index tp_devis.devis.fk_devis_entreprise_contact_idx
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('tp_devis.devis')
            and   name  = 'fk_devis_client_interlocuteur_idx'
            and   indid > 0
            and   indid < 255)
   drop index tp_devis.devis.fk_devis_client_interlocuteur_idx
go

alter table tp_devis.devis
   drop constraint PK_devis
go

alter table tp_devis.devis
   drop constraint num_devis_UNIQUE
go

alter table tp_devis.devis
   drop constraint facture_id__UNIQUE
go

if exists (select 1
            from  sysobjects
           where  id = object_id('tp_devis.devis')
            and   type = 'U')
   drop table tp_devis.devis
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('tp_devis.entreprise')
            and   name  = 'fk_entreprise_adresse_idx'
            and   indid > 0
            and   indid < 255)
   drop index tp_devis.entreprise.fk_entreprise_adresse_idx
go

alter table tp_devis.entreprise
   drop constraint siret_UNIQUE
go

alter table tp_devis.entreprise
   drop constraint PK_entreprise
go

alter table tp_devis.entreprise
   drop constraint adresse_entreprise_UNIQUE
go

if exists (select 1
            from  sysobjects
           where  id = object_id('tp_devis.entreprise')
            and   type = 'U')
   drop table tp_devis.entreprise
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('tp_devis.entreprise_contact')
            and   name  = 'fk_entreprise_contact_entreprise_idx'
            and   indid > 0
            and   indid < 255)
   drop index tp_devis.entreprise_contact.fk_entreprise_contact_entreprise_idx
go

alter table tp_devis.entreprise_contact
   drop constraint PK_entreprise_contact
go

if exists (select 1
            from  sysobjects
           where  id = object_id('tp_devis.entreprise_contact')
            and   type = 'U')
   drop table tp_devis.entreprise_contact
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('tp_devis.facture')
            and   name  = 'fk_facture_statut_facture_idx'
            and   indid > 0
            and   indid < 255)
   drop index tp_devis.facture.fk_facture_statut_facture_idx
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('tp_devis.facture')
            and   name  = 'fk_facture_mode_paiement_idx'
            and   indid > 0
            and   indid < 255)
   drop index tp_devis.facture.fk_facture_mode_paiement_idx
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('tp_devis.facture')
            and   name  = 'fk_facture_delai_paiement_idx'
            and   indid > 0
            and   indid < 255)
   drop index tp_devis.facture.fk_facture_delai_paiement_idx
go

alter table tp_devis.facture
   drop constraint PK_facture
go

if exists (select 1
            from  sysobjects
           where  id = object_id('tp_devis.facture')
            and   type = 'U')
   drop table tp_devis.facture
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('tp_devis.ligne_commande')
            and   name  = 'fk_ligne_commande_modele_idx'
            and   indid > 0
            and   indid < 255)
   drop index tp_devis.ligne_commande.fk_ligne_commande_modele_idx
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('tp_devis.ligne_commande')
            and   name  = 'fk_ligne_commande_devis_idx'
            and   indid > 0
            and   indid < 255)
   drop index tp_devis.ligne_commande.fk_ligne_commande_devis_idx
go

alter table tp_devis.ligne_commande
   drop constraint PK_ligne_commande
go

if exists (select 1
            from  sysobjects
           where  id = object_id('tp_devis.ligne_commande')
            and   type = 'U')
   drop table tp_devis.ligne_commande
go

alter table tp_devis.marque
   drop constraint PK_marque
go

if exists (select 1
            from  sysobjects
           where  id = object_id('tp_devis.marque')
            and   type = 'U')
   drop table tp_devis.marque
go

alter table tp_devis.mode_paiement
   drop constraint PK_mode_paiement
go

if exists (select 1
            from  sysobjects
           where  id = object_id('tp_devis.mode_paiement')
            and   type = 'U')
   drop table tp_devis.mode_paiement
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('tp_devis.modele')
            and   name  = 'fk_modele_marque_idx'
            and   indid > 0
            and   indid < 255)
   drop index tp_devis.modele.fk_modele_marque_idx
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('tp_devis.modele')
            and   name  = 'fk_modele_categorie_idx'
            and   indid > 0
            and   indid < 255)
   drop index tp_devis.modele.fk_modele_categorie_idx
go

alter table tp_devis.modele
   drop constraint PK_modele
go

if exists (select 1
            from  sysobjects
           where  id = object_id('tp_devis.modele')
            and   type = 'U')
   drop table tp_devis.modele
go

alter table tp_devis.param
   drop constraint PK_param
go

if exists (select 1
            from  sysobjects
           where  id = object_id('tp_devis.param')
            and   type = 'U')
   drop table tp_devis.param
go

alter table tp_devis.pays
   drop constraint PK_pays
go

alter table tp_devis.pays
   drop constraint code_pays_UNIQUE
go

if exists (select 1
            from  sysobjects
           where  id = object_id('tp_devis.pays')
            and   type = 'U')
   drop table tp_devis.pays
go

alter table tp_devis.statut_facture
   drop constraint PK_statut_facture
go

if exists (select 1
            from  sysobjects
           where  id = object_id('tp_devis.statut_facture')
            and   type = 'U')
   drop table tp_devis.statut_facture
go

alter table tp_devis.type_livraison
   drop constraint PK_type_livraison
go

if exists (select 1
            from  sysobjects
           where  id = object_id('tp_devis.type_livraison')
            and   type = 'U')
   drop table tp_devis.type_livraison
go

if exists (select 1
            from  sysobjects
           where  id = object_id('tp_devis.v_devis')
            and   type = 'U')
   drop table tp_devis.v_devis
go

drop user tp_devis
go

drop schema tp_devis
go

/*==============================================================*/
/* User: tp_devis                                               */
/*==============================================================*/
create schema tp_devis
go

/*==============================================================*/
/* User: tp_devis                                               */
/*==============================================================*/
execute sp_grantdbaccess tp_devis
go

/*==============================================================*/
/* Table: adresse                                               */
/*==============================================================*/
create table tp_devis.adresse (
   id_adresse           int                  identity,
   ligne1               varchar(45)          not null,
   ligne2               varchar(45)          null,
   code_postal          varchar(10)          not null,
   ville                varchar(45)          not null,
   pays_id              int                  not null
)
go

alter table tp_devis.adresse
   add constraint PK_adresse primary key (id_adresse)
go

/*==============================================================*/
/* Index: fk_adresse_pays_idx                                   */
/*==============================================================*/




create nonclustered index fk_adresse_pays_idx on tp_devis.adresse (pays_id ASC)
go

/*==============================================================*/
/* Table: categorie                                             */
/*==============================================================*/
create table tp_devis.categorie (
   id_categorie         int                  identity,
   libelle              varchar(45)          not null,
   categorie_parente_id int                  null
)
go

alter table tp_devis.categorie
   add constraint PK_categorie primary key (id_categorie)
go

/*==============================================================*/
/* Index: fk_categorie_categorie_idx                            */
/*==============================================================*/




create nonclustered index fk_categorie_categorie_idx on tp_devis.categorie (categorie_parente_id ASC)
go

/*==============================================================*/
/* Table: client                                                */
/*==============================================================*/
create table tp_devis.client (
   id_client            int                  identity,
   libelle              varchar(45)          not null,
   adresse_admin_id     int                  not null
)
go

alter table tp_devis.client
   add constraint PK_client primary key (id_client)
go

/*==============================================================*/
/* Index: fk_client_adresse_idx                                 */
/*==============================================================*/




create nonclustered index fk_client_adresse_idx on tp_devis.client (adresse_admin_id ASC)
go

/*==============================================================*/
/* Table: client_interlocuteur                                  */
/*==============================================================*/
create table tp_devis.client_interlocuteur (
   id_client_interlocuteur int                  identity,
   civilite             enum('mr.','ms.')    not null,
   nom                  varchar(45)          not null,
   prenom               varchar(45)          not null,
   tel                  varchar(45)          not null,
   email                varchar(45)          not null,
   client_id            int                  not null,
   adresse_livraison_id int                  not null
)
go

alter table tp_devis.client_interlocuteur
   add constraint PK_client_interlocuteur primary key (id_client_interlocuteur)
go

/*==============================================================*/
/* Index: fk_client_interlocuteur_adresse_idx                   */
/*==============================================================*/




create nonclustered index fk_client_interlocuteur_adresse_idx on tp_devis.client_interlocuteur (adresse_livraison_id ASC)
go

/*==============================================================*/
/* Index: fk_client_interlocuteur_client_idx                    */
/*==============================================================*/




create nonclustered index fk_client_interlocuteur_client_idx on tp_devis.client_interlocuteur (client_id ASC)
go

/*==============================================================*/
/* Table: delai_paiement                                        */
/*==============================================================*/
create table tp_devis.delai_paiement (
   id_delai_paiement    int                  identity,
   code_delai_paiement  char(5)              not null,
   libelle              varchar(45)          not null,
   delai_jours          int                  not null
)
go

alter table tp_devis.delai_paiement
   add constraint code_delai_paiement_UNIQUE unique (code_delai_paiement)
go

alter table tp_devis.delai_paiement
   add constraint PK_delai_paiement primary key (id_delai_paiement)
go

/*==============================================================*/
/* Table: devis                                                 */
/*==============================================================*/
create table tp_devis.devis (
   id_devis             int                  identity,
   num_devis            char(10)             not null,
   date_devis           datetime             not null,
   date_fin_validite    datetime             null,
   commentaire          text                 null,
   client_interlocuteur_id int                  not null,
   type_livraison_id    int                  not null,
   entreprise_contact_id int                  not null,
   entreprise_id        int                  not null,
   facture_id           int                  not null
)
go

alter table tp_devis.devis
   add constraint facture_id__UNIQUE unique (facture_id)
go

alter table tp_devis.devis
   add constraint num_devis_UNIQUE unique (num_devis)
go

alter table tp_devis.devis
   add constraint PK_devis primary key (id_devis)
go

/*==============================================================*/
/* Index: fk_devis_client_interlocuteur_idx                     */
/*==============================================================*/




create nonclustered index fk_devis_client_interlocuteur_idx on tp_devis.devis (client_interlocuteur_id ASC)
go

/*==============================================================*/
/* Index: fk_devis_entreprise_contact_idx                       */
/*==============================================================*/




create nonclustered index fk_devis_entreprise_contact_idx on tp_devis.devis ( ASC)
go

/*==============================================================*/
/* Index: fk_devis_facture_idx                                  */
/*==============================================================*/




create nonclustered index fk_devis_facture_idx on tp_devis.devis (facture_id ASC)
go

/*==============================================================*/
/* Index: fk_devis_type_livraison_idx                           */
/*==============================================================*/




create nonclustered index fk_devis_type_livraison_idx on tp_devis.devis (type_livraison_id ASC)
go

/*==============================================================*/
/* Table: entreprise                                            */
/*==============================================================*/
create table tp_devis.entreprise (
   id_entreprise        int                  identity,
   siret                char(14)             not null,
   raison_sociale       varchar(255)         not null,
   num_tva              char(13)             not null,
   tel                  varchar(45)          null,
   fax                  varchar(45)          null,
   adresse_entreprise_id int                  not null
)
go

alter table tp_devis.entreprise
   add constraint adresse_entreprise_UNIQUE unique (adresse_entreprise_id)
go

alter table tp_devis.entreprise
   add constraint PK_entreprise primary key (id_entreprise)
go

alter table tp_devis.entreprise
   add constraint siret_UNIQUE unique (siret)
go

/*==============================================================*/
/* Index: fk_entreprise_adresse_idx                             */
/*==============================================================*/




create nonclustered index fk_entreprise_adresse_idx on tp_devis.entreprise (adresse_entreprise_id ASC)
go

/*==============================================================*/
/* Table: entreprise_contact                                    */
/*==============================================================*/
create table tp_devis.entreprise_contact (
   id_entreprise_contact int                  not null,
   entreprise_id        int                  not null,
   civilite             enum('mr.','ms.')    not null,
   nom                  varchar(45)          not null,
   prenom               varchar(45)          not null,
   tel                  varchar(45)          null,
   fax                  varchar(45)          null,
   email                varchar(45)          null
)
go

alter table tp_devis.entreprise_contact
   add constraint PK_entreprise_contact primary key (id_entreprise_contact, entreprise_id)
go

/*==============================================================*/
/* Index: fk_entreprise_contact_entreprise_idx                  */
/*==============================================================*/




create nonclustered index fk_entreprise_contact_entreprise_idx on tp_devis.entreprise_contact (entreprise_id ASC)
go

/*==============================================================*/
/* Table: facture                                               */
/*==============================================================*/
create table tp_devis.facture (
   id_facture           int                  identity,
   date_facturation     datetime             null,
   delai_paiement_id    int                  not null,
   date_paiement        datetime             null,
   total_ht             decimal(10,2)        null,
   taux_tva_100         decimal(5,2)         not null,
   total_ttc            decimal(10,2)        null,
   statut_facture_id    int                  not null,
   mode_paiement_id     int                  not null
)
go

alter table tp_devis.facture
   add constraint PK_facture primary key (id_facture)
go

/*==============================================================*/
/* Index: fk_facture_delai_paiement_idx                         */
/*==============================================================*/




create nonclustered index fk_facture_delai_paiement_idx on tp_devis.facture (delai_paiement_id ASC)
go

/*==============================================================*/
/* Index: fk_facture_mode_paiement_idx                          */
/*==============================================================*/




create nonclustered index fk_facture_mode_paiement_idx on tp_devis.facture (mode_paiement_id ASC)
go

/*==============================================================*/
/* Index: fk_facture_statut_facture_idx                         */
/*==============================================================*/




create nonclustered index fk_facture_statut_facture_idx on tp_devis.facture (statut_facture_id ASC)
go

/*==============================================================*/
/* Table: ligne_commande                                        */
/*==============================================================*/
create table tp_devis.ligne_commande (
   devis_id             int                  not null,
   modele_id            int                  not null,
   quantite             int                  not null,
   montant_ht           decimal(10,2)        null
)
go

alter table tp_devis.ligne_commande
   add constraint PK_ligne_commande primary key (devis_id, modele_id)
go

/*==============================================================*/
/* Index: fk_ligne_commande_devis_idx                           */
/*==============================================================*/




create nonclustered index fk_ligne_commande_devis_idx on tp_devis.ligne_commande (devis_id ASC)
go

/*==============================================================*/
/* Index: fk_ligne_commande_modele_idx                          */
/*==============================================================*/




create nonclustered index fk_ligne_commande_modele_idx on tp_devis.ligne_commande (modele_id ASC)
go

/*==============================================================*/
/* Table: marque                                                */
/*==============================================================*/
create table tp_devis.marque (
   id_marque            int                  identity,
   libelle              varchar(45)          not null
)
go

alter table tp_devis.marque
   add constraint PK_marque primary key (id_marque)
go

/*==============================================================*/
/* Table: mode_paiement                                         */
/*==============================================================*/
create table tp_devis.mode_paiement (
   id_mode_paiement     int                  identity,
   libelle              varchar(45)          not null
)
go

alter table tp_devis.mode_paiement
   add constraint PK_mode_paiement primary key (id_mode_paiement)
go

/*==============================================================*/
/* Table: modele                                                */
/*==============================================================*/
create table tp_devis.modele (
   id_modele            int                  identity,
   reference            varchar(45)          not null,
   descriptif           text                 not null,
   prix_unitaire_ht     decimal(10,2)        not null,
   marque_id            int                  not null,
   categorie_id         int                  not null
)
go

alter table tp_devis.modele
   add constraint PK_modele primary key (id_modele)
go

/*==============================================================*/
/* Index: fk_modele_categorie_idx                               */
/*==============================================================*/




create nonclustered index fk_modele_categorie_idx on tp_devis.modele (categorie_id ASC)
go

/*==============================================================*/
/* Index: fk_modele_marque_idx                                  */
/*==============================================================*/




create nonclustered index fk_modele_marque_idx on tp_devis.modele (marque_id ASC)
go

/*==============================================================*/
/* Table: param                                                 */
/*==============================================================*/
create table tp_devis.param (
   param_nature         varchar(45)          not null,
   param_code           char(5)              not null,
   param_libelle        varchar(45)          null,
   id_param             int                  not null
)
go

alter table tp_devis.param
   add constraint PK_param primary key (param_nature, param_code, id_param)
go

/*==============================================================*/
/* Table: pays                                                  */
/*==============================================================*/
create table tp_devis.pays (
   id_pays              int                  identity,
   code_pays            char(5)              not null,
   libelle              varchar(45)          not null
)
go

alter table tp_devis.pays
   add constraint code_pays_UNIQUE unique (code_pays)
go

alter table tp_devis.pays
   add constraint PK_pays primary key (id_pays)
go

/*==============================================================*/
/* Table: statut_facture                                        */
/*==============================================================*/
create table tp_devis.statut_facture (
   id_statut_facture    int                  identity,
   libelle              varchar(45)          not null
)
go

alter table tp_devis.statut_facture
   add constraint PK_statut_facture primary key (id_statut_facture)
go

/*==============================================================*/
/* Table: type_livraison                                        */
/*==============================================================*/
create table tp_devis.type_livraison (
   id_type_livraison    int                  identity,
   libelle              varchar(45)          not null
)
go

alter table tp_devis.type_livraison
   add constraint PK_type_livraison primary key (id_type_livraison)
go

/*==============================================================*/
/* Table: v_devis                                               */
/*==============================================================*/
create table tp_devis.v_devis (
   entreprise           varchar(255)         not null,
   entreprise_contact_nom varchar(45)          not null,
   num_devis            char(10)             not null,
   total_ht             decimal(10,2)        null,
   taux_tva_100         decimal(5,2)         not null,
   total_ttc            decimal(20,8)        null
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('tp_devis.v_devis') and minor_id = 0)
begin 
   execute sp_dropextendedproperty 'MS_Description',  
   'user', 'tp_devis', 'table', 'v_devis' 
 
end 


execute sp_addextendedproperty 'MS_Description',  
   'VIEW', 
   'user', 'tp_devis', 'table', 'v_devis'
go

alter table tp_devis.adresse
   add constraint fk_adresse_pays foreign key (pays_id)
      references tp_devis.pays (id_pays)
go

alter table tp_devis.categorie
   add constraint fk_categorie_categorie foreign key (categorie_parente_id)
      references tp_devis.categorie (id_categorie)
go

alter table tp_devis.client
   add constraint fk_client_adresse foreign key (adresse_admin_id)
      references tp_devis.adresse (id_adresse)
go

alter table tp_devis.client_interlocuteur
   add constraint fk_client_interlocuteur_adresse foreign key (adresse_livraison_id)
      references tp_devis.adresse (id_adresse)
go

alter table tp_devis.client_interlocuteur
   add constraint fk_client_interlocuteur_client foreign key (client_id)
      references tp_devis.client (id_client)
go

alter table tp_devis.devis
   add constraint fk_devis_client_interlocuteur foreign key (client_interlocuteur_id)
      references tp_devis.client_interlocuteur (id_client_interlocuteur)
go

alter table tp_devis.devis
   add constraint fk_devis_entreprise_contact foreign key (entreprise_contact_id, entreprise_id)
      references tp_devis.entreprise_contact (id_entreprise_contact, entreprise_id)
go

alter table tp_devis.devis
   add constraint fk_devis_facture foreign key (facture_id)
      references tp_devis.facture (id_facture)
go

alter table tp_devis.devis
   add constraint fk_devis_type_livraison foreign key (type_livraison_id)
      references tp_devis.type_livraison (id_type_livraison)
go

alter table tp_devis.entreprise
   add constraint fk_entreprise_adresse foreign key (adresse_entreprise_id)
      references tp_devis.adresse (id_adresse)
go

alter table tp_devis.entreprise_contact
   add constraint fk_entreprise_contact_entreprise foreign key (entreprise_id)
      references tp_devis.entreprise (id_entreprise)
go

alter table tp_devis.facture
   add constraint fk_facture_delai_paiement foreign key (delai_paiement_id)
      references tp_devis.delai_paiement (id_delai_paiement)
go

alter table tp_devis.facture
   add constraint fk_facture_mode_paiement foreign key (mode_paiement_id)
      references tp_devis.mode_paiement (id_mode_paiement)
go

alter table tp_devis.facture
   add constraint fk_facture_statut_facture foreign key (statut_facture_id)
      references tp_devis.statut_facture (id_statut_facture)
go

alter table tp_devis.ligne_commande
   add constraint fk_ligne_commande_devis foreign key (devis_id)
      references tp_devis.devis (id_devis)
go

alter table tp_devis.ligne_commande
   add constraint fk_ligne_commande_modele foreign key (modele_id)
      references tp_devis.modele (id_modele)
go

alter table tp_devis.modele
   add constraint fk_modele_categorie foreign key (categorie_id)
      references tp_devis.categorie (id_categorie)
go

alter table tp_devis.modele
   add constraint fk_modele_marque foreign key (marque_id)
      references tp_devis.marque (id_marque)
go


create procedure tp_devis.calculer_facture_total_ht <@arg> <type> as
declare <@var> <type>
begin

end
go


create procedure tp_devis.calculer_ligne_commande_montant_ht <@arg> <type> as
declare <@var> <type>
begin

end
go


create trigger entreprise_contact_BEFORE_INSERT after insert
   on tp_devis.entreprise_contact for each row
BEGIN 

	DECLARE num INTEGER; 
  
	IF ( NEW.id_entreprise_contact IS NULL OR NEW.id_entreprise_contact = 0 ) THEN 
		
		SET num =  
		( 
			SELECT COALESCE ( MAX( id_entreprise_contact ),  0 )  +  1 
			FROM entreprise_contact 
			WHERE entreprise_id = NEW.entreprise_id 
		); 

		SET NEW.id_entreprise_contact = num; 
	  
	END IF; 
  
  END
go


create trigger ligne_commande_BEFORE_INSERT after insert
   on tp_devis.ligne_commande for each row
BEGIN

	IF ( NEW.montant_ht IS NULL OR NEW.montant_ht = 0 ) THEN 

    SET NEW.montant_ht = NEW.quantite * 
    (
   		select modele.prix_unitaire_ht 
   		from modele  
   		where modele.id_modele = NEW.modele_id
   	);
    
    END IF;

END
go

