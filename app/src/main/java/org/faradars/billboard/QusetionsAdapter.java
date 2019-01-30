package org.faradars.billboard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

public class QusetionsAdapter extends RecyclerView.Adapter<QusetionsAdapter.MyViewHolder> {

    private List<Question> questionList;
    private QusetionsAdapter.OnItemClickListener onItemClickListener;
    private Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView questionContext;
        public RadioGroup options;

        public MyViewHolder(View view) {
            super(view);
            questionContext = view.findViewById(R.id.question_context);
            options = view.findViewById(R.id.options);

        }
    }

    public QusetionsAdapter(List<Question> questionList, QusetionsAdapter.OnItemClickListener onItemClickListener) {
        this.questionList = questionList;
        context= (Context) onItemClickListener;
        this.onItemClickListener=onItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout_question, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Question question = questionList.get(position);
        holder.questionContext.setText( question.getContext());
        int index=question.getItems().size();
        Log.d("billboard", "index:" +index);
        for(int i=0;i<index;i++){
            final RadioButton btn = new RadioButton(context);
            btn.setText(question.getItems().get(i).getContext());
            btn.setId(question.getItems().get(i).getId());
            btn.setChecked(false);
            final int finalI = btn.getId();
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClicked(position, finalI);
                }
            });
            holder.options.addView(btn);
        }


    }

    @Override
    public int getItemCount() {
        return questionList.size();

    }


    public interface OnItemClickListener {
        void onItemClicked(int position,int i);
    }
}