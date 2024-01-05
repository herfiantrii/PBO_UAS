/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sanmartApplication;

import java.sql.Date;

/**
 *
 * @author H M NUR FATTAH
 */
public class CustomersData {

    private Integer id;
    private Integer customerID;
    private String prod_name;
    private Double total;
    private Date date;

    public CustomersData(Integer id, Integer customerID, String prod_name, Double total,
             Date date) {
        this.id = id;
        this.customerID = customerID;
        this.prod_name = prod_name;
        this.total = total;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public Integer getCustomerID() {
        return customerID;
    }
    
    public String getProd_name(){
        return prod_name;
    }

    public Double getTotal() {
        return total;
    }

    public Date getDate() {
        return date;
    }

}
