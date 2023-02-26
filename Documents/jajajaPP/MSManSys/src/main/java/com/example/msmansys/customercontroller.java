package com.example.msmansys;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import com.example.msmansys.HelloController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class customercontroller implements Initializable {
    String query=null;
    Connection conn=null;
    PreparedStatement pst= null;
    ResultSet res= null;
    MobileItems mobItem= null;

    int index;
    ObservableList<MobileItems> mlist = FXCollections.observableArrayList();
    @FXML
    private TextField purchEmail;

    @FXML
    private PasswordField purchPass;

    @FXML
    private TableView<MobileItems> CustomerTable;

    @FXML
    private TableColumn<MobileItems, String> customerTblModel;

    @FXML
    private TableColumn<MobileItems, String> customerTblPrice;

    @FXML
    private TableColumn<MobileItems, String> customerTblversion;
    @FXML
    private Label modellabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label versionLabel;

    public void purchase(ActionEvent actionEvent) {
        System.out.println("in purchase method......");
        String query2 = "select * from `users` where email = ? and password = ?";
        try {
            pst = conn.prepareStatement(query2);
            pst.setString(1, purchEmail.getText());
            pst.setString(2, purchPass.getText());

            ResultSet res = pst.executeQuery();
            System.out.println("query executed......");

            while (res.next()) {
                System.out.println("result found......");

                String query3 = "select balance from bank INNER JOIN users ON bank.email = ?";
                try{
                    pst= conn.prepareStatement(query3);
                    pst.setString(1,purchEmail.getText());
                    ResultSet rs2 = pst.executeQuery();
                    System.out.println("checking balance.....");

                    while(rs2.next()){
                        System.out.println("result found......");

                        int b= Integer.parseInt(rs2.getString("balance"));
                        if (b>= Integer.parseInt(priceLabel.getText())){
                            System.out.println("balance validated.....");

                            b-= Integer.parseInt(priceLabel.getText());
                            String updateBalance= "update bank set balance = ? where bank.email= ?";
                            pst= conn.prepareStatement(updateBalance);
                            pst.setString(1,Integer.toString(b));
                            pst.setString(2,purchEmail.getText());
                            pst.executeUpdate();
                            System.out.println("balance updated......");


                            String getCompanyBalance = "select balance from bank where bank.email= 'tsure@gmail.com'";
                            pst= conn.prepareStatement(getCompanyBalance);
                            ResultSet r= pst.executeQuery();
                            System.out.println("getting company balance.......");

                            while(r.next()){
                                System.out.println("got company balance.........");

                                int nb = Integer.parseInt(r.getString("balance"));
                                    String updateBalance2 = "update bank set balance = ? where bank.email= 'tsure@gmail.com'";
                                    nb += Integer.parseInt(priceLabel.getText());
                                    pst = conn.prepareStatement(updateBalance2);
                                    pst.setString(1, Integer.toString(nb));
                                    pst.executeUpdate();
                                System.out.println("updated company balance.........");

                                if (updateAv()) {

                                    JOptionPane.showMessageDialog(null, "Purchase successfull!!");
                                }
break;
                            }

                        }
                        else{
                            JOptionPane.showMessageDialog(null,"balance insufficient!");
                            break;
                        }
                        return;
                    }
                    JOptionPane.showMessageDialog(null,"unable to  purchase!!");

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null,"unable to  purchase!!");

                    throw new RuntimeException(e);
                } catch (HeadlessException e) {
                    JOptionPane.showMessageDialog(null,"unable to  purchase!!");

                    throw new RuntimeException(e);
                }
            }
        }
        catch (Exception e){e.printStackTrace();}

            }

public boolean updateAv() throws ClassNotFoundException, SQLException {
    System.out.println("in update available method........");

    String getAvailableOfItem= "select available from itemsforsell where modeName= ? and version = ?";
    Connection c= DBC.connMtd();
    PreparedStatement o= c.prepareStatement(getAvailableOfItem);
    o.setString(1, modellabel.getText());
    o.setString(2,versionLabel.getText());
    ResultSet rs= o.executeQuery();
    System.out.println("executing select query for available items........");

    while (rs.next()){
        System.out.println("got result.........");

        int av= Integer.parseInt(rs.getString("available"));
        if (av>0){

        }
        else{
            JOptionPane.showMessageDialog(null,"item sold out.\nWe will bring more soon. Thank you!");
            return false;
        }
        av--;
        System.out.println("item decremented.........");

        String updateAvailable = "update itemsforsell set available = ? where modeName= ? and version = ?";
o=c.prepareStatement(updateAvailable);
        System.out.println("executing update query........");

        o.setString(1,Integer.toString(av));
o.setString(2, modellabel.getText());
o.setString(3,versionLabel.getText());
o.executeUpdate();
        System.out.println("update successful.........");

        return true;

    }
    return false;
}


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadData();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadData() throws ClassNotFoundException, SQLException {
        conn= DBC.connMtd();
getData();
        customerTblModel.setCellValueFactory(new PropertyValueFactory<>("Model"));
        customerTblversion.setCellValueFactory(new PropertyValueFactory<>("version"));
        customerTblPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    public void getData() throws SQLException {
        query= "select * from `itemsforsell` where itName= 'Mobile' and  NOT available = 0";
        pst= conn.prepareStatement(query);
        res= pst.executeQuery();
        while(res.next()){
            mlist.add(new MobileItems(res.getString("modeName"),res.getString("version"),res.getString("Sellprice")));
            CustomerTable.setItems(mlist);
        }

    }

    public void getSelectedData(MouseEvent mouseEvent) {
        String m,v,p;
        index= CustomerTable.getSelectionModel().getSelectedIndex();
        if (index<=-1){
            m="nothing"; v= "nothing"; p="nothing";
        }
        else{
            m= customerTblModel.getCellData(index).toString();
            v= customerTblversion.getCellData(index).toString();

            p= customerTblPrice.getCellData(index).toString();

        }
        modellabel.setText(m); versionLabel.setText(v); priceLabel.setText(p);

    }

    public void toHome(ActionEvent actionEvent) throws IOException {
        FXMLLoader lo = new FXMLLoader(HelloApplication.class.getResource("Home.fxml"));
        Scene sc = new Scene(lo.load());
        Stage st= (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        st.setScene(sc);
        st.setTitle("Mobile Store Management System");
        st.show();
    }
}
