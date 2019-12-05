package com.FinalOOP;

public class DirFile {
    //region PARAMETERS
    private int FileID;
    private String FileName;
    private String FileType;
    private long FileSize;
    private String FilePath;
    private int DirID;
    //endregion

    //region CONSTRUCTORS

    public DirFile(){}

    public DirFile(int fileID, String fileName, String fileType, int fileSize, String filePath, int dirID){
        this.setFileID(fileID);
        this.setFileName(fileName);
        this.setFileType(fileType);
        this.setFileSize(fileSize);
        this.setFilePath(filePath);
        this.setDirID(dirID);
    }

    //endregion

    //region GET AND SET

    public int getFileID() {
        return FileID;
    }

    public void setFileID(int fileID) {
        FileID = fileID;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public String getFileType() {
        return FileType;
    }

    public void setFileType(String fileType) {
        FileType = fileType;
    }

    public long getFileSize() {
        return FileSize;
    }

    public void setFileSize(long fileSize) {
        FileSize = fileSize;
    }

    public String getFilePath() {
        return FilePath;
    }

    public void setFilePath(String filePath) {
        FilePath = filePath;
    }

    public int getDirID() {
        return DirID;
    }

    public void setDirID(int dirID) {
        DirID = dirID;
    }
    //endregion
}
