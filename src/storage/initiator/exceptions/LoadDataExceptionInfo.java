package storage.initiator.exceptions;

/**
 * Created by eliza on 14.04.19.
 */
public enum LoadDataExceptionInfo {

    PARSE_COUNTRY_CITY_ERROR(30, "Error while parse file with country/city data.");

    private int code;
    private String description;

    LoadDataExceptionInfo(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getDescriptionAsFormatStr(Object... args) {
        return String.format(description, args);
    }
}
