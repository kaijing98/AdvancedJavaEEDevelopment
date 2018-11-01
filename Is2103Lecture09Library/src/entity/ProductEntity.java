package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;



@Entity

public class ProductEntity implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @Column(unique = true)
    @NotNull
    @Size(min = 7, max = 7)
    private String skuCode;
    @NotNull
    @Size(min = 8, max = 128)
    private String name;
    @NotNull
    @Size(min = 8, max = 255)
    private String description;
    @NotNull
    @PositiveOrZero
    private Integer quantityOnHand;
    @NotNull
    @PositiveOrZero
    private Integer reorderQuantity;
    @Column(precision = 11, scale = 2)
    @NotNull
    @Digits(integer = 9, fraction = 2)
    private BigDecimal unitPrice;
    @NotNull
    @Min(1)
    private Integer productRating;

    
    
    public ProductEntity() 
    {
//        quantityOnHand = 0;
//        reorderQuantity = 0;
//        unitPrice = new BigDecimal("0.00");
//        productRating = 1;
    }

    
    
    public ProductEntity(String skuCode, String name, String description, Integer quantityOnHand, Integer reorderQuantity, BigDecimal unitPrice, Integer productRating)
    {
        this();
        
        this.skuCode = skuCode;
        this.name = name;
        this.description = description;
        this.quantityOnHand = quantityOnHand;
        this.reorderQuantity = reorderQuantity;
        this.unitPrice = unitPrice;
        this.productRating = productRating;
    }
    
    
    
    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (this.productId != null ? this.productId.hashCode() : 0);
        
        return hash;
    }

    
    
    @Override
    public boolean equals(Object object)
    {
        if (!(object instanceof ProductEntity)) 
        {
            return false;
        }
        
        ProductEntity other = (ProductEntity) object;
        
        if ((this.productId == null && other.productId != null) || (this.productId != null && !this.productId.equals(other.productId))) 
        {
            return false;
        }
        
        return true;
    }

    
    
    @Override
    public String toString() 
    {
        return "entity.ProductEntity[ productId=" + this.productId + " ]";
    }

    
    
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantityOnHand() {
        return quantityOnHand;
    }

    public void setQuantityOnHand(Integer quantityOnHand) {
        this.quantityOnHand = quantityOnHand;
    }
    
    public Integer getReorderQuantity() {
        return reorderQuantity;
    }

    public void setReorderQuantity(Integer reorderQuantity) {
        this.reorderQuantity = reorderQuantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }    
    
    public Integer getProductRating() {
        return productRating;
    }

    public void setProductRating(Integer productRating) {
        this.productRating = productRating;
    }    
}