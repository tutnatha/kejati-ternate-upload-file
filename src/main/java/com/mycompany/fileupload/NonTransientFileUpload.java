package com.mycompany.fileupload;

public class NonTransientFileUpload extends org.primefaces.component.fileupload.FileUpload {
    @Override
    public boolean isTransient() {
        return false;
    }
}