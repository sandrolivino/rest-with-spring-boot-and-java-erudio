package br.com.erudio.restspringboot;

import br.com.erudio.restspringboot.exceptions.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {
    @RequestMapping(value="/sum/{numberOne}/{numberTwo}", method=RequestMethod.GET)
    public Double sum(@PathVariable("numberOne") String numberOne,
                      @PathVariable("numberTwo") String numberTwo) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }
        return covertToDouble(numberOne) + covertToDouble(numberTwo);
    }

    @RequestMapping(value="/subtraction/{numberOne}/{numberTwo}", method=RequestMethod.GET)
    public Double subtraction(@PathVariable("numberOne") String numberOne,
                      @PathVariable("numberTwo") String numberTwo) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }
        return covertToDouble(numberOne) - covertToDouble(numberTwo);
    }

    @RequestMapping(value="/division/{numberOne}/{numberTwo}", method=RequestMethod.GET)
    public Double division(@PathVariable("numberOne") String numberOne,
                              @PathVariable("numberTwo") String numberTwo) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }
        return covertToDouble(numberOne) / covertToDouble(numberTwo);
    }

    @RequestMapping(value="/multiplication/{numberOne}/{numberTwo}", method=RequestMethod.GET)
    public Double multiplication(@PathVariable("numberOne") String numberOne,
                              @PathVariable("numberTwo") String numberTwo) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }
        return covertToDouble(numberOne) * covertToDouble(numberTwo);
    }
    public static Double covertToDouble(String strNumber) {
        if (strNumber == null) return 0d;
        String number = strNumber.replaceAll(",", ".");
        if (isNumeric(number)) {return Double.parseDouble(number);}
    else return 0d;

    }

    public static boolean isNumeric(String strNumber) {
        if (strNumber == null) return false;
        String number = strNumber.replaceAll(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}