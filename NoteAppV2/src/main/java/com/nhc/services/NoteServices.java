package com.nhc.services;

import com.nhc.pojo.Notes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NoteServices extends BaseServices<Notes> {

    @Override
    public PreparedStatement getStm(Connection conn) throws SQLException {
        String sql = "SELECT n.id, n.title, n.content, n.creation_date, n.tag_id, n.is_bold, n.is_italic , t.name AS tagName " +
                     "FROM notes n LEFT JOIN tags t ON n.tag_id = t.id";
        return conn.prepareStatement(sql);
    }

    @Override
    public List<Notes> getResults(ResultSet rs) throws SQLException {
        List<Notes> notes = new ArrayList<>();
        
        while(rs.next()) {
            Notes n = new Notes();
            
            n.setId(rs.getInt("id"));
            n.setTitle(rs.getString("title"));
            n.setContent(rs.getString("content"));
            
            // DÙNG getDate() ĐỂ ĐỌC DATE
            n.setCreationDate(rs.getDate("creation_date"));
            
            n.setTagId(rs.getInt("tag_id"));
            n.setBold(rs.getBoolean("is_bold"));
            n.setItalic(rs.getBoolean("is_italic"));
            n.setTagName(rs.getString("tagName"));
            
            notes.add(n);
        }
        return notes;
    }
}