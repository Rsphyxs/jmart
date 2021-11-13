package IlhamJmartMH;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Vector;

public class JsonTable <T>extends Vector<T> {
    public final String filepath;
    private static final Gson gson = new Gson();

    public JsonTable(Class<T> clazz, String filepath) throws IOException{
        this.filepath = filepath;
        try{
            Class<T[]> array = (Class<T[]>) Array.newInstance(clazz,0).getClass();
            T[] loaded = readJson(array, filepath);
            if(loaded!=null){
                Collections.addAll(this,loaded);
            }
        }
        catch (FileNotFoundException e){
            File file = new File(filepath);
            File file1 = file.getParentFile();
            if(file1!=null){
                file1.mkdirs();
            }
            file.createNewFile();
        }
    }

    public static <T> T readJson (Class<T> clazz, String filepath) throws FileNotFoundException{
       JsonReader jsonReader = new JsonReader(new FileReader(filepath));
       T object = gson.fromJson(jsonReader, clazz);
       return object;
    }

    public void writeJson () throws IOException{
        writeJson(this, this.filepath);
    }

    public static void writeJson (Object object, String filepath) throws IOException{
            FileWriter writer = new FileWriter(filepath);
            String json = gson.toJson(object);
            writer.write(json);
            writer.close();
    }
}
