package com.example.springdemo.controller;
import com.example.springdemo.model.Person;
import com.example.springdemo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
public class personController {
    private final PersonService personService;

    @Autowired
    public personController(PersonService personService) {
        this.personService = personService;
    }
    // return a string from a test
    @GetMapping("/test1")
    @ResponseBody
    public String printHello() {
        // return this text
        return "Hello there!";
    }

    @GetMapping("/test2")
    public String showHelloPage(Model model) {
        model.addAttribute("message", "Have a great day");
        model.addAttribute("rating", 99);
        model.addAttribute("report", "Another great report");
        // return the helloPage.html in templates
        return "helloPage";
    }

    @GetMapping("/test3")
    public ModelAndView printHelloAgain() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", "Having a great day");
        mv.addObject("rating", 90);
        mv.setViewName("helloPage");
        return mv;
        // combines model and view
    }

    @GetMapping("/test4")
    public String showHelloPage4(@RequestParam("message") String message, Model model) {
        // will inject input from url
        // http://localhost:8080/test4?message=HeyPepper
        model.addAttribute("message", message);
        return "helloPage";
    }

    @GetMapping("/people")
    public String showFriends(Model model) {

        List<Person> people = personService.getAllPeople();
//        friends.add(new Person(0L,"Allan", 23, 142f));
//        friends.add(new Person(1L,"Bella", 29, 122f));
//        friends.add(new Person(2L,"Charles", 35, 150f));
//        friends.add(new Person(3L,"Doris", 40, 115f));
//        System.out.println(friends);

        model.addAttribute("people", people);
        // return the helloPage.html in templates
        return "printFriends";
    }

//    @GetMapping("/peopleJSON")
//    @ResponseBody
//    // Not a template; a response
//    // would need to have a separate FE Angular or React
//    public List<Person> showFriendsJSON(Model model) {
//
//        List<Person> friends = new ArrayList<Person>();
//        friends.add(new Person(0L,"Allan", 23, 142f));
//        friends.add(new Person(1L,"Bella", 29, 122f));
//        friends.add(new Person(2L,"Charles", 35, 150f));
//        friends.add(new Person(3L,"Doris", 40, 115f));
//        System.out.println(friends);
//
//        model.addAttribute("people", friends);
//        // return the helloPage.html in templates
//        return friends;
//    }

    @GetMapping("/addPerson")
    public String showAddPersonForm(Model model) {
        model.addAttribute("person", new Person());
        // returns addPerson.html form
        return "addPerson";
    }

    @PostMapping("/addPerson")

    public String addFriend(@ModelAttribute Person person) {
        System.out.println("Received person: " + person);
        Person savedPerson = personService.savePerson(person);
        // saves the person after adding
        return "redirect:/people";
    }


    @GetMapping("/editPerson/{id}")
    public String editFriend(@PathVariable Long id, Model model) {
        // Get the person by id and edit findPersonById(Long id)
        Person personToEdit = personService.findPersonById(id);
        if (personToEdit != null) {
            model.addAttribute("person", personToEdit);
            return "editPerson";
        } else {
            return "personNotFound";
        }
    }

    @PostMapping("updatePerson")
    public String updatePerson(@ModelAttribute Person person) {
        personService.savePerson(person);
        // saves person updates and redirects to people page
        return "redirect:/people";
    }

    @GetMapping("/deletePerson/{id}")
    public String deleteFriend(@PathVariable Long id, Model model) {
        // deletePersonById
     personService.deletePersonById(id);
     return "redirect:/people";
    }
}
