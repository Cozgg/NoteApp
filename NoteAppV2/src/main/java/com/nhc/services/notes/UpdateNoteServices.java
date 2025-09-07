package com.nhc.services.notes;

import com.nhc.pojo.Notes;
import com.nhc.utils.MyConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date; // <-- Import java.sql.Date

public class UpdateNoteServices {

    public boolean addNote(Notes n) throws SQLException {
        String sql = "INSERT INTO notes(title, content, creation_date, tag_id, is_bold, is_italic) VALUES(?,?,?,?,?,?)";

        try (Connection conn = MyConnector.getInstance().getConnect();
             PreparedStatement stm = conn.prepareStatement(sql)) {

            stm.setString(1, n.getTitle());
            stm.setString(2, n.getContent());

            stm.setDate(3, new java.sql.Date(n.getCreationDate().getTime()));

            stm.setInt(4, n.getTagId());
            stm.setBoolean(5, n.isBold());
            stm.setBoolean(6, n.isItalic());
            return stm.executeUpdate() > 0;
        }
    }
    
    public boolean delNote(int noteId) throws SQLException{
         String sql = "DELETE FROM notes WHERE id = ?";
        try (Connection conn = MyConnector.getInstance().getConnect();
         PreparedStatement stm = conn.prepareStatement(sql)) { // Dùng prepareStatement

        // Gán tham số cho câu lệnh
        stm.setInt(1, noteId);

        // Thực thi và trả về kết quả
        return stm.executeUpdate() > 0;
    }
    }
}