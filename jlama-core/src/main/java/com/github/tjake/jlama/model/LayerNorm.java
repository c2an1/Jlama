package com.github.tjake.jlama.model;

import com.google.common.base.Preconditions;

import com.github.tjake.jlama.tensor.AbstractTensor;

public class LayerNorm {

    protected final AbstractModel m;
    private final AbstractTensor bias;
    protected final AbstractTensor weights;

    public LayerNorm(AbstractModel m, AbstractTensor bias, AbstractTensor weights) {
        this.m = m;
        this.bias = bias;
        this.weights = weights;
    }

    public AbstractTensor forward(AbstractTensor input) {
        return forward(input, 0, input.shape()[0]);
    }


    public AbstractTensor forward(AbstractTensor input, int offset, int length) {
        Preconditions.checkArgument(input.shape().length == 1);
        float sum = 0;
        float sumSq = 0;
        int limit = offset + length;
        for (int i = offset; i < limit; i++) {
            float v = input.get(i);
            sum += v;
            sumSq += v * v;
        }

        float mean = sum / length;
        float variance = sumSq / length - mean * mean;
        float invStddev = 1.0f / (float) Math.sqrt(variance + m.c.layerNormEps);

        AbstractTensor out = m.makeTensor(input.shape());
        for (int i = offset; i < limit; i++) {
            float v = (input.get(i) - mean) * invStddev * weights.get(i) + bias.get(i);
            out.set(v, i);
        }

        return out;
    }
}
