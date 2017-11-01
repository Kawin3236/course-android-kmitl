package kmitl.kawin58070006.horyuni;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kmitl.kawin58070006.horyuni.adapter.PostAdapter;
import kmitl.kawin58070006.horyuni.api.Api;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        getUserProfile("android");
        return rootView;
    }


    private void getUserProfile(String name) {
        getConnection();
        Call<UserProfile> call = getConnection().getProfile(name);
        call.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, final Response<UserProfile> response) {
                UserProfile userProfile = response.body();
                setAdapter(userProfile, "G");
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {
            }
        });
    }

    public Api getConnection() {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        return api;
    }

    public void setAdapter(UserProfile userProfile, String layout) {
        PostAdapter postAdaptet = new PostAdapter(HomeFragment.this);
        postAdaptet.setData(userProfile.getPosts(), layout);
        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.list);
        if (layout.equals("Grid")) {
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        recyclerView.setAdapter(postAdaptet);
    }

}
