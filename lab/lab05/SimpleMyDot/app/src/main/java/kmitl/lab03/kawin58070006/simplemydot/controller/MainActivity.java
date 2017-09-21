package kmitl.lab03.kawin58070006.simplemydot.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import kmitl.lab03.kawin58070006.simplemydot.EditDotFragment;
import kmitl.lab03.kawin58070006.simplemydot.MainFragment;
import kmitl.lab03.kawin58070006.simplemydot.R;
import kmitl.lab03.kawin58070006.simplemydot.model.Dot;
import kmitl.lab03.kawin58070006.simplemydot.model.Dots;
import kmitl.lab03.kawin58070006.simplemydot.view.DotView;

import static kmitl.lab03.kawin58070006.simplemydot.R.layout;

public class MainActivity extends AppCompatActivity implements MainFragment.DotFragmentListener {

    private Dots dots;
    private DotView dotView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        if (savedInstanceState == null) {
            initialFragment();
        }


    }
    private void initialFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, MainFragment.newInstance())
                .commit();
    }

    private void viewFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.editd_fragment, fragment)
                .addToBackStack(null)
                .commit();
    }
    @Override
    public void DotLongPressSelected(Dot dot, Dots dots, int dotPosition) {
        viewFragment(EditDotFragment.newInstance(dot, dots, dotPosition));
    }
}