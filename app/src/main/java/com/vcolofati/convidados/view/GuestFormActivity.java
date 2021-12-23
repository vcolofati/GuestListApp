package com.vcolofati.convidados.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.vcolofati.convidados.R;
import com.vcolofati.convidados.constants.Constants;
import com.vcolofati.convidados.enums.GuestFormEnum;
import com.vcolofati.convidados.models.Guest;
import com.vcolofati.convidados.viewmodel.GuestFormViewModel;

public class GuestFormActivity extends AppCompatActivity {

    private final ViewHolder mViewHolder = new ViewHolder();
    private GuestFormViewModel mViewModel;
    private int mGuestId = 0;

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
            this.mGuestId = bundle.getInt(Constants.GUESTID);
            this.mViewModel.getGuest(this.mGuestId);
        }
    }

    private void setObservers() {
        this.mViewModel.resourceGuest.observe(this, guestResource -> {
            switch (guestResource.status) {
                case SUCCESS:
                    assert guestResource.data != null;
                    mViewHolder.editName.setText(guestResource.data.getName());
                    ((RadioButton) mViewHolder.radioGroup.getChildAt(guestResource
                            .data
                            .getConfirmation()
                            .ordinal()))
                            .setChecked(true);
                    Toast.makeText(getApplicationContext(), guestResource.message, Toast.LENGTH_SHORT).show();
                    break;
                case ERROR:
                    Toast.makeText(getApplicationContext(), "Error loading guest",
                            Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }

    private void setListeners() {
        this.mViewHolder.btnSave.setOnClickListener(view -> this.handleSave());
    }

    private void handleSave() {
        String name = this.mViewHolder.editName.getText().toString();
        int index = this.mViewHolder.radioGroup
                .indexOfChild(findViewById(this.mViewHolder.radioGroup.getCheckedRadioButtonId()));
        GuestFormEnum confirmation = GuestFormEnum.get(index);

        this.mViewModel.save(new Guest(this.mGuestId, name, confirmation));
        finish();
    }

    private static class ViewHolder {
        EditText editName;
        RadioGroup radioGroup;
        Button btnSave;
    }
}