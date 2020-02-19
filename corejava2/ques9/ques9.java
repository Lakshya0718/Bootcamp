package ques9;

//9.  Design classes having attributes for furniture where there are wooden chairs and tables, metal chairs and tables.
// There are stress and fire tests for each products.
import java.util.Scanner;

public class ques9 {
    public static void main(String[] args){
        System.out.println("Enter Table Type");
        ques9_table table = null;
        Scanner input =  new Scanner(System.in);
        String str = input.next();
        if(str.equals("wooden")){
            table = new ques9_woodenTable();
        }
        else if (str.equals("metal")){
            table = new ques9_metalTable();
        }

        System.out.println(table.tableType());
        table.stressTest();
        table.fireTest();

        System.out.println("Enter Chair Type");
        ques9_Chair chair = null;
        Scanner input1 =  new Scanner(System.in);
        String str1 = input.next();
        if(str1.equals("wooden")){
            chair = new ques9_woodenChair();

        }
        else if (str1.equals("metal")){
            chair = new ques9_metalChair();
        }

        System.out.println(chair.chairType());
        chair.stressTest();
        chair.fireTest();
    }
}

class ques9_woodenTable extends ques9_table {
    @Override
    public void stressTest() {
        //To change body of implemented methods use File | Settings | File Templates.
        System.out.println("passes the stress test");
    }

    @Override
    public void fireTest() {
        //To change body of implemented methods use File | Settings | File Templates.
        System.out.println("failed the fire test");
    }

    @Override
    public String tableType() {
        //To change body of implemented methods use File | Settings | File Templates.
        String s = "This is a wooden Table";
        return s;
    }
}

class ques9_woodenChair extends ques9_Chair {
    @Override
    public void stressTest() {
        //To change body of implemented methods use File | Settings | File Templates.
        System.out.println("pases the stress test");
    }
    public String chairType(){
        return "Wooden Chair";
    }
    @Override
    public void fireTest() {
        //To change body of implemented methods use File | Settings | File Templates.
        System.out.println("FAILS Fire Test");
    }
}

class ques9_metalTable extends ques9_table {
    @Override
    public void stressTest() {

        System.out.println("Passed Stress Test");
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void fireTest() {
        //To change body of implemented methods use File | Settings | File Templates.
        System.out.println("Passed Fire Test");
    }

    @Override
    public String tableType() {
        //To change body of implemented methods use File | Settings | File Templates.
        String s = "This is a metal Table";
        return s;
    }
}
class ques9_metalChair extends ques9_Chair {
    @Override
    public void stressTest() {
        //To change body of implemented methods use File | Settings | File Templates.
        System.out.println("passes the stress test");
    }
    public String chairType(){
        return "metal chair";
    }
    public void fireTest() {
        System.out.println("pases the fire test");
        //To change body of implemented methods use File | Settings | File Templates.
    }
}