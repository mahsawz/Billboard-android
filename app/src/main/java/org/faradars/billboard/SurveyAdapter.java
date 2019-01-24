package org.billboard;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class SurveyAdapter extends RecyclerView.Adapter<SurveyAdapter.MyViewHolder> {

    private List<Survey> surveyList;
    private SurveyAdapter.OnItemClickListener onItemClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description;
        public CardView card;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            description = view.findViewById(R.id.description);
            card = view.findViewById(R.id.card);
        }
    }

    public SurveyAdapter(List<Survey> surveyList, ShowSurveyActivity onItemClickListener) {
        this.surveyList = surveyList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout_survey, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Survey survey = surveyList.get(position);
        holder.title.setText(survey.getTitle());
        holder.description.setText("موضوع: " + (survey.getDescription()));
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClicked(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return surveyList.size();
    }

    public interface OnItemClickListener {
        void onItemClicked(int position);
    }
}