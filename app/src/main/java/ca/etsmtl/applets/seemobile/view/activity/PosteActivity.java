package ca.etsmtl.applets.seemobile.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.List;

import ca.etsmtl.applets.seemobile.R;
import ca.etsmtl.applets.seemobile.model.Postulation;
import ca.etsmtl.applets.seemobile.view.PosteView;

/**
 * Created by gnut3ll4 on 31/12/15.
 */
public class PosteActivity extends AppCompatActivity implements PosteView {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poste);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setItems(List<Postulation> postulations) {

    }
}
