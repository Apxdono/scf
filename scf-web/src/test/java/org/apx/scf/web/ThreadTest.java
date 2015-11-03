package org.apx.scf.web;

import org.junit.Test;

/**
 * Created by oleg on 9/25/15.
 */


public class ThreadTest {

    @Test
    public void lol(){
        int lol = 1;

        while(){

        }
    }

    @Test
    public void ohShitStack() throws InterruptedException {
        TThread th = new TThread();
        th.start();
        th.interrupt();
        th.join(4000);
    }

    static class TThread extends Thread{

        @Override
        public void run() {
            System.out.println("Alalala");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                if(isInterrupted()){
                    System.err.println("No problemo");
                }
                System.err.println("Shit i'm done");
            }
        }

        @Override
        public boolean isInterrupted() {
            return Thread.currentThread().isInterrupted();
        }
    }

}
