package vn.truongdx.poscoffee_app.utility;

import javafx.application.Platform;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javafx.geometry.Rectangle2D;

public class Stage_Standard {

  // Phương thức căn giữa cửa sổ chính
  public static void CenterStage(Stage stage, double width, double height) {
    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    double centerX = (screenBounds.getWidth() - width) / 2;
    double centerY = (screenBounds.getHeight() - height) / 2;
    stage.setX(centerX);
    stage.setY(centerY);
  }

  // Phương thức ẩn thanh tiêu đề
  public static void removeTitleBar(Stage stage) {
    // Gọi trước khi cửa sổ được hiển thị
    if (!stage.isShowing()) {
      stage.initStyle(StageStyle.UNDECORATED);
    }
  }

  // Phương thức căn giữa cửa sổ modal trong cửa sổ chính
  public static void CenterModal(Stage mainStage, Stage modalStage) {
    // Cập nhật kích thước của modal
    modalStage.sizeToScene();

    // Đảm bảo rằng kích thước của modal đã được cập nhật
    Platform.runLater(() -> {
      // Lấy thông tin vị trí và kích thước của cửa sổ chính
      double mainStageX = mainStage.getX();
      double mainStageY = mainStage.getY();
      double mainStageWidth = mainStage.getWidth();
      double mainStageHeight = mainStage.getHeight();

      // Lấy thông tin vị trí và kích thước của cửa sổ modal
      double modalWidth = modalStage.getWidth();
      double modalHeight = modalStage.getHeight();

      // Đặt vị trí cửa sổ modal sao cho nó nằm giữa cửa sổ chính
      modalStage.setX(mainStageX + (mainStageWidth - modalWidth) / 2);
      modalStage.setY(mainStageY + (mainStageHeight - modalHeight) / 2);
    });
  }
}

