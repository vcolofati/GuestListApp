package com.vcolofati.convidados.view.viewholder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vcolofati.convidados.R;
import com.vcolofati.convidados.models.Guest;
import com.vcolofati.convidados.view.listeners.OnListClick;

public class AllGuestsViewHolder extends RecyclerView.ViewHolder {

    private final TextView mTextName;
    private final Context mContext;
    private final Resources mResources;

    public AllGuestsViewHolder(@NonNull View itemView) {
        super(itemView);
        this.mTextName = itemView.findViewById(R.id.text_name);
        mContext = itemView.getContext();
        mResources = itemView.getResources();
    }

    public void bind(Guest guest, OnListClick listener) {
        this.mTextName.setText(guest.getName());

        this.mTextName.setOnClickListener(view -> listener.onClick(guest.getId()));

        this.mTextName.setOnLongClickListener(view -> {
            new AlertDialog.Builder(mContext)
                    .setTitle(mResources.getString(R.string.delete_dialog_title))
                    .setMessage(mResources.getString(R.string.delete_dialog_message))
                    .setPositiveButton(mResources.getString(R.string.delete_dialog_positive), (dialogInterface, i)
                            -> listener.onLongClick(guest.getId()))
                    .setNeutralButton(mResources.getString(R.string.delete_dialog_negative), null)
                    .show();
            return false;
        });
    }
}
