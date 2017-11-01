package kmitl.kawin58070006.horyuni;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityMainFragment extends Fragment {

    private EditText editUsername;
    private EditText editPassword;
    private Button btnLogin;
    private TextView register;
    private String username;
    private String password;


    public ActivityMainFragment() {

    }

    public static ActivityMainFragment newInstance() {
        Bundle args = new Bundle();
        ActivityMainFragment fragment = new ActivityMainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_activity_main, container, false);

        editUsername = rootView.findViewById(R.id.editUsername);
        editPassword = rootView.findViewById(R.id.editPassword);
        btnLogin = rootView.findViewById(R.id.btnLogin);
        register = rootView.findViewById(R.id.textRegister);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = editUsername.getText().toString();
                password = editPassword.getText().toString();
                System.out.println("username : "+username+"password : "+password);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragmentContainer, HomeFragment.newInstance())
                        .addToBackStack(null)
                        .commit();

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragmentContainer, RegisterFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
            }
        });

        return rootView;
    }





}
