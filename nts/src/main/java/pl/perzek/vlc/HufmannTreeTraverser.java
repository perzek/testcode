package pl.perzek.vlc;

import com.google.common.base.Optional;
import com.google.common.collect.BinaryTreeTraverser;

/**
 * Created by perzek on 2014-05-23.
 */
public class HufmannTreeTraverser<T extends ProbabilityAware> extends BinaryTreeTraverser<HuffmanTreeNode<T>> {
    @Override
    public Optional<HuffmanTreeNode<T>> leftChild(HuffmanTreeNode root) {
        return root.getLeftChild();
    }

    @Override
    public Optional<HuffmanTreeNode<T>> rightChild(HuffmanTreeNode root) {
        return root.getRightChild();
    }
}
