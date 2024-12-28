package vn.truongdx.poscoffee_app.controllers;

import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import vn.truongdx.poscoffee_app.models.entities.SanPham;
import vn.truongdx.poscoffee_app.models.entities.SanPham_Bill;
import vn.truongdx.poscoffee_app.utility.Stage_Standard;
import vn.truongdx.poscoffee_app.utils.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

public class Order_Controller {
  //khai báo biến
  @FXML
  TextField txt_dayKD, txt_time, txt_workshifts, txt_poscode, txt_version;
  @FXML
  TextField txt_searchItem;
  @FXML
  Button btn_orther, btn_topingsize, btn_changesl;
  @FXML
  TableView<SanPham> tb_sanpham;
  @FXML
  TableColumn<SanPham, String> tc_sp;
  @FXML
  TableColumn<SanPham, Double> tc_giasp;
  @FXML
  TableColumn<SanPham, String> tc_dvi;
  @FXML
  TableView<SanPham_Bill> tb_bill;
  @FXML
  TableColumn<SanPham_Bill, String> tc_tensp;
  @FXML
  TableColumn<SanPham_Bill, Integer> tc_sl;
  @FXML
  TableColumn<SanPham_Bill, Double> tc_gia;
  @FXML
  TableColumn<SanPham_Bill, Double> tc_thanhtien;
  @FXML
  Label lb_quantity, lb_temporary, lb_coupon, lb_totalbill;

  //tạo ds rỗng lưu trữ sp dưới dạng String
  ObservableList<SanPham> sanphamList = FXCollections.observableArrayList();
  //tạo ds lưu trữ các sản phẩm được lọc khi tìm kiếm
  ObservableList<SanPham> timkiemSanPham = FXCollections.observableArrayList();
  //tạo ds lưu trữ các sản phẩm được thêm vào bill để thanh toán
  ObservableList<SanPham_Bill> billList = FXCollections.observableArrayList();
  //hàm thực hiện chương trình
  //kết nối database
  Connection connect = DatabaseConnection.getConnection("posdatabase","root","");

