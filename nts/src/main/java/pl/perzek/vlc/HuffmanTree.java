package pl.perzek.vlc;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;

/**
 * Created by perzek on 2014-06-06.
 */
public class HuffmanTree<T extends ProbabilityAware> {

    private HuffmanTreeNode<T> root;

    public HuffmanTree(Iterable<T> iterable) {
        List<HuffmanTreeNode<T>> tmpList = Lists.newArrayList(Lists.transform(Lists.newArrayList(iterable), HuffmanTreeNode::new));
        while (tmpList.size() > 1) {
            tmpList.add(0, new HuffmanTreeNode<>(tmpList.remove(0), tmpList.remove(0)));
        }
        root = tmpList.get(0);
    }

    public Map<String, T> getCodeMapping() {
        return root.getMapping();
    }

    @Override
    public String toString() {
        return String.format("T{%s}", root);
    }
}
