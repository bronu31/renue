package org.example;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringToRPN {


    public static ArrayDeque<String> toRPN(String data){
        ArrayDeque<String> return_stack=new ArrayDeque<>();
        Deque<String> operators_stack=new ArrayDeque<>();
        Matcher m = Pattern.compile("\\d+|\\<|\\>|\\=|\\&|\\|+|\\\"[^\\\"].*?\\\"|\\(|\\)|[\"|\"]").matcher(data);
        String peek;
        while(m.find()) {
            peek= operators_stack.isEmpty()?"" :operators_stack.peekLast();
            if (m.group(0).equals("\"")){
                return_stack.addLast("\"\"");
                m.find();
                continue;
            }
            if (peek.equals("<")&&m.group(0).equals(">")){
                operators_stack.addLast(operators_stack.pollLast()+m.group(0));
                continue;
            }
            if (!(peek.equals(""))&&(m.group(0).equals("&")||m.group(0).equals("||"))&&(peek.equals("<")||peek.equals(">")||
                    peek.equals("<>")||peek.equals("="))){
                return_stack.addLast(operators_stack.pollLast());
                peek= operators_stack.isEmpty()?"" :operators_stack.peekLast();
                if (m.group(0).equals("||")& peek.equals("&") ){
                    return_stack.addLast(operators_stack.pollLast());
                }
                operators_stack.addLast(m.group(0));
                continue;
            }
            if (m.group(0).equals(")")){
                while (!operators_stack.peekLast().equals("(")){
                    return_stack.addLast(operators_stack.pollLast());
                }
                operators_stack.pollLast();
                continue;
            }
            if (m.group(0).equals(">")||m.group(0).equals("<")||m.group(0).equals("=")||m.group(0).equals("<>")||m.group(0).equals("(")) {
                operators_stack.addLast(m.group(0));
                continue;
            }

            return_stack.addLast(m.group(0));
        }
        while (!operators_stack.isEmpty()){
            return_stack.addLast(operators_stack.pollLast());
        }
        return return_stack;
    }
}
