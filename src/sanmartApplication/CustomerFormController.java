/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sanmartApplication;

import java.util.StringJoiner;
import java.util.ArrayList;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author H M NUR FATTAH
 */
public class CustomerFormController implements Initializable {
    
    @FXML
    private AnchorPane main_form;
    
    @FXML
    private Label username;
    
    @FXML
    private Button inventory_btn;
    
    @FXML
    private Button menu_btn;
    
    @FXML
    private Button customers_btn;
    
    @FXML
    private Button logout_btn;
    
    @FXML
    private AnchorPane menu_form;
    
    @FXML
    private ScrollPane menu_scrollPane;
    
    @FXML
    private GridPane menu_gridPane;
    
    @FXML
    private TableView<ProductData> menu_tableView;
    
    @FXML
    private TableColumn<ProductData, String> menu_col_productName;
    
    @FXML
    private TableColumn<ProductData, String> menu_col_quantity;
    
    @FXML
    private TableColumn<ProductData, String> menu_col_price;
    
    @FXML
    private Label menu_total;
    
    @FXML
    private TextField menu_amount;
    
    @FXML
    private Label menu_change;
    
    @FXML
    private Button menu_payBtn;
    
    @FXML
    private Button menu_removeBtn;
    
    @FXML
    private Button menu_receiptBtn;
    
    @FXML
    private AnchorPane customers_form;
    
    @FXML
    private TableView<CustomersData> customers_tableView;
    
    @FXML
    private TableColumn<CustomersData, String> customers_col_customerID;
    
    @FXML
    private TableColumn<CustomersData, String> customers_col_total;
    
    @FXML
    private TableColumn<CustomersData, String> customers_col_date;
    
    private Alert alert;
    
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    
    private Image image;
    
    private ObservableList<ProductData> cardListData = FXCollections.observableArrayList();
    
