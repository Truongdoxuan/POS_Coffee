package vn.truongdx.poscoffee_app.controllers_modal;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import vn.truongdx.poscoffee_app.models.entities.BaoCao;
import vn.truongdx.poscoffee_app.models.entities.HoaDon;
import vn.truongdx.poscoffee_app.models.entities.SanPham;

import java.sql.Date;
import java.sql.Time;
import java.text.DecimalFormat;

public class ReportDoanhThu_Controller {

  @FXML
  private ComboBox<String> filterComboBox;
  @FXML
  private Button btn_close;
  @FXML
  private TextField dateTextField, monthTextField, txt_sum;
  @FXML
  private TableView<HoaDon> tb_report;
  @FXML
  private TableColumn<HoaDon, String> columnMaHD;
  @FXML
  private TableColumn<HoaDon, String> columnDate;
  @FXML
  private TableColumn<HoaDon, String> columnTime;
  @FXML
  private TableColumn<HoaDon, String> columnShift;
  @FXML
  private TableColumn<HoaDon, Double> columnRevenue;

  private BaoCao baoCao = new BaoCao();

  @FXML
  public void initialize() {
    filterComboBox.getSelectionModel().select(0);
    filterComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
      switch (newValue) {
        case "Ngày":
          dateTextField.setVisible(true);
          monthTextField.setVisible(false);
          break;
        case "Tháng":
          dateTextField.setVisible(false);
          monthTextField.setVisible(true);
          break;
        default:
          dateTextField.setVisible(false);
          monthTextField.setVisible(false);
          break;
      }
    });

    columnMaHD.setCellValueFactory(cellData -> {
      Integer maHD = cellData.getValue().getMaHoaDon();
      return new SimpleStringProperty(maHD != null ? maHD.toString() : "");
    });
    columnDate.setCellValueFactory(cellData -> {
      Date date = cellData.getValue().getNgayHoaDon();
      return new SimpleStringProperty(date != null ? date.toString() : "");
    });
    columnTime.setCellValueFactory(cellData -> {
      Time time = cellData.getValue().getTimeHoaDon();
      return new SimpleStringProperty(time != null ? time.toString() : "");
    });
    columnShift.setCellValueFactory(cellData ->
        new SimpleStringProperty(cellData.getValue().caLamviecProperty().get())
    );
    columnRevenue.setCellValueFactory(cellData -> cellData.getValue().tongTienProperty().asObject());
    columnRevenue.setCellFactory(col -> new TableCell<HoaDon, Double>(){
      @Override
      protected void updateItem(Double item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
          setText(null);
        } else {
          // Sử dụng DecimalFormat để thêm dấu phân cách mỗi 3 chữ số
          DecimalFormat format = new DecimalFormat("#,###");
          setText(format.format(item));  // Hiển thị giá trị với dấu phân cách
        }
      }
    });
  }

  @FXML
  public void generateReport(ActionEvent event) {
    String filterType = filterComboBox.getValue();
    String filterValue = "";

    switch (filterType) {
      case "Ngày":
        filterValue = dateTextField.getText();
        break;
      case "Tháng":
        filterValue = monthTextField.getText();
        break;
    }

    if (filterValue != null && !filterValue.isEmpty()) {
      ObservableList<HoaDon> reportList = FXCollections.observableArrayList();

      if ("Ngày".equals(filterType)) {
        reportList.addAll(baoCao.DailyReport(filterValue));
        totalDay(filterValue); //cập nhật doanh thu ngày
      }
      else if ("Tháng".equals(filterType)) {
        int month = Integer.parseInt(filterValue); // Chuyển đổi filterValue thành kiểu int
        reportList.addAll(baoCao.MonthlyReport(month));
        totalMonth(month); // Cập nhật doanh thu tháng
      }
      tb_report.setItems(reportList);
    } else {
      System.out.println("Vui lòng nhập giá trị cho bộ lọc");
    }
  }

  public void totalDay(String date) {
    double totalRevenue = baoCao.getTotalRevenueByDate(date);
    DecimalFormat format = new DecimalFormat("#,###");
    txt_sum.setText(format.format(totalRevenue));
  }
  public void totalMonth(int month) {
    double totalRevenue = baoCao.getTotalRevenueByMonth(month);
    DecimalFormat format = new DecimalFormat("#,###");
    txt_sum.setText(format.format(totalRevenue));
  }
  public void Close_Modal() {
    Stage stage = (Stage) btn_close.getScene().getWindow();
    stage.close();
  }
}
