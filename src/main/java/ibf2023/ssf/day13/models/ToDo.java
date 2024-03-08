package ibf2023.ssf.day13.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ToDo {
    
    @NotEmpty (message = "Please enter a task")        
    private String task;    
   
    @Max (value = 4, message = "Value must < 5")
    private int priority;  // if int (primitive) then NotEmpty if Integer (object) then is NotNull
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Must set a due date")
    @FutureOrPresent(message = "Due date must be in the future")
    private LocalDate dueDate;

    public String getTask() {
        return task;
    }
    public void setTask(String task) {
        this.task = task;
    }

    public int getPriority() {
        return priority;
    }
    public void setPriority(int priority) {
        this.priority = priority;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "ToDo [task=" + task + ", priority=" + priority + ", dueDate=" + dueDate + "]";
    } 
       
    
}
