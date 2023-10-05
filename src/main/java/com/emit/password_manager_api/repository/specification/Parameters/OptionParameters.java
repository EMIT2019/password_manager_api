package com.emit.password_manager_api.repository.specification.Parameters;

public enum OptionParameters {
	EQUALS_TO,
    GREATER_THAN,
    NEGATION,
    LIKE,
    LOWER_THAN,
    STARTS_WITH,
    ENDS_WITH,
    CONTAINS;

    public static final String OR_PREDICATE_FLAG = "'";
    public static final String ZERO_OR_MORE_REGEX = "*";
    public static final String OR_OPERATOR = "OR";
    public static final String AND_OPERATOR = "AND";
    public static final String LEFT_PARENTHESIS = "(";
    public static final String RIGHT_PARENTHESIS = ")";

    public static final String[] SIMPLE_OPERATION_SET = { ":", "!", ">", "<", "~" };
    public String getValue(){
        switch (this){
            case EQUALS_TO:
                return ":";
            case GREATER_THAN:
                return ">";
            case LOWER_THAN:
                return "<";
            case NEGATION:
                return "!";
            case LIKE:
                return "~";
            default:
                throw new RuntimeException("The required field does not exists in "+this.getClass()+"");

        }
    }
    public static OptionParameters getValueByInput(char input){
        switch (input){
            case ':':
                return EQUALS_TO;
            case '!':
                return NEGATION;
            case '>':
                return GREATER_THAN;
            case '<':
                return LOWER_THAN;
            case '~':
                return LIKE;
            default:
                throw new RuntimeException("The required field does not exists in OperationParameter");

        }
    }
}
