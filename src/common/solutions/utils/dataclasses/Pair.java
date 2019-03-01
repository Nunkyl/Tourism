package common.solutions.utils.dataclasses;

/**
 * Created by eliza on 01.03.19.
 */
public class Pair {

    private String left;
    private String[] right;

    public Pair(String left, String[] right) {
        this.left = left;
        this.right = right;
    }

    public String getLeft() {
        return left;
    }

    public String[] getRight() {
        return right;
    }
}
