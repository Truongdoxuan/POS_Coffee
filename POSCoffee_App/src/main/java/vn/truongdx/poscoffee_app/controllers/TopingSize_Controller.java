package vn.truongdx.poscoffee_app.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import vn.truongdx.poscoffee_app.models.entities.SanPham;

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
  TextField tf_soluong;
  @FXML
  Button btn_close, btn_add, btn_cong, btn_tru;


  //chọn 1 radio duy nhất trong group đó
  ToggleGroup radioSize;
  ToggleGroup radioTra;
  ToggleGroup radioDa;
  ToggleGroup radioNgot;

  //hàm thực hiện chức năng
  int soluong = 1; //mặc định 1 món 1 ly
  public void initialize() {
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
    //lấy checked của radio button size, đá, trà, ngọt v.v
    RadioButton selectedSize = (RadioButton) radioSize.getSelectedToggle();
    String size = selectedSize.getText();

    RadioButton selectedDa = (RadioButton) radioDa.getSelectedToggle();
    String luongDa = selectedDa.getText();

    RadioButton selectedTra = (RadioButton) radioTra.getSelectedToggle();
    String luongTra = selectedTra.getText();

    RadioButton selectedNgot = (RadioButton) radioNgot.getSelectedToggle();
    String luongNgot = selectedNgot.getText();

    //lấy checked toppings nếu có
    List<String> toppings = new ArrayList<>();
    if (cb_thachdua.isSelected()) toppings.add("Thạch dừa");
    if (cb_thachsocola.isSelected()) toppings.add("Thạch socola");
    if (cb_tranchau.isSelected()) toppings.add("Trân châu");
    if (cb_banhflan.isSelected()) toppings.add("Bánh flan");

    //lấy số lượng
    int soLuong = Integer.parseInt(tf_soluong.getText());

    //lấy tên sp
    String namesp = orderController.getNameSp();
    if (orderController == null) {
      System.out.println("OrderController is not initialized.");
      return; // Dừng lại nếu không thể lấy orderController
    }

    //tạo đối tượng mới
    ObservableList<String> observableToppings = FXCollections.observableArrayList(toppings);
    SanPham selectedsanPham = new SanPham(namesp, size, observableToppings, luongDa, luongTra, luongNgot, soLuong);

    orderController.billList.add(selectedsanPham);
    closeModal(event);
  }
  public void closeModal(ActionEvent event) {
    Stage stage = (Stage) btn_close.getScene().getWindow();
    stage.close();
  }
}
