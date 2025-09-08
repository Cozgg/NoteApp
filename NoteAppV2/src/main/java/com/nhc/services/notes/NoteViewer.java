/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhc.services.notes;

import com.nhc.pojo.Notes;
import javafx.scene.text.Text;

/**
 *
 * @author nguye
 */
public abstract class NoteViewer {
    public final void display(Notes note){
        Text formattedText = preContent(note);
        
        render(note, formattedText);
    }

    private Text preContent(Notes note) {
        INote noteToDisplay = new SimpleNote();
        
        if(note.isBold()){
            noteToDisplay = new BoldNoteDecorator(noteToDisplay);
        }
        
        if(note.isItalic()){
            noteToDisplay = new ItalicNoteDecorator(noteToDisplay);
        }
        
        String finalStyle = noteToDisplay.getStyle();
        
        Text textNote = new Text(note.getContent());
        
        if(!finalStyle.isEmpty()){
            textNote.setStyle(finalStyle);
        }
        
        return textNote;         
    }

    protected abstract void render(Notes note, Text formattedText);
}
