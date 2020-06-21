class Stack{
    private int stackSize;
    private char[] stackArray;
    private int top;

    public Stack(int n){
        this.stackSize=n;
        this.stackArray = new char[stackSize];
        this.top = -1;
    }

    public void addElement(char element){
        stackArray[++top] = element;
    }

    public char deleteElement(){
        return stackArray[top--];
    }

    public char getTop(){
        return stackArray[top];
    }

    public boolean isEmpty(){
        return (top == - 1);
    }

    public boolean isFull(){
        return (top == stackSize-1);
    }

}
