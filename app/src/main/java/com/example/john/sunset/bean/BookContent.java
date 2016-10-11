package com.example.john.sunset.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by john on 2016/10/11.
 */

public class BookContent {
    public static class Book {
        public Integer id;
        public String title;
        public String desc;

        public Book(Integer id, String title, String desc) {
            this.id = id;
            this.title = title;
            this.desc = desc;
        }
    }

    //使用list集合来记录所包含的Book对象
    public static List<Book> ITMES = new ArrayList<>();
    //使用map集合记录系统所包含的Book对象
    public static Map<Integer, Book> ITEN_MAP = new HashMap<Integer, Book>();

    static {
        addBook(new Book(1,"标题1","这是Book1"));
        addBook(new Book(2,"标题2","这是Book2"));
        addBook(new Book(3,"标题3","这是Book3"));
    }

    private static void addBook(Book book) {
        ITMES.add(book);
        ITEN_MAP.put(book.id, book);
    }
}
