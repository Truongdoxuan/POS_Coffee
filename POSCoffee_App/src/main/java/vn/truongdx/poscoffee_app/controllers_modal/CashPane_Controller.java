package vn.truongdx.poscoffee_app.controllers_modal;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import vn.truongdx.poscoffee_app.utils.DataStorage;

public class CashPane_Controller {
  //khai báo biến
  @FXML
  TextField txt_totalbill, txt_cashSend, txt_cashResend;
  private Double totalBill;

  @FXML
  public void initialize() {
    this.totalBill = DataStorage.getTotalBill();
    txt_totalbill.setText(String.format("%,.0f", totalBill));
    txt_cashSend.textProperty().addListener((observableValue, oldValue, newValue) -> MoneyResend() );
  }

  public void MoneyResend() {
    try {
      totalBill = Double.parseDouble(txt_totalbill.getText().replace(",", ""));
      double cashSend = Double.parseDouble(txt_cashSend.getText().replace(",", ""));

      // Tính tiền trả lại
      double reSend = cashSend - totalBill;
      txt_cashResend.setText(String.format("%,.0f", reSend));
    } catch (NumberFormatException e) {
      // Xử lý trường hợp giá trị không hợp lệ
      txt_cashResend.setText("Giá trị không hợp lệ");
    }
  }
}
