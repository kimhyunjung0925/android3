package com.koreait.com;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.koreait.com.ch07.BookPersonActivity;

public class Utils {

    public static int parseStringToInt (String val) {
        return parseStringToInt(val,0);
    }

    public static int parseStringToInt(String val, int defVal) {
        try {
            return Integer.parseInt(val);
        } catch(Exception e) {
            return  defVal;
        }
    }

    public static String getNumberComma(int val) {
        return String.format("%,d", val);
    }

    public static String getNumberComma(String val) {
        int intVal = parseStringToInt(val);
        return getNumberComma(intVal);
        // return getNumberComma(parseStringToInt(val));
    }


}


/*
//예외처리

        //*필수 try catch
        int intVal = 0;
        try {
            intVal = Integer.parseInt(val);
        } catch(Exception e) {
            // 예외가 발생되었을 때 실행되고 싶은 것들을 여기에다가 작성
            e.printStackTrace(); //에러 내용을 로그에 찍는다.
        }
        //*옵션 finally
        finally {
            // 에러가 터지든 안터지든 !!무조건!! 실행됐으면 하는 것들을 작성.
        }
        return intVal;
    }
 */

/*

    public void ddd(View v) {
        TextView tv = (TextView) v;
        Toast.makeText(this, tv.getText(), Toast.LENGTH_SHORT).show();
    }



 */