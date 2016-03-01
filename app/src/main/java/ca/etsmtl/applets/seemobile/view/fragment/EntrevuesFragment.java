package ca.etsmtl.applets.seemobile.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.j256.ormlite.dao.Dao;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ca.etsmtl.applets.seemobile.Injector;
import ca.etsmtl.applets.seemobile.R;
import ca.etsmtl.applets.seemobile.model.Entrevue;
import ca.etsmtl.applets.seemobile.model.Poste;
import ca.etsmtl.applets.seemobile.presenter.EntrevuesPresenter;
import ca.etsmtl.applets.seemobile.presenter.StagesPresenter;
import ca.etsmtl.applets.seemobile.utils.Synchronizer;
import ca.etsmtl.applets.seemobile.view.EntrevuesView;
import ca.etsmtl.applets.seemobile.view.StagesView;
import ca.etsmtl.applets.seemobile.view.adapter.EntrevuesAdapter;
import ca.etsmtl.applets.seemobile.view.adapter.PosteAdapter;

/**
 * Created by gnut3ll4 on 22/12/15.
 */
public class EntrevuesFragment extends Fragment implements EntrevuesView, AdapterView.OnItemClickListener {

//    private Dao<Poste, ?> posteDao;
//    private Synchronizer<Poste> posteSynchronizer;
    @Bind(R.id.listview_entrevues)
    ListView listViewEntrevues;

    @Bind(R.id.progressbar)
    ProgressBar progressBar;

    EntrevuesPresenter presenter;

    private EntrevuesAdapter entrevuesAdapter;
    private List<Entrevue> entrevues = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_entrevues, container, false);

        ButterKnife.bind(this, view);

        entrevuesAdapter = new EntrevuesAdapter(view.getContext(), R.layout.row_entrevue, entrevues);

        listViewEntrevues.setAdapter(entrevuesAdapter);

        presenter = new EntrevuesPresenter(this);

        listViewEntrevues.setOnItemClickListener(this);


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
//        listView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
//        listView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setItems(List<Entrevue> entrevues) {
        entrevuesAdapter.clear();
        entrevuesAdapter.addAll(entrevues);
//        adapter = new PostulationAdapter(getActivity(), R.layout.row_postulation, postulations);
//        listView.setAdapter(adapter);
        entrevuesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String message) {

    }
}
