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
        int index = pushIndex % elements.length;
        elements[index] = i;
        pushIndex = pushIndex + 1;
        size++;
    }

    private boolean isFull() {
        return size == elements.length;
    }

    public int remove() {
        if (isEmpty())
            throw new Underflow();
        int result = elements[popIndex];
        popIndex = (popIndex + 1) % elements.length;
        size--;
        return result;
    }

    private void grow() {
        int[] newElements = new int[size + 1];
        arraycopy(elements, popIndex, newElements, 0, size - popIndex);
        arraycopy(elements, 0, newElements, size - popIndex, popIndex);
        elements = newElements;
        popIndex = 0;
        pushIndex = size;
    }

    public int getSize() {
        return size;
    }

    int getMemory() {
        return elements.length;
    }
}
