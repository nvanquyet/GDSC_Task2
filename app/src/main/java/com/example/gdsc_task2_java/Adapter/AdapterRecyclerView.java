package com.example.gdsc_task2_java.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gdsc_task2_java.Model.ModelApp;
import com.example.gdsc_task2_java.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<ModelApp> listApp;

    public AdapterRecyclerView(Context mContext) {
        this.mContext = mContext;
    }

    public void SetData(ArrayList<ModelApp> listApp){
        this.listApp = listApp;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelApp modelApp = listApp.get(position);
        if(modelApp == null){
            return;
        }
        holder.imageView.setImageDrawable(modelApp.icApp);
        holder.text.setText(modelApp.nameApp);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launchIntent = mContext.getPackageManager().getLaunchIntentForPackage(modelApp.packageName);
                if (launchIntent != null) {
                    mContext.startActivity(launchIntent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(listApp != null){
            return listApp.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private CircleImageView imageView;
        private TextView text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iconApp);
            text = itemView.findViewById(R.id.nameApp);
        }
    }

}
