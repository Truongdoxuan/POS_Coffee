package vn.truongdx.poscoffee_app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vn.truongdx.poscoffee_app.utils.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;

public class MainApplication extends Application {
  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/login_page.fxml"));
    Scene scene = new Scene(fxmlLoader.load());
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch();
//    Connection connection = DatabaseConnection.getConnection("posdatabase","root","");
//    System.out.println(connection);
  }
}