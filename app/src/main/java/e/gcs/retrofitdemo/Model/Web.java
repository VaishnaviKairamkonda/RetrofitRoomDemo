package e.gcs.retrofitdemo.Model;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_Web")
public class Web {

    @PrimaryKey(autoGenerate=true)
    private Integer webId;

    @SerializedName("title")
    @ColumnInfo(name = "title")
    private String title;

    @SerializedName("explanation")
    @ColumnInfo(name = "explanation")
    private String explanation;

    @SerializedName("url")
    @ColumnInfo(name = "url")
    private String url;

    public Web(String title, String url, String explanation) {
        this.title = title;
        this.url = url;
        this.explanation = explanation;

    }


    @NonNull
    public Integer getWebId() {
        return webId;
    }

    public void setWebId(@NonNull Integer webId) {
        this.webId = webId;
    }

    public String getTitle() {
        return title;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getUrl() {
        return url;
    }



    @Override
    public String toString() {
        return "Web{" +
                "title" + title +
                ", explanation" + explanation +
                ", url'" + url  +

                '}';
    }
}