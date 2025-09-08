package com.nhc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyConnector {
    private static MyConnector instance;
    private Connection conn; 

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MyConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private MyConnector() {}

    public static MyConnector getInstance() {
        if (instance == null) {
            instance = new MyConnector();
        }
        return instance;
    }

    
    public Connection getConnect() throws SQLException {
        if (this.conn == null || this.conn.isClosed()) {
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost/quanly_ghichu", "root", "root");
        }
        return this.conn;
    }
}