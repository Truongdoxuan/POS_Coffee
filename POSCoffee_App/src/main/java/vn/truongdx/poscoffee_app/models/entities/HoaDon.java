package vn.truongdx.poscoffee_app.models.entities;

import vn.truongdx.poscoffee_app.utils.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class HoaDon {
  public int createHoaDon(LocalDate ngayThanhtoan, LocalTime gioThanhtoan, String ca, double tongTien) {
    int mahoadon = -1;
    String insertHoadon = "INSERT INTO hoadon(ngayLap, gioLap, caLamviec, tongTien) VALUES (?,?,?,?)";
    String getMahoadon = "SELECT LAST_INSERT_ID() AS maHoadon";

    try {
      Connection conn = DatabaseConnection.getConnection("posdatabase", "root", "");
      PreparedStatement insertData = conn.prepareStatement(insertHoadon, Statement.RETURN_GENERATED_KEYS);

      insertData.setDate(1, Date.valueOf(ngayThanhtoan));
      insertData.setTime(2,Time.valueOf(gioThanhtoan));
      insertData.setString(3,ca);
      insertData.setDouble(4, tongTien);
      insertData.executeUpdate();

      ResultSet rs = insertData.getGeneratedKeys(); //lấy id
      if (rs.next()) {
        mahoadon = rs.getInt(1);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    //trả về mã hóa đơn mới tạo
    return mahoadon;
  }
}