    public ObservableList<ProductData> menuGetData() {
        
        String sql = "SELECT * FROM product";
        
        ObservableList<ProductData> listData = FXCollections.observableArrayList();
        connect = Database.connectDB();
        
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            ProductData prod;
            
            while (result.next()) {
                prod = new ProductData(result.getInt("id"),
                        result.getString("prod_id"),
                        result.getString("prod_name"),
                        result.getString("type"),
                        result.getInt("stock"),
                        result.getDouble("price"),
                        result.getString("image"),
                        result.getDate("date"));
                
                listData.add(prod);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return listData;
    }
    
    public void menuDisplayCard() {
        
        cardListData.clear();
        cardListData.addAll(menuGetData());
        
        int row = 0;
        int column = 0;
        
        menu_gridPane.getChildren().clear();
        menu_gridPane.getRowConstraints().clear();
        menu_gridPane.getColumnConstraints().clear();
        
        for (int q = 0; q < cardListData.size(); q++) {
            
            try {
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("cardProduct.fxml"));
                AnchorPane pane = load.load();
                CardProductController cardC = load.getController();
                cardC.setData(cardListData.get(q));
                
                if (column == 3) {
                    column = 0;
                    row += 1;
                }
                
                menu_gridPane.add(pane, column++, row);
                
                GridPane.setMargin(pane, new Insets(10));
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public ObservableList<ProductData> menuGetOrder() {
        customerID();
        ObservableList<ProductData> listData = FXCollections.observableArrayList();
        
        String sql = "SELECT * FROM history_customer WHERE customer_id = " + Data.cID;
        
        connect = Database.connectDB();
        
        try {
            
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            ProductData prod;
            
            while (result.next()) {
                prod = new ProductData(result.getInt("id"),
                        result.getString("prod_id"),
                        result.getString("prod_name"),
                        result.getString("type"),
                        result.getInt("quantity"),
                        result.getDouble("price"),
                        result.getString("image"),
                        result.getDate("date"));
                listData.add(prod);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return listData;
    }
    
    private ObservableList<ProductData> menuOrderListData;
    
    public void menuShowOrderData() {
        menuOrderListData = menuGetOrder();
        
        menu_col_productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        menu_col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        menu_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        menu_tableView.setItems(menuOrderListData);
    }
    private int getid;
    
    public void menuSelectOrder() {
        ProductData prod = menu_tableView.getSelectionModel().getSelectedItem();
        int num = menu_tableView.getSelectionModel().getSelectedIndex();
        
        if ((num - 1) < -1) {
            return;
        }
        // TO GET THE ID PER ORDER
        getid = prod.getId();
        
    }
    
    private double totalP;
    
    public void menuGetTotal() {
        customerID();
        String total = "SELECT SUM(price) FROM history_customer WHERE customer_id = " + Data.cID;
        
        connect = Database.connectDB();
        
        try {
            
            prepare = connect.prepareStatement(total);
            result = prepare.executeQuery();
            
            if (result.next()) {
                totalP = result.getDouble("SUM(price)");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public void menuDisplayTotal() {
        menuGetTotal();
        menu_total.setText("Rp" + totalP);
    }
    
    private double amount;
    private double change;
    
    public void menuAmount() {
        menuGetTotal();
        if (menu_amount.getText().isEmpty() || totalP == 0) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Invalid :3");
            alert.showAndWait();
        } else {
            amount = Double.parseDouble(menu_amount.getText());
            if (amount < totalP) {
                menu_amount.setText("");
            } else {
                change = (amount - totalP);
                menu_change.setText("Rp" + change);
            }
        }
    }
    
    public void menuPayBtn() {
        
        if (totalP == 0) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please choose your order first!");
            alert.showAndWait();
        } else {
            menuGetTotal();
            menuGetProduct();
            String insertPay = "INSERT INTO receipt (customer_id, prod_name, total, date) "
                    + "VALUES(?,?,?,?)";
            
            connect = Database.connectDB();
            
            try {
                
                if (amount == 0) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Messaged");
                    alert.setHeaderText(null);
                    alert.setContentText("Something wrong :3");
                    alert.showAndWait();
                } else {
                    alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Are you sure?");
                    Optional<ButtonType> option = alert.showAndWait();
                    
                    if (option.get().equals(ButtonType.OK)) {
                        customerID();
                        menuGetTotal();
                        menuGetProduct();
                        prepare = connect.prepareStatement(insertPay);
                        prepare.setString(1, String.valueOf(Data.cID));
                        prepare.setString(2, String.valueOf(product));
                        
                        prepare.setString(3, String.valueOf(totalP));
                        
                        Date date = new Date();
                        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                        
                        prepare.setString(4, String.valueOf(sqlDate));
                        
                        prepare.executeUpdate();
                        
                        alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Infomation Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Successful.");
                        alert.showAndWait();
                        
                        menuShowOrderData();
                        
                    } else {
                        alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Infomation Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Cancelled.");
                        alert.showAndWait();
                    }
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }
    
    private String product;
	
	public void menuGetProduct() {
        customerID();
        String sql = "SELECT prod_name FROM history_customer WHERE customer_id = " + Data.cID;
        
        connect = Database.connectDB();
        ArrayList<String> productList = new ArrayList<>();
        try {
			prepare = connect.prepareStatement(sql);
			result = prepare.executeQuery();
			
			while (result.next()) {
				String productName = result.getString("prod_name");
				productList.add(productName);
			}
			
			product = String.join(", ", productList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public void menuRemoveBtn() {
        
        if (getid == 0) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please select the order you want to remove");
            alert.showAndWait();
        } else {
            String deleteData = "DELETE FROM history_customer WHERE id = " + getid;
            connect = Database.connectDB();
            try {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete this order?");
                Optional<ButtonType> option = alert.showAndWait();
                
                if (option.get().equals(ButtonType.OK)) {
                    prepare = connect.prepareStatement(deleteData);
                    prepare.executeUpdate();
                }
                
                menuShowOrderData();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
    }
    
    public void menuReceiptBtn() {
        
        if (totalP == 0 || menu_amount.getText().isEmpty()) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("Please order first");
            alert.showAndWait();
        } else {
            HashMap map = new HashMap();
            map.put("getReceipt", (Data.cID - 1));
            
            try {
                
                menuRestart();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        
    }
    
    public void menuRestart() {
        totalP = 0;
        change = 0;
        amount = 0;
        menu_total.setText("Rp0.0");
        menu_amount.setText("");
        menu_change.setText("Rp0.0");
    }
    
    private int cID;
    
    public void customerID() {
        
        String sql = "SELECT MAX(customer_id) FROM history_customer";
        connect = Database.connectDB();
        
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            if (result.next()) {
                cID = result.getInt("MAX(customer_id)");
            }
            
            String checkCID = "SELECT MAX(customer_id) FROM receipt";
            prepare = connect.prepareStatement(checkCID);
            result = prepare.executeQuery();
            int checkID = 0;
            if (result.next()) {
                checkID = result.getInt("MAX(customer_id)");
            }
            
            if (cID == 0) {
                cID += 1;
            } else if (cID == checkID) {
                cID += 1;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ObservableList<CustomersData> customersDataList() {
        
        customerID();
        
        ObservableList<CustomersData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM receipt WHERE customer_id = " + Data.cID;
        connect = Database.connectDB();
        
        try {
            
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            CustomersData cData;
            
            while (result.next()) {
                cData = new CustomersData(result.getInt("id"),
                        result.getInt("customer_id"),
                        result.getString("prod_name"),
                        result.getDouble("total"),
                        result.getDate("date"));
                
                listData.add(cData);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }
    
    private ObservableList<CustomersData> customersListData;
    
    public void customersShowData() {
        customersListData = customersDataList();
        
        customers_col_customerID.setCellValueFactory(new PropertyValueFactory<>("prod_name"));
        customers_col_total.setCellValueFactory(new PropertyValueFactory<>("total"));
        customers_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        customers_tableView.setItems(customersListData);
    }
    
    public void switchForm(ActionEvent event) {
        if (event.getSource() == menu_btn) {
            menu_form.setVisible(true);
            customers_form.setVisible(false);
            
            menuDisplayCard();
            menuDisplayTotal();
            menuShowOrderData();
        } else if (event.getSource() == customers_btn) {
            menu_form.setVisible(false);
            customers_form.setVisible(true);
            
            customersShowData();
        }
        
    }
// LETS PROCEED TO OUR DASHBOARD FORM : )

    public void logout() {
        
        try {
            
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");
            Optional<ButtonType> option = alert.showAndWait();
            
            if (option.get().equals(ButtonType.OK)) {

                // TO HIDE MAIN FORM 
                logout_btn.getScene().getWindow().hide();

                // LINK YOUR LOGIN FORM AND SHOW IT 
                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                
                stage.setTitle("Sanitation Mart Application");
                
                stage.setScene(scene);
                stage.show();
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public void displayUsername() {
        
        String user = Data.username;
        user = user.substring(0, 1).toUpperCase() + user.substring(1);
        
        username.setText(user);
        
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        displayUsername();
        
        menuDisplayCard();
        menuGetOrder();
        menuDisplayTotal();
        menuShowOrderData();
        
        customersShowData();
        
    }
    
}
