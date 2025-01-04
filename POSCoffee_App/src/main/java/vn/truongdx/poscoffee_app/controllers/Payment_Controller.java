package vn.truongdx.poscoffee_app.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import vn.truongdx.poscoffee_app.models.entities.HoaDon;
import vn.truongdx.poscoffee_app.models.entities.SanPham_Bill;
import vn.truongdx.poscoffee_app.utils.DataStorage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Payment_Controller {
  //khai báo biến
  @FXML
  Button btn_cash, btn_banking, btn_momo, btn_close, btn_confirm;
  @FXML
  StackPane contentPage;
  private double totalBill;
  private static Payment_Controller instance;
  private Order_Controller orderController;

  public Payment_Controller() {
    instance = this;
  }

  public static Payment_Controller getInstance() {
    return instance;
  }

  public void setOrderController(Order_Controller orderController) {
    this.orderController = orderController;
  }

  public Order_Controller getOrderController() {
    return orderController;
  }


  //hàm thực hiện chức năng
  @FXML
  public void initialize() {
    this.totalBill = DataStorage.getTotalBill();
    loadPane_Cash();
  }
  public void loadPane(String fxmlFile) {
    try {
      FXMLLoader loader = new FXMLLoader((Objects.requireNonNull(getClass().getResource(fxmlFile))));
      StackPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));
      contentPage.getChildren().clear();
      contentPage.getChildren().add(pane);
    } catch (Exception e) {
      System.err.println("Lỗi khi tải file FXML: " + fxmlFile);
      e.printStackTrace();
    }
  }

  public void loadPane_Cash() {
    loadPane("/vn/truongdx/poscoffee_app/fxml/cash_pane.fxml");
  }


  public void loadPane_Banking() {
    loadPane("/vn/truongdx/poscoffee_app/fxml/banking_pane.fxml");
  }
  public void loadPane_Momo() {
    loadPane("/vn/truongdx/poscoffee_app/fxml/momo_pane.fxml");
  }
  public void Confirm(ActionEvent event) {
    //lấy ngày giờ hiện tại
    LocalTime currentTime = LocalTime.now();
    LocalDate currentDate = LocalDate.now();

    LocalTime start = LocalTime.of(6, 30);
    LocalTime end = LocalTime.of(15, 0);

    String shift = (currentTime.isAfter(start) && currentTime.isBefore(end))
        ? "Sáng" : "Tối";

    // Tạo hóa đơn
    HoaDon hoaDon = new HoaDon();
    int maHoadon = hoaDon.createHoaDon(currentDate, currentTime, shift, totalBill);

    Alert info = new Alert(Alert.AlertType.INFORMATION);
    info.setTitle("Thông tin");
    info.setHeaderText("Thanh toán thành công");
    info.showAndWait().ifPresent(respone -> {
      if (respone == ButtonType.OK) {
        Stage stage = (Stage) btn_close.getScene().getWindow();
        Payment_Controller.getInstance().getOrderController().cleanBillAfterUpdate();
        stage.close();
      }
    });
  }

  public void close_Modal(ActionEvent event) {
    Stage stage = (Stage) btn_close.getScene().getWindow();
    stage.close();
  }
}
