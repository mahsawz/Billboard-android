package org.faradars.billboard;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
<<<<<<< HEAD
import android.widget.Button;
=======
import android.widget.AdapterView;
>>>>>>> 75a632d1f5f8e51bb4ddded9efca4afcd87c78e9
import android.widget.TextView;

import java.util.List;

public class GiftAdapter extends RecyclerView.Adapter<GiftAdapter.MyViewHolder> {

    private List<Gift> giftList;
<<<<<<< HEAD

    private GiftAdapter.OnItemClickListener onItemClickListener;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, description, cost;
        public Button buy;
=======
    private AdapterView.OnItemClickListener onItemClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, description, cost;
        //public Button download;
>>>>>>> 75a632d1f5f8e51bb4ddded9efca4afcd87c78e9

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
<<<<<<< HEAD
            description = view.findViewById(R.id.description);
            cost = view.findViewById(R.id.cost);
            buy = view.findViewById(R.id.receive);
=======
            description =  view.findViewById(R.id.description);
            cost = view.findViewById(R.id.cost);
>>>>>>> 75a632d1f5f8e51bb4ddded9efca4afcd87c78e9

        }
    }


<<<<<<< HEAD
    public GiftAdapter(List<Gift> giftList,OnItemClickListener onItemClickListener) {
        this.giftList = giftList;
        this.onItemClickListener = onItemClickListener;
=======
    public GiftAdapter(List<Gift> giftList) {
        this.giftList = giftList;
>>>>>>> 75a632d1f5f8e51bb4ddded9efca4afcd87c78e9
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout_gift, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
<<<<<<< HEAD
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        Gift gift = giftList.get(position);
        holder.name.setText(gift.getName());
        holder.cost.setText(String.valueOf(gift.getCost()));
        holder.description.setText(gift.getDescription());
        String giftCode = gift.getCode();
        holder.buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClicked(position);
            }
        });
=======
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Gift gift = giftList.get(position);
        holder.name.setText(gift.getName());
        holder.cost.setText(gift.getCost());
        holder.description.setText(gift.getDescription());
        //holder.download.setText(app.getDownload_link());
>>>>>>> 75a632d1f5f8e51bb4ddded9efca4afcd87c78e9
        //holder.download.setOnClickListener(onItemClickListener.onItemClick());
    }

    @Override
    public int getItemCount() {
        return giftList.size();
    }
<<<<<<< HEAD

    public interface OnItemClickListener {
        void onItemClicked(int position);
    }
=======
>>>>>>> 75a632d1f5f8e51bb4ddded9efca4afcd87c78e9
}