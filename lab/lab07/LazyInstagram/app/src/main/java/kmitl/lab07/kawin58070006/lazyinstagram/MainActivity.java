package kmitl.lab07.kawin58070006.lazyinstagram;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

    private String user = "android";
    private Spinner spinner;
    private ArrayAdapter<CharSequence> adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getUserProfile(user);
        spinner = findViewById(R.id.spinUser);
        adapter = ArrayAdapter.createFromResource(this, R.array.username, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getBaseContext(), adapterView.getItemAtPosition(i)+ " selected", Toast.LENGTH_LONG).show();
                getUserProfile((String) adapterView.getItemAtPosition(i));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void getUserProfile(String name) {
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
            public void onResponse(Call<UserProfile> call, final Response<UserProfile> response) {
                UserProfile userProfile = response.body();
                setAdapter(userProfile, "Grid");
                setView(userProfile);

                final Button btnLayout = findViewById(R.id.btnLayout);
                btnLayout.setOnClickListener(new View.OnClickListener() {
                    String layout = "Grid";
                    UserProfile userProfile = response.body();

                    @Override
                    public void onClick(View view) {
                        if (layout.equals("Grid")) {
                            setAdapter(userProfile, "");
                            setView(userProfile);
                            layout = "Linear";
                        } else {
                            setAdapter(userProfile, "Grid");
                            setView(userProfile);
                            layout = "Grid";
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {

            }
        });

    }

    public void setView(UserProfile userProfile) {
        
        TextView post = (TextView) findViewById(R.id.textPost);
        TextView follower = (TextView) findViewById(R.id.textFollower);
        TextView following = (TextView) findViewById(R.id.textFollowing);
        TextView bio = (TextView) findViewById(R.id.textBio);

        post.setText(userProfile.getPost());
        follower.setText(userProfile.getFollower());
        following.setText(userProfile.getFollowing());
        bio.setText(userProfile.getBio());

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        Glide.with(MainActivity.this)
                .load(userProfile.getUrlProfile())
                .into(imageView);
    }

    public void setAdapter(UserProfile userProfile, String layout) {
        PostAdapter postAdaptet = new PostAdapter(MainActivity.this);
        postAdaptet.setData(userProfile.getPosts(), layout);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        if (layout.equals("Grid")) {
            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        }
        recyclerView.setAdapter(postAdaptet);
    }


}
