/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fileupload;

/**
 *
 * @author AMMA
 */
public class FilesVO {

    private String fileName;

    public FilesVO() {
    }

    public FilesVO(String fileName) {
        this.fileName = fileName;
    }
    
    

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
