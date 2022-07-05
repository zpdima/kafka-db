package cf.zpdima.kafkadb;

import java.util.concurrent.CountDownLatch;

//https://javarush.ru/groups/posts/2111-threadom-java-ne-isportishjh--chastjh-vi---k-barjheru

public class ThreadTest1 {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        Runnable task = () -> {
            try {
                System.out.println("Countdown: " + countDownLatch.getCount() + " " + Thread.currentThread().getName());
                countDownLatch.countDown();
                System.out.println("Countdown: " + countDownLatch.getCount()  + " " + Thread.currentThread().getName());
                countDownLatch.await();
                System.out.println("Finished " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        for (int i = 0; i < 3; i++) {
            new Thread(task).start();
        }
    }
}
