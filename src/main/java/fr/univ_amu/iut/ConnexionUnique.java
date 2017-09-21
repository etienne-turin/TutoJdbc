package fr.univ_amu.iut;

import java.sql.Connection;

/**
 * Created by t15002827 on 21/09/17.
 */
public class ConnexionUnique {

    private static ConnexionUnique instance;
    private static Connection connection;
    private static final String CONNECT_URL = "jdbc:mysql://mysql-turin.alwaysdata.net:3306/turin_tpjava";
    private static final String LOGIN = "turin_tpjava";
    private static final String PASSWORD = "13012";

    private ConnexionUnique () {

    }

    public static Connection getConnection() {
        return connection;
    }

    public static ConnexionUnique getInstance()
    {
        return instance;
    }
}
