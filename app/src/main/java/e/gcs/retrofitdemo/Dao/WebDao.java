package e.gcs.retrofitdemo.Dao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import e.gcs.retrofitdemo.Model.Web;

@Dao
public interface WebDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Web> webList);

    @Query("SELECT * FROM tbl_Web ORDER BY WebId DESC")
    LiveData<List<Web>> getAllWebs();


}
