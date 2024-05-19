package entity;

import java.util.Date;

public class Invoice {
  private int maHD;
  private Account account;
  private double tongGia;
  Date ngayXuat;

  public Invoice(int maHD, Account account, double tongGia, Date ngayXuat) {
    this.maHD = maHD;
    this.account = account;
    this.tongGia = tongGia;
    this.ngayXuat = ngayXuat;
  }

  public int getMaHD() {
    return maHD;
  }

  public void setMaHD(int maHD) {
    this.maHD = maHD;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public double getTongGia() {
    return tongGia;
  }

  public void setTongGia(double tongGia) {
    this.tongGia = tongGia;
  }

  public Date getNgayXuat() {
    return ngayXuat;
  }

  public void setNgayXuat(Date ngayXuat) {
    this.ngayXuat = ngayXuat;
  }

  @Override
  public String toString() {
    return "Invoice{" +
      "maHD=" + maHD +
      ", account=" + account +
      ", tongGia=" + tongGia +
      ", ngayXuat=" + ngayXuat +
      '}';
  }
}
