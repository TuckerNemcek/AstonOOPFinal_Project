package mysql;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.FinalOOP.DirFile;

import java.util.ArrayList;
import java.util.List;

public class FileDAOImpl extends MySQL implements FileDAO {

    private static FileDAO fileDAO = new FileDAOImpl();

    @Override
    public DirFile getFileById(int fileID) {
        Connect();
       DirFile file = null;
       try{
           String sp = "{call GetFile(?,?)}";
           CallableStatement cStmt = connection.prepareCall(sp);
           cStmt.setInt(1, GET_BY_ID);
           cStmt.setInt(2, fileID);
           ResultSet rs = cStmt.executeQuery();

           if(rs.next()){
               file = HydrateFile(rs);
           }

       }catch(SQLException sqlEx){
           logger.error(sqlEx);
       }
       return file;
    }

    @Override
    public ArrayList<DirFile> getFileList() {
        Connect();
        ArrayList<DirFile> fileList = new ArrayList<>();
        try{
            String sp = "{call GetFile(?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);
            cStmt.setInt(1, GET_COLLECTION);
            cStmt.setInt(2,0);
            ResultSet rs = cStmt.executeQuery();

            while(rs.next()){
                fileList.add(HydrateFile(rs));
            }

        }catch (SQLException sqlEx){
            logger.error(sqlEx);
        }
        return fileList;
    }

    @Override
    public ArrayList<DirFile> getBiggest() {
        Connect();
        ArrayList<DirFile> fileList = new ArrayList<>();
        try{
            String sp = "{call GetFile(?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);
            cStmt.setInt(1, 35);
            cStmt.setInt(2,0);
            ResultSet rs = cStmt.executeQuery();

            while(rs.next()){
                fileList.add(HydrateFile(rs));
            }

        }catch (SQLException sqlEx){
            logger.error(sqlEx);
        }
        return fileList;
    }

    @Override
    public String initiateSelfDestruct() {
        Connect();
        try{
            String sp = "{call GetFile(?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);
            cStmt.setInt(1, 45);
            cStmt.setInt(2,0);
            ResultSet rs = cStmt.executeQuery();

        }catch (SQLException sqlEx){
            logger.error(sqlEx);
        }
        return "File and Directory databases deleted. All evidence destroyed Mr. President";
    }


    @Override
    public int insertFile(DirFile file) {
        Connect();
        int id = 0;
        try{
            String sp = "{call ExecFile(?,?,?,?,?,?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);
            cStmt.setInt(1, INSERT);
            cStmt.setInt(2, 0);
            cStmt.setString(3,file.getFileName());
            cStmt.setString(4,file.getFileType());
            cStmt.setLong(5,file.getFileSize());
            cStmt.setString(6,file.getFilePath());
            cStmt.setInt(7, file.getDirID());

            ResultSet rs = cStmt.executeQuery();
            if(rs.next()){
                id = rs.getInt(1);
            }

        }catch (SQLException sqlEx){
            logger.error(sqlEx);
        }
        return id;
    }

    @Override
    public boolean updateFile(DirFile file) {
        Connect();
        int id = 0;
        try{
            String sp = "{call ExecFile(?,?,?,?,?,?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);
            cStmt.setInt(1, UPDATE);
            cStmt.setInt(2, file.getFileID());
            cStmt.setString(3,file.getFileName());
            cStmt.setString(4,file.getFileType());
            cStmt.setLong(5,file.getFileSize());
            cStmt.setString(6,file.getFilePath());
            cStmt.setInt(7, file.getDirID());

            ResultSet rs = cStmt.executeQuery();
            if(rs.next()){
                id = rs.getInt(1);
            }

        }catch (SQLException sqlEx){
            logger.error(sqlEx);
        }
        return id > 0;
    }

    @Override
    public boolean deleteFile(int fileID) {
        Connect();
        int id = 0;
        try{
            String sp = "{call ExecFile(?,?,?,?,?,?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);
            cStmt.setInt(1, DELETE);
            cStmt.setInt(2, fileID);
            cStmt.setString(3,"");
            cStmt.setString(4,"");
            cStmt.setInt(5,0);
            cStmt.setString(6,"");
            cStmt.setInt(7, 0);

            ResultSet rs = cStmt.executeQuery();
            if(rs.next()){
                id = rs.getInt(1);
            }

        }catch (SQLException sqlEx){
            logger.error(sqlEx);
        }
        return id > 0;
    }

    private static DirFile HydrateFile(ResultSet rs) throws SQLException {
        DirFile file = new DirFile();
        file.setFileID(rs.getInt(1));
        file.setFileName(rs.getString(2));
        file.setFileType(rs.getString(3));
        file.setFileSize(rs.getInt(4));
        file.setFilePath(rs.getString(5));

        return file;
    }

}
