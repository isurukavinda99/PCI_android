package com.example.procurementconstructionindustry.ui.gallery;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.procurementconstructionindustry.Const;
import com.example.procurementconstructionindustry.Dashboard;
import com.example.procurementconstructionindustry.R;
import com.example.procurementconstructionindustry.database.DatabaseHelper;
import com.example.procurementconstructionindustry.database.DatabaseTable;
import com.example.procurementconstructionindustry.databinding.FragmentGalleryBinding;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private FragmentGalleryBinding binding;
    DatabaseHelper mydb;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
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



//        get orders from db
        String cols [] = {"*"};
        String where = DatabaseTable.PurchaseOrder.ORDER_PLACEDBY+" = ?";
        System.out.println(Const.user_id);
        String whereArgs[] = {""+Const.user_id+""};

        Cursor orders = mydb.view(
                DatabaseTable.PurchaseOrder.TABLE_NAME,
                cols,
                where,
                whereArgs,
                null
        );



        float inPixelsTxtH= this.getResources().getDimension(R.dimen.rc_order_view);
        float inPixelsTxtH_button= this.getResources().getDimension(R.dimen.rc_txt_h);
        float inPixelsTxtP= this.getResources().getDimension(R.dimen.rc_txt_p);
        float inPixelsTxtM= this.getResources().getDimension(R.dimen.rc_txt_m);

        LinearLayout orderParent = view.findViewById(R.id.view_parent);


        //adding order containers
        while(orders.moveToNext()) {


            LinearLayout orderLay = new LinearLayout(this.getContext());
            orderLay.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.rc_input_box_blue));

            LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, (int) inPixelsTxtH);
            lparams.setMargins(0, (int) inPixelsTxtM, 0, 0);

            LinearLayout.LayoutParams lparams_button = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, (int) inPixelsTxtH_button);


            orderLay.setLayoutParams(lparams);
            orderLay.setOrientation(LinearLayout.VERTICAL);

            orderLay.setPadding((int) inPixelsTxtP, (int) inPixelsTxtM, (int) inPixelsTxtP, (int) inPixelsTxtM);

            Button more = new Button(this.getContext());
            more.setLayoutParams(lparams_button);
            more.setText("Order - " + (orders.getString(orders.getColumnIndexOrThrow(DatabaseTable.PurchaseOrder.ORDER_ID))));
            more.setTextColor(Color.parseColor("#ffffff"));

            //set button color acording to order status
            if (orders.getString(orders.getColumnIndexOrThrow(DatabaseTable.PurchaseOrder.ORDER_STATUS)).equals( "waiting_for_approval")){
                more.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.rc_button_blue));
            }else{
                more.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.rc_button_green));
            }




            more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    float inPixelsTxtH = getResources().getDimension(R.dimen.rc_order_view);
                    LinearLayout container = (LinearLayout) v.getParent();


                    if ((int) inPixelsTxtH == container.getHeight()) {
                        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        lparams.setMargins(0, (int) inPixelsTxtM, 0, 0);
                        container.setLayoutParams(lparams);

                    } else {
                        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT, (int) inPixelsTxtH);
                        lparams.setMargins(0, (int) inPixelsTxtM, 0, 0);
                        container.setLayoutParams(lparams);
                    }
                }
            });

            orderLay.addView(more);
            LinearLayout.LayoutParams lparams_lbl_order_d = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            TextView company = new TextView(this.getContext());
            company.setLayoutParams(lparams_lbl_order_d);
            company.setText("Company : " +orders.getString(orders.getColumnIndexOrThrow(DatabaseTable.PurchaseOrder.ORDER_COMPANY)));

            TextView address = new TextView(this.getContext());
            address.setLayoutParams(lparams_lbl_order_d);
            address.setText("Address : " + orders.getString(orders.getColumnIndexOrThrow(DatabaseTable.PurchaseOrder.ORDER_DELIVERYADDRESS)));

            orderLay.addView(company);
            orderLay.addView(address);

            //            get order items from db
            String colsItrm [] = {"*"};
            String whereItem = DatabaseTable.PurchaseOrderItem.ORDER_ID+" = ?";
            String whereArgsItem[] = {orders.getString(orders.getColumnIndexOrThrow(DatabaseTable.PurchaseOrder.ORDER_ID))};

            Cursor orderItems = mydb.view(
                    DatabaseTable.PurchaseOrderItem.TABLE_NAME,
                    colsItrm,
                    whereItem,
                    whereArgsItem,
                    null
            );

            while(orderItems.moveToNext()){
                //            set order items
                TextView orderTv = new TextView(this.getContext());
                LinearLayout.LayoutParams lparams_lbl = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                orderTv.setLayoutParams(lparams_lbl);
                orderTv.setText("Item : " + orderItems.getString(orderItems.getColumnIndexOrThrow(DatabaseTable.PurchaseOrderItem.ITEM_ID)));

                orderLay.addView(orderTv);

            }


            orderParent.addView(orderLay);

        }

    }


}