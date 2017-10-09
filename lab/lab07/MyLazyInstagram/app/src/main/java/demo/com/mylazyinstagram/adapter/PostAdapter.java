package demo.com.mylazyinstagram.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import demo.com.mylazyinstagram.PostModel;
import demo.com.mylazyinstagram.R;

/**
 * Created by student on 10/6/2017 AD.
 */
public class PostAdapter extends RecyclerView.Adapter<PostAdapter.Holder>{

    private Activity activity;
    private List<PostModel> data;
    public PostAdapter(Activity activity) {
        this.activity = activity;
        data = new ArrayList<>();
    }
    public void setData(List<PostModel> data) {
        this.data = data;
    }
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, null);
        Holder holder = new Holder(itemView);
        return holder;
    }
    @Override
    public void onBindViewHolder(Holder holder, int position) {
        String imageUrl = data.get(position).getUrl();
        Glide.with(activity).load(imageUrl).into(holder.imageView);
    }
    @Override
    public int getItemCount() {
        return data.size();
    }
    static class Holder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public Holder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
