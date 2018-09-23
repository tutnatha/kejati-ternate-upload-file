package com.mycompany.fileupload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@SessionScoped
public class FileUploadManagedBean {

    UploadedFile file;
    private List<FilesVO> fileList;
    private String[] selectedFiles;
    private String eventHall;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    @PostConstruct
    public void init() {
        fileList = new ArrayList<FilesVO>();
    }

    public void fileUploadListener(FileUploadEvent e) throws IOException {
        // Get uploaded file from the FileUploadEvent
        this.file = e.getFile();
        String newProfileFileName = e.getFile().getFileName();
        InputStream is = null;
        FileOutputStream os = null;
        //File theDir = new File("c:\\\\31-02-2014\\\\");
	File theDir = new File("//home//tutnatha//github.com//ipresent2k14//FileUpload//31-02-2014//");
        if (!theDir.exists()) {
            theDir.mkdir();
        }
        try {
            is = e.getFile().getInputstream();
            //os = new FileOutputStream(new File("c:\\\\31-02-2014\\\\", newProfileFileName));
            os = new FileOutputStream(new File("//home//tutnatha//github.com//ipresent2k14//FileUpload//31-02-2014//", newProfileFileName));
            IOUtils.copy(is, os);
        } finally {
            IOUtils.closeQuietly(os);
            IOUtils.closeQuietly(is);
        }
        // Print out the information of the file.
        fileList.add(new FilesVO(file.getFileName()));
        System.out.println("Uploaded File Name Is :: " + file.getFileName() + " :: Uploaded File Size :: " + file.getSize());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("File Uploaded Successfully...."));
    }
    
    public void pushFiles()
    {
      String[] files = getSelectedFiles();
      for(int i=0;i<files.length;i++) {
          //String filepath = "C:\\31-02-2014\\"+files[i]; //msdos
          String filepath = "//home//tutnatha//github.com//ipresent2k14//FileUpload//"+files[i];	//unix
	  String host = getEventHall();
          //String cmd = "xcopy "+filepath+" \\\\"+host;
          String cmd = "xcopy "+filepath+" //"+host;
	  System.out.println(cmd);
          //ProcessBuilder builder = new ProcessBuilder(cmd);
          try {
//              Process p = builder.start();
//              InputStream is = p.getInputStream();
//              BufferedReader br = new BufferedReader(new InputStreamReader(is));
//              String line;
//              while((line = br.readLine())!= null) {
//                  System.out.println(line);
//              }
//              int ext = p.waitFor();
              Runtime.getRuntime().exec(cmd);
          } catch (IOException ex) {
              Logger.getLogger(FileUploadManagedBean.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
    }

    public List<FilesVO> getFileList() {
        return fileList;
    }

    public void setFileList(List<FilesVO> fileList) {
        this.fileList = fileList;
    }

    public String[] getSelectedFiles() {
        return selectedFiles;
    }

    public void setSelectedFiles(String[] selectedFiles) {
        this.selectedFiles = selectedFiles;
    }

    public String getEventHall() {
        return eventHall;
    }

    public void setEventHall(String eventHall) {
        this.eventHall = eventHall;
    }
    
    

}
