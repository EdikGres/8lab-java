package com.company.Matrix;

import com.company.Matrix.UsualMatrix;

import java.util.ArrayList;
import java.util.List;

public class ParallelMatrixProduct {
    private int threads;

    public ParallelMatrixProduct(int threads) {
        this.threads = threads;
    }


    public UsualMatrix product(UsualMatrix a, UsualMatrix b){
        if(a.columns != b.rows)
            return null;
        UsualMatrix res = new UsualMatrix(a.rows, b.columns);
        List<Thread> threads = new ArrayList<Thread>();
        int rows1 = a.columns;
        for (int i = 0; i < rows1; i++){
            RowWorker task = new RowWorker(res, a, b, i);
            Thread thread = new Thread(task);
            thread.start();
            threads.add(thread);
            if(threads.size() % this.threads == 0){
                waitThreads(threads);
            }
        }
        waitThreads(threads);
        return res;
    }

    public static void waitThreads(List<Thread> threads){
        for(Thread thread : threads){
            try {
                thread.join();
            } catch (InterruptedException ex){
                ex.printStackTrace();
            }
        }
        threads.clear();
    }
}
