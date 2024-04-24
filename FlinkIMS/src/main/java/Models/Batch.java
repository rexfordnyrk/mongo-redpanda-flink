package Models;

import java.util.Date;

public class Batch {
    private String batchId;
    private Date purchaseDate;
    private Date expiryDate;
    private int currentStock;
    private Discount discount;

    /**
     * No args constructor for use in serialization
     *
     */
    public Batch() {
    }
    /**
     *
     * @param expiryDate
     * @param purchaseDate
     * @param currentStock
     * @param discount
     * @param batchId
     */
    public Batch(String batchId, Date purchaseDate, Date expiryDate, int currentStock, Discount discount) {
        super();
        this.batchId = batchId;
        this.purchaseDate = purchaseDate;
        this.expiryDate = expiryDate;
        this.currentStock = currentStock;
        this.discount = discount;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

}
