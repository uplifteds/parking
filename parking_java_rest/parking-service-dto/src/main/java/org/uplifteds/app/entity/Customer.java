package org.uplifteds.app.entity;

import org.springframework.stereotype.Component;
import org.uplifteds.app.ddl.SpringJDBC;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.reflect.Field;

@Component
@Entity
@Table(name= SpringJDBC.CUSTOMER_TABLE_NAME)
public class Customer {
    @Id
    private int id;

    @Column
    private String name;
    @Column
    private String lastname;
    @Column
    private String email;
    @Column
    private String phone;

    public static String idFieldName;
    public static String nameFieldName;
    public static String lastnameFieldName;
    public static String emailFieldName;
    public static String phoneFieldName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = title;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastName) {
        this.lastname = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String place) {
        this.email = place;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String speaker) {
        this.phone = speaker;
    }

    public Customer() {
    }

    public Customer(int id, String name, String lastname, String email, String phone) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public static void getFieldNameReflection ()  {
        Field field = null;
        try {
            field = Customer.class.getDeclaredField("id");
            idFieldName = field.getName();
            field = Customer.class.getDeclaredField("name");
            nameFieldName = field.getName();
            field = Customer.class.getDeclaredField("lastname");
            lastnameFieldName = field.getName();
            field = Customer.class.getDeclaredField("email");
            emailFieldName = field.getName();
            field = Customer.class.getDeclaredField("phone");
            phoneFieldName = field.getName();

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }


}
