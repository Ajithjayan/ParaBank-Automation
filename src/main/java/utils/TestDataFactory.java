package utils;

import models.User;

public class TestDataFactory {
    public static User generateUser(){
        long timestamp= System.currentTimeMillis();
        User user= new User();
        user.firstname="Test";
        user.lastname="admin";
        user.address="123 Main St";
        user.city="Noida";
        user.state="UP";
        user.zipCode="10011";
        user.phone="1234567892";
        user.ssn="1111";
        user.username="Ajith"+ timestamp;
        user.password="AdminTest@1";

        return user;
    }
}
