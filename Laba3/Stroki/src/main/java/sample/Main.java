package sample;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.out;


public class Main {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        try {
            out.println("Stroka with leksems:");
            String firstline = reader.readLine();
            out.println("Stroka with symbols:");
            String secondline = reader.readLine();
            StringTokenizer token = new StringTokenizer(firstline, secondline);
            ArrayList<String> tokens = new ArrayList<>();
            while (token.hasMoreTokens()) {
                tokens.add(token.nextToken());
            }

            // Регулярное выражение для поиска вещественных чисел
            Pattern numberPattern = Pattern.compile("-?\\d+");
            ArrayList<Integer> numbers = new ArrayList<>();

            for (String tok : tokens) {
                Matcher matcher = numberPattern.matcher(tok);
                if (matcher.matches()) {
                    numbers.add(Integer.parseInt( tok));
                }
            }

            // Регулярное выражение для поиска времени в формате ЧЧ-ММ
            Pattern timePattern = Pattern.compile("\\d{2}-\\d{2}");
            ArrayList<Date> times = new ArrayList<>();
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH-mm");
            dateFormat.setLenient(false);
            Matcher matcher = timePattern.matcher(firstline);
            while (matcher.find()) {
                try {
                    Date date = dateFormat.parse(matcher.group());
                        times.add(date);

                } catch (ParseException e) {
                    System.out.println("Некорректное время: " + matcher.group());
                }
            }
            // Сортируем список валидных времен
            Collections.sort(times, new Comparator<Date>() {
                @Override
                public int compare(Date d1, Date d2) {
                    return d1.compareTo(d2);
                }
            });


            
            out.println("Сортировка время:");
            for (Date time : times) {
                System.out.println(new SimpleDateFormat("HH:mm").format(time));
            }
////////////////////////////////////////////////////////
            // Печать лексем
            out.println("Лексемы:");
            for (String lexeme : tokens) {
                out.println(lexeme);
            }

            // Печать вещественных чисел
            out.println("Вещественные числа:");
            for (Integer number : numbers) {
                out.println(number);
            }
            // Печать времени в формате ЧЧ-ММ
            out.println("Время:");

            for (Date time : times) {
                out.println(new SimpleDateFormat("HH:mm").format(time));
            }
            ////////////////////////////////////////////////////////
            String randomLexeme = "randomLexeme";
            if (!times.isEmpty()) {
                String lastTime = dateFormat.format(times.get(times.size() - 1));
                int index = firstline.lastIndexOf(lastTime);
                StringBuilder sb = new StringBuilder(firstline);
                sb.insert(index, randomLexeme + " ");
                firstline = sb.toString();
            } else {
                int middleIndex = firstline.length() / 2;
                StringBuilder sb = new StringBuilder(firstline);
                sb.insert(middleIndex, " " + randomLexeme + " ");
                firstline = sb.toString();
            }


            // Удаляем подстроку с самой большой длиной, начинающуюся не с цифры и не с символа
            String longestSubstring = " ";
            for (String tok : tokens) {
                if (!Character.isDigit(tok.charAt(0)) && !Character.isLetter(tok.charAt(0)) && tok.length() > longestSubstring.length()) {
                    longestSubstring = tok;
                }
            }
            tokens.remove(longestSubstring);

            /////////////////////////////
           out.println("Добавление случайной лексемы:"+firstline);
            out.println("После удаления наибольшей подстроки:" + String.join(" ",tokens));
            ///////////////////////////
        }catch(IOException e){
        e.printStackTrace();
        }
    }

}