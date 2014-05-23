package pl.perzek.vlc;

import com.google.common.base.Optional;

/**
 * Created by perzek on 2014-05-23.
 */
public class HuffmanTreeNode<T extends ProbabilityAware> {
    private Optional<HuffmanTreeNode<T>> leftChild;
    private Optional<HuffmanTreeNode<T>> rightChild;

    private final T value;


    private HuffmanTreeNode(T object) {
        value = object;
    }

    public void addChild(T object) {
        //TODO
    }

    public Optional<HuffmanTreeNode<T>> getLeftChild() {
        return leftChild;
    }

    public Optional<HuffmanTreeNode<T>> getRightChild() {
        return rightChild;
    }

    private static <T extends ProbabilityAware> HuffmanTreeNode<T> newNode(T object) {
        return new HuffmanTreeNode<T>(object);
    }
}
