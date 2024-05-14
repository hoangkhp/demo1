package dao;

import context.DBContext;
import entity.Category;
import entity.Product;

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
    String query = "select * from account where Username = ? and Password = ?";
    try {
      conn = new DBContext().getConnection(); //ket noi vs SQl
      ps = conn.prepareStatement(query);
      ps.setString(1, user);
      ps.setString(2, pass);
      rs = ps.excuteQuery();
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
  public Account checkAccount(String user) {
    String query = "select * from account where Username = ? and Password = ?";
    try {
      conn = new DBContext().getConnection(); //ket noi vs SQl
      ps = conn.prepareStatement(query);
      ps.setString(1, user);
      ps.setString(2, pass);
      rs = ps.excuteQuery();
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
  public void signup(String user, String pass){
    String query = "insert into account\n" + "values(?,?,0,0)";
    try {
      conn = new DBContext().getConnection(); //ket noi vs SQl
      ps = conn.prepareStatement(query);
      ps.setString(1, user);
      ps.setString(2, pass);
      rs = ps.excuteUpdate();
    } catch (Exception e){
    }
  }

  public static void main(String[] args) {
    String categoryID = "1";
    DAO dao = new DAO();
    List<Product> productList = dao.getProductByCategory(categoryID);
    for(Product o : productList){
      System.out.println(o);
    }
  }

}
