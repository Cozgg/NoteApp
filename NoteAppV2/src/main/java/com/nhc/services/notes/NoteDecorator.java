/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhc.services.notes;

/**
 *
 * @author nguye
 */
public class NoteDecorator implements INote{

    
    protected INote decoratorNote;

    public NoteDecorator(INote decorator) {
        this.decoratorNote = decorator;
    }
    
    
    @Override
    public String getStyle() {
        return this.decoratorNote.getStyle();
    }
}
