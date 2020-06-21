class Deque{
    private int dequeSize;
    private char[] dequeArray;
    private int top;
    private int bot;

    public Deque(int n){
        this.dequeSize=n;
        this.dequeArray = new char[dequeSize];
        this.top = dequeSize/2-1;
        this.bot = dequeSize/2;
    }

    public void addElementTop(char element){
        dequeArray[++top] = element;
    }

    public void addElementBot(char element){
        dequeArray[--bot] = element;
    }

    public char deleteElementTop(){
        return dequeArray[top--];
    }

    public char deleteElementBot(){
        return dequeArray[bot++];
    }

    public int getTop(){
        return dequeArray[top];
    }

    public int getBot(){
        return dequeArray[bot];
    }

    public boolean isEmpty(){
        return (top < bot);
    }

    public boolean isFull(){
        return (top == - 1 && bot == dequeSize);
    }

    public void clear(){
        this.top = dequeSize/2-1;
        this.bot = dequeSize/2;
    }
}