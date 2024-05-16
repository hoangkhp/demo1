package entity;

import java.util.Date;

public class CheckOrder {
  private int orderID;
  private Account account;
  private Product product;
  private String address;
  private double sum;
  private Date billDate;

  public CheckOrder(int orderID, Account account, Product product, String address, double sum, Date billDate) {
    this.orderID = orderID;
    this.account = account;
    this.product = product;
    this.address = address;
    this.sum = sum;
    this.billDate = billDate;
  }

  public CheckOrder() {
  }

  public int getOrderID() {
    return orderID;
  }

  public void setOrderID(int orderID) {
    this.orderID = orderID;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public double getSum() {
    return sum;
  }

  public void setSum(double sum) {
    this.sum = sum;
  }

  public Date getBillDate() {
    return billDate;
  }

  public void setBillDate(Date billDate) {
    this.billDate = billDate;
  }

  @Override
  public String toString() {
    return "CheckOrder{" +
      "orderID=" + orderID +
      ", account=" + account +
      ", product=" + product +
      ", address='" + address + '\'' +
      ", sum=" + sum +
      ", billDate=" + billDate +
      '}';
  }
}
