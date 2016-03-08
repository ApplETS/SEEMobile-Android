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
import ca.etsmtl.applets.seemobile.model.Entrevue;

/**
 * Created by gnut3ll4 on 21/12/15.
 */
public class EntrevuesAdapter extends ArrayAdapter<Entrevue> {

    private final Context context;

    public EntrevuesAdapter(Context context, int resource, List<Entrevue> objects) {
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
            view = inflater.inflate(R.layout.row_entrevue, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        Entrevue item = getItem(position);

        if (item.getDateEntrevue() == null) {
            holder.tvStatut.setText("À confirmer");
        } else {
            holder.tvStatut.setText("Confirmée");
            holder.tvDate.setText(item.getDateEntrevue().toString());
        }

        holder.tvCompanyName.setText(item.getNomEmployeur());

        return view;
    }

    static class ViewHolder {

        @Bind(R.id.tv_company_name)
        TextView tvCompanyName;
        @Bind(R.id.tv_date)
        TextView tvDate;
        @Bind(R.id.tv_statut)
        TextView tvStatut;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }
}
