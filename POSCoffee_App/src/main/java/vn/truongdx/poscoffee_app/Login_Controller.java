package vn.truongdx.poscoffee_app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
      FXMLLoader fxmlLoader = new FXMLLoader(Login_Controller.class.getResource("order_page.fxml"));
      Parent root = fxmlLoader.load();
      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
