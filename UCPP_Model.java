package com.mycompany.undirected_chinese_postman_problem;

import ilog.concert.IloException;
import ilog.concert.IloIntVar;
import ilog.concert.IloLinearNumExpr;
import ilog.concert.IloNumVarType;
import ilog.concert.IloObjective;
import ilog.concert.IloObjectiveSense;
import ilog.cplex.IloCplex;

public class UCPP_Model {

    protected IloCplex model;

    protected int size;

    protected double[][] costs;

    protected IloIntVar[][] x_d;//  matrix of variables x_ij
    

    UCPP_Model(double[][] costs, int problem_size) throws IloException {
        this.size = problem_size;
        this.costs = costs;
        this.model = new IloCplex();
        this.x_d = new IloIntVar[size][size];
        
    }

    protected void addVariables() throws IloException {
        for (int i = 0; i < size; i++) {
            int pos_i = i + 1;
            for (int j = 0; j < size; j++) {
                int pos_j = j + 1;
                x_d[i][j] = (IloIntVar) model.numVar(0, 1, IloNumVarType.Int, "x[" + pos_i + "][" + pos_j + "]");
               
            }
        }

    }
    
    
    //The following code creates the constraints for the problem.
    protected void addConstraints() throws IloException {
        // flow conservation
     for (int i = 0; i < size; i++) {
           IloLinearNumExpr expr_1 = model.linearNumExpr();
            for (int j = 0; j < size; j++) {
                if(i!= j){
                 expr_1.addTerm(x_d[i][j], 1);// x_ij
                 expr_1.addTerm(x_d[j][i], -1);// x_ij
                 
                }
                
            }
            model.addEq(expr_1, 0);
        }
     // Covering at least once each arc
      for (int i = 0; i < size; i++) {
           
            for (int j = 0; j < size; j++) {
              if(i!= j && i<j){ 
                  // condtion i<j avoids that covering constrains are doubled
                  IloLinearNumExpr expr_2 = model.linearNumExpr();
                  expr_2.addTerm(x_d[i][j], 1);// x_ij
              expr_2.addTerm(x_d[j][i], 1);// x_ij
                 
                model.addGe(expr_2, 1);
              }
             
            }
               
            }
            
        }
     //The following code creates the objective function for the problem
    protected void addObjective() throws IloException {
        IloLinearNumExpr objective = model.linearNumExpr();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
              if(i!= j){ 
                  objective.addTerm(x_d[i][j], costs[i][j]);
            }
                
            }}
    
    IloObjective Obj = model.addObjective(IloObjectiveSense.Minimize, objective);
    }
public void solveModel() throws IloException {
        addVariables();
        addObjective();
        addConstraints();
        model.exportModel("Undirected_Chinese_Postman_Problem.lp");

        model.solve();

        if (model.getStatus() == IloCplex.Status.Feasible
                | model.getStatus() == IloCplex.Status.Optimal) {
            System.out.println();
            System.out.println("Solution status = " + model.getStatus());
            System.out.println();
            System.out.println("Total Cost " + model.getObjValue());
             System.out.println("The variables x_{ij} ");
             for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
              if(i!= j && model.getValue(x_d[i][j])!=0){ 
                  System.out.println("---->" + x_d[i][j].getName()+ " "+ model.getValue(x_d[i][j]));
                  
            }
                
            }}

            
           
            
        } else {
            System.out.println("The problem status is: " + model.getStatus());
        }
    }




}
    
    
    
    
    
    
    
      


