package mylittlepony.domain;

import org.junit.Assert;
import org.junit.Test;

public class AnswerObjectTest {

    @Test
    public void shouldCreateAndReturnAnswer()
    {
        //Given
        String answerStr = "answer";
        //When
        Answer answer = new Answer();
        answer.setAnswer(answerStr);
        //Then
        Assert.assertTrue(answer.getAnswer().equals(answerStr));
    }


    @Test
    public void shouldCreateAndReturnId()
    {
        //Given
        String id = "1";
        //When
        Answer answer = new Answer();
        answer.set_id(id);
        //Then
        Assert.assertTrue(answer.get_id().equals(id));
    }

    @Test
    public void shouldCreateAndReturnQuestionId()
    {
        //Given
        String id = "1";
        //When
        Answer answer = new Answer();
        answer.setQuestionId(id);
        //Then
        Assert.assertTrue(answer.getQuestionId().equals(id));
    }

    @Test
    public void shouldCreateAndReturnPonyId()
    {
        //Given
        String id = "1";
        //When
        Answer answer = new Answer();
        answer.setPonyId(id);
        //Then
        Assert.assertTrue(answer.getPonyId().equals(id));
    }


    @Test(expected = NullPointerException.class)
    public void shouldCreateAndReturnAnswerWithEmptyString()
    {
        //Given
        String answerStr = "";
        //When
        Answer answer = new Answer();
        answer.setAnswer(answerStr);
        //Then
        throw new NullPointerException();
    }


   @Test(expected = NullPointerException.class)
    public void shouldCreateAndReturnIdWithEmptyString()
    {
        //Given
        String id = "";
        //When
        Answer answer = new Answer();
        answer.set_id(id);
        //Then
        throw new NullPointerException();
    }

    @Test(expected = NullPointerException.class)
    public void shouldCreateAndReturnQuestionIdWithEmptyString()
    {
        //Given
        String id = "";
        //When
        Answer answer = new Answer();
        answer.setQuestionId(id);
        //Then
        throw new NullPointerException();
    }

    @Test(expected = NullPointerException.class)
    public void shouldCreateAndReturnPonyIdWithEmptyString()
    {
        //Given
        String id = "";
        //When
        Answer answer = new Answer();
        answer.setPonyId(id);
        //Then
        throw new NullPointerException();
    }

    @Test(expected=NullPointerException.class)
    public void exceptionCreatingAndReturningIdWithNull()
    {
        //Given
        String id = null;
        //When
        Answer answer = new Answer();
        answer.set_id(id);
        //Then
        Assert.assertTrue(answer.get_id().equals(id));
    }

    @Test(expected=NullPointerException.class)
    public void exceptionCreatingAndReturningQuestionIdWithNull()
    {
        //Given
        String id = null;
        //When
        Answer answer = new Answer();
        answer.setQuestionId(id);
        //Then
        Assert.assertTrue(answer.getQuestionId().equals(id));
    }

    @Test(expected=NullPointerException.class)
    public void exceptionCreatingAndReturningPonyIdWithNull()
    {
        //Given
        String id = null;
        //When
        Answer answer = new Answer();
        answer.setPonyId(id);
        //Then
        Assert.assertTrue(answer.getPonyId().equals(id));
    }

    @Test(expected=NullPointerException.class)
    public void exceptionCreatingAndReturningAnswerIdWithNull()
    {
        //Given
        String id = null;
        //When
        Answer answer = new Answer();
        answer.setAnswer(id);
        //Then
        Assert.assertTrue(answer.getAnswer().equals(id));
    }
}