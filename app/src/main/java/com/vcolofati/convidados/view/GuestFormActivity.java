package com.vcolofati.convidados.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.vcolofati.convidados.R;
import com.vcolofati.convidados.constants.Constants;
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
        this.mViewHolder.radioGroup = findViewById(R.id.radio_group_form);
        this.mViewHolder.btnSave = findViewById(R.id.form_btn_save);

        this.setListeners();
        this.setObservers();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int id = bundle.getInt(Constants.GUESTID);
            this.mViewModel.getGuest(id);
        }
    }

    private void setObservers() {
        this.mViewModel.guest.observe(this, guest -> {
            mViewHolder.editName.setText(guest.getName());
            ((RadioButton) mViewHolder.radioGroup.getChildAt(guest.getConfirmation().ordinal())).setChecked(true);
        });
    }

    private void setListeners() {
        this.mViewHolder.btnSave.setOnClickListener(view -> this.handleSave());
    }

    private void handleSave() {
        String name = this.mViewHolder.editName.getText().toString();
        RadioButton rb = findViewById(this.mViewHolder.radioGroup.getCheckedRadioButtonId());
        //trabalhando para conseguir obter o enum de acordo com a localização
        GuestFormEnum confirmation = GuestFormEnum.get("Not Confirmed");
        this.mViewModel.save(new Guest(name, confirmation));
    }

    private static class ViewHolder {
        EditText editName;
        RadioGroup radioGroup;
        Button btnSave;
    }
}