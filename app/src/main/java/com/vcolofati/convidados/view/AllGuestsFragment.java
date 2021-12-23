package com.vcolofati.convidados.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vcolofati.convidados.R;
import com.vcolofati.convidados.constants.Constants;
import com.vcolofati.convidados.databinding.FragmentAllGuestsBinding;
import com.vcolofati.convidados.view.adapters.AllGuestAdapter;
import com.vcolofati.convidados.view.listeners.OnListClick;
import com.vcolofati.convidados.viewmodel.AllGuestsViewModel;

public class AllGuestsFragment extends Fragment {

    private AllGuestsViewModel mViewModel;
    private final ViewHolder mViewHolder = new ViewHolder();
    private FragmentAllGuestsBinding binding;
    private final AllGuestAdapter mAdapter = new AllGuestAdapter();
    private int mFilter = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mViewModel =
                new ViewModelProvider(this).get(AllGuestsViewModel.class);

        binding = FragmentAllGuestsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        this.mViewHolder.recyclerGuests = root.findViewById(R.id.recycler_list);
        this.mViewHolder.recyclerGuests.setLayoutManager(new LinearLayoutManager(getContext()));
        this.mViewHolder.recyclerGuests.setAdapter(this.mAdapter);

        OnListClick listener = new OnListClick() {
            @Override
            public void onClick(int id) {
                Bundle bundle = new Bundle();
                bundle.putInt(Constants.GUESTID, id);
                Intent intent = new Intent(getContext(), GuestFormActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public void onLongClick(int id) {
                mViewModel.delete(id);
                mViewModel.getList(mFilter);
            }
        };

        this.mAdapter.attachListener(listener);

        if (getArguments() != null) {
            this.mFilter = getArguments().getInt(Constants.FILTER);
        }

        this.observers();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.mViewModel.getList(mFilter);
    }

    private void observers() {
        this.mViewModel.ResourceGuestListData.observe(getViewLifecycleOwner(), mAdapter::attachList);
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