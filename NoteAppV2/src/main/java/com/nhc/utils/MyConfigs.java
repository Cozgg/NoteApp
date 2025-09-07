/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhc.utils;

import com.nhc.services.BaseServices;
import com.nhc.services.NoteServices;
import com.nhc.services.TagServices;
import com.nhc.services.notes.UpdateNoteServices;

/**
 *
 * @author nguye
 */
public class MyConfigs {

    public static final BaseServices NoteServices = new NoteServices();
    public static final UpdateNoteServices uNServices = new UpdateNoteServices();
    public static final BaseServices TagServices = new TagServices();
    
}
