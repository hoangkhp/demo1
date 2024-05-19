package dao;

import context.DBContext;
import entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//load du lieu tu db len
public class DAO {
  Connection con = null; //ket noi voi sql server
  PreparedStatement ps = null; //nem cau lenh query sang sql server
  ResultSet rs = null; //nhan ket qua tra ve
  //Lay tat ca cac product trong DB va nem vao 1 cai List
  //1. Ham tra ve toan bo Product
  public List<Product> getAllProduct(){
    List<Product> list = new ArrayList<Product>();
    String query = "SELECT\n" +
      "    p.ProductID,\n" +
      "    p.Name, \n" +
      "    p.Image, \n" +
      "    p.Price,\n" +
      "    c.NameCa AS CategoryName,\n" +
      "    p.Description \n" +
      "FROM \n" +
      "    [Products] p \n" +
      "JOIN \n" +
      "    [Category] c \n" +
      "ON \n" +
      "    p.CategoryID = c.CategoryID;";
    try{
      con = new DBContext().getConnection(); //mo ket noi voi sql server
      ps = con.prepareStatement(query);//Nem cau lenh query sang sqlServer
      rs = ps.executeQuery();
      while(rs.next()){
        list.add(new Product(rs.getInt(1),
          rs.getString(2),
          rs.getString(3),
          rs.getDouble(4),
          new Category(rs.getString(5)),
          rs.getString(6)));
      }
    } catch (Exception e){
    }
    return list;
  }
//2. Ham tra ve List Category
  public List<Category> getAllCategory(){
    List<Category> list = new ArrayList<Category>();
    String query = "select * from Category";
    try{
      con = new DBContext().getConnection(); //mo ket noi voi sql server
      ps = con.prepareStatement(query);//Nem cau lenh query sang sqlServer
      rs = ps.executeQuery();
      while(rs.next()){
        list.add(new Category(rs.getInt(1),
          rs.getString(2)));
      }
    } catch (Exception e){

    }
    return list;
  }
//3. Ham tra ve List Product theo Category ID
public List<Product> getProductByCategory(String cid){
  List<Product> list = new ArrayList<>();
  //Cau lenh Sql giup lay cac san pham dua vao category ID
  String query = "SELECT\n" +
    "    p.ProductID,\n" +
    "    p.Name, \n" +
    "    p.Image, \n" +
    "    p.Price,\n" +
    "    c.NameCa AS CategoryName,\n" +
    "    p.Description,\n" +
    "    c.CategoryID  \n" +
    "FROM \n" +
    "    [Products] p \n" +
    "JOIN \n" +
    "    [Category] c \n" +
    "ON \n" +
    "    p.CategoryID = c.CategoryID\n" +
    " where\n" +
    " c.categoryID = ?;";
  try{
    con = new DBContext().getConnection(); //mo ket noi voi sql server
    ps = con.prepareStatement(query);//Nem cau lenh query sang sqlServer
    ps.setString(1,cid);
    rs = ps.executeQuery();
    while(rs.next()){
      list.add(new Product(rs.getInt(1),
        rs.getString(2),
        rs.getString(3),
        rs.getDouble(4),
        new Category(rs.getString(5)),
        rs.getString(6)));
    }
  } catch (Exception e){

  }
  return list;
}
//4. Hien san pham minh hoa
public Product getLast(){
    String query = "SELECT TOP 1 \n" +
      "    p.ProductID,\n" +
      "    p.Name, \n" +
      "    p.Image, \n" +
      "    p.Price,\n" +
      "    c.NameCa AS CategoryName,\n" +
      "    p.Description \n" +
      "FROM \n" +
      "    [Products] p \n" +
      "JOIN \n" +
      "    [Category] c \n" +
      "ON \n" +
      "    p.CategoryID = c.CategoryID \n" +
      "ORDER BY \n" +
      "    p.ProductID DESC;";
  try{
    con = new DBContext().getConnection(); //mo ket noi voi sql server
    ps = con.prepareStatement(query);//Nem cau lenh query sang sqlServer
    rs = ps.executeQuery();
    while(rs.next()){
      return new Product(rs.getInt(1),
        rs.getString(2),
        rs.getString(3),
        rs.getDouble(4),
        new Category(rs.getString(5)),
        rs.getString(6));
    }
  } catch (Exception e){

  }
  return null;
}

