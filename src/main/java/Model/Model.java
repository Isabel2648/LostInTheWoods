package Model;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class Model implements Draw{

    private Forest forest;
    private List<Person> persons;

    public Model (){
        forest = new Forest(2,2);
        Person person1 = new Person();
        Person person2 = new Person();
        persons = new ArrayList<>();
        persons.add(person1);
        persons.add(person2);
    }

    public boolean setup(int width, int height){
        if (forest.setWidth(width) && forest.setHeight(height)) {
            return true;
        } else {
            return false;
        }
    }

    public void update(GraphicsContext gc){
//        persons.forEach(person -> person.move());
        this.draw(gc);
    }

    @Override
    public void draw(GraphicsContext gc) {
        forest.draw(gc);
        //persons.forEach(person -> person.draw());
    }
}
