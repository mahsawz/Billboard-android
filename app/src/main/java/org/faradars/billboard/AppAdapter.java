package org.faradars.billboard;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.MyViewHolder> {

    private List<App> applist;
    private AdapterView.OnItemClickListener onItemClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, count, credit, icon;
        public Button downloadbtn;


        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            count = view.findViewById(R.id.count);
            credit = view.findViewById(R.id.credit);
            icon = view.findViewById(R.id.icon);
            downloadbtn = view.findViewById(R.id.downloadbtn);
        }
    }

    public AppAdapter(List<App> applist) {
        this.applist = applist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout_app, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        App app = applist.get(position);

        holder.name.setText(app.getName());
        holder.count.setText(app.getCredit());
        holder.credit.setText(app.getCount());
        holder.icon.setText(app.getIcon());
//        holder.Downloadbtn.setOnClickListener(onItemClickListener.onItemClick();
    }

    @Override
    public int getItemCount() {
        return applist.size();
    }

}
