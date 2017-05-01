package carshop.entities;

import org.springframework.data.domain.Page;

/**
 * Created by nik on 5/1/17.
 */
public class CarPage {

    private Page<Car> page;
    private int current;
    private int begin;
    private int end;

    public Page<Car> getPage() {
        return page;
    }

    public void setPage(Page<Car> page) {
        this.page = page;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
