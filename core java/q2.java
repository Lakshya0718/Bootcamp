package assessment;

//Q2. Write a program to find the number of occurrences of the duplicate words in a string and print them ?
public class q2
{
    public static void main(String[] args)
    {
        String s3="hello hello you help me so that hello i help you";

        s3=s3.toLowerCase();
        String words[]= s3.split(" ");

        for(int i = 0 ; i < words.length ; i++)
        {  int count=1;
            for(int j = i+1 ; j < words.length; j++)
            {
                if(words[i].equals(words[j]))
                  {  count++;
                    words [j] ="0";
                  }

            }
            if (count>1&&words[i]!="0")
            System.out.println( words[i]+ " "+count);
        }

    }
}