package com.example.elcot.armitcha;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {


    private Context context;
    private List<Item> data;

    public RecyclerViewAdapter(Context context,List<Item> data)
    {
        this.context=context;
        this.data=data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater=LayoutInflater.from(context);
        view=inflater.inflate(R.layout.cardview_items,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.tv.setText(data.get(position).getTitle());
        holder.img.setImageResource(data.get(position).getThumbnail());

        //set click listener

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context,productsdetails.class);

                //passing data to the item details activity
                intent.putExtra("Title",data.get(position).getTitle());
                intent.putExtra("Description",data.get(position).getDescription());
                intent.putExtra("Thumbnail",data.get(position).getThumbnail());
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv;
        ImageView img;
        CardView cardView;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            tv=(TextView)itemView.findViewById(R.id.item_txt_id);
            img=(ImageView)itemView.findViewById(R.id.item_img_id);
            cardView=(CardView)itemView.findViewById(R.id.cardview_id);
        }
    }

}
