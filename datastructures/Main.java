import java.util.Arrays;

class myStack {

    private int[] arr;
    private int capacity;
    private int top;
    private int top2;
    public myStack(int cap) {
        capacity = cap;
        arr = new int[capacity];
        top = -1;
        top2 = capacity;
    }

    public void push(int x) {
        if (isFull()) {
            System.out.println("Stack Overflow");
            return;
        }
        arr[++top] = x;
    }
    public void push2(int x) {
        if (isFull2()) {
            System.out.println("Stack Overflow");
            return;
        }
        arr[--top2] = x;
    }

    public int pop() {
        if (top == -1) {
            System.out.println("Stack Underflow");
            return -1;
        }
        return arr[top--];
    }
    public int pop2() {
        if (top2 == capacity) {
            System.out.println("Stack Underflow");
            return -1;
        }
        return arr[top2++];
    }

    // peek (or top) operation
    public int peek() {
        if (top == -1) {
            System.out.println("Stack is Empty");
            return -1;
        }
        return arr[top];
    }
    public int peek2(){
        if (top2 == capacity) {
            System.out.println("Stack is Empty");
            return -1;
        }
        return arr[top2];
    }

    public boolean isEmpty() {
        return top == -1;
    }
public boolean isEmpty2() {
        return top2 == capacity;
    }
    public boolean isFull() {
        return top == top2-1;
    }
    public boolean isFull2() {
        return top2 == top+1;
    }
    public void display() {
        for (int i = 0; i <=capacity - 1; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}

public class Main {
    public static void main(String[] args) {
        myStack st = new myStack(8);
        st.push(1);
        st.push(2);
        st.push(3);
        st.push2(8);
        st.push2(7);
        st.push2(6);
        st.push2(5);
        st.push(4);
        st.push(7);
        
        st.display();
    }
}