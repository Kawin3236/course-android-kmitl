package kmitl.kawin58070006.horyuni;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {
    private TextView name;
    private ImageView imageView1;
    private ImageView imageView2;
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

        imageView1 = rootView.findViewById(R.id.imageDetail1);
        Glide.with(getActivity()).load(detail.getImg().getUrl()).into(imageView1);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setMessage("this is image");
                builder.create();
                builder.show();
            }
        });

        imageView2 = rootView.findViewById(R.id.imageDetail2);
        Glide.with(getActivity()).load(detail.getImg().getUrl2()).into(imageView2);
        return rootView;


    }


    public static void setDetail(Detail detail) {
       DetailFragment.detail = detail;
    }
}
