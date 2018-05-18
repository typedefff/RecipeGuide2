package com.example.bewika.recipeguide;

//pojo เอาไว้ดึงข้อมูลจากดาต้าเบส ต้องใช้ชื่อแอททริบิวเดียวกับตารางเมนู
public class DataMenu {
    private int menu_id;
    private String menuName;
    private String menuStep;
    private int id_type;

    public int getID(){
        return menu_id;
    }
    public String getMenuName(){
        return menuName;
    }
    public String getMenuStep(){
        return menuStep;
    }
    public int getIDtype(){
        return id_type;
    }
    public void setID(int menu_id){
        this.menu_id=menu_id;
    }
    public void setMenuName(String menuName){
        this.menuName=menuName;
    }
    public void setMenuStep(String menuStep){
        this.menuStep = menuStep;
    }
    public void setIDtype(int id_type){
        this.id_type=id_type;
    }
}
