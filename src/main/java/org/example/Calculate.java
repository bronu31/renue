package org.example;

import java.util.Deque;
import java.util.Stack;
import java.util.regex.Matcher;

public class Calculate {

    Stack<Boolean> booleanStack=new Stack<>();
    private boolean error=false;
    public  boolean beginCalculation(Deque<String> stack, Matcher data){
        while (!stack.isEmpty()){
            if (stack.peekFirst().equals("&")||stack.peekFirst().equals("||")){
                calculateBoolean(stack.pop());
                continue;
            }
            sorter(stack.pop(),stack.pop(),stack.pop(),data);
        }
        return booleanStack.pop();
    }

    private  void calculateBoolean(String pop) {
        if (pop.equals("&")){
            booleanStack.push(booleanStack.pop()&&booleanStack.pop());
        }else {booleanStack.push(booleanStack.pop()||booleanStack.pop());}
    }

    public  void sorter(String left, String right, String operand, Matcher data){
        int temp=Integer.parseInt(left);
        try {
            if (temp<1||temp>14) throw new IllegalArgumentException("Illegal filter data");
            if (temp == 1||temp==9) {
                calculateNums(Integer.parseInt(data.group(temp)), Integer.parseInt(right), operand);
            }
            if (temp == 7||temp==8) {
                calculatedDouble(Double.parseDouble(data.group(temp)),Double.parseDouble(right),operand);
            }
            if ((temp >= 2&&temp<=6)||(temp >= 11&&temp<=14)) {
                calculateStrs(data.group(temp),right,operand);
            }
            if(temp==10) {
                if (data.group(temp).contains(".") || data.group(temp).contains("\\")) {
                    calculateStrs(data.group(10), right, operand);
                } else {
                    calculateNums(Integer.parseInt(data.group(10)), Integer.parseInt(right), operand);
                }
            }

        }
        catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Illegal filter data");
        }
    }

    private void calculateNums(int group, int right, String operand) {
        switch (operand){
            case "<":booleanStack.push(group<right);break;
            case ">":booleanStack.push(group>right);break;
            case "<>":booleanStack.push(group!=right);break;
            case "=":booleanStack.push(group==right);break;
        }

    }

    private void calculateStrs(String group, String right, String operand) {
        switch (operand){
            case "<":booleanStack.push(group.length()<right.length());break;
            case ">":booleanStack.push(group.length()>right.length());break;
            case "<>":booleanStack.push(!group.equals(right));break;
            case "=":booleanStack.push(group.equals(right));break;
        }
    }
    private void calculatedDouble(double group, double right, String operand) {

        switch (operand){
            case "<":booleanStack.push(group<right);break;
            case ">":booleanStack.push(group>right);break;
            case "<>":booleanStack.push(group!=right);break;
            case "=":booleanStack.push(group==right);break;
        }
    }
}