  //5. Ham tra ve 1 Product theo Product ID
  public Product getProductByID(String id){
    //Cau lenh Sql giup lay cac san pham dua vao category ID
    String query = "SELECT\n" +
      "    p.ProductID,\n" +
      "    p.Name, \n" +
      "    p.Image, \n" +
      "    p.Price,\n" +
      "    c.NameCa AS CategoryName,\n" +
      "    p.Description \n" +
      "FROM \n" +
      "    [Products] p \n" +
      "JOIN \n" +
      "    [Category] c \n" +
      "ON \n" +
      "    p.CategoryID = c.CategoryID\n" + "where p.ProductID = ?;";
    try{
      con = new DBContext().getConnection(); //mo ket noi voi sql server
      ps = con.prepareStatement(query);//Nem cau lenh query sang sqlServer
      ps.setString(1,id);
      rs = ps.executeQuery();
      while(rs.next()){
        return new Product(rs.getInt(1),
          rs.getString(2),
          rs.getString(3),
          rs.getDouble(4),
          new Category(rs.getString(5)),
          rs.getString(6));
      }
    } catch (Exception e){

    }
    return null;
  }
  //6. Ham Xay Dung cho search tra ve 1 list cac san pham
  public List<Product> searchByName(String txtSearch){
    List<Product> list = new ArrayList<>();
    //Cau lenh Sql giup lay cac san pham dua vao category ID
    String query = "SELECT \n" +
      "    p.ProductID, \n" +
      "    p.Name,\n" +
      "    p.Image,\n" +
      "    p.Price,\n" +
      "    c.NameCa AS CategoryName,\n" +
      "    p.Description \n" +
      "FROM \n" +
      "    [Products] p\n" +
      "JOIN \n" +
      "    [Category] c \n" +
      "ON  \n" +
      "    p.CategoryID = c.CategoryID\n" +
      "WHERE \n" +
      "    p.Name LIKE ?;";
    try{
      con = new DBContext().getConnection(); //mo ket noi voi sql server
      ps = con.prepareStatement(query);//Nem cau lenh query sang sqlServer
      ps.setString(1,"%" + txtSearch + "%");
      rs = ps.executeQuery();
      while(rs.next()){
        list.add(new Product(rs.getInt(1),
          rs.getString(2),
          rs.getString(3),
          rs.getDouble(4),
          new Category(rs.getString(5)),
          rs.getString(6)));
      }
    } catch (Exception e){

    }
    return list;
  }
  public Account login(String user, String pass) {
    String query = "select * from Account where Username = ? and Password = ?";
    try {
      con = new DBContext().getConnection(); //ket noi vs SQl
      ps = con.prepareStatement(query);
      ps.setString(1, user);
      ps.setString(2, pass);
      rs = ps.executeQuery();
      while (rs.next()) {
        return new Account(rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getInt(4),
                rs.getInt(5));
      }
    } catch (Exception e) {
    }
    return null;
  }
  //7. Ham check ket qua dang nhap
  public Account checkAccount(String user) {
    String query = "select * from Account where Username = ?";
    try {
      con = new DBContext().getConnection(); //ket noi vs SQl
      ps = con.prepareStatement(query);
      ps.setString(1, user);
      rs = ps.executeQuery();
      while (rs.next()) {
        return new Account(rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getInt(4),
                rs.getInt(5));
      }
    } catch (Exception e) {
    }
    return null;
  }
  //8. Ham dang ki them tai khoan
  public void signup(String user, String pass){
    String query = "insert into Account\n" + "values(?,?,0,0)";
    try {
      con = new DBContext().getConnection(); //ket noi vs SQl
      ps = con.prepareStatement(query);
      ps.setString(1, user);
      ps.setString(2, pass);
      ps.executeUpdate();
    } catch (Exception e){
    }
  }


