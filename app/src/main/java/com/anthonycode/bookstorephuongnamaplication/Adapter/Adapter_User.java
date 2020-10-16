package com.anthonycode.bookstorephuongnamaplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.anthonycode.bookstorephuongnamaplication.Activity.MyAccount;
import com.anthonycode.bookstorephuongnamaplication.DAO.UserDAO;
import com.anthonycode.bookstorephuongnamaplication.Dialog.BottomSheet_Update_User;
import com.anthonycode.bookstorephuongnamaplication.Fragment.Fragment_User;
import com.anthonycode.bookstorephuongnamaplication.Model.User;
import com.anthonycode.bookstorephuongnamaplication.R;

import java.util.ArrayList;

public class Adapter_User extends RecyclerView.Adapter<Adapter_User.MyViewHolder> {

    UserDAO theLoaiDAO;
    private ArrayList<User> ds_tl;
    private Context context;


    public Adapter_User(ArrayList<User> ds_tl, Context context) {
        this.ds_tl = ds_tl;
        this.context = context;
    }

    @Override
    public Adapter_User.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.tvUsername.setText(ds_tl.get(position).getUserName());
        holder.tvFullname.setText(ds_tl.get(position).getHoTen());
        holder.tvPhone.setText(ds_tl.get(position).getPhone());

//        holder.img_edit_kt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {

//            }
//        });


        holder.img_edit_kt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
//                dialog.setTitle("");
                dialog.setItems(R.array.alert, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int pos) {
                        // TODO Auto-generated method stub
                        if (pos == 2) {
                            xoa(position);
                        }
                        if (pos == 1){
                            chinhsua(position);
                        }
                        if (pos == 0){
                            xemchitiet(position);
                        }

                    }

                });
                AlertDialog alert = dialog.create();
                alert.show();
                //
            }
        });

    }

    public void xoa(final int position) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage("Bạn có chắc muốn xóa " + ds_tl.get(position).getUserName());
        builder1.setCancelable(true);
        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        theLoaiDAO = new UserDAO(context);
                        theLoaiDAO.deleteUserByID(ds_tl.get(position).getId());
                        Toast.makeText(context, "Xóa thành công " + ds_tl.get(position).getUserName(), Toast.LENGTH_SHORT).show();
                        ds_tl.remove(position);
                        notifyDataSetChanged();
                        dialog.cancel();
                    }
                }).setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder1.create();
        alert.show();
    }

    public void chinhsua(int id) {
        Bundle args = new Bundle();
        args.putInt("id_user", ds_tl.get(id).getId());
        args.putString("username_user", ds_tl.get(id).getUserName());
        args.putString("password_user", ds_tl.get(id).getPassword());
        args.putString("phone_user", ds_tl.get(id).getPhone());
        args.putString("fullname_user", ds_tl.get(id).getHoTen());
        theLoaiDAO = new UserDAO(context);

        BottomSheet_Update_User bottom_sheet = new BottomSheet_Update_User();
        bottom_sheet.setArguments(args);
        bottom_sheet.show(((AppCompatActivity) context).getSupportFragmentManager(), bottom_sheet.getTag());
    }

    public void xemchitiet(int position){
        Intent intent = new Intent(context, MyAccount.class);
        Bundle args = new Bundle();
        args.putString("username_user", ds_tl.get(position).getUserName());
        args.putString("password_user", ds_tl.get(position).getPassword());
        args.putString("phone_user", ds_tl.get(position).getPhone());
        args.putString("fullname_user", ds_tl.get(position).getHoTen());
        intent.putExtras(args);
        ((Activity) context).startActivityForResult(intent, position);
    }

    @Override
    public int getItemCount() {
        return ds_tl.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvUsername, tvFullname, tvPhone;
        private ImageView img_edit_kt;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tv_username);
            tvFullname = itemView.findViewById(R.id.tv_fullname);
            tvPhone = itemView.findViewById(R.id.tv_phone);
            img_edit_kt = itemView.findViewById(R.id.imgBaChamUser);
        }
    }
}
