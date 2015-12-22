package ca.etsmtl.applets.seemobile.postulations;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.List;

import ca.etsmtl.applets.seemobile.R;
import ca.etsmtl.applets.seemobile.model.Postulation;

/**
 * A placeholder fragment containing a simple view.
 */
public class PostulationFragment extends Fragment implements PostulationView, AdapterView.OnItemClickListener {

    private ListView listView;
    private ProgressBar progressBar;
    private PostulationPresenter presenter;
    private PostulationAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_postulation, container, false);

        listView = (ListView) view.findViewById(R.id.listview_postulations);
        progressBar = (ProgressBar) view.findViewById(R.id.progressbar);
        presenter = new PostulationPresenter(this);

        listView.setOnItemClickListener(this);


        return view;


    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        presenter.onItemClicked(position);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        listView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
        listView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setItems(List<Postulation> postulations) {
        adapter = new PostulationAdapter(getActivity(), R.layout.row_postulation, postulations);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).setAction("Action", null).show();
        ;
    }
}
