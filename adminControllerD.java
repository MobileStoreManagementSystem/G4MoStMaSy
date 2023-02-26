package com.example.msmansys;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class adminControllerD implements Initializable {
    @FXML
    private TextField assigEmail;
    @FXML
    private TableColumn<AvailableItems, String> AVAILABLE;

    @FXML
    private TableColumn<AvailableItems, String> ItETyP;

    @FXML
    private TableColumn<AvailableItems, String> ModEl;

    @FXML
    private TableColumn<AvailableItems, String> PrIcE;

    @FXML
    private TableColumn<AvailableItems, String> VErSIon;

    @FXML
    private TableColumn<AvailableItems, String> cOst;


    @FXML
    private TableView<AvailableItems> tbl;

    Connection conn = DBC.connMtd();
    String query;
    PreparedStatement pst;
    ResultSet res;
    ObservableList<AvailableItems> mlist = FXCollections.observableArrayList();

    public adminControllerD() throws ClassNotFoundException {
    }


    public void getData() throws SQLException {
        try {
            query = "select * from itemsforsell";
            pst = conn.prepareStatement(query);
            res = pst.executeQuery();
            while (res.next()) {
                System.out.println("query result found");
                mlist.add(new AvailableItems(res.getString("itName"), res.getString("modeName"), res.getString("version"), res.getString("costPrice"), res.getString("Sellprice"), res.getString("available")));
                System.out.println("added to the list");
                tbl.setItems(mlist);
            }
        } catch (Exception e) {
        }
    }

    public void loadData() throws ClassNotFoundException, SQLException {
        try {
            conn = DBC.connMtd();
            getData();
            ItETyP.setCellValueFactory(new PropertyValueFactory<>("Item Type"));
            ModEl.setCellValueFactory(new PropertyValueFactory<>("model"));
            VErSIon.setCellValueFactory(new PropertyValueFactory<>("version"));
            cOst.setCellValueFactory(new PropertyValueFactory<>("cost"));
            PrIcE.setCellValueFactory(new PropertyValueFactory<>("price"));
            AVAILABLE.setCellValueFactory(new PropertyValueFactory<>("available"));
        } catch (Exception e) {
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadData();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void logout(ActionEvent actionEvent) throws IOException {
        FXMLLoader lo = new FXMLLoader(HelloApplication.class.getResource("Home.fxml"));
        Scene sc = new Scene(lo.load());
        Stage st = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        st.setScene(sc);
        st.setTitle("Mobile Store Management System");
        st.show();
    }

    public void goToRegister(MouseEvent mouseEvent) {
    }

    public void AsIGN(ActionEvent actionEvent) throws IOException {
        FXMLLoader lo = new FXMLLoader(HelloApplication.class.getResource("Assign.fxml"));
        Scene sc = new Scene(lo.load());
        Stage st = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        st.setScene(sc);
        st.setTitle("Mobile Store Management System");
        st.show();
    }

    public void dlod(ActionEvent actionEvent) throws IOException {
        FXMLLoader lo = new FXMLLoader(HelloApplication.class.getResource("adminDash.fxml"));
        Scene sc = new Scene(lo.load());
        Stage st = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        st.setScene(sc);
        st.setTitle("Mobile Store Management System");
        st.show();
    }

    public void det(ActionEvent actionEvent) throws IOException {
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

    public void assign(ActionEvent actionEvent) throws SQLException {
        String assignQuery = "update users set role= 'storekeeper' where email = ?";
        pst = conn.prepareStatement(assignQuery);
        pst.setString(1, assigEmail.getText());
        pst.executeUpdate();
        JOptionPane.showMessageDialog(null, "Assigned!");
    }
}