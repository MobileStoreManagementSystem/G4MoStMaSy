package com.example.msmansys;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloController {

    @FXML
    private Label regLabel;

    @FXML
    private TextField emailfield;
    @FXML
    private PasswordField passwordfield;

    @FXML
    private TextField regAdress;

    @FXML
    private Button regBtn;

    @FXML
    private PasswordField regCpass;

    @FXML
    private TextField regEmail;
    @FXML
    private PasswordField regPassword;

    @FXML
    private TextField regPhone;

    @FXML
    private TextField regname;
    DBC db = new DBC();
    Connection conn = db.connMtd();
    PreparedStatement prst = null;

    public HelloController() throws ClassNotFoundException {
    }

    @FXML
    void goToRegister(MouseEvent event) throws IOException {
        FXMLLoader lo = new FXMLLoader(HelloApplication.class.getResource("register.fxml"));
        Scene sc = new Scene(lo.load());
        Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
        st.setScene(sc);
        st.setTitle("Mobile Store Management System");
        st.show();

    }

    public void pcView(ActionEvent actionEvent) {
JOptionPane.showMessageDialog(null,"We have not started this service yet.\n coming soon........");
    }

    public void mobileView(ActionEvent actionEvent) throws IOException {
        FXMLLoader lo = new FXMLLoader(HelloApplication.class.getResource("customerMobileHome.fxml"));
        Scene sc = new Scene(lo.load());
        Stage st = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        st.setScene(sc);
        st.setTitle("Mobile Store Management System");
        st.show();

    }

    public void login(ActionEvent actionEvent) throws IOException {
        FXMLLoader lo = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene sc = new Scene(lo.load());
        Stage st = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        st.setScene(sc);
        st.setTitle("Mobile Store Management System");
        st.show();
    }

    public void loginMeth(ActionEvent actionEvent) throws IOException {
        String query = "select * from `users` where email = ? and password = ?";
        try {
            prst = conn.prepareStatement(query);
            prst.setString(1, emailfield.getText());
            prst.setString(2, passwordfield.getText());

            ResultSet res = prst.executeQuery();
            while (res.next()) {
                String role = res.getString("role");
                if (role.equals("admin")) {

                    JOptionPane.showMessageDialog(null, "login successful!!");
                    FXMLLoader lo = new FXMLLoader(HelloApplication.class.getResource("adminDash.fxml"));
                    Scene sc = new Scene(lo.load());
                    Stage st = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    st.setScene(sc);
                    st.setTitle("Mobile Store Management System");
                    st.show();
                } else if (role.equals("storekeeper")) {
                    JOptionPane.showMessageDialog(null, "login successful!!");

                    FXMLLoader lo = new FXMLLoader(HelloApplication.class.getResource("storekeeper.fxml"));
                    Scene sc = new Scene(lo.load());
                    Stage st = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    st.setScene(sc);
                    st.setTitle("Mobile Store Management System");
                    st.show();
                }
                else if (role.equals("client")){
                    JOptionPane.showMessageDialog(null,"Can't login!! \nSorry!!");
                }



            }
            if (!res.next()){
                JOptionPane.showMessageDialog(null,"Account NOT FOUND!!!");

            }
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    public void aboutMtd(ActionEvent actionEvent) throws IOException {
        FXMLLoader lo = new FXMLLoader(HelloApplication.class.getResource("About.fxml"));
        Scene sc = new Scene(lo.load());
        Stage st = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        st.setScene(sc);
        st.setTitle("Mobile Store Management System");
        st.show();
    }

    public void contactMtd(ActionEvent actionEvent) throws IOException {
        FXMLLoader lo = new FXMLLoader(HelloApplication.class.getResource("contact.fxml"));
        Scene sc = new Scene(lo.load());
        Stage st = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        st.setScene(sc);
        st.setTitle("Mobile Store Management System");
        st.show();

    }

    public void home(ActionEvent actionEvent) throws IOException {
        FXMLLoader lo = new FXMLLoader(HelloApplication.class.getResource("Home.fxml"));
        Scene sc = new Scene(lo.load());
        Stage st = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        st.setScene(sc);
        st.setTitle("Mobile Store Management System");
        st.show();

    }

    public void regMtd(ActionEvent actionEvent) throws SQLException, IOException {
        String query = "insert into users(name,email,address,phoneno,password,role) values (?,?,?,?,?,?)";
        if (regPassword.getText().equals(regCpass.getText())) {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, regname.getText());
            pst.setString(2, regEmail.getText());
            pst.setString(3, regAdress.getText());
            pst.setString(4, String.valueOf(Integer.parseInt(regPhone.getText())));
            pst.setString(5, regPassword.getText());
            pst.setString(6, "client");
            pst.execute();
            JOptionPane.showMessageDialog(null, "registration complete!");
            FXMLLoader lo = new FXMLLoader(HelloApplication.class.getResource("Home.fxml"));
            Scene sc = new Scene(lo.load());
            Stage st = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            st.setScene(sc);
            st.setTitle("Mobile Store Management System");
            st.show();
        }
    }
}
