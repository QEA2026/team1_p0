package com.revature.DAOs;

import java.util.ArrayList;

import com.revature.models.Approval;
import com.revature.models.User;

public interface ApprovalDAOInterface {

     
    ArrayList<Approval> getApprovals();

    ArrayList<Approval> getApprovalsByManager(User m);

    ArrayList<Approval> getApprovalsByEmployee(User u);

    ArrayList<Approval> getApprovalsByStatus(String status);

    ArrayList<Approval> getApprovalsByDate(String date);

 // ArrayList<Approval> getApprovalsByCategory(String category);

    Approval insertApproval(Approval approval);

    Approval updateApproval(Approval approval);

    
    
}
