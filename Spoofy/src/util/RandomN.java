package util;

import java.util.Random;

public class RandomN {

    public static int rng(int max){
        Random r = new Random();
        int i = r.nextInt(max);
        return i;
    }
}
