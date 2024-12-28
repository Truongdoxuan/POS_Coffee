package vn.truongdx.poscoffee_app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import vn.truongdx.poscoffee_app.utility.Stage_Standard;

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
    Alert confirmLogout = new Alert(Alert.AlertType.CONFIRMATION);
    confirmLogout.setTitle("Xác nhận");
    confirmLogout.setHeaderText("Đăng xuất POS ?");
    Button btn_ok = (Button) confirmLogout.getDialogPane().lookupButton(ButtonType.OK);
    Button btn_cancel = (Button) confirmLogout.getDialogPane().lookupButton(ButtonType.CANCEL);
    if (btn_ok != null) {
      btn_ok.setText("Đồng ý");
    }
    if (btn_cancel != null) {
      btn_cancel.setText("Không");
    }
    confirmLogout.showAndWait().ifPresent(respone -> {
      if (respone == ButtonType.OK) {
        try {
          //trở về trang login
          Stage Orther_Stage = (Stage) btn_logout.getScene().getWindow();
          FXMLLoader fxmlLoader = new FXMLLoader(OrtherFunction_Controller.class.getResource("/vn/truongdx/poscoffee_app/fxml/login_page.fxml"));
          Parent root = fxmlLoader.load();
          Stage loginStage = new Stage();
          loginStage.setScene(new Scene(root));
          Stage_Standard.removeTitleBar(loginStage);
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
    });
  }
  public void close_Modal(ActionEvent event) {
    Stage stage = (Stage) btn_close.getScene().getWindow();
    stage.close();
  }
}
