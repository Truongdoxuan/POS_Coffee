package vn.truongdx.poscoffee_app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import vn.truongdx.poscoffee_app.utility.Stage_Standard;

public class Login_Controller {
  //khai báo biến
  @FXML
  TextField txt_maCH, txt_matkhau;
  @FXML
  Button btn_login;
  //hàm thực hiện chức năng
  public void Login(ActionEvent event) {
    //onclick button chuyển đến trang order
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(Login_Controller.class.getResource("/vn/truongdx/poscoffee_app/fxml/order_page.fxml"));
      Parent root = fxmlLoader.load();
      Stage Order_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      Scene scene = new Scene(root);
      Stage_Standard.removeTitleBar(Order_stage);
      Stage_Standard.CenterStage(Order_stage,1056,698);
      Order_stage.setScene(scene);
      Order_stage.show();
    } catch (Exception e) {
      //nếu lỗi in lỗi ra terminal
      e.printStackTrace();
    }

  }
}
