package ques9;

interface ques9_furniture {
    public void stressTest();
    public void fireTest();
}

abstract class ques9_Chair implements ques9_furniture {
    public abstract String chairType();
}

abstract class ques9_table implements ques9_furniture {
    public abstract String tableType();
}

