package com.solomka.test;

import com.solomka.exceptions.InvalidDataException;
import com.solomka.models.Category;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.CategoryValidator;


public class CategoryTest {

    @Test
    public void newCategoryShouldHaveGreaterThanZeroMaxCapacity(){
        Category category = new Category(25);
        Assert.assertEquals(25, category.getMaxCapacity());
    }

    @Test(expected = InvalidDataException.class)
    public void nullTitleShouldThrowInvalidDataException(){
        Category category = new Category();
        category.setTitle(null);
    }

    @Test(expected = InvalidDataException.class)
    public void emptyTitleShouldThrowInvalidDataException(){
        Category category = new Category();
        category.setTitle("");
    }

    @Test(expected = InvalidDataException.class)
    public void negativePriceShouldThrowInvalidDataException(){
        Category category = new Category();
        category.setPrice(-1.1);
    }

    @Test
    public void newCategoryShouldHaveGreaterThanZeroPrice(){
        Category category = new Category();
        category.setPrice(10.2);
        Assert.assertEquals(10.2, category.getPrice(), 1e-9);
    }

    @Test(expected = InvalidDataException.class)
    public void negativeQuantityShouldThrowInvalidDataException(){
        Category category = new Category();
        category.setQuantity(-10);
    }

    @Test(expected = InvalidDataException.class)
    public void setQuantityShouldThrowInvalidDataException(){
        Category category = new Category(25);
        category.setQuantity(26);
    }

    @Test
    public void newCategoryShouldHaveGreaterThanZeroQuantity(){
        Category category = new Category(25);
        category.setQuantity(25);
        Assert.assertEquals(25, category.getQuantity());
    }

    @Test(expected = InvalidDataException.class)
    public void increaseQuantityShouldThrowInvalidDataException(){
        Category category = new Category(25);
        category.increaseQuantity(26);
    }

    @Test(expected = InvalidDataException.class)
    public void decreaseQuantityShouldThrowInvalidDataException(){
        Category category = new Category(25);
        category.setQuantity(25);
        category.decreaseQuantity(30);    }


}
