package com.vcolofati.convidados.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vcolofati.convidados.R;
import com.vcolofati.convidados.constants.Constants;
import com.vcolofati.convidados.databinding.FragmentAllGuestsBinding;
import com.vcolofati.convidados.models.Guest;
import com.vcolofati.convidados.view.adapters.AllGuestAdapter;
import com.vcolofati.convidados.view.listeners.OnListClick;
import com.vcolofati.convidados.viewmodel.AllGuestsViewModel;

import java.util.List;

public class AllGuestsFragment extends Fragment {

    private AllGuestsViewModel mViewModel;
    private final ViewHolder mViewHolder = new ViewHolder();
    private FragmentAllGuestsBinding binding;
    private AllGuestAdapter mAdapter = new AllGuestAdapter();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mViewModel =
                new ViewModelProvider(this).get(AllGuestsViewModel.class);

        binding = FragmentAllGuestsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        this.mViewHolder.recyclerGuests = root.findViewById(R.id.recycler_list);
        this.mViewHolder.recyclerGuests.setLayoutManager(new LinearLayoutManager(getContext()));
        this.mViewHolder.recyclerGuests.setAdapter(this.mAdapter);

        OnListClick listener = id -> {
            Bundle bundle = new Bundle();
            bundle.putInt(Constants.GUESTID, id);
            Intent intent = new Intent(getContext(), GuestFormActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        };

        this.mAdapter.attachListener(listener);

        this.observers();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.mViewModel.getList();
    }

    private void observers() {
        this.mViewModel.guestLiveData.observe(getViewLifecycleOwner(), guests -> mAdapter.attachList(guests));
    }

    private static class ViewHolder {
        RecyclerView recyclerGuests;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}