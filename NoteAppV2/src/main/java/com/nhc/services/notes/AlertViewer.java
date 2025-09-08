/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhc.services.notes;

import com.nhc.pojo.Notes;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;

/**
 *
 * @author nguye
 */
public class AlertViewer extends NoteViewer{
    
    

    @Override
    protected void render(Notes note, Text formattedText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
        alert.setTitle("Noi dung ghi chu");
        alert.setHeaderText("Tieu de: " + note.getTitle());
        
        alert.setContentText(formattedText.getText());
        
        alert.showAndWait();
    }
    
}
