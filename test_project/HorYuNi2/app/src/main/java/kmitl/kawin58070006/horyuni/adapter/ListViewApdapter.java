package kmitl.kawin58070006.horyuni.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import kmitl.kawin58070006.horyuni.User;

/**
 * Created by Administrator on 1/11/2560.
 */

public class ListViewApdapter extends BaseAdapter{

    Activity activity;
    List<User> lstUsers;
    LayoutInflater inflater;

    @Override
    public int getCount() {
        return lstUsers.size();
    }

    @Override
    public Object getItem(int i) {
        return lstUsers.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
