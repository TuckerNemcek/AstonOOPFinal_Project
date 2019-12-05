package mysql;
import com.FinalOOP.DirFile;


import java.util.ArrayList;
import java.util.List;

public interface FileDAO {
    //region GET METHODS
    public DirFile getFileById(int fileID);
    public ArrayList<DirFile> getFileList();
    public ArrayList<DirFile> getBiggest();
    public String initiateSelfDestruct();
    //endregion

    //region EXECUTE METHODS
    public int insertFile(DirFile file);
    public boolean updateFile(DirFile file);
    public boolean deleteFile(int fileID);
}
