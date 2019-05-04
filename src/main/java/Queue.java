import static java.lang.System.arraycopy;

public class Queue {
    private int[] elements = new int[0];
    private int pushIndex;
    private int popIndex;

    public boolean isEmpty() {
        return getSize() == 0;
    }

    public boolean isFull() {
        return getSize() == elements.length;
    }

    public void add(int i) {
        if (isFull())
            grow();
        elements[pushIndex++ % elements.length] = i;
    }

    public int remove() {
        if (isEmpty())
            throw new Underflow();
        return elements[popIndex++ % elements.length];
    }

    private void grow() {
        int size = getSize();
        int[] newElements = new int[size + 1];
        if (!isEmpty()) {
            popIndex = popIndex % elements.length;
            arraycopy(elements, popIndex, newElements, 0, size - popIndex);
            arraycopy(elements, 0, newElements, size - popIndex, popIndex);
        }
        elements = newElements;
        popIndex = 0;
        pushIndex = size;
    }

    public int getSize() {
        return pushIndex - popIndex;
    }

    int getMemory() {
        return elements.length;
    }
}
