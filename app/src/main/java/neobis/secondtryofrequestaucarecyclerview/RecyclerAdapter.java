package neobis.secondtryofrequestaucarecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by admin on 9/12/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
    private String[] list;
    private int selected = -1;

    public RecyclerAdapter() {
        list = new String[]{};
    }


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell, viewGroup, false);
        return new RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder viewHolder, int i) {
        viewHolder.textView.setText(list[i]);
        viewHolder.radioButton.setChecked(i == selected);

    }


    @Override
    public int getItemCount() {
        return list.length;
    }

    public String getSelected() {
        if (selected == -1){
            throw new NullPointerException();
        }

        return list[selected];
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        RadioButton radioButton;
        TextView textView;

        public RecyclerViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.text);
            radioButton = (RadioButton) view.findViewById(R.id.radio);
            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selected = getAdapterPosition();
                    notifyDataSetChanged();
                }
            });

        }
    }

    public void setItem(String[] list) {
        this.list = list;
        selected = -1;
        notifyDataSetChanged();
    }

    public void setSelected(String selected){
        for(int i = 0; i < list.length;++i){
            if(list[i].equals(selected)){
                this.selected = i;
                notifyDataSetChanged();
            }
        }
    }

}
