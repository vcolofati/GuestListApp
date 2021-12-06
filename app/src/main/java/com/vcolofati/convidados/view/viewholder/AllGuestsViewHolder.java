package com.vcolofati.convidados.view.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vcolofati.convidados.R;
import com.vcolofati.convidados.models.Guest;
import com.vcolofati.convidados.view.listeners.OnListClick;

public class AllGuestsViewHolder extends RecyclerView.ViewHolder {

    private TextView mTextName;

    public AllGuestsViewHolder(@NonNull View itemView) {
        super(itemView);
        this.mTextName = itemView.findViewById(R.id.text_name);
    }

    public void bind(Guest guest, OnListClick listener) {
        this.mTextName.setText(guest.getName());

        this.mTextName.setOnClickListener(view -> listener.onClick(guest.getId()));
    }
}
