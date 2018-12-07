package org.faradars.billboard;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class GiftAdapter extends RecyclerView.Adapter<GiftAdapter.MyViewHolder> {

    private List<Gift> giftList;

    private GiftAdapter.OnItemClickListener onItemClickListener;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, description, cost;
        public Button buy;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            description = view.findViewById(R.id.description);
            cost = view.findViewById(R.id.cost);
            buy = view.findViewById(R.id.receive);

        }
    }


    public GiftAdapter(List<Gift> giftList,OnItemClickListener onItemClickListener) {
        this.giftList = giftList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout_gift, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
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
        //holder.download.setOnClickListener(onItemClickListener.onItemClick());
    }

    @Override
    public int getItemCount() {
        return giftList.size();
    }

    public interface OnItemClickListener {
        void onItemClicked(int position);
    }
}