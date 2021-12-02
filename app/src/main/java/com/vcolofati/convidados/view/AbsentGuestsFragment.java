package com.vcolofati.convidados.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.vcolofati.convidados.databinding.FragmentAbsentGuestsBinding;
import com.vcolofati.convidados.viewmodel.AbsentGuestsViewModel;

public class AbsentGuestsFragment extends Fragment {

    private AbsentGuestsViewModel absentGuestsViewModel;
    private FragmentAbsentGuestsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        absentGuestsViewModel =
                new ViewModelProvider(this).get(AbsentGuestsViewModel.class);

        binding = FragmentAbsentGuestsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSlideshow;
        absentGuestsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}