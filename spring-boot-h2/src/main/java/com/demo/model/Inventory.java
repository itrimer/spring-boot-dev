package com.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Inventory {

    @Id
    @GeneratedValue
    private Long inventoryId;
    private Long productId;
    private Long qty;
    private Long lockQty;
    private Long version;

    public Inventory() {
    }

    public Inventory(Long id, Long itemUkid){
        this.inventoryId = id;
        this.productId = itemUkid;
    }

    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getQty() {
        return qty;
    }

    public Long getLockQty() {
        return lockQty;
    }

    public void setLockQty(Long lockQty) {
        this.lockQty = lockQty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getAvailableQty(){
        if(qty == null && lockQty == null){
            return 0L;
        }

        if(qty == null){
            return - lockQty;
        }
        if(lockQty == null){
            return qty;
        }

        return qty - lockQty;
    }
}
