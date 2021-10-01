package com.example.procurementconstructionindustry;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.procurementconstructionindustry.database.DatabaseHelper;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.procurementconstructionindustry.databinding.ActivityDashboardBinding;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityDashboardBinding binding;

    int counter = 2;
    ArrayList<String> itemTags = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DatabaseHelper mydb = new DatabaseHelper(this);

        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarDashboard.toolbar);
        binding.appBarDashboard.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_place_order, R.id.nav_view_order_status, R.id.nav_conferm_order)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_dashboard);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        itemTags.add("txt_item_1");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_dashboard);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }



    public void addMoreItems(View v){

        float inPixelsTxtH= this.getResources().getDimension(R.dimen.rc_txt_h);
        float inPixelsTxtP= this.getResources().getDimension(R.dimen.rc_txt_p);
        float inPixelsTxtM= this.getResources().getDimension(R.dimen.rc_txt_m);

        LinearLayout items = findViewById(R.id.item_container);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 0, (int)inPixelsTxtM);


        EditText txtItem = new EditText(this);
        txtItem.setHint("Item");
        txtItem.setBackground(getDrawable(R.drawable.rc_input_box_blue));
        txtItem.setHeight((int) inPixelsTxtH);
        txtItem.setPadding((int) inPixelsTxtP,0,(int) inPixelsTxtP,0);
        txtItem.setLayoutParams(lp);
        txtItem.setTag("txt_item_"+counter);
        itemTags.add("txt_item_"+counter);
        items.addView(txtItem);

        counter++;


    }

    public void saveOrder(View v){
        ArrayList<EditText> itemList = new ArrayList<>();
        LinearLayout items = findViewById(R.id.item_container);

        boolean error = true;

        for(String itemTag : itemTags){
            System.out.println(itemTag);
            EditText item = items.findViewWithTag(itemTag);
            itemList.add(item);
        }

//        check txt empty or not
        for(EditText e : itemList){
            if (e.getText().length() == 0){
                e.setError("Please fill this item");
                error = false;
            }
        }

//        save into database

    }
}