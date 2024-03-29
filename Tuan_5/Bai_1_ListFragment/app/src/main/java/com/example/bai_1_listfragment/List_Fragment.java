package com.example.bai_1_listfragment;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class List_Fragment extends ListFragment {
    String[] name = new String[]{"C++","PYTHON","JAVA","GOLANG","SWIFT","PHP"};
    int[] image = new int[]{R.drawable.c,R.drawable.python,R.drawable.java,R.drawable.golang,R.drawable.swift,R.drawable.php};
    String[] Contents = new String[]{
            "C++ là một ngôn ngữ lập trình hướng đối tượng (object-oriented) " +
                    "được phát triển từ C. C++ có thể viết được các chương trình hiệu năng cao, phức tạp " +
                    "và có thể chạy trên nhiều nền tảng khác nhau.C++ cũng là ngôn ngữ lập trình cơ sở " +
                    "cho nhiều ngôn ngữ khác như Java, C# và Python",
            "Python là một ngôn ngữ lập trình thông dịch (interpreted) và hướng đối tượng. " +
                    "Python có cú pháp đơn giản, dễ đọc và dễ học",
            "Java là một ngôn ngữ lập trình hướng đối tượng và biên dịch (compiled). " +
                    "Java được thiết kế để chạy trên nhiều nền tảng khác nhau mà không cần thay đổi mã nguồn (platform-independent)." +
                    " Java có thể được sử dụng cho các ứng dụng doanh nghiệp, web, di động và nhúng",
            "Golang hay Go là một ngôn ngữ lập trình biên dịch và hướng thủ tục (procedural). " +
                    "Go được thiết kế để xử lý các ứng dụng song song (concurrent) và phân tán (distributed) hiệu quả. " +
                    "Go có cú pháp gọn gàng, giống Python và có hiệu suất cao, giống C/C++",
            "Swift là một ngôn ngữ lập trình hướng đối tượng và biên dịch. " +
                    "Swift được phát triển bởi Apple để thay thế cho Objective-C",
            "PHP là một ngôn ngữ lập trình thông dịch và hướng đối tượng. " +
                    "PHP được sử dụng chủ yếu để phát triển các ứng dụng web động (dynamic) và tương tác với cơ sở dữ liệu. " +
                    "PHP có thể nhúng vào HTML và chạy trên máy chủ web"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_danh_sach,container,false);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,name);
        setListAdapter(adapter);
        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Detail_Fragment txt = (Detail_Fragment) getFragmentManager().findFragmentById(R.id.fmContent);
        txt.changeData(name[position],image[position],Contents[position]);
        getListView().setSelector(android.R.color.holo_blue_dark);
    }
}

