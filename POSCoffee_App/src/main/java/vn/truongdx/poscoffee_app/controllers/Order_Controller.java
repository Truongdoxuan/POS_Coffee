package vn.truongdx.poscoffee_app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Order_Controller {
  //khai báo biến
  @FXML
  TextField txt_dayKD, txt_time, txt_workshifts, txt_poscode, txt_version;
  @FXML
  TextField txt_searchItem;
  @FXML
  Button btn_orther, btn_topingsize;

  //hàm thực hiện chương trình
  //hàm khởi chạy màn hình trước khi thực hiện chức năng
  @FXML
  public void initialize() {
    //mã cửa hàng và mã version (tự đặt)
    txt_poscode.setText("POS001");
    txt_version.setText("V1.0.0");
  }
  //xóa sản phẩm khi đã thêm vào bill
  public void delete_Item(ActionEvent event) {

  }
  //khuyến mãi
  public void Coupon(ActionEvent event) {

  }
  //hủy giao dịch
  public void cancel_Bill(ActionEvent event) {

  }
  //button mở modal các chức năng khác
  public void orther_Functions() {
    //mở modal chức năng khác
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vn/truongdx/poscoffee_app/fxml/ortherFunctions_page.fxml"));
      Parent root = fxmlLoader.load();
      Stage ortherSatge = new Stage();
      ortherSatge.setScene(new Scene(root));
      ortherSatge.showAndWait(); //thao tác only modal này đến khi được đóng lại
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
  //báo cáo nhanh
  public void fast_Report(ActionEvent event) {

  }
  //thanh toán hóa đơn
  public void pay_Bill(ActionEvent event) {

  }
  //tìm kiếm sản phẩm
  public void search_Item(ActionEvent event) {

  }
  //ghi chú vào sản phẩm
  public void note_Item(ActionEvent event) {

  }
  //bắt đầu ca làm việc
  public void start_Day(ActionEvent event) {

  }
  public void start_workingShifts(ActionEvent event) {

  }
  //thay đổi kích cỡ sản phẩm, thêm toping
  public void change_SizeToping(ActionEvent event) {

  }
}
