package e.gcs.retrofitdemo.Database;

import android.content.Context;
import android.os.AsyncTask;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import e.gcs.retrofitdemo.Dao.WebDao;
import e.gcs.retrofitdemo.Model.Web;

@Database(entities = {Web.class}, version = 1)
public abstract class WebRoomDatabase extends RoomDatabase {


    private static final String DATABASE_NAME="web_database";
    public abstract WebDao webDao();

    private static volatile WebRoomDatabase INSTANCE;


    public static WebRoomDatabase getInstance(Context context){
        if(INSTANCE == null)
        {
            synchronized (WebRoomDatabase.class){
                if(INSTANCE == null)
                {
                    INSTANCE= Room.databaseBuilder(context,WebRoomDatabase.class,
                            DATABASE_NAME)
                            .addCallback(callback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    static Callback callback=new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsynTask(INSTANCE);
        }
    };
    static class PopulateAsynTask extends AsyncTask<Void,Void,Void>
    {
        private WebDao webDao;
        PopulateAsynTask(WebRoomDatabase webRoomDatabase)
        {
            webDao=webRoomDatabase.webDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            webDao.deleteAll();
            return null;
        }
    }

}

