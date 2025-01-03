package vn.truongdx.poscoffee_app.models.entities;

import vn.truongdx.poscoffee_app.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;

public class HoaDon {
  public int createHoaDon(LocalDate ngayThanhtoan, LocalTime gioThanhtoan, String ca, double tongTien) {
    int mahoadon = -1;
    String insertHoadon = "INSERT INTO hoadon(ngayLap, gioLap, caLamviec, tongTien) VALUES (?,?,?,?)";
    String getMahoadon = "SELECT LAST_INSERT_ID() AS maHoadon";

    try {
      Connection conn = DatabaseConnection.getConnection("posdatabase", "root", "");
      PreparedStatement insertData = conn.prepareStatement(insertHoadon);
      PreparedStatement getId = conn.prepareStatement(getMahoadon);

      insertData.setString(1,ca);
      insertData.setString(2,ca);
      insertData.executeUpdate();

      ResultSet rs = getId.executeQuery();
      if (rs.next()) {
        mahoadon = rs.getInt("maHoadon");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    //trả về mã hóa đơn mới tạo
    return mahoadon;
  }
}
