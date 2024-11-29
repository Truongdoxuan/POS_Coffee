package vn.truongdx.poscoffee_app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.*;

public class OrtherFunction_Controller {
  //khai báo biến
  @FXML
  Button btn_logout, btn_close;
  //hàm thực hiện chức năng
  public void end_Shifts(ActionEvent event) {

  }
  public void end_Day(ActionEvent event) {

  }
  public void Logout(ActionEvent event) {
    try {
      //trở về trang login
      FXMLLoader fxmlLoader = new FXMLLoader(OrtherFunction_Controller.class.getResource("login.fxml"));
      Parent root = fxmlLoader.load();
      Stage loginStage = new Stage();
      loginStage.setScene(new Scene(root));
      loginStage.show();

      //đóng các trang đang mở
      Stage.getWindows().stream()
          .filter(window -> window instanceof Stage && window != loginStage)
          .forEach(window -> ((Stage)window).close());
      Stage stage = (Stage) btn_close.getScene().getWindow();
      stage.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
  public void close_Modal(ActionEvent event) {
    Stage stage = (Stage) btn_close.getScene().getWindow();
    stage.close();
  }
}
