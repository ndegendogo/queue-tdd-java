import static java.lang.System.arraycopy;

public class Queue {
    private int size;
    private int[] elements = new int[0];
    private int pushIndex;
    private int popIndex;

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(int i) {
        if (isFull())
            grow();
        elements[pushIndex++ % elements.length] = i;
        size++;
    }

    private boolean isFull() {
        return size == elements.length;
    }

    public int remove() {
        if (isEmpty())
            throw new Underflow();
        size--;
        return elements[popIndex++ % elements.length];
    }

    private void grow() {
        int[] newElements = new int[size + 1];
        arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }

    public int getSize() {
        return size;
    }

    int getMemory() {
        return elements.length;
    }
}