  //9. Sua
  public void insertCart(int accountID, int productID, int amount) {
    String query = "insert Cart(accountID, productID, amount)\r\n"
      + "values(?,?,?)";
    try {
      con = new DBContext().getConnection();//mo ket noi voi sql
      ps = con.prepareStatement(query);
      ps.setInt(1, accountID);
      ps.setInt(2, productID);
      ps.setInt(3, amount);
      ps.executeUpdate();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
  //10. Sua
public List<Cart> getCartByAccountID(int accountID) {
  List<Cart> list = new ArrayList<>();
  String query = "select accountid, productid,amount,macart from Cart where AccountID = ?";
  try {
    con = new DBContext().getConnection();//mo ket noi voi sql
    ps = con.prepareStatement(query);
    ps.setInt(1, accountID);
    rs = ps.executeQuery();
    while (rs.next()) {
      list.add(new Cart(new Account(rs.getInt(1)),
        new Product(rs.getInt(2)),
        rs.getInt(3),
        rs.getInt(4)));
    }
  } catch (Exception e) {
  }
  return list;
}
//11
  public void deleteCart(int productID) {
    String query = "delete from Cart where productID = ?";
    try {
      con = new DBContext().getConnection();//mo ket noi voi sql
      ps = con.prepareStatement(query);
      ps.setInt(1, productID);
      ps.executeUpdate();
    } catch (Exception e) {
    }
  }
  //12.
  public Cart checkCartExist(int accountID,int productID) {

    String query = "select * from Cart\r\n"
      + "where [accountID] = ? and [productID] = ?";
    try {
      con = new DBContext().getConnection();//mo ket noi voi sql
      ps = con.prepareStatement(query);
      ps.setInt(1, accountID);
      ps.setInt(2, productID);
      rs = ps.executeQuery();
      while (rs.next()) {
        return new Cart(new Account(rs.getInt(1)),
          new Product(rs.getInt(1)),
          rs.getInt(3),
          rs.getInt(4));
      }
    } catch (Exception e) {
    }
    return null;
  }
  //13.
  public void editAmountAndSizeCart(int accountID, int productID, int amount) {
    String query = "update Cart set [amount]=?,\r\n"
      + "where [accountID]=? and [productID]=?";
    try {
      con = new DBContext().getConnection();//mo ket noi voi sql
      ps = con.prepareStatement(query);
      ps.setInt(1, amount);
      ps.setInt(3, accountID);
      ps.setInt(4, productID);
      ps.executeUpdate();
    } catch (Exception e) {
    }
  }
  //14.
  public void editAmountCart(int accountID, int productID, int amount) {
    String query = "update Cart set [amount]=?\r\n"
      + "where [accountID]=? and [productID]=?";
    try {
      con = new DBContext().getConnection();//mo ket noi voi sql
      ps = con.prepareStatement(query);
      ps.setInt(1, amount);
      ps.setInt(2, accountID);
      ps.setInt(3, productID);
      ps.executeUpdate();
    } catch (Exception e) {
    }
  }

  //15.
  public void insertInvoice(int accountID, double tongGia) {
    String query = "insert Invoice(accountID,tongGia,ngayXuat)\r\n"
      + "values(?,?,?)";

    try {
      con = new DBContext().getConnection();//mo ket noi voi sql
      ps = con.prepareStatement(query);
      ps.setInt(1, accountID);
      ps.setDouble(2, tongGia);
      ps.setDate(3,getCurrentDate());
      ps.executeUpdate();

    } catch (Exception e) {

    }
  }
  private static java.sql.Date getCurrentDate() {
    java.util.Date today = new java.util.Date();
    return new java.sql.Date(today.getTime());
  }

  //16.
  public void deleteInvoiceByAccountId(String id) {
    String query = "delete from Invoice\n"
      + "where [accountID] = ?";
    try {
      con = new DBContext().getConnection();//mo ket noi voi sql
      ps = con.prepareStatement(query);
      ps.setString(1, id);
      ps.executeUpdate();
    } catch (Exception e) {
    }
  }

  //17.
  public List<Invoice> searchByNgayXuat(String ngayXuat) {
    List<Invoice> list = new ArrayList<>();
    String query = "select * from Invoice\r\n"
      + "where [ngayXuat] ='"+ngayXuat+"'";
    try {
      con = new DBContext().getConnection();//mo ket noi voi sql
      ps = con.prepareStatement(query);
//            ps.setString(1,ngayXuat);
      rs = ps.executeQuery();
      while (rs.next()) {
        list.add(new Invoice(rs.getInt(1),
          new Account(rs.getInt(2)),
          rs.getDouble(3),
          rs.getDate(4)
        ));
      }
    } catch (Exception e) {
    }
    return list;
  }

  //19.
  public double sumAllInvoice() {
    String query = "select SUM(tongGia) from Invoice";
    try {
      con = new DBContext().getConnection();//mo ket noi voi sql
      ps = con.prepareStatement(query);
      rs = ps.executeQuery();
      while (rs.next()) {
        return rs.getDouble(1);
      }
    } catch (Exception e) {
    }
    return 0;
  }
  //20.
  public List<Invoice> getAllInvoice() {
    List<Invoice> list = new ArrayList<>();
    String query = "select * from Invoice";
    try {
      con = new DBContext().getConnection();//mo ket noi voi sql
      ps = con.prepareStatement(query);
      rs = ps.executeQuery();
      while (rs.next()) {
        list.add(new Invoice(rs.getInt(1),
          new Account(rs.getInt(2)),
          rs.getDouble(3),
          rs.getDate(4)
        ));
      }
    } catch (Exception e) {
    }
    return list;
  }
  //21.
  public List<Account> getAllAccount() {
    List<Account> list = new ArrayList<>();
    String query = "select * from Account";
    try {
      con = new DBContext().getConnection();//mo ket noi voi sql
      ps = con.prepareStatement(query);
      rs = ps.executeQuery();
      while (rs.next()) {
        list.add(new Account(rs.getInt(1),
          rs.getString(2),
          rs.getString(3),
          rs.getInt(4),
          rs.getInt(5)));
      }
    } catch (Exception e) {
    }
    return list;
  }

  public static void main(String[] args) {
    DAO dao = new DAO();
    String user = "HoangDuong";
    String pass = "1234567";
    Account a = dao.login(user, pass);
    if (a == null) {
      System.out.println("tk sai");
    } else{
      System.out.println("tk dung");
    }
  }

  public void deleteCartByAccountID(int accountID) {
    String query = "delete from Cart \r\n"
      + "where [accountID]=?";
    try {
      con = new DBContext().getConnection();//mo ket noi voi sql
      ps = con.prepareStatement(query);
      ps.setInt(1, accountID);
      ps.executeUpdate();
    } catch (Exception e) {
    }
  }
}
