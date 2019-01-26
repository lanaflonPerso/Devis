package com.devis.mariaDb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {

    private static String url = "jdbc:mariadb://localhost:3306/TP_DEVIS";
    private static String user = "root";
    private static String passwd = "";
    private static Connection connection;

    public static Connection getInstance(){
        if(connection == null){

            try {
                Class.forName("org.mariadb.jdbc.Driver");
            } catch (ClassNotFoundException e) {}

            try {
                connection = DriverManager.getConnection(url, user, passwd);
                connection.setAutoCommit(false);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}