package kmitl.kawin58070006.horyuni;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private DatabaseReference mDatabaseRef;
    private List<ImageUpload> imgList;
    private ListView lv;
    private ImageListAdapter adapter;
    private ProgressDialog progressDialog;
    private ImageView addPost;
    private Detail detail;


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
        imgList = new ArrayList<>();
        lv = (ListView) rootView.findViewById(R.id.listViewImage);
        //Show progress dialog during list image loading
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please wait loading list image...");
        progressDialog.show();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference(MainActivity.FB_Database_Path);

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.dismiss();

                //Fetch image data from firebase database
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //ImageUpload class require default constructor
                    ImageUpload img = snapshot.getValue(ImageUpload.class);
                    imgList.add(img);
                }


                //Init adapter
                Collections.reverse(imgList);
                adapter = new ImageListAdapter(getActivity(), R.layout.image_item, imgList, getActivity());
                //Set adapter for listview
                lv.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                progressDialog.dismiss();
            }
        });


        addPost = rootView.findViewById(R.id.addPost);
        addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragmentContainer, MainFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
            }
        });



        return rootView;
    }

    public void setDetail(Detail detail){
        this.detail = detail;
    }

}