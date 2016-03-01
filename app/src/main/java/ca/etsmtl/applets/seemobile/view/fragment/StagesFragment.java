package ca.etsmtl.applets.seemobile.view.fragment;

import android.accounts.AccountManager;
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

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import ca.etsmtl.applets.seemobile.R;
import ca.etsmtl.applets.seemobile.model.Poste;
import ca.etsmtl.applets.seemobile.presenter.StagesPresenter;
import ca.etsmtl.applets.seemobile.service.DatabaseHelper;
import ca.etsmtl.applets.seemobile.service.SEEService;
import ca.etsmtl.applets.seemobile.utils.AuthenticationInterceptor;
import ca.etsmtl.applets.seemobile.utils.Synchronizer;
import ca.etsmtl.applets.seemobile.view.StagesView;
import ca.etsmtl.applets.seemobile.view.adapter.PosteAdapter;

/**
 * Created by gnut3ll4 on 22/12/15.
 */
public class StagesFragment extends Fragment implements StagesView, AdapterView.OnItemClickListener {

    private Dao<Poste, ?> posteDao;
    private Synchronizer<Poste> posteSynchronizer;

    @Bind(R.id.listview_stages)
    ListView listViewPostes;

    @Bind(R.id.progressbar_stages)
    ProgressBar progressBar;

    StagesPresenter presenter;

    private PosteAdapter posteAdapter;
    private List<Poste> postes = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_stages, container, false);

        ButterKnife.bind(this, view);
        listViewPostes = (ListView) view.findViewById(R.id.listview_stages);

        posteAdapter = new PosteAdapter(view.getContext(), R.layout.row_poste, postes);

        listViewPostes.setAdapter(posteAdapter);





//        Injector.INSTANCE.getServiceComponent().inject(this);

        presenter = new StagesPresenter(this);

        listViewPostes.setOnItemClickListener(this);


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
    public void setItems(List<Poste> postes) {
        posteAdapter.clear();
        posteAdapter.addAll(postes);
//        adapter = new PostulationAdapter(getActivity(), R.layout.row_postulation, postulations);
//        listView.setAdapter(adapter);
        posteAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String message) {
//        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).setAction("Action", null).show();
    }
}
