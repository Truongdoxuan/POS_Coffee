package vn.truongdx.poscoffee_app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import vn.truongdx.poscoffee_app.utility.Stage_Standard;

public class Payment_Controller {
  //khai báo biến
  @FXML
  Button btn_cash, btn_banking, btn_momo, btn_close;
  //hàm thực hiện chức năng
  public void pay_Cash(ActionEvent event){
    Stage Payment_Stage = (Stage) btn_cash.getScene().getWindow();
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vn/truongdx/poscoffee_app/fxml/cash_page.fxml"));
      Parent root = fxmlLoader.load();
      Stage cash_Stage = new Stage();
      cash_Stage.setScene(new Scene(root));
      Stage_Standard.removeTitleBar(cash_Stage);

      Stage_Standard.CenterModal(Payment_Stage, cash_Stage);
      cash_Stage.showAndWait();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
  public void pay_Banking(ActionEvent event) {

  }
  public void pay_Momo(ActionEvent event) {

  }
  public void close_Modal() {

  }
}
