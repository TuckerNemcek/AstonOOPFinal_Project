package com.FinalOOP;

import java.util.ArrayList;

public class Directory {
    //region PARAMETERS
    private int DirID;
    private String DirName;
    private int NumberOfFiles;
    private String Path;
    private long DirSize;
    private ArrayList<DirFile> FileList;
    //endregion

    //region Constructors
    public Directory(){};

    public Directory (int dirID, String dirName, int numberOfFiles, String path, int dirSize, ArrayList fileList){
        this.setDirID(dirID);
        this.setDirName(dirName);
        this.setNumberOfFiles(numberOfFiles);
        this.setPath(path);
        this.setDirSize(dirSize);
        this.setFileList(fileList);
    }
    //endregion

    //region GET AND SET

    public int getDirID() {
        return DirID;
    }

    public void setDirID(int dirID) {
        DirID = dirID;
    }

    public String getDirName() {
        return DirName;
    }

    public void setDirName(String dirName) {
        DirName = dirName;
    }

    public int getNumberOfFiles() {
        return NumberOfFiles;
    }

    public void setNumberOfFiles(int numberOfFiles) {
        NumberOfFiles = numberOfFiles;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }

    public long getDirSize() {
        return DirSize;
    }

    public void setDirSize(long dirSize) {
        DirSize = dirSize;
    }

    public ArrayList<DirFile> getFileList() {
        return FileList;
    }

    public void setFileList(ArrayList<DirFile> fileList) {
        FileList = fileList;
    }

//endregion
}
