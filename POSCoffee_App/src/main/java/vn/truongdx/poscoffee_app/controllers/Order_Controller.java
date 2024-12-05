package vn.truongdx.poscoffee_app.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import vn.truongdx.poscoffee_app.utils.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Order_Controller {
  //khai báo biến
  @FXML
  TextField txt_dayKD, txt_time, txt_workshifts, txt_poscode, txt_version;
  @FXML
  TextField txt_searchItem;
  @FXML
  Button btn_orther, btn_topingsize;
  @FXML
  ListView<String> lv_sanpham;
  //tạo ds rỗng lưu trữ sp dưới dạng String
  ObservableList<String> sanphamList = FXCollections.observableArrayList();
  //tạo ds lưu trữ các sản phẩm được lọc khi tìm kiếm
  ObservableList<String> timkiemSanPham = FXCollections.observableArrayList();

  //hàm thực hiện chương trình
  //kết nối database
  Connection connect = DatabaseConnection.getConnection("posdatabase","root","");

  //lấy list sản phẩm trong menu từ database về tableview
  private ObservableList<String> getSanPhamFromDataBase() {
    String sql = "SELECT stt, tensp FROM danhmucsp";
    try (Connection conn = connect;
         PreparedStatement prepared = conn.prepareStatement(sql);
         ResultSet rs = prepared.executeQuery()) {
      while (rs.next()) {
        int stt = rs.getInt("stt");
        String tenSanPham = rs.getString("tensp");
        String Menu = "\t" + tenSanPham;
        sanphamList.add(Menu);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return sanphamList;
  }
  //hàm khởi chạy màn hình trước khi thực hiện chức năng
  @FXML
  public void initialize() {
    //đặt dữ liệu từ csdl vào listview
    sanphamList = getSanPhamFromDataBase();
    lv_sanpham.setItems(sanphamList);

    //thêm tất cả item sanpham vào mảng tìm kiếm
    timkiemSanPham.addAll(sanphamList);

    //mã cửa hàng và mã version (tự đặt)
    txt_poscode.setText("POS001");
    txt_version.setText("V1.0.0");
  }
  //xóa sản phẩm khi đã thêm vào bill
  public void delete_Item(ActionEvent event) {

  }
  //khuyến mãi
  public void Coupon(ActionEvent event) {

  }
  //hủy giao dịch
  public void cancel_Bill(ActionEvent event) {

  }
  //button mở modal các chức năng khác
  public void orther_Functions() {
    //mở modal chức năng khác
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vn/truongdx/poscoffee_app/fxml/ortherFunctions_page.fxml"));
      Parent root = fxmlLoader.load();
      Stage ortherSatge = new Stage();
      ortherSatge.setScene(new Scene(root));
      ortherSatge.showAndWait(); //thao tác only modal này đến khi được đóng lại
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
  //tìm kiếm sản phẩm
  public void search_Item(ActionEvent event) {

  }
  //ghi chú vào sản phẩm
  public void note_Item(ActionEvent event) {

  }
  //bắt đầu ca làm việc
  public void start_Day(ActionEvent event) {

  }
  public void start_workingShifts(ActionEvent event) {

  }
  //thay đổi kích cỡ sản phẩm, thêm toping
  public void change_SizeToping(ActionEvent event) {
    //mở modal
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vn/truongdx/poscoffee_app/fxml/topingsize_page.fxml"));
      Parent root = fxmlLoader.load();
      Stage modalStage = new Stage();
      modalStage.setScene(new Scene(root));
      modalStage.showAndWait();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
