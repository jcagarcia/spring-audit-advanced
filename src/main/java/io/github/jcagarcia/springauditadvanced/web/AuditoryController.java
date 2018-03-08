package io.github.jcagarcia.springauditadvanced.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * = AuditoryController
 * <p>
 * Controller that serves a page in the "/auditory" url where the admin could check the
 * auditory registries.
 *
 * @author Juan Carlos García
 */
@Controller
public class AuditoryController {


    /**
     * Method that serves the auditory page
     * @return
     */
    @GetMapping("/auditory")
    public ModelAndView list() {
        return new ModelAndView("list");
    }

}
