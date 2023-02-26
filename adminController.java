package com.example.msmansys;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class adminController implements Initializable {
    @FXML
    private Label balanceLabel;

    public void goToRegister(MouseEvent mouseEvent) {

    }

    public void assignMtd(ActionEvent actionEvent) throws IOException {
       /* FXMLLoader lo = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene sc = new Scene(lo.load());
        Stage st= (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        st.setScene(sc);
        st.setTitle("Mobile Store Management System");
        st.show();
*/
    }

    public void logout(ActionEvent actionEvent) throws IOException {
        FXMLLoader lo = new FXMLLoader(HelloApplication.class.getResource("Home.fxml"));
        Scene sc = new Scene(lo.load());
        Stage st = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        st.setScene(sc);
        st.setTitle("Mobile Store Management System");
        st.show();
    }

    public void showDetail(ActionEvent actionEvent) throws IOException {
        FXMLLoader lo = new FXMLLoader(HelloApplication.class.getResource("adminDetail.fxml"));
        Scene sc = new Scene(lo.load());
        Stage st = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        st.setScene(sc);
        st.setTitle("Mobile Store Management System");
        st.show();
    }

    public void showAssign(ActionEvent actionEvent) throws IOException {
        FXMLLoader lo = new FXMLLoader(HelloApplication.class.getResource("Assign.fxml"));
        Scene sc = new Scene(lo.load());
        Stage st = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        st.setScene(sc);
        st.setTitle("Mobile Store Management System");
        st.show();
    }

    public void dash(ActionEvent actionEvent) throws IOException {

        FXMLLoader lo = new FXMLLoader(HelloApplication.class.getResource("adminDash.fxml"));
        Scene sc = new Scene(lo.load());
        Stage st = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        st.setScene(sc);
        st.setTitle("Mobile Store Management System");
        st.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String query = "select balance from bank where email= 'tsure@gmail.com'";
        Connection con = null;
        try {
            con = DBC.connMtd();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        ResultSet res = null;
        try {
            res = con.createStatement().executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        balanceLabel.setText("");
        while (true) {
            try {
                if (!res.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                balanceLabel.setText(res.getString("balance"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
