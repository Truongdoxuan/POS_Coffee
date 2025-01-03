package vn.truongdx.poscoffee_app.utils;

public class DataStorage {
  private static double totalBill;

  public static double getTotalBill() {
    return totalBill;
  }

  public static void setTotalBill(double totalBill) {
    DataStorage.totalBill = totalBill;
  }
}
