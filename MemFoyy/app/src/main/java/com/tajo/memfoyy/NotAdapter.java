package com.tajo.memfoyy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NotAdapter extends RecyclerView.Adapter<NotAdapter.MyViewHolder> {

    private Context mCtx;
    private List<Not> notList;
    FirebaseDatabase db;
    String userID;
    NotlarFragment notlarFragment;

    public NotAdapter(Context mCtx, List<Not> notList){
        this.mCtx= mCtx;
        this.notList = notList;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new MyViewHolder(
                LayoutInflater.from(mCtx).inflate(R.layout.not_karti,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {
        final Not not = notList.get(position);
        db = FirebaseDatabase.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        userID = auth.getCurrentUser().getUid();
        notlarFragment = new NotlarFragment();
        final AppActivity appActivity = new AppActivity();

        holder.not_b.setText(not.getNott());
        holder.not_y.setText(not.getGovde());

        holder.n_btn_sil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("notlar").child(userID).child(not.getKey());
                databaseReference.removeValue();
            }
        });
    }

    @Override
    public int getItemCount() {
        return notList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView not_b, not_y;
        Button n_btn_sil;
        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            not_b = itemView.findViewById(R.id.n_head);
            not_y = itemView.findViewById(R.id.n_desc);
            n_btn_sil = itemView.findViewById(R.id.n_not_sil);
        }
    }
}
