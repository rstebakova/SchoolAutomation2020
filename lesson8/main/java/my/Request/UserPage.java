package my.Request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserPage {

    public User[] getData() {
        return data;
    }

    Integer total_pages;

    public void setData(User[] data) {
        this.data = data;
    }

    User[] data;

}
