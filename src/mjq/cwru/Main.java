package mjq.cwru;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        // write your code here
    }

    public class Key {
    }

    public class Value {
    }

    /**
     * scan through all keys until find a match
     */
    public class SequentialSearchST {
    }

    /**
     * Maintain an ordered array of key-value pairs
     */
    public class BinarySearchST {
    }

    public class BinarySearchTreeST<Key extends Comparable<Key>> {
        private Node root;

        private class Node {
            private Key key;
            private Value val;
            private Node left, right;
            private int count;

            public Node(Key key, Value val) {
                this.key = key;
                this.val = val;
            }
        }

        private class MyQueue<Key extends Comparable<Key>> implements Queue<Key> {

            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Key> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Key key) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Key> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public boolean offer(Key key) {
                return false;
            }

            @Override
            public Key remove() {
                return null;
            }

            @Override
            public Key poll() {
                return null;
            }

            @Override
            public Key element() {
                return null;
            }

            @Override
            public Key peek() {
                return null;
            }
        }

        public BinarySearchTreeST(Key key, Value val) {
            root = new Node(key, val);
        }

        public void put(Key key, Value val) {
            root = put(root, key, val);
        }

        private Node put(Node x, Key key, Value val) {
            if (x == null)
                return new Node(key, val);
            int cmp = key.compareTo(x.key);
            if (cmp < 0)
                x = x.left;
            else if (cmp > 0)
                x = x.right;
            else x.val = val;
            x.count = 1 + size(x.left) + size(x.right);
            return x;
        }

        public Value get(Key key) {
            Node x = root;
            while (x != null) {
                int cmp = key.compareTo(x.key);
                if (cmp < 0)
                    x = x.left;
                else if (cmp > 0)
                    x = x.right;
                else return x.val;
            }
            return null;
        }

        public void delete(Key key) {
            root = delete(root, key);
        }

        private Node delete(Node x, Key key) {
            if (x == null) return null;
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x.left = delete(x.left, key);
            else if (cmp > 0) x.right = delete(x.right, key);
            else {
                if (x.left == null) return x.right;
                if (x.right == null) return x.left;

                Node t = x;

                x = min(t.right);
                //because x is the least node in t.right tree, so it needs deleteMin to delete x.
                x.right = deleteMin(t.right);
                x.left = t.left;
            }
            x.count = 1 + size(x.left) + size(x.right);
            return x;
        }

        public boolean contains(Key key) {
            Node x = root;
            while (x != null) {
                int cmp = key.compareTo(x.key);
                if (cmp < 0)
                    x = x.left;
                else if (cmp > 0)
                    x = x.right;
                else return true;
            }
            return false;
        }

        public boolean isEmpty(Key key) {
            return root == null;
        }

        public int size() {
            return size(root);
        }

        private int size(Node x) {
            if (x == null) return 0;
            return x.count;
        }

        public Node min(Node x) {
            while (x != null)
                x = x.left;
            return x;
        }

        public Node max(Node x) {
            while (x != null)
                x = x.right;
            return x;
        }

        public Key floor(Key key) {
            Node x = floor(root, key);
            if (x == null)
                return null;
            return x.key;
        }

        private Node floor(Node x, Key key) {
            if (x == null) return null;
            int cmp = key.compareTo(x.key);
            if (cmp == 0) return x;
            if (cmp < 0) return floor(x.left, key);
            Node t = floor(x.right, key);
            if (t != null) return t;
            else return x;
        }

        public Key ceiling(Key key) {
            Key key1 = null;
            return key1;
        }

        /**
         * number of keys less than key
         */
        public int rank(Key key) {
            return rank(root, key);
        }

        public int rank(Node x, Key key) {
            if (x == null) return 0;
            int cmp = key.compareTo(x.key);
            if (cmp < 0)
                return rank(x.left, key);
            else if (cmp > 0)
                return 1 + size(x.left) + rank(x.right, key);
            else return size(x.left);

        }

        public Key select(int k) {
            Key key = null;
            return key;
        }

        public void deleteMin() {
            root = deleteMin(root);
        }

        private Node deleteMin(Node x) {
            if (x.left == null) return x.right;
            x.left = deleteMin(x.left);
            x.count = 1 + size(x.left) + size(x.right);
            return x;
        }

        public void deleteMax() {
        }

        public int size(Key lo, Key hi) {
            return 0;
        }

        public Iterable<Key> keys(Key lo, Key hi) {
            return null;
        }

        /**
         * the order is from small to large
         */
        private void inorder(Node x, MyQueue<Key> q) {
            if (x == null) return;
            inorder(x.left, q);
            q.add(x.key);
            inorder(x.right, q);
        }

        public Iterable<Key> keys() {
            MyQueue<Key> q = new MyQueue<Key>();
            inorder(root, q);
            return q;
        }

    }


}
