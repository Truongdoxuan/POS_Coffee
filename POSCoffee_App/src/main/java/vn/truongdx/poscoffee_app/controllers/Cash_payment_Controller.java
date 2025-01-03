package vn.truongdx.poscoffee_app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Stack;

public class Cash_payment_Controller {
  //khai báo biến
  @FXML
  TextField txt_totalbill, txt_cashSend, txt_cashResend;
  @FXML
  Button btn_openKet, btn_close;

  //hàm thực hiện chức năng
  public void openKet(ActionEvent event) {
    Alert info = new Alert(Alert.AlertType.INFORMATION);
    info.setTitle("Thông tin");
    info.setHeaderText("Đã mở két");
    info.showAndWait();
  }
  public void close_Modal(ActionEvent event) {
    Stage modalStage = (Stage) btn_close.getScene().getWindow();
    modalStage.close();
  }
}
