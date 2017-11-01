package kmitl.kawin58070006.horyuni;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {
    private EditText inputUsername;
    private EditText inputPassword;
    private EditText inputCmfPassword;
    private EditText inputEmail;

    private String username;
    private String password;
    private String cmfPassword;
    private String email;

    private List<User> list_users = new ArrayList<>();

    private ListView list_data;
    private ProgressBar circular_progress;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;


    public RegisterFragment() {
        // Required empty public constructor
    }

    public static RegisterFragment newInstance() {
        
        Bundle args = new Bundle();
        
        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_register, container, false);
        setEditText(rootView);

        //firebase
        initFireBase();
        addEventFirebaseListener();

        Button submit = rootView.findViewById(R.id.btnSubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }


        });
        
        return rootView;
    }

    private void createUser() {
        User user = new User(UUID.randomUUID().toString(), inputUsername.getText().toString(), inputEmail.getText().toString());
        mDatabaseReference.child("users").child(user.getId()).setValue(user);
        clearEditText();
    }

    private void clearEditText() {
        inputUsername.setText("");
        inputEmail.setText("");
    }

    private void addEventFirebaseListener() {

    }

    private void initFireBase() {
        FirebaseApp.initializeApp(getActivity());
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();
    }

    public void checkInputRegister(){


    }



    public void setEditText(View rootview){
        inputUsername = rootview.findViewById(R.id.inputUsername);
        username = inputUsername.getText().toString();

        inputPassword = rootview.findViewById(R.id.inputPassword);
        password = inputPassword.getText().toString();

        inputCmfPassword = rootview.findViewById(R.id.inputPassword2);
        cmfPassword = inputCmfPassword.getText().toString();

        inputEmail = rootview.findViewById(R.id.inputEmail);
        email = inputEmail.getText().toString();


    }


}
