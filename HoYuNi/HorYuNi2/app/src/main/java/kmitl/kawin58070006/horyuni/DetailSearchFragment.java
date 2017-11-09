package kmitl.kawin58070006.horyuni;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailSearchFragment extends Fragment {


    public DetailSearchFragment() {
        // Required empty public constructor
    }

    public static DetailSearchFragment newInstance() {

        Bundle args = new Bundle();

        DetailSearchFragment fragment = new DetailSearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail_search, container, false);


        return rootView;
    }

}
