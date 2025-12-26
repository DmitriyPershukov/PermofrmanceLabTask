package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class task3 {
    public static void main(String[] args) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        TestResults testResults;
        Tests tests;
        try(FileReader testResultsReader = new FileReader(args[0]);
            FileReader testsReader = new FileReader(args[1])
        ) {
            JsonReader jsonReader = new JsonReader(testResultsReader);
            testResults = gson.fromJson(jsonReader, TestResults.class);

            jsonReader = new JsonReader(testsReader);
            tests = gson.fromJson(jsonReader, Tests.class);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        HashMap<Integer, Test> idTestMap = buildIdTestMap(tests);;
        for(TestResult testResult: testResults.getValues()){
            idTestMap.get(testResult.getId()).setValue(testResult.getValue());
        }

        try(FileWriter writer = new FileWriter(args[2])) {
            String b = gson.toJson(tests);
            gson.toJson(tests, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static HashMap<Integer, Test> buildIdTestMap(Tests testReport){
        Test[] tests = testReport.getTests();
        HashMap<Integer, Test> idTestMap = new HashMap<>();
        for (Test test: tests){
            addTestToMap(test, idTestMap);
        }
        return idTestMap;
    }
    private static void addTestToMap(Test test, HashMap<Integer, Test> map){
        map.put(test.getId(), test);
        if(test.getValues() != null){
            for(Test nestedTest: test.getValues()){
                addTestToMap(nestedTest, map);
            }
        }
    }
}