import static java.lang.System.arraycopy;

public class Queue {
    private int[] elements = new int[0];
    private int pushIndex;
    private int popIndex;

    public int getSize() {
        return pushIndex - popIndex;
    }

    int getMemory() {
        return elements.length;
    }

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

    public void growTo(int newSize) {
        int size = getSize();
        int[] newElements = new int[newSize];
        if (!isEmpty()) {
            popIndex = popIndex % elements.length;
            if (popIndex + size <= elements.length) {
                arraycopy(elements, popIndex, newElements, 0, size);
            } else {
                arraycopy(elements, popIndex, newElements, 0, size - popIndex);
                arraycopy(elements, 0, newElements, size - popIndex, popIndex);
            }
        }
        elements = newElements;
        popIndex = 0;
        pushIndex = size;
    }

    public void growBy(int by) {
        growTo(getMemory() + by);
    }

    private void grow() {
        growBy(1);
    }
}
