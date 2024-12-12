package vn.truongdx.poscoffee_app.models.entities;

public class SanPham {
  private String tenSP;
  private String donviTinh;
  private double giaSP;

  public SanPham(String tenSP, String donviTinh, double giaSP) {
    this.tenSP = tenSP;
    this.donviTinh = donviTinh;
    this.giaSP = giaSP;
  }

  public String getTenSP() {
    return tenSP;
  }

  public void setTenSP(String tenSP) {
    this.tenSP = tenSP;
  }

  public String getDonviTinh() {
    return donviTinh;
  }

  public void setDonviTinh(String donviTinh) {
    this.donviTinh = donviTinh;
  }

  public double getGiaSP() {
    return giaSP;
  }

  public void setGiaSP(double giaSP) {
    this.giaSP = giaSP;
  }
}

