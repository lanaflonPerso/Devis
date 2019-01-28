package com.devis.dao;

import com.devis.dao.daoException.DAOConfigurationException;
import com.devis.dao.implement.DevisDao;
import com.devis.dao.implement.DevisDaoImpl;
import com.devis.dao.implement.FactureDao;
import com.devis.dao.implement.FactureDaoImpl;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAOFactory {

    private static final String FICHIER_PROPERTIES       = "/com/devis/dao/dao.properties";
    private static final String PROPERTY_URL             = "url";
    private static final String PROPERTY_DRIVER          = "driver";
    private static final String PROPERTY_NOM_UTILISATEUR = "nomutilisateur";
    private static final String PROPERTY_MOT_DE_PASSE    = "motdepasse";

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
        if( connectionPool == null )
        {
            throw new DAOConfigurationException( "Erreur de configuration du pool de connexions." );
        }
        connectionPool.setPoolProperties(p);

        /*
         * Enregistrement du pool créé dans une variable d'instance via un appel
         * au constructeur de DAOFactory
         */
        DAOFactory instance = new DAOFactory( connectionPool );
        return instance;
    }

    /* Méthode chargée de fournir une connexion à la base de données */
    public Connection getConnection() throws SQLException {

        //connexion.setAutoCommit(false);
        return this.connectionPool.getConnection();
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

}