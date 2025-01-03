package vn.truongdx.poscoffee_app.controllers_modal;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import vn.truongdx.poscoffee_app.models.entities.BaoCao;
import vn.truongdx.poscoffee_app.models.entities.HoaDon;

import java.sql.Date;

public class ReportDoanhThu_Controller {

  @FXML
  private ComboBox<String> filterComboBox;
  @FXML
  private TextField dateTextField, monthTextField;
  @FXML
  private TableView<HoaDon> tb_report;
  @FXML
  private TableColumn<HoaDon, String> columnDate;
  @FXML
  private TableColumn<HoaDon, String> columnShift;
  @FXML
  private TableColumn<HoaDon, Double> columnRevenue;

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

    columnDate.setCellValueFactory(cellData -> {
      Date date = cellData.getValue().getNgayHoaDon();
      return new SimpleStringProperty(date != null ? date.toString() : "");
    });
    columnShift.setCellValueFactory(cellData ->
        new SimpleStringProperty(cellData.getValue().caLamviecProperty().get())
    );
    columnRevenue.setCellValueFactory(cellData -> cellData.getValue().tongTienProperty().asObject());
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
      BaoCao baoCao = new BaoCao();
      ObservableList<HoaDon> reportList = FXCollections.observableArrayList();

      if ("Ngày".equals(filterType)) {
        reportList.addAll(baoCao.DailyReport(filterValue));
      }
      else if ("Tháng".equals(filterType)) {
        reportList.addAll(baoCao.MonthlyReport(Integer.parseInt(filterValue)));
      }
      System.out.println("Filter Value: " + filterValue);
      System.out.println("Report List Size: " + reportList.size());
      for (HoaDon hoaDon : reportList) {
        System.out.println(hoaDon.getNgayHoaDon() + " - " + hoaDon.caLamviecProperty().get() + " - " + hoaDon.tongTienProperty().get());
      }
      tb_report.setItems(reportList);
    } else {
      System.out.println("Vui lòng nhập giá trị cho bộ lọc");
    }
  }
}
