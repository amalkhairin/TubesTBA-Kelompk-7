package model;

import java.util.ArrayList;

public class Validator {
    
    //check posisi tanda kurung
    public boolean cekPosisiKurung(ArrayList<String> list) {
        int bukakurung = 0, tutupkurung = 0; //init state
        boolean flag1 = false, flag2 = true; //init state
        //check jika tanda kurung tanpa isi
        for (int i = 0; i < list.size(); i++) {
            if ("9".equals(list.get(i))) {
                if (i != list.size() - 1) {
                    if ("10".equals(list.get(i + 1))) {
                        flag2 = false; //tidak valid
                        break;
                    }
                }
            }
        }
        //check buka kurung lebih dulu dari tutup kurung
        for (int i = 0; i < list.size(); i++) {
            //kondisi valid 9 < 10;
            if ("9".equals(list.get(i))) {
                bukakurung = 1;
                for (int j = i; j < list.size(); j++) {
                    if ("10".equals(list.get(j))) {
                        tutupkurung = 2;
                        break;
                    }
                }
                break;
            //kondisi invalid 9 > 10;
            } else if ("10".equals(list.get(i))) {
                tutupkurung = 1;
                for (int j = i; j < list.size(); j++) {
                    if ("9".equals(list.get(j))) {
                        bukakurung = 2;
                        break;
                    }
                }
                break;
            }
        }
        //check valid
        flag1 = bukakurung < tutupkurung && (bukakurung != 0 && tutupkurung != 0);
        if (flag1 == true && flag2 == true) {
            return true;  //valid
        } else {
            return false;  //tidak valid
        }
    }
    
    //check jumlah tanda kurung
    public boolean cekJumlahKurung(ArrayList<String> list) {
        int bukakurung = 0, tutupkurung = 0; //init state
        boolean flag1, flag2;   //init state
        //check jumlah buka kurung == tutup kurung
        for (String s : list) {
            if ("9".equals(s)) {
                bukakurung++;
            }
            if ("10".equals(s)) {
                tutupkurung++;
            }
        }
        //jika tidak pakai tanda kurung
        if (bukakurung == 0 && tutupkurung == 0) {
            return true;    //valid
        } else {
            flag1 = bukakurung == tutupkurung;
            flag2 = cekPosisiKurung(list);         //check posisi tanda kurung
            if (flag1 == false && flag2 == false) {
                return false;   //invalid
            } else {
                return flag1 == flag2;
            }
        }
    }
    
    //check operator valid
    public boolean isOperator(String str) {
        return "2".equals(str) || "3".equals(str) || "4".equals(str) || "5".equals(str)
          || "6".equals(str) || "7".equals(str) || "8".equals(str);
    }
    
    //chek operand valid
    public boolean isOperand(String str) {
        return "1".equals(str);
    }
    
    //check valid proposisi
    public boolean isValidProposisi(ArrayList<String> list) {
        boolean flag = true;    //valid
        if (list.size() > 1) {
            for (int i = 0; i < list.size(); i++) {
                if (i != list.size() - 1 && "1".equals(list.get(i)) && "1".equals(list.get(i + 1))) {
                    flag = false;   //invalid
                }
            }
        }
        return flag;
    }

    public boolean validIfThen(ArrayList<String> list) {
        boolean flag = false; //init state
        int jika = 0, maka = 0; //init state
        //check if and then is valid
        for (int i = 0; i < list.size(); i++) {
            if ("6".equals(list.get(i))) {
                jika++;
                for (int j = i; j < list.size(); j++) {
                    if ("7".equals(list.get(j))) {
                        maka++;
                        flag = true;
                        break;
                    }
                }
            }
        }
        // check jumlah if dan then
        if (jika == 0 && maka == 0) {
            flag = true;    //valid
        }
        return flag;
    }

    public boolean validIff(ArrayList<String> list) {
        boolean flag = false;   //init state
        //check penempatan iff
        if (!"8".equals(list.get(0))) {
            for (int i = 0; i < list.size(); i++) {
                if (isOperand(list.get(i))) {
                    if (i < list.size() - 2) {
                        if ("8".equals(list.get(i + 1)) && isOperand(list.get(i + 2))) {
                            flag = true;    //valid jika operand - iff - operand
                            break;
                        }
                    }
                }
            }
            //jika tidak menggunakan iff
            for (String s : list) {
                if (!"8".equals(s)) {
                    flag = true;    //valid
                }
            }
        } else {
            flag = false;   //invalid
        }
        return flag;
    }
    
    //check valid operator dan penempatan
    public boolean isValidOperator(ArrayList<String> list) {
        boolean flag = true; // init state
        //check penggunaan if
        if (validIfThen(list) == false) {
            flag = false;
        }
        //check penggunaan iff
        if (validIff(list) == false) {
            flag = false;
        }
        //check operator diawal dan panjang string 1
        if (list.size() == 1 && isOperator(list.get(0))) {
            flag = false;
        }
        //check operator diakhir string
        for (int i = 0; i < list.size(); i++) {
            if (isOperator(list.get(i)) && i == list.size() - 1) {
                flag = false;
            }
        }
        //check operator - operator
        for (int i = 0; i < list.size(); i++) {
            if (isOperator(list.get(i))) {
                if (i < list.size() - 1) {
                    if (isOperator(list.get(i + 1))) {
                        flag = false;
                    }
                    break;
                }
            }
        }
        return flag;
    }

    public boolean validatorCek(ArrayList<String> list) {
        //check valid
        return isValidOperator(list) == true && isValidProposisi(list) == true && cekJumlahKurung(list) == true;
    }

}
