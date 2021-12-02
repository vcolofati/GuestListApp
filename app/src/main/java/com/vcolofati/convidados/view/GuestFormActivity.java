package com.vcolofati.convidados.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.vcolofati.convidados.R;
import com.vcolofati.convidados.enums.GuestFormEnum;
import com.vcolofati.convidados.models.Guest;
import com.vcolofati.convidados.viewmodel.GuestFormViewModel;

public class GuestFormActivity extends AppCompatActivity {

    private ViewHolder mViewHolder = new ViewHolder();
    private GuestFormViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_form);

        this.mViewModel = new ViewModelProvider(this).get(GuestFormViewModel.class);

        this.mViewHolder.editName = findViewById(R.id.edit_name);
        this.mViewHolder.radioNotConfirmed = findViewById(R.id.form_radio_not_confirmed);
        this.mViewHolder.radioPresent = findViewById(R.id.form_radio_present);
        this.mViewHolder.radioAbsent = findViewById(R.id.form_radio_absent);
        this.mViewHolder.btnSave = findViewById(R.id.form_btn_save);

        this.setListeners();
    }

    private void setListeners() {
        this.mViewHolder.btnSave.setOnClickListener(view -> {
            this.handleSave();
        });
    }

    private void handleSave() {
        String name = this.mViewHolder.editName.getText().toString();
        GuestFormEnum confirmation = GuestFormEnum.NOT_CONFIRMED;
        this.mViewModel.save(new Guest(name, confirmation));
    }

    private static class ViewHolder {
        EditText editName;
        RadioButton radioNotConfirmed, radioPresent, radioAbsent;
        Button btnSave;
    }
}