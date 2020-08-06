
package Utility;

public class Data {
     // Model and data taken from:
    //Yilmaz, Mustafa & Kayaci Çodur, Merve & Yılmaz, Hamid. (2017). Chinese postman problem approach for a large-scale conventional rail network in Turkey. Tehnicki Vjesnik. 24. 1471-1477. 10.17559/TV-20151231153445. 
    //Research gate URL: https://www.researchgate.net/publication/327364506_Chinese_postman_problem_approach_for_a_large-scale_conventional_rail_network_in_Turkey
    
    //Given a connected undirected graph G=(V,E)
    // V={1, 2, 3, 4},
    //E={(1,2), (1,3), (1,4), (2,3), (2,4), (3,4)}.
    //cij={c12=1, c13=5, c14=4, c23=2, c24=6, c34=3} and c_ij = c_ji
    public static double[][] costs(){
    double[][] costs ={
        {Double.MAX_VALUE,1,5,4},
        {1,Double.MAX_VALUE,2,6},
        {5,2,Double.MAX_VALUE,3},
        {4,6,3,Double.MAX_VALUE},
    };
    return costs;
    }
    public static int Problem_size(double[][]costs){
    int size= costs.length;
    return size;
    }
}
