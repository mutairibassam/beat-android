package com.datum.android.todoapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.datum.android.todoapp.data.TodoTable;
import com.datum.android.todoapp.databinding.FragmentListBinding;

public class ListFragment extends Fragment {

    private FragmentListBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentListBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        MainActivityViewModel viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        binding.add.setOnClickListener(View -> {

            TodoTable todoTable = new TodoTable("Bassam");
            viewModel.insert(todoTable);

            // to navigate
            Navigation.findNavController(view).navigate(R.id.navigateToEditFragment);

        });

        return view;


    }
}