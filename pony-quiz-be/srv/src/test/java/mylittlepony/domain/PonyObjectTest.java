package mylittlepony.domain;

import org.junit.Assert;
import org.junit.Test;

public class PonyObjectTest {

    @Test
    public void shouldCreateAndReturnId()
    {
        //Given
        String id = "1";
        //When
        Pony pony = new Pony();
        pony.set_id(id);
        //Then
        Assert.assertTrue(pony.get_id().equals(id));
    }

    @Test
    public void shouldCreateAndReturnName()
    {
        //Given
        String name= "name";
        //When
        Pony pony = new Pony();
        pony.setName(name);
        //Then
        Assert.assertTrue(pony.getName().equals(name));
    }

    @Test
    public void shouldCreateAndReturnDescription()
    {
        //Given
        String description= "description";
        //When
        Pony pony = new Pony();
        pony.setDescription(description);
        //Then
        Assert.assertTrue(pony.getDescription().equals(description));
    }

    @Test
    public void shouldCreateAndReturnImage()
    {
        //Given
        String image= "image";
        //When
        Pony pony = new Pony();
        pony.setImage(image);
        //Then
        Assert.assertTrue(pony.getImage().equals(image));
    }

    @Test(expected = NullPointerException.class)
    public void shouldCreateAndReturnIdWithEmptyString()
    {
        //Given
        String id = "";
        //When
        Pony pony = new Pony();
        pony.set_id(id);
        //Then
        throw new NullPointerException();
    }

    @Test(expected = NullPointerException.class)
    public void shouldCreateAndReturnNameWithEmptyString()
    {
        //Given
        String name = "";
        //When
        Pony pony = new Pony();
        pony.setName(name);
        //Then
        throw new NullPointerException();
    }

    @Test(expected = NullPointerException.class)
    public void shouldCreateAndReturnDescriptionWithEmptyString()
    {
        //Given
        String descripion = "";
        //When
        Pony pony = new Pony();
        pony.setDescription(descripion);
        //Then
        throw new NullPointerException();
    }

    @Test(expected = NullPointerException.class)
    public void shouldCreateAndReturnImageWithEmptyString()
    {
        //Given
        String image= "";
        //When
        Pony pony = new Pony();
        pony.setImage(image);
        //Then
        throw new NullPointerException();
    }

    @Test(expected=NullPointerException.class)
    public void exceptionCreatingAndReturningIdWithNull()
    {
        //Given
        String id = null;
        //When
        Pony pony = new Pony();
        pony.set_id(id);
        //Then
        Assert.assertTrue(pony.get_id().equals(id));
    }


    @Test(expected=NullPointerException.class)
    public void exceptionCreatingAndReturningNameWithNull()
    {
        //Given
        String name = null;
        //When
        Pony pony = new Pony();
        pony.setName(name);
        //Then
        Assert.assertTrue(pony.getName().equals(name));
    }

    @Test(expected=NullPointerException.class)
    public void exceptionCreatingAndReturningDescriptionWithNull()
    {
        //Given
        String description = null;
        //When
        Pony pony = new Pony();
        pony.setDescription(description);
        //Then
        Assert.assertTrue(pony.getDescription().equals(description));
    }

    @Test(expected=NullPointerException.class)
    public void exceptionCreatingAndReturningImageWithNull()
    {
        //Given
        String image = null;
        //When
        Pony pony = new Pony();
        pony.setImage(image);
        //Then
        Assert.assertTrue(pony.getImage().equals(image));
    }

}