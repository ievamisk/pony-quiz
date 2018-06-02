package mylittlepony.domain;


import org.junit.Assert;
import org.junit.Test;

public class QuestionObjectTest {

    @Test
    public void shouldCreateAndReturnId()
    {
        //Given
        String id = "1";
        //When
        Question question = new Question();
        question.set_id(id);
        //Then
        Assert.assertTrue(question.get_id().equals(id));
    }

    @Test
    public void shouldCreateAndReturnQuestion()
    {
        //Given
        String questionStr = "question";
        //When
        Question question = new Question();
        question.setQuestion(questionStr);
        //Then
        Assert.assertTrue(question.getQuestion().equals(questionStr));
    }

    @Test
    public void shouldCreateAndReturnImage()
    {
        //Given
        String image = "image";
        //When
        Question question = new Question();
        question.setImage(image);
        //Then
        Assert.assertTrue(question.getImage().equals(image));
    }

    @Test
    public void shouldCreateAndReturnQuestionWithEmptyString()
    {
        //Given
        String questionStr = "";
        //When
        Question question = new Question();
        question.setQuestion(questionStr);
        //Then
        Assert.assertTrue(question.getQuestion().equals(questionStr));
    }

    @Test
    public void shouldCreateAndReturnIdWithEmptyString()
    {
        //Given
        String id = "";
        //When
        Question question = new Question();
        question.set_id(id);
        //Then
        Assert.assertTrue(question.get_id().equals(id));
    }

    @Test
    public void shouldCreateAndReturnImageWithEmptyString()
    {
        //Given
        String image = "";
        //When
        Question question = new Question();
        question.setImage(image);
        //Then
        Assert.assertTrue(question.getImage().equals(image));
    }

    @Test(expected=NullPointerException.class)
    public void exceptionCreatingAndReturningIdWithNull()
    {
        //Given
        String id = null;
        //When
        Question question = new Question();
        question.set_id(id);
        //Then
        Assert.assertTrue(question.get_id().equals(id));
    }

    @Test(expected=NullPointerException.class)
    public void exceptionCreatingAndReturningQuestionWithNull()
    {
        //Given
        String questionStr = null;
        //When
        Question question = new Question();
        question.setQuestion(questionStr);
        //Then
        Assert.assertTrue(question.getQuestion().equals(question));
    }

    @Test(expected=NullPointerException.class)
    public void exceptionCreatingAndReturningImageWithNull()
    {
        //Given
        String image = null;
        //When
        Question question = new Question();
        question.setImage(image);
        //Then
        Assert.assertTrue(question.getImage().equals(image));
    }

}