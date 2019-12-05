package mysql;

import com.FinalOOP.Directory;

import java.util.List;

public interface DirectoryDAO {
    //region GET METHODS
    public Directory getDirectoryById(int fileID);
    public List<Directory> getDirectoryList();
    public Directory getBiggestDirectory();
    public Directory getMostPopulous();
    //endregion

    //region EXECUTE METHODS
    public int insertDirectory(Directory directory);
    public boolean updateDirectory(Directory directory);
    public boolean deleteDirectory(int DirID);
    //endregion
}
