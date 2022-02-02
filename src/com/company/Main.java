package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {


    public static void createFile(){
        try{
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter File name");
            String fileName = sc.next();
            File newFile =new File(fileName);
            newFile.createNewFile();
            System.out.println("File created "+newFile.getName());
            editFile(newFile);
        }catch (Exception e){
            System.out.println("Problem Occured,File not created!");
        }
    }



    public static void editFile(File file){
        String line=null;
        Scanner sc  = new Scanner(System.in);
        try {
            Scanner sc1 = new Scanner(file);
            while (sc1.hasNextLine()){
                line= sc1.nextLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try{
            FileWriter fileWriter = new FileWriter(file);
            System.out.println("Enter content");
            if (line==null){
                fileWriter.write(sc.nextLine());
            }
            else {
                fileWriter.write(line+" " + sc.nextLine());
            }
            fileWriter.close();
        }catch (Exception e){
            System.out.println("problem occurred");
        }
    }




    public static void readFile(File file){
        try {
            Scanner sc1 = new Scanner(file);
            while (sc1.hasNextLine()){
                String line = sc1.nextLine();
                System.out.println(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



    public static void deleteFile(File file){
        if(file.delete()) System.out.println("file deleted :"+file.getName());
        else System.out.println("problem occurred,cannot delete");
    }



    public static void fileTraversing(String path) {
        Scanner sc = new Scanner(System.in);
        int Index = 0;
        File file = new File(path);
        ArrayList<File> fileArrayList = new ArrayList<File>(Arrays.asList(file.listFiles())); //Arraylist of files
        Collections.sort(fileArrayList); //sorting in ascending order
        //printing files for each loop
        for (File file1 : fileArrayList) {
            System.out.println(Index + ": " + file1.getName());
            Index++;
        }
        System.out.println(Index + ":Go back");
        System.out.println("Enter Number to select file");

        try{
           int selectedIndex = sc.nextInt();


            if (selectedIndex == fileArrayList.size()) {
                menuFirst(path);
            }
            else {
                if (selectedIndex >= fileArrayList.size()) {
                    System.out.println("Entered wrong number");
                    fileTraversing(path);
                } else {
                    menuSecond(fileArrayList.get(selectedIndex), path);
                }
            }
            //last option  is "Go back" and Arraylist start with 0
        }catch(Exception e){
            System.out.println("Entered wrong number");
            fileTraversing(path);
        }
    }



    public static void searchFile(String path,String searchFileName){
        boolean match=false;
        File file = new File(path);
        ArrayList<File> fileArrayList = new ArrayList<File>(Arrays.asList(file.listFiles()));
        for (File file1:fileArrayList){
            if(searchFileName.equals(file1.getName())){
                System.out.println("File Found!!!");
                menuSecond(file1,path);
                match=true;
                break;
            }
        }
        if(match==false){
            System.out.println("file not found");
            menuFirst(path);
        }
    }





    public static void menuSecond(File file, String path){
        Scanner sc = new Scanner(System.in);
        System.out.println("1 : Read");
        System.out.println("2 : Write");
        System.out.println("3 : Delete");
        System.out.println("4 : Go to all file list");
        System.out.println("Select one option");
        int index = sc.nextInt();//fileArrayList starts with index 0;

        switch (index){
            case 1:{
                readFile(file);
                System.out.println("To continue Enter - any thing & press enter-");
                sc.next();
                fileTraversing(path);
                break;
            }
            case 2:{
                editFile(file);
                fileTraversing(path);
                break;
            }
            case 3:{
                deleteFile(file);
                fileTraversing(path);
                break;
            }
            case 4:{
                fileTraversing(path);
                break;
            }
            default: {
                System.out.println("Enter correct number!!!");
                menuSecond(file,path);
            }
        }
    }





    public  static  void  menuFirst(String path){

        System.out.println("1: Create New File");
        System.out.println("2: See All File");
        System.out.println("3: Search File");
        System.out.println("4: Exit");
        Scanner sc = new Scanner(System.in);
        int index = sc.nextInt();
        switch (index){
            case 1:{
                createFile();
                menuFirst(path);
                break;
            }
            case 2:{
                fileTraversing(path);
                break;
            }
            case 3:{
                System.out.println("Enter file Name");
                String searchFileName=sc.next();
                searchFile(path,searchFileName);
                break;
            }
            case 4:break;
            default:
                System.out.println("Entered wrong choice");
                menuFirst(path);
        }
    }





    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
     //   System.out.println("Enter path of Directory");
     //   String path = sc.next();
        menuFirst("/Users/saeali/IdeaProjects/PasswordManagement/");

    }
}
