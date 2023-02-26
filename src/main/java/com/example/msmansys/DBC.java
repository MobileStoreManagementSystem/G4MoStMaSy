package com.example.msmansys;

import java.sql.*;

public class DBC{
    public static final String url= "jdbc:mysql://localhost:3306/storemanagment";
    public static final String user= "root";
    public static final String password="";
    public static Connection connMtd() throws ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn= null;
        try{
            conn= DriverManager.getConnection(url,user,password);

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return  conn;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Connection conn= connMtd();
        if (conn== null){
            System.out.println("connection failed!!");
        }
        else{
            System.out.println("connection succesfull!!!");
        }
    }
}