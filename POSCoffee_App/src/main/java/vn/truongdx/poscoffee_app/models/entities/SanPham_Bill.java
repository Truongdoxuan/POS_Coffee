package vn.truongdx.poscoffee_app.models.entities;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SanPham_Bill {

  private final SimpleStringProperty tenSanPham;
  private final SimpleStringProperty size;
  private final ObservableList<String> topings;
  private final SimpleStringProperty da;
  private final SimpleStringProperty tra;
  private final SimpleStringProperty ngot;
  private final SimpleIntegerProperty soluong;
  private final SimpleDoubleProperty donGia;

  // Constructor
  public SanPham_Bill(String tenSanPham, String size, ObservableList<String> topings, String da, String tra, String ngot, int soluong, double donGia) {
    this.tenSanPham = new SimpleStringProperty(tenSanPham);
    this.size = new SimpleStringProperty(size);
    this.topings = topings != null ? topings : FXCollections.observableArrayList();
    this.da = new SimpleStringProperty(da);
    this.tra = new SimpleStringProperty(tra);
    this.ngot = new SimpleStringProperty(ngot);
    this.soluong = new SimpleIntegerProperty(soluong);
    this.donGia = new SimpleDoubleProperty(donGia);
  }

  // Getter and Setter methods
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
    return topings;
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

  public double getDonGia() {
    return donGia.get();
  }

  public void setDonGia(double donGia) {
    this.donGia.set(donGia);
  }
}
