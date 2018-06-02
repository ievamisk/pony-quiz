package mylittlepony.domain;

import org.bson.Document;

import javax.validation.constraints.NotNull;

public class Question {
    private String _id;
    @NotNull
    private String question;
    @NotNull
    private String image;


    public String getQuestion() {
        return question;
    }

    public String getImage() {
        return image;
    }

    public String get_id() {
        return _id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Document intoDocument (String sequence){

        set_id(sequence);
        Document document = new Document();
        document.append("_id",sequence)
                .append("question",question)
                .append("image",image);
        return document;
    }
    public Document intoDocumentUpdate (){
        Document document = new Document();
        document.append("_id",_id)
                .append("question",question)
                .append("image",image);
        return document;
    }
}
