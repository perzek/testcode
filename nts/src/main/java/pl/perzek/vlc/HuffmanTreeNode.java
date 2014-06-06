package pl.perzek.vlc;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Optional;

/**
 * Created by perzek on 2014-05-23.
 */
public class HuffmanTreeNode<T extends ProbabilityAware> {
    private Optional<HuffmanTreeNode<T>> leftNode;
    private Optional<HuffmanTreeNode<T>> rightNode;

    private final Optional<T> value;
    private final double probability;

    public HuffmanTreeNode(T value) {
        this.probability = value.getProbability();
        this.value = Optional.of(value);
        leftNode = Optional.empty();
        rightNode = Optional.empty();
    }

    public HuffmanTreeNode(HuffmanTreeNode<T> left, HuffmanTreeNode<T> right) {
        value = Optional.empty();
        probability = left.getProbability() + right.getProbability();
        leftNode = Optional.of(left);
        rightNode = Optional.of(right);
    }

    public double getProbability() {
        return probability;
    }

    @Override
    public String toString() {
        return String.format("N{P = %.2f; V = %s; L = %s; R = %s}", probability, value, leftNode, rightNode);
    }

    public Map<String, T> getMapping() {
        return mapToCode("");
    }

    private Map<String, T> mapToCode(String prefix) {
        Map<String, T> result = Maps.newHashMap();
        value.ifPresent((v) -> result.put(prefix, v));
        leftNode.ifPresent((v) -> result.putAll(v.mapToCode(prefix + "0")));
        rightNode.ifPresent((v) -> result.putAll(v.mapToCode(prefix + "1")));
        return result;
    }
}
