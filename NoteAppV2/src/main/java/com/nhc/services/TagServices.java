/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhc.services;

import com.nhc.pojo.Tags;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nguye
 */
public class TagServices extends BaseServices<Tags>{

    @Override
    public PreparedStatement getStm(Connection conn) throws SQLException {
        return conn.prepareStatement("SELECT * FROM tags");
    }

    @Override
    public List<Tags> getResults(ResultSet rs) throws SQLException {
        List<Tags> tags = new ArrayList<>();
        
        while(rs.next()){
            int id = rs.getInt("id");
            String name = rs.getString("name");
            
            Tags t = new Tags(id, name);
            tags.add(t);
            
        }
        return tags;
    }
    
}
