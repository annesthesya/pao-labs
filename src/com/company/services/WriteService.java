package src.com.company.services;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WriteService {

    public static <T> String convertToCSV(T object, Class<T> tClass) throws IllegalAccessException {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        List<String> data = new ArrayList<>();
        Field[] fields;
        if (tClass.getSuperclass() != null && !tClass.getSuperclass().equals(Object.class)){
            Field[] parentFields = tClass.getSuperclass().getDeclaredFields();
            parentFields = Arrays.copyOfRange(parentFields, 2, parentFields.length);
            Field[] childFields = tClass.getDeclaredFields();
            fields = Arrays.copyOf(parentFields, parentFields.length + childFields.length);
            System.arraycopy(childFields, 0, fields, parentFields.length, childFields.length);
        }else{
            fields = tClass.getDeclaredFields();
            fields = Arrays.copyOfRange(fields, 2, fields.length);
        }
        for (var field : fields){
            field.setAccessible(true);
            if (field.getType() == int.class){
                data.add("i-" + field.get(object).toString());
            }else if (field.getType() == double.class){
                data.add("d-" + field.get(object).toString());
            }else if (field.getType() == boolean.class){
                data.add("b-" + field.get(object).toString());
            }else if (field.getType() == Date.class){
                data.add("da-" + format.format(field.get(object)));
            }else{
                data.add(field.get(object).toString());
            }
        }
        return String.join(",", data) + "\n";
    }

    public static <T> void write(String file, T object, Class<T> tClass) throws IllegalAccessException {
        String text = convertToCSV(object, tClass);
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(file, true))) {
            pw.append(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

