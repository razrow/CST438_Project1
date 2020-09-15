package com.example.cst438_project1;

import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class DataTypeConverter {
    @TypeConverter
    public static ArrayList<Assignment> assignmentsFromString(String value) {
        Type listType = new TypeToken<ArrayList<Assignment>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public String assignmentsFromArrayList(ArrayList<Assignment> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
    @TypeConverter
    public static ArrayList<Course> coursesFromString(String value) {
        Type listType = new TypeToken<ArrayList<Course>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public String coursesFromArrayList(ArrayList<Course> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
    @TypeConverter
    public static HashMap<Course, ArrayList<Assignment>>  courseAssignmentsFromString(String value) {
        Type listType = new TypeToken<ArrayList<Course>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public String courseAssignmentsFromArrayList(HashMap<Course, ArrayList<Assignment>> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

}
