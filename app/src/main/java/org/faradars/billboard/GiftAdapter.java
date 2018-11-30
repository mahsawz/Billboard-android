package org.faradars.billboard;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.List;

public class GiftAdapter extends RecyclerView.Adapter<GiftAdapter.MyViewHolder> {

    private List<Gift> giftList;
    private AdapterView.OnItemClickListener onItemClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, description, cost;
        //public Button download;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            description =  view.findViewById(R.id.description);
            cost = view.findViewById(R.id.cost);

        }
    }


    public GiftAdapter(List<Gift> giftList) {
        this.giftList = giftList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gift_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Gift gift = giftList.get(position);
        holder.name.setText(gift.getName());
        holder.cost.setText(gift.getCost());
        holder.description.setText(gift.getDescription());
        //holder.download.setText(app.getDownload_link());
        //holder.download.setOnClickListener(onItemClickListener.onItemClick());
    }

    @Override
    public int getItemCount() {
        return giftList.size();
    }
}