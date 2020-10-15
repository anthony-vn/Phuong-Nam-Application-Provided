package com.anthonycode.bookstorephuongnamaplication.Adapter;

import android.content.Context;
import android.content.DialogInterface;
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

import com.anthonycode.bookstorephuongnamaplication.DAO.TheLoaiDAO;
import com.anthonycode.bookstorephuongnamaplication.Dialog.BottomSheet_Update_TheLoai;
import com.anthonycode.bookstorephuongnamaplication.Fragment.Fragment_Category;
import com.anthonycode.bookstorephuongnamaplication.Model.TheLoai;
import com.anthonycode.bookstorephuongnamaplication.R;

import java.util.ArrayList;

public class Adapter_Category extends RecyclerView.Adapter<Adapter_Category.MyViewHolder> {

    private ArrayList<TheLoai> ds_tl;
    private Context context;
    TheLoaiDAO theLoaiDAO;

    public Adapter_Category(ArrayList<TheLoai> ds_tl, Context context) {
        this.ds_tl = ds_tl;
        this.context = context;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_matheloai, tv_tentheloai, tv_mota;
        private ImageView img_edit_kt;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv_matheloai = itemView.findViewById(R.id.tv_matheloai);
            tv_tentheloai = itemView.findViewById(R.id.tv_tentheloai);
            tv_mota = itemView.findViewById(R.id.tv_motatheloai);
            img_edit_kt = itemView.findViewById(R.id.img_edit_theloai);
        }
    }

    @Override
    public Adapter_Category.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_theloai, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.tv_matheloai.setText(ds_tl.get(position).getMaTheLoai());
        holder.tv_tentheloai.setText(ds_tl.get(position).getTenTheLoai());
        holder.tv_mota.setText(ds_tl.get(position).getMoTa());

        holder.img_edit_kt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putInt("id_TL", ds_tl.get(position).getId());
                args.putString("ma_TL", ds_tl.get(position).getMaTheLoai());
                args.putString("ten_TL", ds_tl.get(position).getTenTheLoai());
                args.putString("mota_TL", ds_tl.get(position).getMoTa());
                theLoaiDAO = new TheLoaiDAO(context);

                BottomSheet_Update_TheLoai bottom_sheet = new BottomSheet_Update_TheLoai();
                bottom_sheet.setArguments(args);
                bottom_sheet.show(((AppCompatActivity) context).getSupportFragmentManager(),bottom_sheet.getTag());
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Bạn có chắc muốn xóa "+ ds_tl.get(position).getTenTheLoai());
                builder1.setCancelable(true);
                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                theLoaiDAO = new TheLoaiDAO(context);
                                theLoaiDAO.deleteTheLoaiByID(ds_tl.get(position).getId());
                                Toast.makeText(context, "Xóa thành công "+ ds_tl.get(position).getTenTheLoai(), Toast.LENGTH_SHORT).show();
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
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return ds_tl.size();
    }
}
