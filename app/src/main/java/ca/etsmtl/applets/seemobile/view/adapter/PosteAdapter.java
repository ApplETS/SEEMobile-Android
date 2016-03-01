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
import ca.etsmtl.applets.seemobile.model.Poste;

/**
 * Created by gnut3ll4 on 21/12/15.
 */
public class PosteAdapter extends ArrayAdapter<Poste> {

    private final Context context;

    public PosteAdapter(Context context, int resource, List<Poste> objects) {
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
            view = inflater.inflate(R.layout.row_poste, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        Poste item = getItem(position);

        holder.tvCity.setText(item.getLieu());
        holder.tvJobTitle.setText(item.getNomPoste());
        holder.tvCompanyName.setText(item.getNomEmployeur());

        return view;
    }

    static class ViewHolder {

        @Bind(R.id.tv_company_name)
        TextView tvCompanyName;
        @Bind(R.id.tv_job_title)
        TextView tvJobTitle;
        @Bind(R.id.tv_city)
        TextView tvCity;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }
}
