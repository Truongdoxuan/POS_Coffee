package vn.truongdx.poscoffee_app.repositories;

import vn.truongdx.poscoffee_app.models.entities.Employee;
import vn.truongdx.poscoffee_app.utils.DatabaseConnection;

import java.sql.Connection;

public class EmployeeRepositoryImpl implements EmployeeRepository {
  Connection connection;

  public Connection getConnection() {
    return connection;
  }

  public EmployeeRepositoryImpl() {
    super();
    this.connection = DatabaseConnection.getConnection("posdatabase","root","");
  }

  @Override
  public boolean AddNew(Employee emp) {
    return false;
  }
}
