package PojoClasses;

public class BoardPojo {
    private String name  ;
    private String id  ;
    private String listID1  ;
    private String listID2  ;
    private String listID3  ;
    private String cardID ;
    private String attachID ;

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

    public String getListID1() {return listID1;}
    public void setListID1(String listID1) {this.listID1 = listID1;}
    public String getListID2() {return listID2;}
    public void setListID2(String listID2) {this.listID2 = listID2;}
    public String getListID3() {return listID3;}
    public void setListID3(String listID3) {this.listID3 = listID3;}
    public BoardPojo() {}
    public void setName(String name) {this.name = name;}
    public String getName() {return name;}
    public String getCardID() {return cardID;}
    public void setCardID(String cardID) {this.cardID = cardID;}
    public String getAttachID() {return attachID;}
    public void setAttachID(String attachID) {this.attachID = attachID;}
}