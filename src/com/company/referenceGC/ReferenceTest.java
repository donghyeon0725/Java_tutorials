package com.company.referenceGC;


import com.company.Thread.runnable.Sample;

import java.lang.ref.*;
import java.util.LinkedList;
import java.util.List;

class BigData {
    private int[] array = new int[2500];
}

public class ReferenceTest {
    private List<WeakReference<BigData>> weakReferences = new LinkedList<>();
    private List<SoftReference<BigData>> softReferences = new LinkedList<>();
    private List<BigData> bigDatas = new LinkedList<>();

    public void weakReferenceTest() {
        try {
            while (true) {
                weakReferences.add(new WeakReference<BigData>(new BigData()));
            }
        } catch (OutOfMemoryError e) {
            System.out.println("out of memory");
        }
    }

    public void softReferenceTest() {
        try {
            while (true) {
                softReferences.add(new SoftReference<BigData>(new BigData()));
            }
        } catch (OutOfMemoryError e) {
            System.out.println("out of memory");
        }
    }

    public void strongReferenceTest() {
        try {
            while (true) {
                bigDatas.add(new BigData());
            }
        } catch (OutOfMemoryError e) {
            System.out.println("out of memory");
        }
    }

    public static void main(String[] args) {
        System.out.println("실행");

//        ReferenceQueue<Test> rq = new ReferenceQueue<Test>();
//        PhantomReference<Test> pr = new PhantomReference<Test>(new Test("test"), rq);


        ReferenceTest test = new ReferenceTest();
        test.weakReferenceTest();
//        test.softReferenceTest();
//        test.strongReferenceTest();

        System.out.println("종료");
    }
}
