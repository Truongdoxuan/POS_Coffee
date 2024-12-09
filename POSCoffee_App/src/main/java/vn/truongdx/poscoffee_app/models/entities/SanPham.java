package vn.truongdx.poscoffee_app.models.entities;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SanPham {
  private SimpleStringProperty tenSanPham;
  private SimpleStringProperty size;
  private SimpleListProperty<String> topings; // Dùng SimpleListProperty
  private SimpleStringProperty da, tra, ngot;
  private SimpleIntegerProperty soluong;

  // Constructor
  public SanPham(String tensanpham, String size, ObservableList<String> topings, String da, String tra, String ngot, int soluong) {
    this.tenSanPham = new SimpleStringProperty(tensanpham);
    this.size = new SimpleStringProperty(size);
    this.topings = new SimpleListProperty<>(topings);
    this.da = new SimpleStringProperty(da);
    this.tra = new SimpleStringProperty(tra);
    this.ngot = new SimpleStringProperty(ngot);
    this.soluong = new SimpleIntegerProperty(soluong);
  }

  public String getTenSanPham() {
    return tenSanPham.get();
  }

  public void setTenSanPham(String tenSanPham) {
    this.tenSanPham.set(tenSanPham);
  }

  public String getSize() {
    return size.get();
  }

  public void setSize(String size) {
    this.size.set(size);
  }

  public ObservableList<String> getTopings() {
    return topings.get();
  }

  public void setTopings(ObservableList<String> topings) {
    this.topings.setAll(topings);
  }

  public String getDa() {
    return da.get();
  }

  public void setDa(String da) {
    this.da.set(da);
  }

  public String getTra() {
    return tra.get();
  }

  public void setTra(String tra) {
    this.tra.set(tra);
  }

  public String getNgot() {
    return ngot.get();
  }

  public void setNgot(String ngot) {
    this.ngot.set(ngot);
  }

  public int getSoluong() {
    return soluong.get();
  }

  public void setSoluong(int soluong) {
    this.soluong.set(soluong);
  }

  public String infoSP() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(size.get()).append("\n");
    stringBuilder.append("Đá: ").append(da.get()).append("\n");
    stringBuilder.append("Trà: ").append(tra.get()).append("\n");
    stringBuilder.append("Ngọt: ").append(ngot.get()).append("\n");
    stringBuilder.append("Toppings: ").append(topings.get()).append("\n");
    stringBuilder.append("x ").append(soluong.get()).append("\n");
    return stringBuilder.toString();
  }
}
