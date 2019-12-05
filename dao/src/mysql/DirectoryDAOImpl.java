package mysql;

import com.FinalOOP.Directory;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DirectoryDAOImpl extends MySQL implements DirectoryDAO {


    @Override
        public Directory getDirectoryById(int DirID) {
            Connect();
            Directory directory = null;
            try{
                String sp = "{call GetDirectory(?,?)}";
                CallableStatement cStmt = connection.prepareCall(sp);
                cStmt.setInt(1,GET_BY_ID);
                cStmt.setInt(2,DirID);
                ResultSet rs = cStmt.executeQuery();

                if(rs.next()) {
                    directory = HydrateDirectory(rs);
                }

            }catch (SQLException sqlEx){
                logger.error(sqlEx);
            }

            return directory;
        }

    @Override
    public List<Directory> getDirectoryList() {
        Connect();
        List<Directory> dirList = new ArrayList<>();
        try{
            String sp = "{call GetDirectory(?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);
            cStmt.setInt(1,GET_COLLECTION);
            cStmt.setInt(2,0);
            ResultSet rs = cStmt.executeQuery();

            while(rs.next()) {
                dirList.add(HydrateDirectory(rs));
            }

        }catch (SQLException sqlEx){
            logger.error(sqlEx);
        }

        return dirList;
    }

    @Override
    public Directory getBiggestDirectory() {
        Connect();
        Directory directory = null;
        try{
            String sp = "{call GetDirectory(?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);
            cStmt.setInt(1, 35);
            cStmt.setInt(2,0);
            ResultSet rs = cStmt.executeQuery();

            if(rs.next()) {
                directory = HydrateDirectory(rs);
            }

        }catch (SQLException sqlEx){
            logger.error(sqlEx);
        }

        return directory;
    }

    @Override
    public Directory getMostPopulous() {
        Connect();
        Directory directory = null;
        try{
            String sp = "{call GetDirectory(?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);
            cStmt.setInt(1, 45);
            cStmt.setInt(2,0);
            ResultSet rs = cStmt.executeQuery();

            if(rs.next()) {
                directory = HydrateDirectory(rs);
            }

        }catch (SQLException sqlEx){
            logger.error(sqlEx);
        }

        return directory;
    }

    @Override
    public int insertDirectory(Directory directory) {
        Connect();
        int id = 0;
        try{
            String sp = "{call ExecDirectory(?,?,?,?,?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);
            cStmt.setInt(1,INSERT);
            cStmt.setInt(2, directory.getDirID());
            cStmt.setString(3, directory.getDirName());
            cStmt.setLong(4, directory.getDirSize());
            cStmt.setInt(5, directory.getNumberOfFiles());
            cStmt.setString(6, directory.getPath());

            ResultSet rs = cStmt.executeQuery();
            if(rs.next()){
                id = rs.getInt(1);
            }

        }catch(SQLException sqlEx){
            logger.error(sqlEx);
        }
        return id;
    }

    @Override
    public boolean updateDirectory(Directory directory) {
        Connect();
        int id = 0;
        try{
            String sp = "{call ExecDirectory(?,?,?,?,?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);
            cStmt.setInt(1,UPDATE);
            cStmt.setInt(2, directory.getDirID());
            cStmt.setString(3, directory.getDirName());
            cStmt.setLong(4, directory.getDirSize());
            cStmt.setInt(5, directory.getNumberOfFiles());
            cStmt.setString(6, directory.getPath());

            ResultSet rs = cStmt.executeQuery();
            if(rs.next()){
                id = rs.getInt(1);
            }

        }catch(SQLException sqlEx){
            logger.error(sqlEx);
        }
        return id > 0;
    }

    @Override
    public boolean deleteDirectory(int DirID) {
        Connect();
        int id = 0;
        try{
            String sp = "{call ExecDirectory(?,?,?,?,?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);
            cStmt.setInt(1,DELETE);
            cStmt.setInt(2, DirID);
            cStmt.setString(3,"");
            cStmt.setInt(4, 0);
            cStmt.setInt(5, 0);
            cStmt.setString(6, "");

            ResultSet rs = cStmt.executeQuery();
            if(rs.next()){
                id = rs.getInt(1);
            }

        }catch(SQLException sqlEx){
            logger.error(sqlEx);
        }
        return id > 0;
    }

    public static Directory HydrateDirectory(ResultSet rs) throws SQLException {
        Directory directory = new Directory();
        directory.setDirID(rs.getInt(1));
        directory.setDirName(rs.getString(2));
        directory.setDirSize(rs.getInt(3));
        directory.setNumberOfFiles(rs.getInt(4));
        directory.setPath(rs.getString(5));

        return directory;
    }



}
