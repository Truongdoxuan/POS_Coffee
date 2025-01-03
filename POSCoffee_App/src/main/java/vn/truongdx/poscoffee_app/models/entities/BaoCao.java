package vn.truongdx.poscoffee_app.models.entities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import vn.truongdx.poscoffee_app.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaoCao {
  //kha báo biến
  ObservableList<HoaDon> reportList = FXCollections.observableArrayList();
  //kết nối cơ sở dữ liệu
  Connection conn = DatabaseConnection.getConnection("posdatabase","root","");

  //báo cáo theo ngày
  public ObservableList<HoaDon> DailyReport(String date) {
    String query = "SELECT * FROM hoadon WHERE DATE(ngayLap) = ?";
    try {
      PreparedStatement pstmt = conn.prepareStatement(query);
      pstmt.setString(1,date);
      ResultSet rs = pstmt.executeQuery();

      while (rs.next()) {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setMaHoaDon(rs.getInt("maHoadon"));
        hoaDon.setNgayHoaDon(rs.getDate("ngayLap"));
        hoaDon.setTongTien(rs.getDouble("tongTien"));
        reportList.add(hoaDon);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return reportList;
  }

  // Phương thức lấy báo cáo doanh thu theo tháng
  public ObservableList<HoaDon> MonthlyReport(int monthNumber) {
    ObservableList<HoaDon> reportList = FXCollections.observableArrayList();
    String query = "SELECT * FROM hoadon WHERE MONTH(ngayLap) = ?";

    try {
      PreparedStatement pstmt = conn.prepareStatement(query);

      pstmt.setInt(1, monthNumber);
      ResultSet rs = pstmt.executeQuery();

      while (rs.next()) {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setMaHoaDon(rs.getInt("maHoadon"));
        hoaDon.setNgayHoaDon(rs.getDate("ngayLap"));
        hoaDon.setTongTien(rs.getDouble("tongTien"));
        reportList.add(hoaDon);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return reportList;
  }

  // Phương thức lấy báo cáo doanh thu theo ca
  public ObservableList<HoaDon> ShiftReport(String shift) {
    ObservableList<HoaDon> reportList = FXCollections.observableArrayList();
    String query = "SELECT * FROM hoadon WHERE caLamviec = ?";

    try {
         PreparedStatement pstmt = conn.prepareStatement(query);

      pstmt.setString(1, shift);
      ResultSet rs = pstmt.executeQuery();

      while (rs.next()) {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setMaHoaDon(rs.getInt("maHoadon"));
        hoaDon.setNgayHoaDon(rs.getDate("ngayLap"));
        hoaDon.setTongTien(rs.getDouble("tongTien"));
        reportList.add(hoaDon);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return reportList;
  }
}
