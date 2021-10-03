package com.example.procurementconstructionindustry.ui.home;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.procurementconstructionindustry.R;
import com.example.procurementconstructionindustry.database.DatabaseHelper;
import com.example.procurementconstructionindustry.database.DatabaseTable;
import com.example.procurementconstructionindustry.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    DatabaseHelper mydb;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mydb = new DatabaseHelper(this.getContext());


        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Spinner s = view.findViewById(R.id.suppler_drop_down);

        ArrayList<String> arrayList = new ArrayList<>();

        String cols [] = {"*"};
        String where = DatabaseTable.User.USER_LEVEL + " = ? ";
        String whereArgs [] = {"4"};

        Cursor supplerList = mydb.view(
                DatabaseTable.User.TABLE_NAME,
                cols,
                where,
                whereArgs,
                null
        );

        while(supplerList.moveToNext()){
            arrayList.add(supplerList.getString(supplerList.getColumnIndexOrThrow(DatabaseTable.User.USER_NAME)));
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, arrayList);
        s.setAdapter(arrayAdapter);
    }
}