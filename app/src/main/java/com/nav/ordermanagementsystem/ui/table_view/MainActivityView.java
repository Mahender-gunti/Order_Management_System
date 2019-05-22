package com.nav.ordermanagementsystem.ui.table_view;

public interface MainActivityView {
    void deleteItem(ListModel listModel);
    void editItem(ListModel listModel, int index);
    void onC1ickItem(ListModel listModel, int index);
}
