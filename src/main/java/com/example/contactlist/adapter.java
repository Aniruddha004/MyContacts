package com.example.contactlist;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class adapter extends RecyclerView.Adapter<adapter.Viewholder> {

    private List<model_class> model_classList;
    private OnItemClickListener listener;
    public adapter(List<model_class> model_classList)
    {
        this.model_classList = model_classList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_layout,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        int resource =model_classList.get(position).getImage();
        String name=model_classList.get(position).getName();
        holder.setData(resource,name);
    }

    @Override
    public int getItemCount() {
        return model_classList.size();
    }

    class Viewholder extends RecyclerView.ViewHolder{
        private ImageView image;
        private TextView name;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
            name=itemView.findViewById(R.id.name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(position!=RecyclerView.NO_POSITION && listener!=null){
                        listener.onItemClick(position);
                    }
                }
            });
        }
        private void setData(int resource,String nameText){
            image.setImageResource(resource);
            name.setText(nameText);
        }
    }
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }

}
