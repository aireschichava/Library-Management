package bci.works;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class Creator implements Serializable {
    private static final long serialVersionUID = 202507171003L;

    private String name;
    private List<Work> works;

    public Creator(String name) {
        this.name = name;
        this.works = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Work> getWorks() {
        return works;
    }

    public void addWork(Work work) {
        if (!works.contains(work)) {
            works.add(work);
        }
    }

    public void removeWork(Work work) {
        works.remove(work);
    }

    public boolean hasWorks() {
        return !works.isEmpty();
    }
}
