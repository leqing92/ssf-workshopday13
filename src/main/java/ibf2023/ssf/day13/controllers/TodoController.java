package ibf2023.ssf.day13.controllers;

import java.util.List;
import java.util.LinkedList;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ibf2023.ssf.day13.models.ToDo;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping
public class TodoController {    
    
    @GetMapping(path = {"/", "/index.html"})
    public ModelAndView getIndex(HttpSession sess){
        ModelAndView mav = new ModelAndView("todo"); //refer to todo.html
        List<ToDo> toDoList = getList(sess);

        mav.addObject("ToDo", new ToDo()); //<<<<<<<  if toDo then can work
        mav.addObject("toDoList", toDoList);

        return mav;
    }

    @PostMapping (path = "/todo")
    public ModelAndView postToDo (HttpSession sess,
                                @RequestBody @ModelAttribute @Valid ToDo todo1,
                                BindingResult bindings){
        
        ModelAndView mav = new ModelAndView("todo"); //refer to /templates/todo.html
        List <ToDo> toDoList =  getList(sess);
        mav.addObject("toDoList", toDoList);

        if(bindings.hasErrors()){
            mav.addObject("ToDo", todo1); //<<<<<<< if toDo then can work
            System.out.println(bindings.getFieldError());
            //cannot redirect here as it activate the getIndex(GetMapping) and get a new Todo that overwrite the original wrong form
            //mav.setViewName("redirect:/");
        }
        else{
            toDoList.add(todo1);
            //mav.addObject("toDoUrl", new Todo());
            //POST-redirect-GET
            //with this then the form is not resubmit again when user refresh(F5) browser after success submit 1 to do item            
            mav.setViewName("redirect:/"); //it activate getIndex(GetMapping) again so no need mav.addObject("toDoUrl", new Todo());
            
        }

        System.out.println("todoList: " + toDoList);

        return mav;
    }

    private List <ToDo> getList(HttpSession sess){
        List <ToDo> toDoList = (List <ToDo>) sess.getAttribute("toDoList");
    
        //check if to-do-list exists, if doesnt exist, then this is a new session
        //initialise session
        if (null == toDoList){
            toDoList = new LinkedList<>();            
            sess.setAttribute("toDoList", toDoList);
        }

        return toDoList;
    }
}
