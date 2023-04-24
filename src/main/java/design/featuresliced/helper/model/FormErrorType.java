package design.featuresliced.helper.model;

public enum FormErrorType {

    NAME_INCORRECT("Please enter a name"),
    ALREADY_EXISTS("Already exists"),
    SEGMENT_NOT_SELECTED("Select at least one segment");

    private final String message;

    FormErrorType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
