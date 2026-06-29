package com.revature.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.revature.models.Manager;
import com.revature.utils.ConnectionUtil;

public class ManagerDAO implements ManagerDAOInterface{
    @Override
    public ArrayList<Manager> getManagers()
    {
        //instantiate a Connection object so that we can talk to the DB.
        try(Connection conn = ConnectionUtil.getConnection()){

            //A string that will represent our SQL statement
            String sql = "select * from employees;";

            Statement s = conn.createStatement();

            ResultSet rs = s.executeQuery(sql);

            ArrayList<Manager> managerList = new ArrayList<>();

            while(rs.next()){

                int roleFK = rs.getInt("role_id_fk");
                // RoleDAO rDAO = new RoleDAO();
                // Role role = rDAO.getRoleById(roleFK);

                Manager e = new Manager(
                        rs.getInt("employee_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name")
                        

                );
                managerList.add(e);
            }
            return managerList;

        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public Manager insertManager(Manager emp) {
        //instantiate a Connection object so that we can talk to the DB.
        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "insert into employees (first_name, last_name, role_id_fk) values (?,?,?);";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1,emp.getUsername());
            ps.setString(2,emp.getPassword());
            //ps.setInt(3,emp.getRole_id_fk());

            ps.executeUpdate();

            return emp;

        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Manager deleteManager(Manager emp)
    {
        return null;
    }
}