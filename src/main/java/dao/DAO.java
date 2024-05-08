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
  public List<Product> getAllProduct(){
    List<Product> list = new ArrayList<Product>();
    String query = "select * from Products";
    try{
      con = new DBContext().getConnection(); //mo ket noi voi sql server
      ps = con.prepareStatement(query);//Nem cau lenh query sang sqlServer
      rs = ps.executeQuery();
      while(rs.next()){
        list.add(new Product(rs.getInt(1),
          rs.getString(2),
          rs.getString(3),
          rs.getDouble(4),
          rs.getString(5),
          rs.getString(6)));
      }
    } catch (Exception e){

    }
    return list;
  }

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

  public static void main(String[] args) {
    DAO dao = new DAO();
    List<Product> list = dao.getAllProduct();//dao goi den ham getAllProduct de lay du lieu dua vao list
    List<Category> listC = dao.getAllCategory();
    for(Product p : list){
      System.out.println(p);//in ra tat ca san pham co trong db
    }
    for(Category o : listC){
      System.out.println(o);
    }
  }
}