  //lấy list sản phẩm trong menu từ database về tableview
  private ObservableList<SanPham> getSanPhamFromDataBase() {
    ObservableList<SanPham> splist = FXCollections.observableArrayList();
    String sql = "SELECT tensp,dvtinh, giasp FROM danhmucsp";
    try (Connection conn = connect;
         PreparedStatement prepared = conn.prepareStatement(sql);
         ResultSet rs = prepared.executeQuery()) {
      while (rs.next()) {
        String tenSanPham = rs.getString("tensp");
        String dvTinh = rs.getString("dvtinh");
        double giaSanPham = rs.getDouble("giaSP");
        splist.add(new SanPham(tenSanPham,dvTinh,giaSanPham));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return splist;
  }
  //hàm khởi chạy màn hình trước khi thực hiện chức năng
  @FXML
  public void initialize() {
    //thiết lập các cột table view
    tc_sp.setCellValueFactory(new PropertyValueFactory<>("tenSP"));
    tc_dvi.setCellValueFactory(new PropertyValueFactory<>("donviTinh"));
    tc_giasp.setCellValueFactory(new PropertyValueFactory<>("giaSP"));
    tc_thanhtien.setCellValueFactory(new PropertyValueFactory<>("thanhTien"));

    tc_giasp.setCellFactory(col -> new TableCell<SanPham, Double>(){
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

    // Liên kết cột với thuộc tính của SanPham_Bill
    tc_tensp.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTenSanPham()));
    tc_sl.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getSoluong()).asObject());
    tc_gia.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getDonGia()).asObject());
    tc_thanhtien.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getThanhTien()).asObject());
    tc_gia.setCellFactory(col -> new TableCell<SanPham_Bill, Double>(){
      @Override
      protected void updateItem(Double item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
          setText(null);
        } else {
          // Sử dụng DecimalFormat để thêm dấu phân cách mỗi 3 chữ số
          DecimalFormat format = new DecimalFormat("#,###");
          setText(format.format(item));
        }
      }
    });
    tc_thanhtien.setCellFactory(col -> new TableCell<SanPham_Bill, Double>(){
      @Override
      protected void updateItem(Double item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
          setText(null);
        } else {
          // Sử dụng DecimalFormat để thêm dấu phân cách mỗi 3 chữ số
          DecimalFormat format = new DecimalFormat("#,###");
          setText(format.format(item));
        }
      }
    });
    tb_bill.setItems(billList);

    //đặt dữ liệu từ csdl vào listview
    sanphamList = getSanPhamFromDataBase();
    tb_sanpham.setItems(sanphamList);

    //mã cửa hàng và mã version (tự đặt)
    txt_poscode.setText("POS001");
    txt_version.setText("V1.0.0");

    txt_searchItem.textProperty().addListener((observable, oldValue, newValue) -> {
      timkiemSP(newValue);
    });

    // Thiết lập TableView để hiển thị dữ liệu từ billList
    tb_bill.setItems(billList);
  }

  //hàm tìm kiếm sản phẩm
  private void timkiemSP(String keyword) {
    timkiemSanPham.clear();
    for (SanPham sp : sanphamList) {
      if (sp.getTenSP().toLowerCase().contains(keyword.toLowerCase())) {
        timkiemSanPham.add(sp);
      }
    }
    tb_sanpham.setItems(timkiemSanPham);
  }

  //xóa sản phẩm khi đã thêm vào bill
  public void delete_Item(ActionEvent event) {
    SanPham_Bill selectedSP = tb_bill.getSelectionModel().getSelectedItem();
    if (selectedSP == null) {
      Alert warning = new Alert(Alert.AlertType.WARNING);
      warning.setTitle("Cảnh báo");
      warning.setHeaderText("Vui lòng chọn sản phẩm trước");
      warning.showAndWait();
    } else {
      Alert confirmDelete = new Alert(Alert.AlertType.CONFIRMATION);
      confirmDelete.setTitle("Thực hiện xóa");
      confirmDelete.setHeaderText("Xác nhận xóa: "+selectedSP.getTenSanPham());
      Button btn_ok = (Button) confirmDelete.getDialogPane().lookupButton(ButtonType.OK);
      Button btn_cancel = (Button) confirmDelete.getDialogPane().lookupButton(ButtonType.CANCEL);
      if (btn_ok != null) {
        btn_ok.setText("Xóa");
      }
      if (btn_cancel != null) {
        btn_cancel.setText("Hủy");
      }
      confirmDelete.showAndWait().ifPresent(respone -> {
        if (respone == ButtonType.OK) {
          tb_bill.getItems().remove(selectedSP);
          updateTotalBill();
        }
      });
    }
  }
  public void changeSL(ActionEvent event) {
    SanPham_Bill selectedSP = tb_bill.getSelectionModel().getSelectedItem();
    Stage Order_Stage = (Stage) btn_changesl.getScene().getWindow();
    if (selectedSP == null) {
      Alert warning = new Alert(Alert.AlertType.WARNING);
      warning.setTitle("Cảnh báo");
      warning.setHeaderText("Vui lòng chọn sản phẩm trước");
      warning.showAndWait();
    }
    else {
      try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vn/truongdx/poscoffee_app/fxml/changeSL_page.fxml"));
        Parent root = fxmlLoader.load();
        // Truyền đối tượng sản phẩm vào controller
        ChangeSL_Controller controller = fxmlLoader.getController();
        controller.setSelectedProduct(selectedSP);
        Stage changeSL_Stage = new Stage();
        changeSL_Stage.setScene(new Scene(root));
        Stage_Standard.removeTitleBar(changeSL_Stage);
        Stage_Standard.CenterModal(Order_Stage,changeSL_Stage);
        changeSL_Stage.showAndWait();
        //cập nhật lại bill
        tb_bill.refresh();
        //cập nhật tổng thành tiền
        updateTotalBill();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
  //hủy giao dịch
  public void cancel_Bill(ActionEvent event) {
    if (billList.isEmpty()) {
      Alert warning = new Alert(Alert.AlertType.WARNING);
      warning.setTitle("Cảnh báo");
      warning.setHeaderText("Không có giao dịch nào đang thực hiện ");
      warning.showAndWait();
    }
    else {
     Alert confirmDelete = new Alert(Alert.AlertType.CONFIRMATION);
     confirmDelete.setTitle("Xác nhận");
     confirmDelete.setHeaderText("Hủy giao dịch hiện tại ?");
     Button btn_ok = (Button) confirmDelete.getDialogPane().lookupButton(ButtonType.OK);
     Button btn_cancel = (Button) confirmDelete.getDialogPane().lookupButton(ButtonType.CANCEL);
     if (btn_ok != null) {
       btn_ok.setText("Hủy");
     }
     if (btn_cancel != null) {
       btn_cancel.setText("Không");
     }
     confirmDelete.showAndWait().ifPresent(respone -> {
       if (respone == ButtonType.OK) {
          billList.clear();
          updateTotalBill();
       }
     });
    }


  }
  //button mở modal các chức năng khác
  public void orther_Functions() {
    Stage Order_Stage = (Stage) btn_orther.getScene().getWindow();
    //mở modal chức năng khác
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vn/truongdx/poscoffee_app/fxml/ortherFunctions_page.fxml"));
      Parent root = fxmlLoader.load();
      Stage ortherStage = new Stage();
      ortherStage.setScene(new Scene(root));
      Stage_Standard.removeTitleBar(ortherStage);

      // Căn giữa cửa sổ modal trong cửa sổ chính
      Stage_Standard.CenterModal(Order_Stage, ortherStage);
      ortherStage.showAndWait();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
  //báo cáo nhanh
  public void fast_Report(ActionEvent event) {

  }
  //thanh toán hóa đơn
  public void pay_Bill(ActionEvent event) {

  }

  //ghi chú vào sản phẩm
  public void Voucher(ActionEvent event) {

  }
  public void start_workingShifts(ActionEvent event) {

  }
  //thay đổi kích cỡ sản phẩm, thêm toping
  public void change_SizeToping(ActionEvent event) {
    SanPham selectedSP = tb_sanpham.getSelectionModel().getSelectedItem();
    Stage Order_Stage = (Stage) btn_topingsize.getScene().getWindow();

    // Chọn 1 sản phẩm từ TableView
    if (selectedSP == null) {
      Alert warning = new Alert(Alert.AlertType.WARNING);
      warning.setTitle("Cảnh báo");
      warning.setHeaderText("Vui lòng chọn sản phẩm trước");
      warning.showAndWait();
    } else {
      try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vn/truongdx/poscoffee_app/fxml/topingsize_page.fxml"));
        Parent root = fxmlLoader.load();

        TopingSize_Controller topingSizeController = fxmlLoader.getController();
        topingSizeController.setOrderController(this);

        topingSizeController.laytenSP(selectedSP.getTenSP());
        topingSizeController.laygiaSP(selectedSP.getGiaSP());

        Stage changeSize_Stage = new Stage();
        changeSize_Stage.setScene(new Scene(root));
        Stage_Standard.removeTitleBar(changeSize_Stage);

        // Căn giữa cửa sổ modal trong cửa sổ chính
        Stage_Standard.CenterModal(Order_Stage, changeSize_Stage);

        changeSize_Stage.showAndWait();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public void addSanPhamintoBill(SanPham_Bill sanphamBill) {
    billList.add(sanphamBill);
    tb_bill.setItems(billList);
    updateTotalBill();
  }

  public void fastAddintoBill(ActionEvent event) {
    SanPham selectedSP = tb_sanpham.getSelectionModel().getSelectedItem();
    String tensp = tb_sanpham.getSelectionModel().getSelectedItem().getTenSP();
    Double giasp = tb_sanpham.getSelectionModel().getSelectedItem().getGiaSP();
    if (selectedSP == null) {
      Alert warning = new Alert(Alert.AlertType.WARNING);
      warning.setTitle("Cảnh báo");
      warning.setHeaderText("Vui lòng chọn sản phẩm trước");
      warning.showAndWait();
    } else {
      SanPham_Bill sanPhamBill = new SanPham_Bill(tensp,null,null,null,null,null,1,giasp,giasp);
      billList.add(sanPhamBill);
      tb_bill.setItems(billList);
      updateTotalBill();
    }
  }
  public void updateTotalBill() {
    int SL = billList.size();
    int totalSL = billList.stream()
        .mapToInt(SanPham_Bill::getSoluong)
        .sum();
    double totalTemp = billList.stream()
        .mapToDouble(SanPham_Bill::getThanhTien)
        .sum();

    double discount = 0.0;
    double totalBill = 0.000;
    totalBill = totalTemp - discount;

    lb_quantity.setText(String.valueOf(totalSL));
    lb_temporary.setText(String.format("%,.0f", totalTemp));
    lb_coupon.setText(String.format("%.0f", discount));
    lb_totalbill.setText(String.format("%,.0f", totalBill));
  }
}