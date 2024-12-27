package vn.truongdx.poscoffee_app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import vn.truongdx.poscoffee_app.models.entities.SanPham;
import vn.truongdx.poscoffee_app.models.entities.SanPham_Bill;

public class ChangeSL_Controller {
  @FXML
  private Button btn_cong, btn_tru, btn_changeSl;
  @FXML
  private TextField txt_sl, txt_tensp;

  private int soluong = 1;
  private String tensp;
  private double giasp;
  private double thanhtien;
  private SanPham_Bill selectedProduct; // Sản phẩm được chọn trên bảng hóa đơn

  public void setSelectedProduct(SanPham_Bill selectedProduct) {
    this.selectedProduct = selectedProduct;
    this.tensp = selectedProduct.getTenSanPham();
    this.giasp = selectedProduct.getDonGia();
    this.soluong = selectedProduct.getSoluong(); // Lấy số lượng hiện tại
    this.thanhtien = selectedProduct.getDonGia();

    txt_tensp.setText(tensp);
    txt_sl.setText(String.valueOf(soluong));

    txt_tensp.setEditable(false);
    txt_tensp.setMouseTransparent(true);
    txt_tensp.setFocusTraversable(false);
  }


  public void initialize() {
    txt_sl.setText(String.valueOf(soluong));
  }

  @FXML
  public void tangSl() {
    soluong++;
    txt_sl.setText(String.valueOf(soluong));
  }

  @FXML
  public void giamSl() {
    if (soluong > 1) {
      soluong--;
      txt_sl.setText(String.valueOf(soluong));
    }
  }

  @FXML
  public void changeSL(ActionEvent event) {
    try {
      int soLuongMoi = Integer.parseInt(txt_sl.getText());
      double thanhtienMoi = soLuongMoi * selectedProduct.getDonGia();
      selectedProduct.setSoluong(soLuongMoi); // Cập nhật số lượng mới vào đối tượng
      selectedProduct.setThanhTien(thanhtienMoi);
      System.out.println("Số lượng đã thay đổi thành: " + soLuongMoi);
      System.out.println("Đơn giá đã thay đổi thành: " + thanhtienMoi);
      closeModal(event);
    } catch (NumberFormatException e) {
      System.out.println("Số lượng không hợp lệ!");
    }
  }

  public void closeModal(ActionEvent event) {
    Stage stage = (Stage) btn_changeSl.getScene().getWindow();
    stage.close();
  }
}
