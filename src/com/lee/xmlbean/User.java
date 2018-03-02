package com.lee.xmlbean;

import com.lee.annotation.*;
import com.lee.api.XMLAPI;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijie on 2017/7/8.
 */
@XmlBean(name = "UserBean")
public class User {
    @XmlAttribute
    public String name;
    @XmlAttribute
    public Integer age;
    @XmlListNode(name = "Knowledge", nodeType = Book.class)
    public List<Book> books;
    @XmlSingleNode(name = "Writer", nodeType = Author.class)
    public Author author;
    @Ignore()
    public Book vip;

    public User() {
        books = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Book getVip() {
        return vip;
    }

    public void setVip(Book vip) {
        this.vip = vip;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", books=" + books +
                ", author=" + author +
                ", vip=" + vip +
                '}';
    }

    @Test
    public void writeUser() throws IllegalAccessException, IOException, InvocationTargetException {
        User user = new User();
        user.setName("huaLPppppp");
        user.setAge(13);
        List<Book> b1 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Book book = new Book();
            book.setName("bookFF" + i);
            book.setPage(87 + i * 3);
            book.setPrice((float) (37.5 + i));
            b1.add(book);
        }
        user.setBooks(b1);
        Book book = new Book();
        book.setName("JPM");
        book.setPage(69);
        book.setPrice(69.96f);
        user.setVip(book);

        user.setAuthor(new Author("llxxs"));

        XMLAPI.writeObj2Xml(user, "/Users/lijie/Desktop/user.xml");
    }

    @Test
    public void readUser() throws IOException, ClassNotFoundException {
        XMLAPI.setXmlBeanScanPackage("com.lee");
        User object = (User) XMLAPI.readXML(new FileInputStream("/Users/lijie/Desktop/user.xml"));
        System.out.println(object);
    }

    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException {
        Class<?> clazz = Class.forName("com.lee.xmlbean.User");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String name = field.getGenericType().toString();
            System.out.println(name);
        }
    }
}
