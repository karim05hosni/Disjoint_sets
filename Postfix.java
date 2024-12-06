class Stack {
    public int top;
    char arrStack[];
    public Stack(int size) {
        this.top = -1;
        this.arrStack = new char[size];
    }
    public boolean isEmpty(){
        return this.top == -1;
    }

    public void push(char item){
        this.top ++;
        this.arrStack[this.top] = item;
    }
    public char pop(){
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        char n =  this.arrStack[top];
        this.arrStack[top] = ' ';
        this.top --;
        return n;
    }

    public char top(){
        return this.arrStack[this.top];
    }

    public void display(){
        System.out.println("{");
        // for (char i : top) {
            int counter = 0;
            while (counter <= this.top) {
                System.out.println(this.arrStack[counter]);
                counter++;
            }
    //   }
        System.out.println("}");
    }
}

public class Postfix {
    public static void main(String[] args) {
        toPostfix("2*((3+(4/2))/2)");
    }
    public static void toPostfix(String expression){
        Stack stack = new Stack(expression.length());
        Stack nums = new Stack(expression.length());
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (Character.isDigit(c)) {
                nums.push(c);
            } else if ( c == '*' || c=='+' || c=='-' || c=='/'){
                while (!stack.isEmpty() && (precedence(c) <= precedence(stack.top()) || c=='(' || c==')')){
                    char n = stack.pop();
                    nums.push(n);
                }
                    stack.push(c);
            } else if (c=='(') {
                stack.push(c);
            } else if (c==')'){
                while (stack.top() != '(' && !stack.isEmpty()) {
                    nums.push(stack.pop());
                }
                stack.pop();
            }
            }
        
        while (!stack.isEmpty()) { 
            char n = stack.pop();
            if (n != '(' || n!=')')
                nums.push(n);
        }
        nums.display();
    }
    private static int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0;
        }
    }
}

