/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhc.utils;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author nguye
 */
public class MyAlert {
    private static MyAlert instance;
    private final Alert alert;

    private MyAlert(){
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Note App");
        alert.setHeaderText("Note App");
    }
    
    public static MyAlert getInstance(){
        if(instance == null){
            instance = new MyAlert();
        }
        return instance;
    }
    
    public void showMsg(String content){
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    public Optional<ButtonType> showMsg(String content, Alert.AlertType type){
        alert.setContentText(content);
        alert.setAlertType(type);
        return alert.showAndWait();
    }
    
}
