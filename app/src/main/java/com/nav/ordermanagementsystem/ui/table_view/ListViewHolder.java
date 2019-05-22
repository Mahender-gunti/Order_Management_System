package com.nav.ordermanagementsystem.ui.table_view;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nav.ordermanagementsystem.R;

public class ListViewHolder extends RecyclerView.ViewHolder{

    private View view;
    private TextView textOrderNumber;
    private TextView textDate;
    private TextView textName;
    private TextView textAddress;
    private TextView textPhone;
    private TextView textTotal;
    private TextView textGPS;

    public ListViewHolder(View view) {
        super(view);
        this.view = view;
        textOrderNumber = view.findViewById(R.id.txt_order);
        textDate = view.findViewById(R.id.txt_due_date);
        textName= view.findViewById(R.id.txt_name);
        textAddress= view.findViewById(R.id.txt_address);
        textPhone= view.findViewById(R.id.txt_phone);
        textTotal= view.findViewById(R.id.txt_order_total);
        textGPS= view.findViewById(R.id.txt_location);
    }

    public View getView(){
        return  view;
    }

    public void bindData(ListModel listModel){

        textOrderNumber.setText(listModel.getOrderNumber());
        textDate.setText(listModel.getOrderDueDate());
        textName.setText(listModel.getCustomerBuyerName());
        textAddress.setText(listModel.getCustomerAddress());
        textPhone.setText(listModel.getCustomerPhone());
        textTotal.setText(listModel.getCustomerOrderTotal());
        textGPS.setText(listModel.getCustomerGPSAddress());
    }
}