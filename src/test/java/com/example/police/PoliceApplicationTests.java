package com.example.police;

import com.example.police.api.NumberGeneratorInt;
import com.example.police.impl.QSGenerator;
import com.example.police.impl.QWGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static junit.framework.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PoliceApplicationTests {

    List<String> safeList;
    List<Thread> threads;

    @Test
    public void contextLoads() {
        safeList = Collections.synchronizedList(new ArrayList<>());
        threads = new ArrayList<>();
        generatorTest(new QWGenerator());
        safeList = Collections.synchronizedList(new ArrayList<>());
        threads = new ArrayList<>();
        generatorTest(new QSGenerator());
    }


    private void generatorTest(NumberGeneratorInt generator) {
        System.out.println("Start generator test. class: "+ generator.getClass().getSimpleName());
        long stTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            threads.add(new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < 200; j++) {
                        safeList.add(generator.getNumber(false));
                    }
                }
            });
            threads.add(new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < 200; j++) {
                        safeList.add(generator.getNumber(true));
                    }
                }
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }

        boolean isWork;
        do {
            isWork = false;
            for (Thread thread : threads) {
                if (thread.isAlive()) {
                    isWork = true;
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        } while (isWork);

        stTime = System.currentTimeMillis() - stTime;
        System.out.println("Test is over. Take time: " + (stTime / 1000) + " sec.");
        System.out.println("size: " + safeList.size());

        Set<String> testSet = new HashSet<>(safeList);

        assertEquals("Problems with size !", 40000, safeList.size());
        assertEquals("Problems with uniqueness !", testSet.size(), safeList.size());
    }

}
