package vn.truongdx.poscoffee_app.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import vn.truongdx.poscoffee_app.models.entities.SanPham_Bill;

import java.util.ArrayList;
import java.util.List;

public class TopingSize_Controller {
  //truyền đối tượng Order_controller
  private Order_Controller orderController;

  public void setOrderController(Order_Controller orderController) {
    this.orderController = orderController;
  }

  //khai báo biến
  @FXML
  RadioButton rd_sizeM, rd_sizeL;
  @FXML
  CheckBox cb_thachdua, cb_thachsocola, cb_tranchau, cb_banhflan;
  @FXML
  RadioButton rd_khda, rd_dabth, rd_itda;
  @FXML
  RadioButton rd_khtra, rd_trabth, rd_ittra, rd_nhieutra;
  @FXML
  RadioButton rd_khngot, rd_ngotbth, rd_itngot, rd_ngotnhieu;
  @FXML
  TextField tf_soluong, txt_tensp, txt_giasp;
  @FXML
  Button btn_close, btn_add, btn_cong, btn_tru;

  ObservableList<SanPham_Bill> billList;

  //chọn 1 radio duy nhất trong group đó
  ToggleGroup radioSize;
  ToggleGroup radioTra;
  ToggleGroup radioDa;
  ToggleGroup radioNgot;

  //hàm thực hiện chức năng
  int soluong = 1; //mặc định 1 món 1 ly
  public void initialize() {
    billList = FXCollections.observableArrayList();

    tf_soluong.setText(String.valueOf(soluong));
    radioSize = new ToggleGroup();
    rd_sizeM.setToggleGroup(radioSize);
    rd_sizeL.setToggleGroup(radioSize);
    //size M là mặc định
    rd_sizeM.setSelected(true);

    radioDa = new ToggleGroup();
    rd_dabth.setToggleGroup(radioDa);
    rd_itda.setToggleGroup(radioDa);
    rd_khda.setToggleGroup(radioDa);
    //đá bth là mặc định
    rd_dabth.setSelected(true);

    radioTra = new ToggleGroup();
    rd_trabth.setToggleGroup(radioTra);
    rd_ittra.setToggleGroup(radioTra);
    rd_khtra.setToggleGroup(radioTra);
    rd_nhieutra.setToggleGroup(radioTra);
    //trà bth là mặc định
    rd_trabth.setSelected(true);

    radioNgot = new ToggleGroup();
    rd_ngotbth.setToggleGroup(radioNgot);
    rd_itngot.setToggleGroup(radioNgot);
    rd_ngotnhieu.setToggleGroup(radioNgot);
    rd_khngot.setToggleGroup(radioNgot);
    //ngọt bth là mặc định
    rd_ngotbth.setSelected(true);
  }

  @FXML
  public void laytenSP(String tensp) {
    txt_tensp.setText(tensp);
    txt_tensp.setEditable(false);
    txt_tensp.setMouseTransparent(true);
    txt_tensp.setFocusTraversable(false);
  }

  @FXML
  public void laygiaSP(Double giasp) {
    txt_giasp.setText(String.format("%.0f",giasp));
    txt_giasp.setEditable(false);
    txt_giasp.setMouseTransparent(true);
    txt_giasp.setFocusTraversable(false);
  }
  @FXML
  public void tangSl() {
    soluong++;
    tf_soluong.setText(String.valueOf(soluong));
  }

  @FXML
  public void giamSl() {
    if (soluong > 1) {
      soluong--;
      tf_soluong.setText(String.valueOf(soluong));
    }
  }

  public void addintoBill(ActionEvent event) {
    String tenSanPham = txt_tensp.getText();
    int soLuong = 1;
    soLuong = Integer.parseInt(tf_soluong.getText());
    double donGia = Double.parseDouble(txt_giasp.getText());

    RadioButton selectedSize = (RadioButton) radioSize.getSelectedToggle();
    String size = selectedSize.getText();

    if ("L (+10.000)".equals(size)) {
      donGia += 10000;
    }
    RadioButton selectedDa = (RadioButton) radioDa.getSelectedToggle();
    String luongDa = selectedDa.getText();

    RadioButton selectedTra = (RadioButton) radioTra.getSelectedToggle();
    String luongTra = selectedTra.getText();

    RadioButton selectedNgot = (RadioButton) radioNgot.getSelectedToggle();
    String luongNgot = selectedNgot.getText();

    // Lấy checked toppings nếu có
    List<String> toppings = new ArrayList<>();
    if (cb_thachdua.isSelected()) toppings.add("Thạch dừa");
    if (cb_thachsocola.isSelected()) toppings.add("Thạch socola");
    if (cb_tranchau.isSelected()) toppings.add("Trân châu");
    if (cb_banhflan.isSelected()) toppings.add("Bánh flan");

    if (soluong > 1) {
      donGia *= soLuong;
    }

    SanPham_Bill sanPhamBill = new SanPham_Bill(
        tenSanPham, size, FXCollections.observableArrayList(toppings), luongDa, luongTra, luongNgot, soLuong, donGia);

    billList.add(sanPhamBill);

    if (orderController != null) {
      orderController.addSanPhamintoBill(sanPhamBill);
    }

    // Đóng cửa sổ modal
    closeModal(event);
  }

  public void closeModal(ActionEvent event) {
    Stage stage = (Stage) btn_close.getScene().getWindow();
    stage.close();
  }
}
