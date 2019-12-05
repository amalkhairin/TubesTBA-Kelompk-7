package model;

import java.util.*;

public class LexicAnalyzer {

    Stack stack = new Stack();
    
    // state 9 & 10
    public void state9_10(String str) {
        //final state 9
        if ('(' == str.charAt(0)) {
            this.stack.push("9");
            if (str.length() > 1) {
                //goto state 0
                state0(str.substring(1));
            }
        //final state 10
        } else if (')' == str.charAt(0)) {
            this.stack.push("10");
            if (str.length() > 1) {
                //goto state 0
                state0(str.substring(1));
            }
        }
    }
    
    //state 1
    public void state1(String str) {
        if (str.length() > 1 && ('p' == str.charAt(1) || 'q' == str.charAt(1) || 'r' == str.charAt(1) || 's' == str.charAt(1))) {
            this.stack.push("error");
        } else if (str.length() > 1 && (str.charAt(1) == '(' || str.charAt(1) == ')')) {
            this.stack.push("1");
            //goto state 0
            state0(str.substring(1));
        } else {
            this.stack.push("1");
        }
    }
    
    // state 2
    public void state2(String str) {
        //state Q1
        if (str.length() == 3 && str.charAt(0) == 'n') {
            //state Q2
            if (str.charAt(1) == 'o') {
                //final state 2
                if (str.charAt(2) == 't') {
                    this.stack.push("2");
                } else {
                    this.stack.push("error");
                }
            } else {
                this.stack.push("error");
            }
        } else if(str.length() > 3 && (str.charAt(3) == '(' || str.charAt(3) == ')')) {
            state2(str.substring(0,3));
            //goto state 0
            state0(str.substring(3));
        } else {
            this.stack.push("error");
        }
    }
    
    //state 3
    public void state3(String str){
        //state Q3
        if (str.length() == 3 && str.charAt(0) == 'a') {
            //state Q4
            if (str.charAt(1) == 'n') {
                //final state 3
                if (str.charAt(2) == 'd') {
                    this.stack.push("3");
                } else {
                    this.stack.push("error");
                }
            } else {
                this.stack.push("error");
            }
        } else if(str.length() > 3 && (str.charAt(3) == '(' || str.charAt(3) == ')')) {
            state3(str.substring(0,3));
            //goto state 0
            state0(str.substring(3,str.length()));
        } else {
            this.stack.push("error");
        }
    }
    
    
    //state 4
    public void state4(String str){
        //state Q5
        if (str.length() == 2 && str.charAt(0) == 'o') {
            //final state 4
            if (str.charAt(1) == 'r') {
                this.stack.push("4");
            } else {
                this.stack.push("error");
            }
        } else if(str.length() > 2 && (str.charAt(2) == '(' || str.charAt(2) == ')')) {
            state4(str.substring(0, 2));
            //goto state 0
            state0(str.substring(2,str.length()));
        } else {
            this.stack.push("error");
        }
    }
    
    
    //state 5
    public void state5(String str){
        //state Q6
        if (str.length() == 3 && str.charAt(0) == 'x') {
            //state Q7
            if (str.charAt(1) == 'o') {
                //final state 5
                if (str.charAt(2) == 'r') {
                    this.stack.push("5");
                } else {
                    this.stack.push("error");
                }
            } else {
                this.stack.push("error");
            }
        } else if(str.length() > 3 && (str.charAt(3) == '(' || str.charAt(3) == ')')) {
            state5(str.substring(0, 3));
            //goto state 0
            state0(str.substring(3,str.length()));
        } else {
            this.stack.push("error");
        }
    }
    
    
    //state 6 & 8
    public void state6_8(String str){
        //state Q8
        if (str.length() == 2 && str.charAt(0) == 'i') {
            //final state 6
            if (str.charAt(1) == 'f') {
                this.stack.push("6");
            } else {
                this.stack.push("error");
            }
        } else if(str.length() > 2 && str.charAt(2) == 'f') {
            //goto state8
            state8(str);
        } else if(str.length() > 2 && (str.charAt(2) == '(' || str.charAt(2) == ')')) {
            state6_8(str.substring(0, 2));
            //goto state0
            state0(str.substring(2, str.length()));
        } else {
            this.stack.push("error");
        }
    }
    //state 7
    public void state7(String str){
        //state Q9
        if(str.length() == 4 && str.charAt(0) == 't'){
            //state Q10
            if(str.charAt(1) == 'h'){
                //state Q11
                if(str.charAt(2) == 'e'){
                    //final state 7
                    if(str.charAt(3) == 'n'){
                        this.stack.push("7");
                    } else {
                        this.stack.push("error");
                    }
                } else {
                    this.stack.push("error");
                }
            } else {
                this.stack.push("error");
            }
        } else if(str.length() > 4 && (str.charAt(4) == '(' || str.charAt(4) == ')')){
            state7(str.substring(0,4));
            //goto state 0
            state0(str.substring(4,str.length()));
        } else {
            this.stack.push("error");
        }
    }
    
    //state 8
    public void state8(String str){
        //state Q12
        if (str.length() == 3 && str.charAt(0) == 'i') {
            //state Q13
            if (str.charAt(1) == 'f') {
                //final state 8
                if (str.charAt(2) == 'f') {
                    this.stack.push("8");
                } else {
                    this.stack.push("error");
                }
            } else {
                this.stack.push("error");
            }
        } else if(str.length() > 3 && (str.charAt(3) == '(' || str.charAt(3) == ')')) {
            state8(str.substring(0, 3));
            //goto state 0
            state0(str.substring(3,str.length()));
        } else {
            this.stack.push("error");
        }
    }
    
    //state 0
    public void state0(String str) {
        if ('p' == str.charAt(0) || 'q' == str.charAt(0) || 'r' == str.charAt(0) || 's' == str.charAt(0)) {
            if (str.length() == 1) {
                //goto state 1
                state1(str);
            } else {
                //goto state 1
                state1(str);
            }
        } else if ('n' == str.charAt(0)) {
            //goto state 2
            state2(str);
        } else if('a' == str.charAt(0)){
            //goto state 3
            state3(str);
        } else if('o' == str.charAt(0)){
            //goto state 4
            state4(str);
        } else if('x' == str.charAt(0)){
            //goto state 5
            state5(str);
        } else if('i' == str.charAt(0)){
            //goto state 6 or 8
            state6_8(str);
        }else if('t' == str.charAt(0)){
            //goto state 7
            state7(str);
        } else if('(' == str.charAt(0) || ')' == str.charAt(0)){
            //goto state 9 or 10
            state9_10(str);
        } else {
            //error state
            this.stack.push("error");
        }
    }

    public Stack getStack() {
        return stack;
    }

}
