/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhc.services.notes;

/**
 *
 * @author nguye
 */
public class BoldNoteDecorator extends NoteDecorator{
    
    public BoldNoteDecorator(INote decoratorNote) {
        super(decoratorNote);
    }

    @Override
    public String getStyle() {
        return decoratorNote.getStyle() + "-fx-font-weight: bold; ";
    }
}
