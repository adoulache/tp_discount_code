/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

/**
 *
 * @author Anass
 */
public class DiscountEntity {
    private String code;
    private float taux;
    
    public DiscountEntity(String code, float taux){
        this.code = code;
        this.taux = taux;
    }
    
    public void setCode(String code){
        this.code = code;
    }
    
    public String getCode(){
        return code;
    }
    
    public void setTaux(float taux){
        this.taux = taux;
    }
    
    public float getTaux(){
        return taux;
    }
}
