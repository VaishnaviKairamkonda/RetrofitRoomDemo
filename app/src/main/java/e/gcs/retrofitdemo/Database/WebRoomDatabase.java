package e.gcs.retrofitdemo.Database;

import android.content.Context;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import e.gcs.retrofitdemo.Dao.WebDao;
import e.gcs.retrofitdemo.Model.Web;

@Database(entities = {Web.class}, version = 1)
public abstract class WebRoomDatabase extends RoomDatabase {


    private static final String DATABASE_NAME="web_database";
    public abstract WebDao webDao();

    private static volatile WebRoomDatabase INSTANCE;
    public static Executor executor = Executors.newSingleThreadExecutor();

    public static WebRoomDatabase getInstance(Context context){
        if(INSTANCE == null)
        {
            synchronized (WebRoomDatabase.class){
                if(INSTANCE == null)
                {
                    INSTANCE= Room.databaseBuilder(context,WebRoomDatabase.class,
                            DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}

