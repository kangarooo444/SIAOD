import java.io.*;

public class MathCheck {
    public static void main(String[] args) throws IOException{
        FileReader fileReader = new FileReader("Math.txt");

        Stack stack = new Stack(15);

        boolean flag=true;


        while(fileReader.ready() && flag) {
            char t = (char) fileReader.read();

            if (Character.isLetter(t)) {
                if (stack.isEmpty())
                    stack.addElement(t);
                else if(stack.getTop()=='(')
                    stack.addElement(t);
                else flag=false;
            }

            if (t=='+' || t=='-'){
                if (Character.isLetter(stack.getTop())) stack.deleteElement();
                else flag=false;
            }

            if (t=='('){
                if (stack.isEmpty())
                    stack.addElement(t);
                else {
                    char s=stack.getTop();
                    if (s=='(' || s=='+' || s=='-')
                    stack.addElement(t);
                    else flag=false;
                }
            }
            if (t==')'){
                char s0=stack.deleteElement();
                char s1=stack.deleteElement();

                if (Character.isLetter(s0) && s1=='('){
                    t=s0;
                    if (Character.isLetter(t)) {
                        if (stack.isEmpty() || stack.getTop()=='(')
                            stack.addElement(t);
                        else flag=false;
                    }
                }
                else flag=false;
            }
        }

        if (flag==true) {
            char s0 = stack.deleteElement();
            if (stack.isEmpty() && Character.isLetter(s0)) flag=true;
            else flag=false;
        }

        System.out.println(flag);

        fileReader.close();
    }
}
