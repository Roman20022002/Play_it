package utils;

public class Matrix {
    public static double dotProduct(double[] arr1, double[] arr2){
        if(arr1.length != arr2.length)
            throw new RuntimeException("Arrays must be same size");
        double result = 0;
        for(int i=0; i<arr1.length; i++){
            result += arr1[i] * arr2[i];
        }
        return result;
    }



}