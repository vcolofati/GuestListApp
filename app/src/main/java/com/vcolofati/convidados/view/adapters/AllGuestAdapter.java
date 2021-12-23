package com.vcolofati.convidados.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vcolofati.convidados.R;
import com.vcolofati.convidados.errorHandling.Resource;
import com.vcolofati.convidados.models.Guest;
import com.vcolofati.convidados.view.listeners.OnListClick;
import com.vcolofati.convidados.view.viewholder.AllGuestsViewHolder;

import java.util.ArrayList;
import java.util.List;

public class AllGuestAdapter extends RecyclerView.Adapter<AllGuestsViewHolder> {

    private List<Guest> mList = new ArrayList<>();
    private OnListClick mListener;

    @NonNull
    @Override
    public AllGuestsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_guest_row, parent, false);
        return new AllGuestsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllGuestsViewHolder holder, int position) {
        holder.bind(this.mList.get(position), this.mListener);
    }

    @Override
    public int getItemCount() {
        return this.mList.size();
    }

    public void attachList(Resource<List<Guest>> resource) {
        this.mList = resource.data;
        notifyDataSetChanged();
    }

    public void attachListener(OnListClick listener) {
        this.mListener = listener;
    }
}
