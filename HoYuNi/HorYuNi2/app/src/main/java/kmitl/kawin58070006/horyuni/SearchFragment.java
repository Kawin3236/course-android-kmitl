package kmitl.kawin58070006.horyuni;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements View.OnClickListener {

    private ImageView zoneK1;
    private ImageView zoneK2;
    private ImageView zoneK3;
    private ImageView zoneWP;
    private ImageView zoneJL;
    private ImageView zoneHM;
    private ImageView zoneRNP;
    private ImageView zonePapaMama;
    private ImageView zoneKS;
    private DatabaseReference mDatabaseRef;
    private int num1 = 0;


    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance() {
        Bundle args = new Bundle();

        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        zoneK1 = rootView.findViewById(R.id.ZoneK1);
        zoneK2 = rootView.findViewById(R.id.ZoneK2);
        zoneK3 = rootView.findViewById(R.id.ZoneK3);
        zoneWP = rootView.findViewById(R.id.ZoneWP);
        zoneJL = rootView.findViewById(R.id.ZoneKJL);
        zoneHM = rootView.findViewById(R.id.ZoneHM);
        zoneRNP = rootView.findViewById(R.id.ZoneRNP);
        zonePapaMama = rootView.findViewById(R.id.ZonePapaMama);
        zoneKS = rootView.findViewById(R.id.ZoneKS);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference(MainActivity.FB_Database_Path);

        mDatabaseRef.orderByChild("name").equalTo("หอหีเยดมัย").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot data : dataSnapshot.getChildren()){
                    num1++;
                    //zoneK1.setText(String.valueOf(num1));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(":::::::1111111");
            }
        });





        zoneK1.setOnClickListener(this);
        zoneK2.setOnClickListener(this);
        zoneK3.setOnClickListener(this);
        zoneWP.setOnClickListener(this);
        zoneJL.setOnClickListener(this);
        zoneHM.setOnClickListener(this);
        zoneRNP.setOnClickListener(this);
        zonePapaMama.setOnClickListener(this);
        zoneKS.setOnClickListener(this);



        return rootView;
    }

    @Override
    public void onClick(View v) {

        if(zoneK1.getId() == v.getId()){
            getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, DetailSearchFragment.newInstance("เกกี1")).addToBackStack(null).commit();
        }
        else if(zoneK2.getId() == v.getId()){
            getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, DetailSearchFragment.newInstance("หอหีเยดมัย")).addToBackStack(null).commit();
        }
        else if(zoneK3.getId() == v.getId()){
            getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, DetailSearchFragment.newInstance("")).addToBackStack(null).commit();
        }
        else if(zoneWP.getId() == v.getId()){
            getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, DetailSearchFragment.newInstance("")).addToBackStack(null).commit();
        }
        else if(zoneJL.getId() == v.getId()){
            getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, DetailSearchFragment.newInstance("")).addToBackStack(null).commit();
        }
        else if(zoneHM.getId() == v.getId()){
            getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, DetailSearchFragment.newInstance("")).addToBackStack(null).commit();
        }
        else if(zoneRNP.getId() == v.getId()){
            getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, DetailSearchFragment.newInstance("")).addToBackStack(null).commit();
        }
        else if(zonePapaMama.getId() == v.getId()){
            getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, DetailSearchFragment.newInstance("")).addToBackStack(null).commit();
        }
        else if(zoneKS.getId() == v.getId()){
            getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, DetailSearchFragment.newInstance("")).addToBackStack(null).commit();
        }

    }


}
