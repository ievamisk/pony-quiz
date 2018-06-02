package mylittlepony.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Required;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pony {
    String _id;
    @NotNull
    String name;
    @NotNull
    String description;
    @NotNull
    String image;


    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public void set_id(String _id) {
        if(_id !=null && !_id.isEmpty())
        {
            this._id = _id;
        }
    }

    public void setName(String name) {
        if(name!=null && !name.isEmpty())
        {
            this.name = name;
        }
    }

    public void setDescription(String description) {
        if(description!=null && !description.isEmpty()){
            this.description = description;
        }
    }

    public void setImage(String image) {
        if(image!=null && !image.isEmpty()){
            this.image = image;
        }
    }

    public Document intoDocument(String sequence){
        Document document = new Document();
        set_id(sequence);
        document.append("_id",sequence)
                .append("name",name)
                .append("description", description)
                .append("image",image);
        return document;
    }

    public Document intoDocumentUpdate(){
        Document document = new Document();
        document.append("_id",_id)
                .append("name",name)
                .append("description", description)
                .append("image",image);
        return document;
    }


}
