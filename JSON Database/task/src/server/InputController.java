package server;

import java.util.Scanner;

public class InputController {
    CellDataBase cellDataBase;
    InputController(){
        this.cellDataBase = new CellDataBase();
    }
    protected void inputMenu(){
        final Scanner scanner = new Scanner(System.in);
        boolean active = true;
        while (active) {
            final String input = scanner.next();
            switch (input) {
                case "set" -> set(scanner.nextInt(),scanner.nextLine());
                case "get" -> get(scanner.nextInt());
                case "delete" -> delete(scanner.nextInt());
                case "exit" -> active = false;
                default -> {
                    System.out.println("ERROR\n");
                }
            }
        }
    }

    public String get(int index){
        return cellDataBase.getCellByIndex(index-1);
    }
    protected void set(int index, String value){
        this.cellDataBase.setCellByIndex(index-1,value);
    }
    protected void delete(int index){
        this.cellDataBase.deleteCellByIndex(index-1);
    }


}
