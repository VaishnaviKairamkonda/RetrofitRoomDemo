package e.gcs.retrofitdemo.Dao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import e.gcs.retrofitdemo.Model.Web;

@Dao
public interface WebDao {
  //  @Insert(onConflict = OnConflictStrategy.IGNORE)
    //void insert(Web web);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Web> webList);

    @Query("DELETE FROM tbl_Web")
    void deleteAll();
    @Delete
    public void delete(Web web);

    @Update
    public void update(Web web);
    @Query("SELECT * FROM tbl_Web ORDER BY WebId DESC")
    LiveData<List<Web>> getAllWebs();
 //   List<Student> getStudentData();

}
