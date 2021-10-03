package com.example.procurementconstructionindustry;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.procurementconstructionindustry.database.DatabaseHelper;
import com.example.procurementconstructionindustry.database.DatabaseTable;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.procurementconstructionindustry.databinding.ActivityDashboardBinding;

import java.util.ArrayList;
import java.util.logging.Level;


public class Dashboard extends AppCompatActivity {

    DatabaseHelper mydb;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityDashboardBinding binding;

    int counter = 2;
    ArrayList<String> itemTags = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mydb = new DatabaseHelper(this);
        super.onCreate(savedInstanceState);

//        DatabaseHelper mydb = new DatabaseHelper(this);

        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarDashboard.toolbar);
//        binding.appBarDashboard.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
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

//        showSupplers();
//        loadOrderData();

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

//    public void showSupplers(){
//
//        Spinner s = findViewById(R.id.suppler_drop_down);
//
//        ArrayList<String> arrayList = new ArrayList<>();
//
//        String cols [] = {"*"};
//        String where = DatabaseTable.User.USER_LEVEL + " = ? ";
//        String whereArgs [] = {"4"};
//
//        Cursor supplerList = mydb.view(
//                DatabaseTable.User.TABLE_NAME,
//                cols,
//                where,
//                whereArgs,
//                null
//        );
//
//        while(supplerList.moveToNext()){
//            arrayList.add(supplerList.getString(supplerList.getColumnIndexOrThrow(DatabaseTable.User.USER_NAME)));
//        }
//
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
//        s.setAdapter(arrayAdapter);
//    }



    public void addMoreItems(View v){

        float inPixelsTxtH= this.getResources().getDimension(R.dimen.rc_txt_h);
        float inPixelsTxtP= this.getResources().getDimension(R.dimen.rc_txt_p);
        float inPixelsTxtM= this.getResources().getDimension(R.dimen.rc_txt_m);

        LinearLayout items = findViewById(R.id.item_container);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 0, (int)inPixelsTxtM);

        LinearLayout container = new LinearLayout(this);
        container.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        p.weight = 1;

        EditText txtItem = new EditText(this);
        txtItem.setHint("Item");
        txtItem.setBackground(getDrawable(R.drawable.rc_input_box_blue));
        txtItem.setHeight((int) inPixelsTxtH);
        txtItem.setPadding((int) inPixelsTxtP,0,(int) inPixelsTxtP,0);
        txtItem.setLayoutParams(p);
        txtItem.setTag("txt_item_"+counter);
        itemTags.add("txt_item_"+counter);

        EditText qty = new EditText(this);
        qty.setHint("Quantity");
        qty.setBackground(getDrawable(R.drawable.rc_input_box_blue));
        qty.setHeight((int) inPixelsTxtH);
        qty.setPadding((int) inPixelsTxtP,0,(int) inPixelsTxtP,0);
        qty.setLayoutParams(p);

        container.addView(txtItem);
        container.addView(qty);

        items.addView(container);

        counter++;


    }

    public void saveOrder(View v){
        ArrayList<EditText> itemList = new ArrayList<>();
        LinearLayout items = findViewById(R.id.item_container);

        Spinner suppler = findViewById(R.id.suppler_drop_down);

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
        if (error){

            boolean insertError = true;

            String supplerUserName = suppler.getSelectedItem().toString();
            EditText company = findViewById(R.id.txt_company);
            EditText address = findViewById(R.id.txt_address);
            int supId = 0;
            if (company.getText().length() != 0){

                if(address.getText().length() != 0){

                    String cols[] = {"*"};
                    String user_where = DatabaseTable.User.USER_NAME + " = ? ";
                    String whereArgs[] = {supplerUserName};
                    Cursor user = mydb.view(
                            DatabaseTable.User.TABLE_NAME,
                            cols,
                            user_where,
                            whereArgs,
                            null
                    );

                    while (user.moveToNext()){
                        supId = Integer.parseInt(user.getString(user.getColumnIndexOrThrow(DatabaseTable.User.USER_ID)));
                    }

                    ContentValues order = new ContentValues();
                    order.put(DatabaseTable.PurchaseOrder.ORDER_SUPPLER , supId );
                    order.put(DatabaseTable.PurchaseOrder.ORDER_PLACEDBY , Const.user_id);
                    order.put(DatabaseTable.PurchaseOrder.ORDER_COMPANY , company.getText().toString());
                    order.put(DatabaseTable.PurchaseOrder.ORDER_DELIVERYADDRESS , address.getText().toString());
                    order.put(DatabaseTable.PurchaseOrder.ORDER_STATUS , "waiting_for_approval");

                    int orderId = (int)mydb.save(DatabaseTable.PurchaseOrder.TABLE_NAME , order);


                    for(EditText item : itemList)
                    {
                        ContentValues orderItems = new ContentValues();
                        orderItems.put(DatabaseTable.PurchaseOrderItem.ORDER_ID , orderId);
                        orderItems.put(DatabaseTable.PurchaseOrderItem.ITEM_ID , item.getText().toString());
                        try {
                            mydb.save(DatabaseTable.PurchaseOrderItem.TABLE_NAME, orderItems);
                        }catch (Exception e){
                            insertError = false;
                        }
                    }

                    if(insertError){
                        Toast success = new Toast(this);
                        success.setText("Order placed");
                        success.show();

                        company.setText("");
                        address.setText("");

                        for(EditText item : itemList){
                            item.setText("");
                        }

                    }else{
                        Toast errormsg = new Toast(this);
                        errormsg.setText("Some thing went wrong");
                        errormsg.show();
                    }

                }else{
                    address.setError("Address field is required");
                }

            }else{
                company.setError("Company field is required");
            }
        }
    }

    public void showMoreOrder(@NonNull View v){

        float inPixelsTxtH= this.getResources().getDimension(R.dimen.rc_order_view);
        LinearLayout container = (LinearLayout) v.getParent();


        if((int)inPixelsTxtH == container.getHeight()){
            LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            container.setLayoutParams(lparams);
        }else{
            LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, (int)inPixelsTxtH);
            container.setLayoutParams(lparams);
        }


    }

//    public void loadOrderData(){
//
//        float inPixelsTxtH= this.getResources().getDimension(R.dimen.rc_order_view);
//        float inPixelsTxtP= this.getResources().getDimension(R.dimen.rc_txt_p);
//
//        LinearLayout orderParent = findViewById(R.id.view_parent);
//
//        LinearLayout orderLay = new LinearLayout(this);
//
////        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
////                LinearLayout.LayoutParams.MATCH_PARENT, (int)inPixelsTxtH);
////
////        order.setLayoutParams(lparams);
//
////        orderLay.setPadding(0,(int)inPixelsTxtP,0,(int)inPixelsTxtP);
//        Button more = new Button(this);
//        more.setBackground(getDrawable(R.drawable.rc_button_blue));
//
//        orderLay.addView(more);
//
//        System.out.println(orderParent);
//
////        orderParent.addView(orderLay);
//
//    }
}