package thedevelopers.project.demo.util;

import java.util.List;

public interface ServiceTemplate<T> {

    public List<T> getAll();

    public T createElement(T element);

    public T getElement(String id);

    public void deleteElement(T element);

    public T updateElement(T element, T newElement);
}
