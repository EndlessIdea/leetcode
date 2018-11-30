package Heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MinimumCostHireKWorkers857 {
    class Worker implements Comparable<Worker> {
        public int quality, wage;
        public double ratio;
        public Worker(int q, int w) {
            quality = q;
            wage = w;
            ratio = (double)w/q;
        }

        public int compareTo(Worker other) {
            return Double.compare(ratio, other.ratio);
        }
    }

    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        Worker[] workers = new Worker[wage.length];
        for (int i = 0; i < wage.length; i++) {
            workers[i] = new Worker(quality[i], wage[i]);
        }

        double minCost = Double.MAX_VALUE;
        Arrays.sort(workers);
        PriorityQueue<Integer> qualities = new PriorityQueue<>();
        int qualitySum = 0;
        for (int i = 0; i < workers.length; i++) {
            double ratio = workers[i].ratio;
            qualities.offer(workers[i].quality);
            qualitySum += workers[i].quality;
            if (qualities.size() > K) {
                qualitySum -= qualities.poll();
            }
            if (qualities.size() == K) {
                minCost = Math.min(minCost, qualitySum * ratio);
            }
        }
        return minCost;
    }

    public static void main(String[] args) {

    }
}
