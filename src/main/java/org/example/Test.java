package org.example;
import java.lang.reflect.Field;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Test {
    double doubleNum = 1.6;
    int intNum =10;
    String word = "unit";
    boolean boolType = true;
    String emptyStr;
    double[] arrayNum  = new double[] {1,3,-2};
    String[] arrayStr  = new String[] {"Eins","Zwei","Drei"};

    //      тут было экспериментальное получение значения через toString
    //      public String toString() { return String.valueOf(cactus); }

    public static void main(String args[]) throws IllegalAccessException {
        Test test = new Test();
        // функция среагировала на массив:
        //if (test.seasons.getClass().isArray()) System.out.println("FIGHT");

        Field[] declaredFields = Test.class.getDeclaredFields();
        for (Field field : declaredFields) {
            System.out.println("Name of field: " + field.getName() + ", type - " + field.getType()
                    + ", value of variable: " + field.get(test));
            //  снова поймала массив
            // if (field.get(test)==test.seasons) System.out.println("FIGHT");
            String s = field.getType().getName();
            System.out.println(s);
            //System.out.println(test.toString());
            // след строка получает название класса
            // System.out.println(((Object)test).getClass().getSimpleName());
        }
        int z = declaredFields.length;
        System.out.println(z + "___________ \n\n");

        //  конец тестовой части

        System.out.print('{');
        for (Field field : declaredFields)
        {
            // обработка нулей - дальше уже по типам
            if (field.get(test)==null)
                System.out.print('"' + field.getName() + "\":null");
            // обработка string
            else if (field.getType().getName() == "java.lang.String")
                System.out.print('"' + field.getName() + "\":\"" + field.get(test) + '"');
            // обработка массивов
            else if (field.get(test).getClass().isArray())
            {
                //System.out.print(" тип массива - " + field.get(test).getClass().getComponentType().getName());
//                Object value = field.get(test);
//                System.out.print('"' + field.getName() + "\":" + Arrays.deepToString(new Object[]{value}));
                System.out.print('"' + field.getName() + "\":");
                Object array = field.get(test);
                int length = Array.getLength(array);
                System.out.print("[");
                for (int i = 0; i < length; i++) {
                    if (i != 0)
                        System.out.print(", ");
                    System.out.print(Array.get(array, i));
                }
                System.out.print("]");
            }
            // обработка чисел
            else
                System.out.print('"' + field.getName() + "\":" + field.get(test));

            // оформление
            z--;
            if (z>=1) System.out.print(";");
        }
        System.out.print('}');
// аналогичный рабочий способ
//        Field field = test.getClass().getDeclaredFields()[0];
//        System.out.println(field.getType() + " " + field.getName() + " = " + test.toString());
    }
}