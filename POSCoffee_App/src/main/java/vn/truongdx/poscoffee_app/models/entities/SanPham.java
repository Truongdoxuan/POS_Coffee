package vn.truongdx.poscoffee_app.models.entities;

import java.util.List;

public class SanPham {
  private String size;
  private List<String> topings;
  private String da, tra, ngot;
  private int soluong;

  public SanPham(String size, List<String> topings, String da, String tra, String ngot, int soluong) {
    this.soluong = soluong;
    this.size = size;
    this.topings = topings;
    this.da = da;
    this.tra = tra;
    this.ngot = ngot;
  }

  public void setSoluong(int soluong) {
    this.soluong = soluong;
  }

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }

  public List<String> getTopings() {
    return topings;
  }

  public void setTopings(List<String> topings) {
    this.topings = topings;
  }

  public String getDa() {
    return da;
  }

  public void setDa(String da) {
    this.da = da;
  }

  public String getTra() {
    return tra;
  }

  public void setTra(String tra) {
    this.tra = tra;
  }

  public String getNgot() {
    return ngot;
  }

  public void setNgot(String ngot) {
    this.ngot = ngot;
  }

  public int getSoluong() {
    return soluong;
  }

  //in thông tin vào cột tên sản phẩm
  public String infoSP() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(size).append("\n");
    stringBuilder.append("Đá: ").append(da).append("\n");
    stringBuilder.append("Trà: ").append(tra).append("\n");
    stringBuilder.append("Ngọt: ").append(ngot).append("\n");
    stringBuilder.append("Toppings: ").append(topings).append("\n");
    stringBuilder.append("x ").append(soluong).append("\n");
    return stringBuilder.toString();
  }
}
