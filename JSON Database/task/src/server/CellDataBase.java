package server;

import java.sql.Array;
import java.sql.SQLOutput;
import java.util.Arrays;

public class CellDataBase {

    private String[] dataBase;

    public String[] getDataBase() {
        return dataBase.clone();
    }
    public void setDataBase(String[] dataBase) {
        this.dataBase = dataBase;
    }

    public String getCellByIndex(int index){

        try {
            if(!this.getDataBase()[index].equals("") ){
                System.out.println(this.getDataBase()[index]);
                return this.getDataBase()[index];
            }else{
                System.out.println("ERROR");
            }
        }catch (Exception e){
            System.out.println("ERROR");
        }
        return null;
    }

    public void setCellByIndex(int index, String value){
        try{
            this.dataBase[index] = value;
            System.out.println("OK");
        }catch (Exception e){
            System.out.println("ERROR");
        }
    }

    public void deleteCellByIndex(int index){
        try{
            this.dataBase[index] = "";
            System.out.println("OK");
        }catch (Exception e){
            System.out.println("ERROR");
        }
    }

    CellDataBase(){
        this.dataBase = new String[100];
        Arrays.fill(this.dataBase,"");

    }



}
