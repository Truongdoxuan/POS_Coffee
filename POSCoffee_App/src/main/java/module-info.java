module vn.truongdx.poscoffee_app {
  requires javafx.controls;
  requires javafx.fxml;


  opens vn.truongdx.poscoffee_app to javafx.fxml;
  exports vn.truongdx.poscoffee_app;
  exports vn.truongdx.poscoffee_app.controllers;
  opens vn.truongdx.poscoffee_app.controllers to javafx.fxml;
}