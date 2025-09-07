/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhc.pojo;

import java.util.Date;

/**
 *
 * @author nguye
 */
public class Notes {
    private int id;
    private String title;
    private String content;
    private Date creationDate; // Sử dụng LocalDateTime để lưu cả ngày và giờ
    private int tagId;
    private String tagName;
    private Boolean bold;
    private Boolean italic;

    public Boolean isBold() {
        return bold;
    }

    public void setBold(Boolean bold) {
        this.bold = bold;
    }

    public Boolean isItalic() {
        return italic;
    }

    public void setItalic(Boolean italic) {
        this.italic = italic;
    }

    
    

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    // Constructor cập nhật
    public Notes(int id, String title, String content, Date creationDate, int tagId, String tagName) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
        this.tagId = tagId;
        this.tagName = tagName;
    }

    public Notes() {
    }

    
    

    // Getters and Setters (đã được cập nhật)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }
}