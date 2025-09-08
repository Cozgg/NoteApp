/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhc.services.notes;

import com.nhc.pojo.Notes;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 *
 * @author nguye
 */
public class DialogViewer extends NoteViewer{
    
    @Override
    protected void render(Notes note, Text formattedText) {
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Noi dung ghi chu");
        dialog.setHeaderText("Tieu de: " + note.getTitle());
        
        TextFlow txtFlow = new TextFlow();
        txtFlow.getChildren().add(formattedText);
        txtFlow.setPrefWidth(250);
        txtFlow.setLineSpacing(5);
        
        dialog.getDialogPane().setContent(txtFlow);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        
        dialog.showAndWait();
    }
    
}
