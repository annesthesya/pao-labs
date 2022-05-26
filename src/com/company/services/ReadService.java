package src.com.company.services;

import src.com.company.assignments.Work;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.reflect.*;

public class ReadService<T>{

    public ReadService() {
    }

    public static <T extends Serializable> T read(String file, Class<T> tClass){
        try (FileInputStream in = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(in)) {
            return tClass.cast(ois.readObject());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> List<T> readCSV(String file, Class<T> tClass){
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        try {
            List<T> objectList = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            reader.readLine();
            String buf = reader.readLine();
            while (buf != null){
                String[] values = buf.split(",");
                T object = tClass.getConstructor().newInstance();
                Field[] fields;
                if (tClass.getSuperclass() != null  && !tClass.getSuperclass().equals(Object.class)){
                    Field[] parentFields = tClass.getSuperclass().getDeclaredFields();
                    parentFields = Arrays.copyOfRange(parentFields, 2, parentFields.length);
                    Field[] childFields = tClass.getDeclaredFields();
                    fields = Arrays.copyOf(parentFields, parentFields.length + childFields.length);
                    System.arraycopy(childFields, 0, fields, parentFields.length, childFields.length);
                }else{
                    fields = tClass.getDeclaredFields();
                    fields = Arrays.copyOfRange(fields, 2, fields.length);
                }
                for (int i = 0; i < values.length; i++){
                    var field = fields[i];
                    field.setAccessible(true);
                    var value = values[i];
                    if (value.startsWith("i-")){
//                        System.out.println("ok1");
                        field.set(object, Integer.parseInt(value.substring(2)));
                    } else if (value.startsWith("d-")){
//                        System.out.println("ok2");
                        field.set(object, Double.parseDouble(value.substring(2)));
                    } else if (value.startsWith("b-")){
//                        System.out.println("ok3");
                        field.set(object, Boolean.parseBoolean(value.substring(2)));
                    } else if (value.startsWith("da-")) {
//                        System.out.println("ok4");
                        field.set(object, format.parse(value.substring(3)));
                    }else if(value.startsWith("li-")){
//                        System.out.println("ok5");
                        List<Integer> listValues = new ArrayList<>();
                        String[] arrayValues = value.substring(3).split("\\|");
                        for (var av : arrayValues){
                            listValues.add(Integer.parseInt(av));
                        }
                        field.set(object, listValues);
                    }else if(value.startsWith("lw-")){
//                        System.out.println("ok6");
                        List<Work> listValues = new ArrayList<>();
                        String[] arrayValues = value.substring(4).split("\\|");
                        for (var av : arrayValues){
                            String[] workAttr = av.substring(4).split("-");
                            Work w = new Work(Integer.parseInt(workAttr[0]), Integer.parseInt(workAttr[1]),
                                    format.parse(workAttr[2]), Boolean.parseBoolean(workAttr[3]));
                            listValues.add(w);
                        }
                        field.set(object, listValues);
                    }else {
                        field.set(object, value);
                    }
                }
            objectList.add(object);
            buf = reader.readLine();
            }
            reader.close();
            return objectList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
