package model;
// Generated 26.04.2012 16:15:26 by Hibernate Tools 3.2.1.GA



/**
 * IngredientTbl generated by hbm2java
 */
public class IngredientTbl  implements java.io.Serializable {


     private Integer id;
     private Receipt receipt;
     private String name;
     private String amount;
     private String unit;

    public IngredientTbl() {
    }

	
    public IngredientTbl(Receipt receipt, String name) {
        this.receipt = receipt;
        this.name = name;
    }
    public IngredientTbl(Receipt receipt, String name, String amount, String unit) {
       this.receipt = receipt;
       this.name = name;
       this.amount = amount;
       this.unit = unit;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Receipt getReceipt() {
        return this.receipt;
    }
    
    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getAmount() {
        return this.amount;
    }
    
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getUnit() {
        return this.unit;
    }
    
    public void setUnit(String unit) {
        this.unit = unit;
    }




}


