package com.revature.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.revature.models.Approval;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class ApprovalDAO implements ApprovalDAOInterface{

    // Get all approvals 
    @Override
    public ArrayList<Approval> getApprovals()
    {
        //instantiate a Connection object so that we can talk to the DB.
        try(Connection conn = ConnectionUtil.getConnection()){

            //A string that will represent our SQL statement
            String sql = "select * from approvals;";

            Statement s = conn.createStatement();

            ResultSet rs = s.executeQuery(sql);

            ArrayList<Approval> approvalList = new ArrayList<>();

            while(rs.next()){

                Approval e = new Approval(
                        rs.getInt("id"),
                        rs.getInt("expense_id"),
                        rs.getString("status"),
                        rs.getInt("reviewer_id"),
                        rs.getString("comment"),
                        rs.getString("review_date")
                        

                );
                approvalList.add(e);
            }
            return approvalList;

        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;

    }

    // get approvals by Manager 
    @Override
    public ArrayList<Approval> getApprovalsByManager(User m) {
        //instantiate a Connection object so that we can talk to the DB.
        try(Connection conn = ConnectionUtil.getConnection()){

            //A string that will represent our SQL statement
            String sql = "select * from approvals where reviewer_id = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, m.getUser_id());

            ResultSet rs = ps.executeQuery();

            ArrayList<Approval> approvalList = new ArrayList<>();

            while(rs.next()){

                Approval e = new Approval(
                        rs.getInt("id"),
                        rs.getInt("expense_id"),
                        rs.getString("status"),
                        rs.getInt("reviewer_id"),
                        rs.getString("comment"),
                        rs.getString("review_date")
                        

                );
                approvalList.add(e);
            }
            return approvalList;

        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;

    }

    // get approvals by employee 
    @Override
    public ArrayList<Approval> getApprovalsByEmployee(User u) {
        //instantiate a Connection object so that we can talk to the DB.
        try(Connection conn = ConnectionUtil.getConnection()){

            //A string that will represent our SQL statement
            String sql = "select * from approvals where employee_id = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, u.getUser_id());

            ResultSet rs = ps.executeQuery();

            ArrayList<Approval> approvalList = new ArrayList<>();

            while(rs.next()){

                Approval a = new Approval(
                        rs.getInt("id"),
                        rs.getInt("expense_id"),
                        rs.getString("status"),
                        rs.getInt("reviewer_id"),
                        rs.getString("comment"),
                        rs.getString("review_date")
                        

                );
                approvalList.add(a);
            }
            return approvalList;

        } catch (SQLException e){
            e.printStackTrace();
        }
        return null; 
    }   

    // get approvals by status 
    @Override
    public ArrayList<Approval> getApprovalsByStatus(String status)
    {
        try(Connection conn = ConnectionUtil.getConnection()){

            //A string that will represent our SQL statement
            String sql = "select * from approvals where status = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, status);

            ResultSet rs = ps.executeQuery();

            ArrayList<Approval> approvalList = new ArrayList<>();

            while(rs.next()){

                Approval e = new Approval(
                        rs.getInt("id"),
                        rs.getInt("expense_id"),
                        rs.getString("status"),
                        rs.getInt("reviewer_id"),
                        rs.getString("comment"),
                        rs.getString("review_date")
                        

                );
                approvalList.add(e);
            }
            return approvalList;

        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;

    }

    // get approvals by date
    @Override
    public ArrayList<Approval> getApprovalsByDate(String date)
    {
        try(Connection conn = ConnectionUtil.getConnection()){

            //A string that will represent our SQL statement
            String sql = "select * from approvals where review_date = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, date);

            ResultSet rs = ps.executeQuery();

            ArrayList<Approval> approvalList = new ArrayList<>();

            while(rs.next()){

                Approval e = new Approval(
                        rs.getInt("id"),
                        rs.getInt("expense_id"),
                        rs.getString("status"),
                        rs.getInt("reviewer_id"),
                        rs.getString("comment"),
                        rs.getString("review_date")
                        

                );
                approvalList.add(e);
            }
            return approvalList;

        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;

    }

    // add approval to database 
    @Override
    public Approval insertApproval(Approval approval)
    {
        
        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "insert into approvals (expense_id, status, reviewer_id, comment, review_date) values (?, ?, ?, ?, ?);";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, approval.getExpense_id());
            ps.setString(2, approval.getStatus());
            ps.setInt(3, approval.getReviewer_id());
            ps.setString(4, approval.getComment());
            ps.setString(5, approval.getReview_date());

            int rowsInserted = ps.executeUpdate();

            if(rowsInserted != 0){
                return approval;
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    // update approval 
    @Override
    public Approval updateApproval(Approval approval)
    {
        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "update approvals set status = ?, comment = ? where id = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, approval.getStatus());
            ps.setString(2, approval.getComment());
            ps.setInt(3, approval.getId());

            int rowsUpdated = ps.executeUpdate();

            if(rowsUpdated != 0){
                return approval;
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    
}

