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
@Table(name= SpringJDBC.CAR_TABLE_NAME)
public class Car {
    @Id
    private int id;

    @Column
    private String carnumber;
    @Column
    private String color;
    @Column
    private String model;
    @Column(name="customerid")
    private int customerid;

    public static String idFieldName;
    public static String carnumberFieldName;
    public static String colorFieldName;
    public static String modelFieldName;
    public static String customerIdFieldName;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarnumber() {
        return carnumber;
    }

    public void setCarnumber(String carnumber) {
        this.carnumber = carnumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCustomerId() {
        return customerid;
    }

    public void setCustomerId(int customerId) {
        this.customerid = customerId;
    }

    public Car(int id, String carnumber, String color, String model, int customerid) {
        this.id = id;
        this.carnumber = carnumber;
        this.color = color;
        this.model = model;
        this.customerid = customerid;
    }

    public Car() {
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", carnumber='" + carnumber + '\'' +
                ", color='" + color + '\'' +
                ", model='" + model + '\'' +
                ", customerid=" + customerid +
                '}';
    }

    public static void getFieldNameReflection ()  {
        Field field = null;
        try {
            field = Car.class.getDeclaredField("id");
            idFieldName = field.getName();
            field = Car.class.getDeclaredField("carnumber");
            carnumberFieldName = field.getName();
            field = Car.class.getDeclaredField("color");
            colorFieldName = field.getName();
            field = Car.class.getDeclaredField("model");
            modelFieldName = field.getName();
            field = Car.class.getDeclaredField("customerid");
            customerIdFieldName = field.getName();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
