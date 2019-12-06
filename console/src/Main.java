import com.FinalOOP.Directory;
import mysql.DirectoryDAO;
import mysql.DirectoryDAOImpl;

import java.util.*;

import mysql.FileDAO;
import mysql.FileDAOImpl;
import org.apache.log4j.Logger;

import java.io.File;

import com.FinalOOP.DirFile;

public class Main{

    final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args){
        FinalOOP();
    }

    private static void FinalOOP(){

        String chosen = takeInput();
        openAndSave(chosen);
        System.out.println("DIRECTORY SUCCESSFULLY SAVED TO DATABASE");
        selectOperation();

}

    public static Directory diplayMostPopulous(){
        DirectoryDAO directoryDAO = new DirectoryDAOImpl();
        Directory mostDir = directoryDAO.getMostPopulous();
        return mostDir;
    }

    public static Directory displayBiggestDirectory(){
        DirectoryDAO directoryDAO = new DirectoryDAOImpl();
        Directory bigDir = directoryDAO.getBiggestDirectory();
        return bigDir;
    }

    public static ArrayList<DirFile> bigFiles(){
        FileDAO fileDAO = new FileDAOImpl();
        ArrayList<DirFile> bigFiles = fileDAO.getBiggest();
        return bigFiles;
    }

    public static void filterByFile(){
        FileDAO fileDAO = new FileDAOImpl();
        String selected = "";
        System.out.println("What kind of file extension are you looking for?");
        Scanner sc = new Scanner(System.in);
        selected = sc.nextLine();

        ArrayList<DirFile> allFiles = fileDAO.getFileList();

        if (checkForExt(selected)){
            for(DirFile file : allFiles){
                if (file.getFileType().equals(selected)){
                    System.out.println(file.getFileName());
                }
            }
        } else{
            System.out.println("Looks like we don't have that file. Please select a different file");
            filterByFile();
        }


    }

    public static boolean checkForExt(String selected){
        FileDAO fileDAO = new FileDAOImpl();
        ArrayList<DirFile> allFiles = fileDAO.getFileList();

        for(DirFile file : allFiles){
            if (file.getFileType().equals(selected)){
                return true;
            }
        } return false;
    };

    public static void selectOperation(){
        try {
            System.out.println("Select the number of the operation you would like to choose next");
            int selected = 0;
            System.out.println("1: Display directory largest in size");
            System.out.println("2: Display directory with most files");
            System.out.println("3: Display 5 largest files in size");
            System.out.println("4: Display all files of a certain type");
            System.out.println("5: Clear database and start over");
            Scanner sc = new Scanner(System.in);
            selected = sc.nextInt();

            if (selected == 1) {
                Directory bigDir = displayBiggestDirectory();
                System.out.println("Your biggest Directory is " + bigDir.getDirName() + " it is " + bigDir.getDirSize() + " kb");
                return;
            }
            if (selected == 2) {
                Directory mostDir = diplayMostPopulous();
                System.out.println("Your directory with the most files is named " + mostDir.getDirName() + " it has " + mostDir.getNumberOfFiles() + " files");
                return;
            }
            if (selected == 3) {
                ArrayList<DirFile> largeFiles = bigFiles();
                for (DirFile largeFile : largeFiles){
                    System.out.println("Your big file is named " + largeFile.getFileName() + "its size is " + largeFile.getFileSize() / 1000 + " mb");
                }
                return;
            }
            if (selected == 4){
                filterByFile();
            }
            if (selected == 5){
                FileDAO fileDAO = new FileDAOImpl();
                System.out.println(fileDAO.initiateSelfDestruct());
            }
            else {
                System.out.println("Oops, you must select one of the given directories");
                selectOperation();
            }
        } catch (InputMismatchException inMis){
            System.out.println("Whoops, you must put in a number");
            selectOperation();
        }
    }

    public static void openAndSave(String chosen){
        File myFile = new File(chosen);
        System.out.println();
        if(myFile.isFile()) {
            String ext = myFile.getName();
            String  extension = getExtension(ext);

            DirFile dirFile = new DirFile();
            dirFile.setFileName(myFile.getName());
            dirFile.setFileType(extension);
            dirFile.setFileSize(getFileSizeKiloBytes(myFile));
            logger.info(myFile.getPath());
            dirFile.setFilePath(myFile.getPath());

            FileDAO fileDAO = new FileDAOImpl();
            int id = fileDAO.insertFile(dirFile);
            logger.info("New file record inserted. ID = " + id);
        }
        else if(myFile.isDirectory()){
            int fileCount = 0;
            long totalDirSize = 0;
            Directory directory = new Directory();

            File[] files = myFile.listFiles();

            for(File dirFile : files){
                fileCount = fileCount +1;
                System.out.println(dirFile.getPath());
                ArrayList<File> fileList = new ArrayList<>();
                fileList.add(dirFile);
                for(File innerFile : fileList){
                    totalDirSize += innerFile.length();
                    openAndSave(innerFile.getPath());
                }
                directory.setDirSize(totalDirSize / 1024);
            }
            DirectoryDAO directoryDAO = new DirectoryDAOImpl();
            directory.setDirName(myFile.getName());
            directory.setNumberOfFiles(fileCount);
            //todo: check why directory size is always 4kb
            directory.setPath(myFile.getPath());
            int id = directoryDAO.insertDirectory(directory);
            logger.info("New Directory record inserted. ID = " + id);
        }
        else{
            System.out.println("That is not a valid directory");
            FinalOOP();
        }
    }

    public static void pullFromDatabase(){
        System.out.println("\n What would you like to do next?");
    }

    public static long getFileSizeKiloBytes(File file){
        System.out.println(file.length());
        return file.length() / 1024;
    }

    //todo: right now it will add numbers as extensions. I need a way to filter out the numbers
     public static String getExtension(String filename) {
        if(filename.contains(".")){
            String extension = filename.substring((filename.lastIndexOf(".") + 1));
              return extension;
        } else {
            return "";
        }
    }

    public static String takeInput(){
        String chosen = null;

        Scanner sc = new Scanner(System.in);
        System.out.println("Please input a Directory");
        System.out.println("If you want to test input . or /home/tucker/Downloads");

       chosen = sc.nextLine();
       return chosen;
    }

}
