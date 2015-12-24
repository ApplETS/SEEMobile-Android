package ca.etsmtl.applets.seemobile.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ca.etsmtl.applets.seemobile.R;
import ca.etsmtl.applets.seemobile.model.Postulation;

/**
 * Created by gnut3ll4 on 21/12/15.
 */
public class PostulationAdapter extends ArrayAdapter<Postulation> {


    private final Context context;

    public PostulationAdapter(Context context, int resource, List<Postulation> objects) {
        super(context, resource, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.row_postulation, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        Postulation item = getItem(position);

        holder.tvStatus.setText(item.getStatut());
        holder.tvJobTitle.setText(item.getNomPoste());
        holder.tvCompanyName.setText(item.getNomEmployeur());

        return view;
    }

    static class ViewHolder {

        @Bind(R.id.tv_company_name)
        TextView tvCompanyName;
        @Bind(R.id.tv_job_title)
        TextView tvJobTitle;
        @Bind(R.id.tv_status)
        TextView tvStatus;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }
}
