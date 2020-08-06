
package com.mycompany.undirected_chinese_postman_problem;

import Utility.Data;
import ilog.concert.IloException;
import java.io.FileNotFoundException;
import java.io.PrintStream;


public class Main {
    // Model and data taken from:
    //Yilmaz, Mustafa & Kayaci Çodur, Merve & Yılmaz, Hamid. (2017). Chinese postman problem approach for a large-scale conventional rail network in Turkey. Tehnicki Vjesnik. 24. 1471-1477. 10.17559/TV-20151231153445. 
    //Research gate URL: https://www.researchgate.net/publication/327364506_Chinese_postman_problem_approach_for_a_large-scale_conventional_rail_network_in_Turkey
    
    //Given a connected undirected graph G=(V,E)
    // V={1, 2, 3, 4},
    //E={(1,2), (1,3), (1,4), (2,3), (2,4), (3,4)}.
    //cij={c12=1, c13=5, c14=4, c23=2, c24=6, c34=3} 
    public static void main(String[] args) throws IloException, FileNotFoundException{
     System.setOut(new PrintStream("Undirected_Chinese_Postman_Problem.log"));
     double [][] costs= Data.costs();
     int problem_size= Data.Problem_size(costs);
     UCPP_Model model = new UCPP_Model(costs,problem_size);
  model.solveModel();
}
}