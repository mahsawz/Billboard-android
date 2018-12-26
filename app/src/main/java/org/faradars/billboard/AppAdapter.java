package org.faradars.billboard;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.MyViewHolder> {

    private List<App> applist;
    private AppAdapter.OnItemClickListener onItemClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, count, credit;
        public ImageView icon;
        public Button downloadBtn;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            count = view.findViewById(R.id.count);
            credit = view.findViewById(R.id.credit);
            icon = view.findViewById(R.id.imageview);
            downloadBtn = view.findViewById(R.id.downloadbtn);
        }
    }

    public AppAdapter(List<App> applist,AppAdapter.OnItemClickListener onItemClickListener) {
        this.applist = applist;
        this.onItemClickListener=onItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout_app, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final App app = applist.get(position);
        holder.name.setText("نام: " + app.getName());
        holder.count.setText("تعداد دانلود: " + String.valueOf(app.getCount()));
        holder.credit.setText("اعتبار: " + String.valueOf(app.getCredit()));
        Picasso.get().load(app.getIcon()).into(holder.icon);
        //Picasso.get().load(app.getIcon()).error(R.drawable.notfound).into(holder.icon);
        holder.downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onItemClickListener.onItemClicked(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return applist.size();
    }

    public interface OnItemClickListener {
        void onItemClicked(int position);
    }
}