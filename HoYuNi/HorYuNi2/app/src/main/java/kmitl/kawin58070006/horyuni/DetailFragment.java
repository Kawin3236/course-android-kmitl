package kmitl.kawin58070006.horyuni;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {
    private TextView name;
    private static Detail detail;


    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance(Detail detail) {
        Bundle args = new Bundle();
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        setDetail(detail);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        name = rootView.findViewById(R.id.textnameDetail);
        name.setText(detail.getName());


        return rootView;


    }


    public static void setDetail(Detail detail) {
       DetailFragment.detail = detail;
    }
}
