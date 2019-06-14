package com.mygdx.game.util.MapGeneration;

import com.badlogic.gdx.math.MathUtils;

public class Topmap {
    private int mapsize;
    private double[] result;
    private double[] noise;

    private int[] frequencies = {1, 2, 4, 8, 16, 32};
    private double[] amplitude = {0.1, 0.1, 0.2, 0.3, 0.5, 1.0};
    private double[][] noises;

    public Topmap(int mapsize) {
        noise = new double[mapsize];
        noises = new double[frequencies.length][noise.length];
        this.mapsize = mapsize;
        setNoises();
        result = weightedSum(amplitude, noises);
    }

    private void setNoises() {
        double phase = MathUtils.random(0, 1);
        for(int i = 0; i < frequencies.length; i++) {
            for(int j = 0; j < mapsize; j++) {
                noise[i] = Math.sin(2*Math.PI * frequencies[i]*j/mapsize + phase) * 10;
                noises[i][j] = noise[i];
            }
        }
    }

    private double[] weightedSum(double[] amplitudes, double[][] noises) {
        double output[] = new double[mapsize];
        for(int k = 0; k < noises.length; k++) {
            for(int x = 0; x < mapsize; x++) {
                output[x] += amplitudes[k] * noises[k][x];
            }
        }
        return output;
    }

    public double[] getResult() {
        return result;
    }
}

/**
 * result = noise; // noise with frequency = 1
 * result = adjacentMin(adjacentMin(noise)); // white noise
 * result = smoother(noise); // red noise
 *
 *     private int[] frequencies = setFrequencies(1,8);
 *     private double[] amplitude = setAmplitudes(frequencies, MathUtils.random(-1f,1f));
 *
 *
 * // white noise
 *     private double[] adjacentMin(double[] noise) {
 *         double[] output = new double[1000];
 *         for(int i = 0; i < noise.length - 1; i++) {
 *             output[i] = Math.min(noise[i], noise[i+1]);
 *         }
 *         return output;
 *     }
 *
 *     // red noise
 *     private double[] smoother(double[] noise) {
 *         double[] output = new double[1000];
 *         for(int i = 0; i < noise.length - 1; i++) {
 *             output[i] = 1.5f * (noise[i] + noise[i+1]);
 *         }
 *         return output;
 *     }
 *
 *     private void setNoise() {
 *         for(int i = 0; i < mapsize; i++) {
 *             noise[i] = MathUtils.random(1,3); // white and red
 *         }
 *     }
 *
 *     private double[] setAmplitudes(int[] f, double exp) {
 *         double[] amplitude = new double[frequencies.length];
 *         for(int i = 0; i < frequencies.length; i++) {
 *             amplitude[i] = Math.pow(f[i], exp);
 *         }
 *         return amplitude;
 *     }
 *
 *     private int[] setFrequencies(int first, int last) {
 *         int[] freq = new int[last-1];
 *         int count = 0;
 *         for(int i = first; i < last; i++) {
 *             freq[count] = i;
 *             count++;
 *         }
 *         return freq;
 *     }
 */

