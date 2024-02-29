package com.example.apiRest.controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import  com.example.apiRest.controller.ParametroClass;

@RestController
@RequestMapping("/api")
class ExercisesController {

    @GetMapping("/helloworld")
    String getGreeting(){  "hello world!"    }


    @GetMapping("/fizzbuzz")
    @ResponseBody
    Map method_fizzbuzz(@RequestParam("num") int num){
        String result= fizzbuzz(num)
        ["ENTRADA":num, "EJERCICIO":"FizzBuzz", "SECUENCIA":result]
    }


    @PostMapping("/endPointPost")
    @ResponseBody
    Map endPointPost(@RequestBody ParametroClass request){
        ["suma": request.num+2, "text": request.ejercicio ]
        String ejercicio =  request.ejercicio
        int num =  request.num
        String result

        if(ejercicio == "collatz") result= collatz( num)
        else if(ejercicio == "fizzbuzz") result= fizzbuzz( num)
        else{"Lo siento no tengo ese ejercicio  " }

        ["ENTRADA": num, "EJERCICIO": ejercicio, "SECUENCIA": result]

    }


    @GetMapping("/endPointGet")
    @ResponseBody
    def endPointGet(@RequestParam("ejercicios") String ejercicios, @RequestParam("num") int num){
        if(ejercicios == "collatz") collatz(num)
        else if(ejercicios == "fizzbuzz") fizzbuzz(num)
        else{"Lo siento no tengo ese ejercicio  " }
    }


    def collatz(int num){
        println("entro")
        def result = []
        while (num != 1){
            // si n es par, se divide entre 2
            if (num % 2 == 0) {
                num = num/2
                result.add(num)
            }else{
                //si n es impar se multiplica por 3 y se suma 1
                num = (num*3) + 1
                result.add(num)
            }
        }
        result
    }

    def fizzbuzz(int num){
        def result = []
        (1..num).each(){i ->
            if (i % 3 == 0 && i % 5 == 0) {
                result.add("FizzBuzz")
            } else if (i % 3 == 0) {
                result.add("Fizz")
            } else if (i % 5 == 0) {
                result.add("Buzz")
            } else {
                result.add(i)
            }
        }
        result
    }


}
