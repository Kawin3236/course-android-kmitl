package kmitl.lab07.kawin58070006.lazyinstagram;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import kmitl.lab07.kawin58070006.lazyinstagram.adapter.PostAdapter;
import kmitl.lab07.kawin58070006.lazyinstagram.api.LazyInstagramApi;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getUserProfile("android");

    }

    private void getUserProfile(String name){
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LazyInstagramApi.BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LazyInstagramApi lazyInstagramApi = retrofit.create(LazyInstagramApi.class);
        Call<UserProfile> call = lazyInstagramApi.getProfile(name);
        call.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                UserProfile userProfile = response.body();
                setAdapter(userProfile);
                setView(userProfile);
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {

            }
        });

    }

    public void setView(UserProfile userProfile) {
        TextView username = (TextView) findViewById(R.id.textName);
        TextView post = (TextView) findViewById(R.id.textPost);
        TextView follower = (TextView) findViewById(R.id.textFollower);
        TextView following = (TextView) findViewById(R.id.textFollowing);
        TextView bio = (TextView) findViewById(R.id.textBio);

        username.setText("@" + userProfile.getUser());
        post.setText(userProfile.getPost());
        follower.setText(userProfile.getFollower());
        following.setText(userProfile.getFollowing());
        bio.setText(userProfile.getBio());

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        Glide.with(MainActivity.this)
                .load(userProfile.getUrlProfile())
                .into(imageView);
    }

    public void setAdapter(UserProfile userProfile) {
        PostAdapter postAdaptet = new PostAdapter(MainActivity.this);
        postAdaptet.setData(userProfile.getPosts());
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));
        recyclerView.setAdapter(postAdaptet);
    }




}
