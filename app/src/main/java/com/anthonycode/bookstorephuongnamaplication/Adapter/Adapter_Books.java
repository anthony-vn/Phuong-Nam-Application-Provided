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

import com.anthonycode.bookstorephuongnamaplication.DAO.SachDAO;
import com.anthonycode.bookstorephuongnamaplication.DAO.TheLoaiDAO;
import com.anthonycode.bookstorephuongnamaplication.Dialog.BottomSheet_Update_Book;
import com.anthonycode.bookstorephuongnamaplication.Dialog.BottomSheet_Update_TheLoai;
import com.anthonycode.bookstorephuongnamaplication.Model.Sach;
import com.anthonycode.bookstorephuongnamaplication.Model.TheLoai;
import com.anthonycode.bookstorephuongnamaplication.Model.User;
import com.anthonycode.bookstorephuongnamaplication.R;

import java.util.ArrayList;

public class Adapter_Books extends RecyclerView.Adapter<Adapter_Books.MyViewHolder> {

    private ArrayList<Sach> ds_tl;
    private Context context;
    SachDAO sachDAO;

    public Adapter_Books(ArrayList<Sach> ds_tl, Context context) {
        this.ds_tl = ds_tl;
        this.context = context;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTenSach, tvGiaSach, tvSoLuong;
        private ImageView img_edit_kt, img_books;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvTenSach = itemView.findViewById(R.id.tv_tensach);
            img_books = itemView.findViewById(R.id.img_books);
            tvGiaSach = itemView.findViewById(R.id.tv_giabia);
            tvSoLuong = itemView.findViewById(R.id.tv_soluong);
            img_edit_kt = itemView.findViewById(R.id.img_edit_books);
        }
    }

    @Override
    public Adapter_Books.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_books, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        Sach user = ds_tl.get(position);
        if (position % 3 == 0) {
            holder.img_books.setImageResource(R.drawable.ic_book_kt);
        }else if (position % 3 == 1){
            holder.img_books.setImageResource(R.drawable.ic_book_it);
        }else if (position % 3 == 2){
            holder.img_books.setImageResource(R.drawable.ic_bookshelf_blue);
        }else {
            holder.img_books.setImageResource(R.drawable.ic_history_book);
        }
        holder.tvTenSach.setText("Tên sách : " + ds_tl.get(position).getTenSach());
        holder.tvGiaSach.setText("Giá bìa : " + String.valueOf(ds_tl.get(position).getGiaBia()));
        holder.tvSoLuong.setText("Số lượng : " + String.valueOf(ds_tl.get(position).getSoLuong()));

        holder.img_edit_kt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putInt("id_sach", ds_tl.get(position).getId());
                args.putString("ma_sach", ds_tl.get(position).getMaSach());
                args.putString("matheloai_sach", ds_tl.get(position).getMaTheLoai());
                args.putString("ten_sach", ds_tl.get(position).getTenSach());
                args.putString("tacgia_sach", ds_tl.get(position).getTacGia());
                args.putString("nxb_sach", ds_tl.get(position).getNXB());
                args.putDouble("giabia_sach", ds_tl.get(position).getGiaBia());
                args.putInt("soluong_sach", ds_tl.get(position).getSoLuong());
                sachDAO = new SachDAO(context);

                BottomSheet_Update_Book bottom_sheet = new BottomSheet_Update_Book();
                bottom_sheet.setArguments(args);
                bottom_sheet.show(((AppCompatActivity) context).getSupportFragmentManager(),bottom_sheet.getTag());
            }
        });


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Bạn có chắc muốn xóa "+ ds_tl.get(position).getTenSach() + " ?");
                builder1.setCancelable(true);
                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                sachDAO = new SachDAO(context);
                                sachDAO.deleteSachByID(ds_tl.get(position).getId());
                                Toast.makeText(context, "Xóa thành công "+ ds_tl.get(position).getTenSach(), Toast.LENGTH_SHORT).show();
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
