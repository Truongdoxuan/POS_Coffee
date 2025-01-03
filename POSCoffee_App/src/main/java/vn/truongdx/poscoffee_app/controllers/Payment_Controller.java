package vn.truongdx.poscoffee_app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import vn.truongdx.poscoffee_app.utility.Stage_Standard;

import java.util.Objects;

public class Payment_Controller {
  //khai báo biến
  @FXML
  Button btn_cash, btn_banking, btn_momo, btn_close, btn_confirm;
  @FXML
  StackPane contentPage;
  //hàm thực hiện chức năng
  @FXML
  public void initialize() {
    loadPane_Cash();
  }
  public void loadPane(String fxmlFile) {
    try {
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
  public void close_Modal(ActionEvent event) {
    Stage stage = (Stage) btn_close.getScene().getWindow();
    stage.close();
  }
  public void Confirm(ActionEvent event) {

  }
}
