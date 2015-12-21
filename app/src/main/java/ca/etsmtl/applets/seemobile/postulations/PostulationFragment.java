package ca.etsmtl.applets.seemobile.postulations;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ca.etsmtl.applets.seemobile.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PostulationFragment extends Fragment {

    public PostulationFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
}
