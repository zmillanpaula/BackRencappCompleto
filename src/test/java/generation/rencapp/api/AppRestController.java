package generation.rencapp.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//ANOTACION @restcontroller le indica a Spring que esta clase es un controlador
@RestController
//ANOTACION @RequestMapping permite indicar las rutas hacia las que hay que realizar a peticion
@RequestMapping("/api")


public class AppRestController {
    @RequestMapping(path = "/saludo", method = RequestMethod.GET)
    public String saludar(){
        return "Hola, bienvenido a Rencappp!!";
    }

}
