package net.drakupo.todoservlet;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import jakarta.persistence.*;

@Entity
@Table(name = "task")

public class ToDoTask
{

    public static Logger logger = LogManager.getLogger("debug");

    @Id @Column(name = "taskId") private Integer id;
    @Column(name = "taskDesc") private String description;
    @Column(name = "taskDue") private LocalDate dueDate;
    @Column(name = "taskStart") private LocalDate startDate;
    @Column(name = "taskDone") private boolean done;


    public ToDoTask () {}

    public ToDoTask (int id, String desc, LocalDate due, LocalDate start) {
        this.id = id;
        this.description = desc;
        this.dueDate = due;
        this.startDate = start;
        this.done = false;
        logger.info("Object created, dates specified.");
    }

    //If no start date provided, assume today as the start
    public ToDoTask (int id, String desc, LocalDate due) {
        this(id, desc, due, LocalDate.now());
    }

    //If no due date provided, assume due in a week
    public ToDoTask (int id, String desc){
        LocalDate setDue = LocalDate.now();
        setDue = setDue.plusDays(7);
        this.id = id;
        this.description = desc;
        this.dueDate = setDue;
        this.startDate = LocalDate.now();
        this.done = false;
        logger.info("Object created with default dates.");
    }

    //Getter methods and completion toggle
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String desc) { this.description = desc; }
    public LocalDate getDueDate() {
        return dueDate;
    }
    public void setDueDate(LocalDate due) { this.dueDate = due; }
    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate start) { this.startDate = start; }
    public boolean isDone() {
        return done;
    }
    public void setDone(boolean done) { this.done = done; }
    public void toggleDone() {
        done = !done;
    }

    public String toString() {
        return id + ": " + description + " Due: " + dueDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) +
                " Started: " + startDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) + " " +
                (done ? "Done" : "Not Done");
    }

}
