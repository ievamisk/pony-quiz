package mylittlepony.domain;

import javax.validation.constraints.NotNull;

public class Counter {
    @NotNull
    private String id;
    @NotNull
    private String sequence;

    public String getId() {
        return id;
    }

    public String getSequence() {
        return sequence;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

}
