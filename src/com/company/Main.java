package com.company;

import com.company.Matrix.ParallelMatrixProduct;
import com.company.Matrix.UsualMatrix;

import java.util.Date;

public class Main {

    public static void main(String[] args) {
        UsualMatrix matrixA = new UsualMatrix(1000,1000);
        UsualMatrix matrixB = new UsualMatrix(1000,1000);
        UsualMatrix.GenerateRandom(matrixA, 10);
        UsualMatrix.GenerateRandom(matrixB, 10);
//        System.out.println(matrixA);
//        System.out.println("------\n\n");
//        System.out.println(matrixB);
//        System.out.println("------\n\n");

        System.out.println("Multiply 1 thread matrix:");
        Date start = new Date();
        UsualMatrix res = matrixA.product(matrixB);
        Date end = new Date();
//        System.out.println(res);
        System.out.println("Time taken in milli seconds: " + (end.getTime() - start.getTime()));
        System.out.println("------");

        int threads = Runtime.getRuntime().availableProcessors();
        System.out.println("Multiply " + threads + " threads matrix");
        ParallelMatrixProduct parallelMatrixProduct = new ParallelMatrixProduct(threads);
        start = new Date();
        UsualMatrix res2 = parallelMatrixProduct.product(matrixA, matrixB);
        end = new Date();
//        System.out.println(res2);
        System.out.println("Time taken in milli seconds: " + (end.getTime() - start.getTime()));


        //Проверка для преподавателя
        if(res.getRows() != res2.getRows() || res.getColumns() != res.getColumns())
            System.err.println("ERROR RESULT SIZES NOT EQUAL");
        for (int i = 0; i < res.getRows(); i++){
            for (int j = 0; j < res.getColumns(); j++){
                if(res.getElement(i,j) != res2.getElement(i,j)){
                    System.err.println("ERROR RESULT NOT EQUAL");
                    break;
                }
            }
        }
    }
}
