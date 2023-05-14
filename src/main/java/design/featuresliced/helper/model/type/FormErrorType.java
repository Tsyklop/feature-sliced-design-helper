package design.featuresliced.helper.model.type;

public enum FormErrorType {

    NAME_INCORRECT("Please enter a name"),
    ALREADY_EXISTS("Already exists"),
    TEXT_FIELD_EMPTY("Please enter a value"),
    SEGMENT_NOT_SELECTED("Select at least one segment"),
    TEMPLATE_NOT_SELECTED("Select template");

    private final String message;

    FormErrorType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
