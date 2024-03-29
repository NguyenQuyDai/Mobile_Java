package com.example.bai_3;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class List_Fragment extends ListFragment {
    String[] names = new String[]{"Youtube","Facebook","VN Express","HNUE","FITHNUE"};
    String[] urls  = new String[]{
            "https://www.youtube.com/",
            "https://www.facebook.com/",
            "https://vnexpress.net/",
            "https://hnue.edu.vn/",
            "https://fit.hnue.edu.vn/"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_item,container,false);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,names);
        setListAdapter(adapter);
        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Web_Fragment txt = (Web_Fragment)getFragmentManager().findFragmentById(R.id.fmWeb);
        txt.changeData(urls[position]);
        getListView().setSelector(android.R.color.holo_blue_dark);
    }

}
