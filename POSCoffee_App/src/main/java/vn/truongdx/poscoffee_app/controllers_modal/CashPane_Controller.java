package vn.truongdx.poscoffee_app.controllers_modal;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CashPane_Controller {
  //khai báo biến
  @FXML
  TextField txt_totalbill, txt_cashSend, txt_cashResend;

  public void MoneyResend() {
    try {
      double totalbill = Double.parseDouble(txt_totalbill.getText());
      double cashSend = Double.parseDouble(txt_cashSend.getText());

      double reSend = cashSend - totalbill;
      txt_cashResend.setText(String.format("%,.0f",reSend));
    } catch (Exception e) {
      txt_cashResend.setText("Giá trị không hợp lệ");
    }
  }
  @FXML
  public void initialize() {
    txt_totalbill.textProperty().addListener((observable, oldValue, newValue) -> MoneyResend());
    txt_cashSend.textProperty().addListener((observableValue, oldValue, newValue) -> MoneyResend() );
  }
}
