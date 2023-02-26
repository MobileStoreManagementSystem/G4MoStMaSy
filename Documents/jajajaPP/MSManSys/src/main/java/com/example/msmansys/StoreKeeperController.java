package com.example.msmansys;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StoreKeeperController {

    @FXML
    private TextField itemModel;

    @FXML
    private TextField itemName;

    @FXML
    private TextField itemPdate;

    @FXML
    private TextField itemPrice;

    @FXML
    private TextField itemVersion;

    @FXML
    private TextField itemcost;

    @FXML
    private TextField assetAmmount;

    @FXML
    private TextField assetCost;

    @FXML
    private TextField itemModelu;

    @FXML
    private TextField itemNameu;

    @FXML
    private TextField itemPdateu;

    @FXML
    private TextField itemPriceu;

    @FXML
    private TextField itemVersionu;

    @FXML
    private TextField itemcostu;

    @FXML
    private TextField itemsBoughtu;

    @FXML
    private Button updateAvbtn;
    @FXML
    private TextField assetMode;

    @FXML
    private TextField assetName;

    @FXML
    private TextField assetPdate;


    @FXML
    private TextField accountAmmount;

    @FXML
    private TextField rechargeAmmount;

    @FXML
    private TextField rechargeCombo;

    @FXML
    private TextField selectAccount;
    @FXML
    private TextField itemsBought;

    @FXML
    void clearAc(ActionEvent event) {

    }

    @FXML
    void clearAm(ActionEvent event) {

    }

    @FXML
    void createAc(ActionEvent event) {
        String query = "select email from users where email = ?";
        try {
            Connection cn = DBC.connMtd();
            PreparedStatement pst = cn.prepareStatement(query);
            pst.setString(1, selectAccount.getText());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String q2 = "insert into bank(email,balance) values(?,?)";
                pst = cn.prepareStatement(q2);
                pst.setString(1, selectAccount.getText());
                pst.setString(2, accountAmmount.getText());
                pst.execute();
                JOptionPane.showMessageDialog(null, "created!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void clear(ActionEvent event) {
        clear();
    }

    @FXML
    void contactMtd(ActionEvent event) {

    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        FXMLLoader lo = new FXMLLoader(HelloApplication.class.getResource("Home.fxml"));
        Scene sc = new Scene(lo.load());
        Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
        st.setScene(sc);
        st.setTitle("Mobile Store Management System");
        st.show();
    }

    @FXML
    void registerItemsForSell(ActionEvent event) throws IOException {
        FXMLLoader lo = new FXMLLoader(HelloApplication.class.getResource("storekeeper.fxml"));
        Scene sc = new Scene(lo.load());
        Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
        st.setScene(sc);
        st.setTitle("Mobile Store Management System");
        st.show();
    }

    @FXML
    void registerItem(ActionEvent event) throws ClassNotFoundException, SQLException {
        Connection connection = DBC.connMtd();
        PreparedStatement preparedStatement;
        String update_company_balance = "select balance from bank where email='tsure@gmail.com'";
        preparedStatement = connection.prepareStatement(update_company_balance);
        ResultSet resultSet2 = preparedStatement.executeQuery();
        while (resultSet2.next()) {
            int balance = Integer.parseInt(resultSet2.getString("balance"));
            if (balance >= Integer.parseInt(itemcost.getText())) {
                balance -= Integer.parseInt(itemcost.getText());
                String updateforreal = "update bank set balance = ? where email = 'tsure@gmail.com'";
                preparedStatement = connection.prepareStatement(updateforreal);
                preparedStatement.setString(1, Integer.toString(balance));
                preparedStatement.executeUpdate();

                String query = "insert into itemsforsell(itname,modeName,version,SellPrice,purchaseDate,costPrice,available) values(?,?,?,?,?,?,?)";
                try {
                    int pn = Integer.parseInt(itemcost.getText())/Integer.parseInt(itemsBought.getText());
                    Connection conn = DBC.connMtd();
                    PreparedStatement pst = conn.prepareStatement(query);
                    pst.setString(1, itemName.getText());
                    pst.setString(2, itemModel.getText());
                    pst.setString(3, itemVersion.getText());
                    pst.setString(4, itemPrice.getText());
                    pst.setString(5, itemPdate.getText());
                    pst.setString(6, Integer.toString(pn));
                    pst.setString(7, itemsBought.getText());
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Registered!!");
                    clear();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "company balance insufficient!");
                break;
            }
        }


    }

    private void clear() {
        itemcost.setText("");
        itemPdate.setText("");
        itemPrice.setText("");
        itemVersion.setText("");
        itemModel.setText("");
        itemName.setText("");
        itemsBought.setText("");

        assetName.setText("");
        assetPdate.setText("");
        assetCost.setText("");
        assetMode.setText("");
        assetAmmount.setText("");

    }

    public void manageClient(ActionEvent actionEvent) throws IOException {
        FXMLLoader lo = new FXMLLoader(HelloApplication.class.getResource("ManageClientAccount.fxml"));
        Scene sc = new Scene(lo.load());
        Stage st = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        st.setScene(sc);
        st.setTitle("Mobile Store Management System");
        st.show();
    }

    public void registerAssets(ActionEvent actionEvent) throws IOException {
        FXMLLoader lo = new FXMLLoader(HelloApplication.class.getResource("regAsset.fxml"));
        Scene sc = new Scene(lo.load());
        Stage st = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        st.setScene(sc);
        st.setTitle("Mobile Store Management System");
        st.show();
    }

    public void regAsset(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        try {
            String getCompanyBalance = "select balance from bank where bank.email= 'tsure@gmail.com'";
            Connection conn = DBC.connMtd();
            PreparedStatement pst = conn.prepareStatement(getCompanyBalance);

            ResultSet r = pst.executeQuery();
            while (r.next()) {
                int nb = Integer.parseInt(r.getString("balance"));


                if (nb >= Integer.parseInt(assetCost.getText())) {
                    nb -= Integer.parseInt(assetCost.getText());
                    String updateBalance2 = "update bank set balance = ? where bank.email= 'tsure@gmail.com'";
                    pst = conn.prepareStatement(updateBalance2);
                    pst.setString(1, Integer.toString(nb));
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Registered!!");
                    String query = "insert into assets(assetname,number,model,purchasedate,cost) values(?,?,?,?,?)";
                    pst = conn.prepareStatement(query);
                    pst.setString(1, assetName.getText());
                    pst.setString(2, assetAmmount.getText());
                    pst.setString(3, assetMode.getText());
                    pst.setString(4, assetPdate.getText());
                    pst.setString(5, assetCost.getText());
                    pst.execute();
                    clear();
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "company balance insufficient!!");
                    break;

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearasset(ActionEvent actionEvent) {
        clear();
    }


    public void rechargeAmmount(ActionEvent actionEvent) {
        System.out.println(rechargeCombo.getText() + "\n" + rechargeAmmount.getText());
        try {

            Connection con = DBC.connMtd();
            PreparedStatement pts = con.prepareStatement("select bank.email from bank where email = ?");
            ResultSet rs = con.createStatement().executeQuery("select balance from bank where email = '" + rechargeCombo.getText() + "'");
            while (rs.next()) {
                int cb = Integer.parseInt(rs.getString("balance"));
                cb += Integer.parseInt(rechargeAmmount.getText());
                pts = con.prepareStatement("update bank set balance = ? where email = ?");
                pts.setString(1, Integer.toString(cb));
                pts.setString(2, rechargeCombo.getText());
                pts.executeUpdate();
                JOptionPane.showMessageDialog(null, "recharged!!");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void updateAvailableItem(ActionEvent actionEvent) throws IOException {
        FXMLLoader lo = new FXMLLoader(HelloApplication.class.getResource("updateavailableitem.fxml"));
        Scene sc = new Scene(lo.load());
        Stage st = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        st.setScene(sc);
        st.setTitle("Mobile Store Management System");
        st.show();
    }

    public void updateavailableitemmethod(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        String select_item = "select * from itemsforsell where modeName= ? and version = ?";
        Connection connection = DBC.connMtd();
        PreparedStatement preparedStatement = connection.prepareStatement(select_item);
        preparedStatement.setString(1, itemModelu.getText());
        preparedStatement.setString(2, itemVersionu.getText());
        ResultSet resultSet1 = preparedStatement.executeQuery();
        while (resultSet1.next()) {
            int availableNow = Integer.parseInt(resultSet1.getString("available"));
            String update_company_balance = "select balance from bank where email='tsure@gmail.com'";
            preparedStatement = connection.prepareStatement(update_company_balance);
            ResultSet resultSet2 = preparedStatement.executeQuery();
            while (resultSet2.next()) {
                int balance = Integer.parseInt(resultSet2.getString("balance"));
                if (balance >= Integer.parseInt(itemcostu.getText())) {
                    balance -= Integer.parseInt(itemcostu.getText());
                    String updateforreal = "update bank set balance = ? where email = 'tsure@gmail.com'";
                    preparedStatement = connection.prepareStatement(updateforreal);
                    preparedStatement.setString(1, Integer.toString(balance));
                    preparedStatement.executeUpdate();
                } else {
                    JOptionPane.showMessageDialog(null, "company balance insufficient!");
                    break;
                }
            }
            String updateItem = "update itemsforsell set available=?, costPrice = ?, sellPrice=?, purchaseDate=? where modeName=? and version = ?";
            preparedStatement = connection.prepareStatement(updateItem);
            availableNow += Integer.parseInt(itemsBoughtu.getText());
            int costnow = Integer.parseInt(itemcostu.getText()) / Integer.parseInt(itemsBoughtu.getText());
            preparedStatement.setString(1, Integer.toString(availableNow));
            preparedStatement.setString(2, Integer.toString(costnow));
            preparedStatement.setString(3, itemPriceu.getText());
            preparedStatement.setString(4, itemPdateu.getText());
            preparedStatement.setString(5, itemModelu.getText());
            preparedStatement.setString(6, itemVersionu.getText());
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "update successful!!");
            break;
        }
    }
}
