package vn.truongdx.poscoffee_app.models.entities;

import javafx.beans.property.*;
import vn.truongdx.poscoffee_app.utils.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class HoaDon {
  private IntegerProperty maHoaDon;
  private ObjectProperty<Date> ngayHoaDon;
  private DoubleProperty tongTien;
  private StringProperty caLamviec;

  public HoaDon() {
    this.maHoaDon = new SimpleIntegerProperty();
    this.ngayHoaDon = new SimpleObjectProperty<>();
    this.tongTien = new SimpleDoubleProperty();
    this.caLamviec = new SimpleStringProperty();
  }

  public StringProperty caLamviecProperty() {
    return caLamviec;
  }

  public DoubleProperty tongTienProperty() {
    return tongTien;
  }


  public void setMaHoaDon(int maHoaDon) {
    this.maHoaDon.set(maHoaDon);
  }

  public java.sql.Date getNgayHoaDon() {
    return ngayHoaDon.get();
  }

  public void setNgayHoaDon(Date ngayHoaDon) {
    this.ngayHoaDon.set(ngayHoaDon);
  }

  public void setTongTien(double tongTien) {
    this.tongTien.set(tongTien);
  }

  public int createHoaDon(LocalDate ngayThanhtoan, LocalTime gioThanhtoan, String ca, double tongTien) {
    int mahoadon = -1;
    String insertHoadon = "INSERT INTO hoadon(ngayLap, gioLap, caLamviec, tongTien) VALUES (?,?,?,?)";
    String getMahoadon = "SELECT LAST_INSERT_ID() AS maHoadon";

    try (Connection conn = DatabaseConnection.getConnection("posdatabase", "root", "");
         PreparedStatement insertData = conn.prepareStatement(insertHoadon, Statement.RETURN_GENERATED_KEYS)) {

      insertData.setDate(1, Date.valueOf(ngayThanhtoan));
      insertData.setTime(2, Time.valueOf(gioThanhtoan));
      insertData.setString(3, ca);
      insertData.setDouble(4, tongTien);
      insertData.executeUpdate();

      try (ResultSet rs = insertData.getGeneratedKeys()) {
        if (rs.next()) {
          mahoadon = rs.getInt(1);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Dữ liệu cột caLamviec: " + ca);
    }

    return mahoadon;
  }
}

