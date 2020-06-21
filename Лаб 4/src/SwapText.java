import java.io.*;

public class SwapText {
    public static void main(String[] args) throws IOException{
        FileReader fileReader = new FileReader("Input.txt");
        FileWriter fileWriter = new FileWriter("Output.txt");

        Deque deq = new Deque(60);

        while(fileReader.ready()){
            char ch=(char)fileReader.read();
            if(Character.isLetter(ch)) fileWriter.write((char)ch);
            else if(Character.isDigit(ch)) deq.addElementTop(ch);
            else {
                char c= deq.deleteElementBot();
                deq.addElementBot(ch);
                deq.addElementBot(c);
            }

            if (ch=='\n') {
                while (!deq.isEmpty())
                        fileWriter.write((char)deq.deleteElementTop());
                deq.clear();
            }
        }

        fileReader.close();
        fileWriter.close();
    }
}
