package vn.truongdx.poscoffee_app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vn.truongdx.poscoffee_app.utility.Stage_Standard;

import java.io.IOException;

public class MainApplication extends Application {
  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/login_page.fxml"));
    Scene scene = new Scene(fxmlLoader.load());
    Stage_Standard.removeTitleBar(stage);
    Stage_Standard.CenterStage(stage,600,400);
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }
}