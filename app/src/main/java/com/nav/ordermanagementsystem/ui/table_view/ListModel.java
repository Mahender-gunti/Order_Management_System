package com.nav.ordermanagementsystem.ui.table_view;

import android.os.Parcel;
import android.os.Parcelable;

public class ListModel implements Parcelable {

    private String orderNumber;
    private String orderDueDate;
    private String customerBuyerName;
    private String customerAddress;
    private String customerPhone;
    private String customerOrderTotal;
    private String customerGPSAddress;


    protected ListModel(Parcel in) {
        orderNumber = in.readString();
        orderDueDate = in.readString();
        customerBuyerName = in.readString();
        customerAddress = in.readString();
        customerPhone = in.readString();
        customerOrderTotal = in.readString();
        customerGPSAddress = in.readString();
    }

    public static final Creator<ListModel> CREATOR = new Creator<ListModel>() {
        @Override
        public ListModel createFromParcel(Parcel in) {
            return new ListModel(in);
        }

        @Override
        public ListModel[] newArray(int size) {
            return new ListModel[size];
        }
    };

    public ListModel() {

    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderDueDate() {
        return orderDueDate;
    }

    public void setOrderDueDate(String orderDueDate) {
        this.orderDueDate = orderDueDate;
    }

    public String getCustomerBuyerName() {
        return customerBuyerName;
    }

    public void setCustomerBuyerName(String customerBuyerName) {
        this.customerBuyerName = customerBuyerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerOrderTotal() {
        return customerOrderTotal;
    }

    public void setCustomerOrderTotal(String customerOrderTotal) {
        this.customerOrderTotal = customerOrderTotal;
    }

    public String getCustomerGPSAddress() {
        return customerGPSAddress;
    }

    public void setCustomerGPSAddress(String customerGPSAddress) {
        this.customerGPSAddress = customerGPSAddress;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(orderNumber);
        dest.writeString(orderDueDate);
        dest.writeString(customerBuyerName);
        dest.writeString(customerAddress);
        dest.writeString(customerPhone);
        dest.writeString(customerOrderTotal);
        dest.writeString(customerGPSAddress);
    }
}