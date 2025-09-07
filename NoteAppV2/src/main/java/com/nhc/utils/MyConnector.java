package com.nhc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyConnector {
    private static MyConnector instance;
    private Connection conn; // Bỏ 'final' để có thể gán lại

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MyConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Giữ constructor là private
    private MyConnector() {}

    // getInstance không cần ném ra SQLException nữa
    public static MyConnector getInstance() {
        if (instance == null) {
            instance = new MyConnector();
        }
        return instance;
    }

    /**
     * Phương thức này sẽ là nơi lấy kết nối.
     * Nó kiểm tra nếu kết nối null hoặc đã bị đóng thì tạo mới.
     * @return Một kết nối CSDL đang mở.
     * @throws SQLException
     */
    public Connection getConnect() throws SQLException {
        // Nếu kết nối chưa được tạo hoặc đã bị đóng
        if (this.conn == null || this.conn.isClosed()) {
            // Tạo lại kết nối
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost/quanly_ghichu", "root", "root");
        }
        return this.conn;
    }
}