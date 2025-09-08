/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhc.services.notes;

import com.nhc.pojo.Notes;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 *
 * @author nguye
 */
public class TextFlowViewer extends NoteViewer{
    
    private final TextFlow txtFlowContainer ;

    public TextFlowViewer(TextFlow txtFlowContainer) {
        this.txtFlowContainer = txtFlowContainer;
    }
    
    
    @Override
    protected void render(Notes note, Text formattedText) {
        this.txtFlowContainer.getChildren().clear();
        this.txtFlowContainer.getChildren().add(formattedText);
    }
    
}
