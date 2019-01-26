package com.devis.dao;

import com.devis.dao.implement.DevisDAOimpl;
import com.devis.mariaDb.DBconnection;

import java.sql.Connection;

public class DAOFactory {
    protected static final Connection connection = DBconnection.getInstance();


    public static DAO getDevisDAO(){
        return new DevisDAOimpl(connection);
    }

}