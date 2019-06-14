package com.mygdx.game.util.MapGeneration;

import java.util.Random;

public class Perlin2D {
    byte[] permutationTable;

    public Perlin2D(long seed) {
        Random random = new Random(seed);
        permutationTable = new byte[1024];
        random.nextBytes(permutationTable);
    }

    public float noise(float fx, float fy) {
        int left = (int) fx;
        int top = (int) fy;

        float localX = fx - left;
        float localY = fy - top;

        Vector topLeftGradient = getPseudoRandomGradientVector(left, top);
        Vector topRightGradient = getPseudoRandomGradientVector(left+1, top);
        Vector bottomLeftGradient = getPseudoRandomGradientVector(left, top+1);
        Vector bottomRightGradient = getPseudoRandomGradientVector(left+1, top+1);

        Vector distanceToTopLeft = new Vector(localX, localY);
        Vector distanceToTopRight = new Vector(localX - 1, localY);
        Vector distanceToBottomLeft = new Vector(localX, localY-1);
        Vector distanceToBottomRight = new Vector(localX-1, localY-1);

        localX = qunticCurve(localX);
        localY = qunticCurve(localY);

        float tx1 = dot(distanceToTopLeft, topLeftGradient);
        float tx2 = dot(distanceToTopRight, topRightGradient);
        float bx1 = dot(distanceToBottomLeft, bottomLeftGradient);
        float bx2 = dot(distanceToBottomRight, bottomRightGradient);

        float tx = lerp(tx1, tx2, qunticCurve(localX));
        float bx = lerp(bx1, bx2, qunticCurve(localX));
        float tb = lerp(tx, bx, qunticCurve(localY));

        return tb;
    }

    public float noise(float fx, float fy, int octaves, float persistence) {
        float amplitude = 1;
        float max = 0;
        float result = 0;

        while(octaves-- > 0) {
            max += amplitude;
            result += noise(fx, fy) * amplitude;
            amplitude *= persistence;
            fx *= 2;
            fy *= 2;
        }

        return result / max;
    }

    private float dot(Vector a, Vector b) {
        return a.x * b.x + a.y * b.y;
    }

    private float lerp(float tx, float bx, float t) {
        return tx + (bx - tx) * t;
    }

    private Vector getPseudoRandomGradientVector(int x, int y) {
        int v = (int)(((x * 1836311903L) ^ (y * 2971215073L) + 4807526976L) & 1023L);
        v = permutationTable[v]&3;

        switch (v) {
            case 0: return new Vector(1,0);
            case 1: return new Vector(-1, 0);
            case 2: return new Vector(0,1);
            default: return new Vector(0, -1);
        }
    }

    private float qunticCurve(float t) {
        return t * t * t * (t * (t * 6 - 15) + 10);
    }

}
