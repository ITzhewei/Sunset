package com.example.john.sunset;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.john.sunset.bean.BookContent;

public class BookDetailFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "item_id";

    private String mParam1;

    //保存该Fragment显示的Book
    BookContent.Book mBook;


    public static BookDetailFragment newInstance(int param1) {
        BookDetailFragment fragment = new BookDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(ARG_PARAM1)) {
            Bundle bundle = getArguments();
            mBook = BookContent.ITEN_MAP.get(bundle.getInt(ARG_PARAM1));
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_detail, container, false);
        if (mBook != null) {
            TextView title = (TextView) view.findViewById(R.id.book_title);
            title.setText(mBook.title);
            TextView content = (TextView) view.findViewById(R.id.book_content);
            content.setText(mBook.desc);
        }
        return view;
    }


}
