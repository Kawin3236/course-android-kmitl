package kmitl.lab07.kawin58070006.lazyinstagram.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import kmitl.lab07.kawin58070006.lazyinstagram.PostModel;
import kmitl.lab07.kawin58070006.lazyinstagram.R;


/**
 * Created by Administrator on 11/10/2560.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.Holder> {

    private Activity activity;
    private List<PostModel> data;
    private String layout;

    public PostAdapter(Activity activity) {
        this.activity = activity;
        data = new ArrayList<>();
    }

    public void setData(List<PostModel> data, String layout) {
        this.data = data;
        this.layout = layout;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layout.equals("Grid")) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, null);
            Holder holder = new Holder(itemView);
            return holder;
        } else {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item2, null);
            Holder holder = new Holder(itemView);
            return holder;
        }


    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        String imageUrl = data.get(position).getUrl();
        TextView like = holder.like;
        TextView comment = holder.comment;
        Glide.with(activity).load(imageUrl).into(holder.imageView);
        like.setText(data.get(position).getLike());
        comment.setText(data.get(position).getComment());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView like;
        TextView comment;

        public Holder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            like = (TextView) itemView.findViewById(R.id.txtLike);
            comment = (TextView) itemView.findViewById(R.id.txtComment);
        }
    }
}

