package org.billboard;

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
        public TextView name, count, credit;
        public Button downloadbtn;

        @BindView(R.id.name)
        TextView Name;
        @BindView(R.id.count)
        TextView Count;
        @BindView(R.id.credit)
        TextView Credit;
        @BindView(R.id.downloadbtn)
        Button Downloadbtn;


        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
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

        holder.Name.setText(app.getName());
        holder.Count.setText(app.getCredit());
        holder.Credit.setText(app.getCount());
//        holder.Downloadbtn.setOnClickListener(onItemClickListener.onItemClick();
    }

    @Override
    public int getItemCount() {
        return applist.size();
    }

}
