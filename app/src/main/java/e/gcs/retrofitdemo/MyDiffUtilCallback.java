package e.gcs.retrofitdemo;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import e.gcs.retrofitdemo.Model.Web;

public class MyDiffUtilCallback extends DiffUtil.Callback{
    List<Web> oldlist;

    List<Web > newlist;

    public MyDiffUtilCallback(List<Web> oldlist, List<Web> newlist) {
        this.oldlist = oldlist;
        this.newlist = newlist;
    }
    @Override
    public int getOldListSize() {
        return oldlist.size();
    }
    @Override
    public int getNewListSize() {
        return newlist.size();
    }
    @Override
    public boolean areItemsTheSame(int olditempostion, int newitemPostion) {
        return oldlist.get(olditempostion).getWebId().equals( newlist.get(newitemPostion).getWebId());

    }
    @Override
    public boolean areContentsTheSame(int olditempostion, int newitemPostion) {

        return oldlist.get(olditempostion).equals(newlist.get(newitemPostion));
    }
    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
