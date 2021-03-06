package com.devis.dao;

import com.devis.dao.daoException.DAOConfigurationException;
import com.devis.dao.implement.*;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolConfiguration;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DAOFactory {

    private static final String FICHIER_PROPERTIES       = "/com/devis/dao/dao.properties";
    private static final String PROPERTY_URL             = "url";
    private static final String PROPERTY_DRIVER          = "driver";
    private static final String PROPERTY_NOM_UTILISATEUR = "nomutilisateur";
    private static final String PROPERTY_MOT_DE_PASSE    = "motdepasse";

    // Singleton
    private static DAOFactory instance = null;

    // Tomcat JDBC Connection Pool
    private DataSource connectionPool = null;

    DAOFactory( DataSource connectionPool ) {
        this.connectionPool = connectionPool;
    }

    /*
     * Méthode chargée de récupérer les informations de connexion à la base de
     * données, charger le driver JDBC et retourner une instance de la Factory
     */
    public static DAOFactory getInstance() throws DAOConfigurationException {
        Properties properties = new Properties();
        String url;
        String driver;
        String nomUtilisateur;
        String motDePasse;
        DataSource connectionPool = null;

        // Singleton
        if(instance != null){
            throw new DAOConfigurationException( "La ressource DAOFactory est déjà instanciée." );
        }

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream( FICHIER_PROPERTIES );

        if ( fichierProperties == null ) {
            throw new DAOConfigurationException( "Le fichier properties " + FICHIER_PROPERTIES + " est introuvable." );
        }

        try {
            properties.load( fichierProperties );
            url = properties.getProperty( PROPERTY_URL );
            driver = properties.getProperty( PROPERTY_DRIVER );
            nomUtilisateur = properties.getProperty( PROPERTY_NOM_UTILISATEUR );
            motDePasse = properties.getProperty( PROPERTY_MOT_DE_PASSE );
        } catch ( IOException e ) {
            throw new DAOConfigurationException( "Impossible de charger le fichier properties " + FICHIER_PROPERTIES, e );
        }

        try {
            Class.forName( driver );
        } catch ( ClassNotFoundException e ) {
            throw new DAOConfigurationException( "Le driver est introuvable dans le classpath.", e );
        }

        // Tomcat JDBC Connection Pool //
        /*
         * Création d'une configuration de pool de connexions via l'objet
         * PoolProperties et les différents setters associés.
         */
        PoolProperties p = new PoolProperties();
        p.setUrl( url );
        p.setDriverClassName( driver );
        p.setUsername( nomUtilisateur );
        p.setPassword( motDePasse );
        p.setMaxActive(50);
        p.setInitialSize(15);
        p.setMaxWait(20000);
        p.setMaxIdle(15);
        p.setMinIdle(8);
        p.setJdbcInterceptors(
                "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"+
                        "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");

        /* Création du pool à partir de la configuration, via l'objet DataSource */
        connectionPool = new DataSource();

        /* TODO : Trouver comment tester la création de connectionPool (!= null dans tous les cas)
        if( connectionPool == null )
        {
            throw new DAOConfigurationException( "Erreur de configuration du pool de connexions." );
        }*/

        connectionPool.setPoolProperties(p);

        /*
         * Enregistrement du pool créé dans une variable d'instance via un appel
         * au constructeur de DAOFactory
         */
        instance = new DAOFactory( connectionPool );
        return instance;
    }

    /* Méthode chargée de fournir une connexion à la base de données */
    public Connection getConnection() throws SQLException {

        //connexion.setAutoCommit(false);
        return this.connectionPool.getConnection();
    }

    public static PoolConfiguration getPoolProperties() {
        return instance.connectionPool.getPoolProperties();
    }

    /*
     * Méthodes de récupération de l'implémentation des différents Dao
     */

    public DevisDao getDevisDao() {
        return new DevisDaoImpl( this );
    }
    public FactureDao getFactureDao() {
        return new FactureDaoImpl( this );
    }
    public TypeLivraisonDao getTypeLivraisonDao() {
        return new TypeLivraisonDaoImpl( this );
    }
    public EntrepriseDao getEntrepriseDao() {
        return new EntrepriseDaoImpl( this );
    }
    public EntrepriseContactDao getEntrepriseContactDao() { return new EntrepriseContactDaoImpl( this ); }
    public ClientInterlocuteurDao getClientInterlocuteurDao() {
        return new ClientInterlocuteurDaoImpl( this );
    }
}