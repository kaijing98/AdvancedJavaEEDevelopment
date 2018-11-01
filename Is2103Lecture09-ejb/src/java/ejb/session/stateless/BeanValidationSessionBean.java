package ejb.session.stateless;

import entity.ProductEntity;
import java.math.BigDecimal;
import java.util.Set;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;



@Stateless
@Local(BeanValidationSessionBeanLocal.class)
@Remote(BeanValidationSessionBeanRemote.class)

public class BeanValidationSessionBean implements BeanValidationSessionBeanLocal, BeanValidationSessionBeanRemote 
{
    @Override
    public void validateProgrammatically()
    {
        ProductEntity productEntity = new ProductEntity();
        
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<ProductEntity>>errors = validator.validate(productEntity);
        
        for(ConstraintViolation error:errors)
        {
            System.err.println("******* Error: " + error.getPropertyPath() + "; " + error.getInvalidValue() + "; " + error.getMessage());
        }
        
        productEntity.setQuantityOnHand(0);
        productEntity.setReorderQuantity(0);
        productEntity.setUnitPrice(new BigDecimal("0.00"));
        productEntity.setProductRating(0);
        
        errors = validator.validate(productEntity);
        
        for(ConstraintViolation error:errors)
        {
            System.err.println("******* Error: " + error.getPropertyPath() + "; " + error.getInvalidValue() + "; " + error.getMessage());
        }
    }
}