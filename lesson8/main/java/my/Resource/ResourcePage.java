package my.Resource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResourcePage {

    Integer total_pages;

    Resources[] data;

    public Resources[] getData() {
        return data;
    }

    public Integer getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(Integer total_pages) {
        this.total_pages = total_pages;
    }

    public void setData(Resources[] data) {
        this.data = data;
    }

    public boolean isTotalPageMoreOrEquals(int n) {
        int pages = getTotal_pages();
        if (pages >= n) {
            return true;
        }
        return false;
    }

    public int countOfItems(String name) {
        List<Resources> list = Arrays.asList(data);
        return list.stream().filter(resource -> resource.getName().equals(name))
                .collect(Collectors.toList())
                .size();
    }
}



