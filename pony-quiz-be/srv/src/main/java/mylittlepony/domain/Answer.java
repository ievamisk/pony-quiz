package mylittlepony.domain;


import org.bson.Document;

import javax.validation.constraints.NotNull;

public class  Answer {


    private String _id;
    @NotNull
    private String questionId;
    @NotNull
    private String ponyId;
    @NotNull
    private String answer;


    public String get_id() {
        return _id;
    }

    public String getQuestionId() {
        return questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public String getPonyId() {
        return ponyId;
    }

    public void set_id(String _id) {
        if(_id !=null && !_id.isEmpty()){
            this._id = _id;
        }
    }

    public void setQuestionId(String questionId) {
        if(questionId !=null && !questionId.isEmpty()){
            this.questionId = questionId;
        }
    }

    public void setPonyId(String ponyId) {
        if(ponyId!= null && !ponyId.isEmpty()){
            this.ponyId = ponyId;
        }
    }

    public void setAnswer(String answer) {
        if(answer!=null && !answer.isEmpty())
        {
            this.answer = answer;
        }
    }



    public Document intoDocument(String sequence){
        Document document = new Document()
                .append("_id",sequence)
                .append("ponyId", ponyId)
                .append("questionId",questionId)
                .append("answer", answer);
        return document;
    }

    public Document intoDocumentUpdate() {
        Document document = new Document()
                .append("_id",_id)
                .append("ponyId", ponyId)
                .append("questionId",questionId)
                .append("answer", answer);
        return document;
    }
}
