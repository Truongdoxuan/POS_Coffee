package vn.truongdx.poscoffee_app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import vn.truongdx.poscoffee_app.utility.Stage_Standard;
import vn.truongdx.poscoffee_app.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login_Controller {
  //khai báo biến
  @FXML
  TextField txt_maCH, txt_matkhauVis;
  @FXML
  PasswordField txt_matkhau;
  @FXML
  Button btn_login;
  @FXML
  CheckBox cb_showpass;
  //hàm thực hiện chức năng
  public void Login(ActionEvent event) {
    String maCH = txt_maCH.getText();
    String matkhau = txt_matkhau.getText();
    if (maCH.isEmpty() || matkhau.isEmpty()) {
      Alert warning = new Alert(Alert.AlertType.WARNING);
      warning.setTitle("Cảnh báo");
      warning.setHeaderText("Mã cửa hàng hoặc mật khẩu không được để trống");
      warning.showAndWait();
    }
    else if (KiemTraLogin(maCH, matkhau)) {
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
        e.printStackTrace();
      }
    } else {
      Alert warning = new Alert(Alert.AlertType.WARNING);
      warning.setTitle("Cảnh báo");
      warning.setHeaderText("Mã cửa hàng hoặc mật khẩu không đúng");
      warning.showAndWait();
    }
  }

  private boolean KiemTraLogin(String tentaikhoan, String matkhau) {
    boolean isAuthenticated = false;
    Connection conn = DatabaseConnection.getConnection("posdatabase","root","");
    String query = "SELECT * FROM user WHERE tendn = ? AND matkhau = ?";
    try {
      PreparedStatement psmt = conn.prepareStatement(query);
      psmt.setString(1,tentaikhoan);
      psmt.setString(2,matkhau);

      ResultSet rs = psmt.executeQuery();
      if (rs.next()) {
        isAuthenticated = true;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return isAuthenticated;
  }

  public void ShowPassword(ActionEvent event) {
    if (cb_showpass.isSelected()) {
      txt_matkhauVis.setText(txt_matkhau.getText());
      txt_matkhauVis.setVisible(true);
      txt_matkhau.setVisible(false);
      return;
    }
    txt_matkhau.setText(txt_matkhau.getText());
    txt_matkhau.setVisible(true);
    txt_matkhauVis.setVisible(false); }
}
