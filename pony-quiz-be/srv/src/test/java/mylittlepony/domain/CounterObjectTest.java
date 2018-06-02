package mylittlepony.domain;

import org.junit.Assert;
import org.junit.Test;

public class CounterObjectTest {

    @Test
    public void shouldCreateAndReturnId()
    {
        //Given
        String id = "1";
        //When
        Counter counter = new Counter();
        counter.setId(id);
        //Then
        Assert.assertTrue(counter.getId().equals(id));
    }

    @Test
    public void shouldCreateAndReturnSequence()
    {
        //Given
        String sequence = "test";
        //When
        Counter counter = new Counter();
        counter.setSequence(sequence);
        //Then
        Assert.assertTrue(counter.getSequence().equals(sequence));
    }

    @Test
    public void shouldCreateAndReturnIdWithEmptyString()
    {
        //Given
        String id = "";
        //When
        Counter counter = new Counter();
        counter.setId(id);
        //Then
        Assert.assertTrue(counter.getId().equals(id));
    }

    @Test
    public void shouldCreateAndReturnSequenceWithEmptyString()
    {
        //Given
        String sequence = "";
        //When
        Counter counter = new Counter();
        counter.setSequence(sequence);
        //Then
        Assert.assertTrue(counter.getSequence().equals(sequence));
    }

    @Test(expected=NullPointerException.class)
    public void exceptionCreatingAndReturningSequenceWithNull()
    {
        //Given
        String sequence = null;
        //When
        Counter counter = new Counter();
        counter.setSequence(sequence);
        //Then
        Assert.assertTrue(counter.getSequence().equals(sequence));
    }

    @Test(expected=NullPointerException.class)
    public void exceptionCreatingAndReturningIdWithNull()
    {
        //Given
        String id = null;
        //When
        Counter counter = new Counter();
        counter.setId(id);
        //Then
        Assert.assertTrue(counter.getSequence().equals(id));
    }
}