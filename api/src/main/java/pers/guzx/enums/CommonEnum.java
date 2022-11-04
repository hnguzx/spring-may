package pers.guzx.enums;

public interface CommonEnum {

    String getValueObject();

    String getDescription();

    default String getDetailMessage(){
        return "[" + this.getValueObject() + "] " + this.getDescription();
    }
}
